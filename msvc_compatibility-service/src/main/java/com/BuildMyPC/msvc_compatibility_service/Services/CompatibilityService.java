package com.BuildMyPC.msvc_compatibility_service.Services;

import com.BuildMyPC.msvc_compatibility_service.Clients.*;
import com.BuildMyPC.msvc_compatibility_service.Models.Dtos.*;
import com.BuildMyPC.msvc_compatibility_service.Exceptions.CompatibilityException;
import com.BuildMyPC.msvc_compatibility_service.Models.DetalleCompatibility;
import com.BuildMyPC.msvc_compatibility_service.Models.Dtos.CompatibilityRequestDTO;
import com.BuildMyPC.msvc_compatibility_service.Models.ValidacionCompatibility;
import com.BuildMyPC.msvc_compatibility_service.Repositories.CompatibilityRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CompatibilityService {

    private static final Logger log = LoggerFactory.getLogger(CompatibilityService.class);
    private final CompatibilityRepository repository;

    // Inyección de todos los clientes Feign
    private final CpuClient cpuClient;
    private final GpuClient gpuClient;
    private final MotherboardClient motherboardClient;
    private final RamClient ramClient;
    private final PowerSupplyClient powerSupplyClient;

    public CompatibilityService(CompatibilityRepository repository, CpuClient cpuClient,
                                GpuClient gpuClient, MotherboardClient motherboardClient,
                                RamClient ramClient, PowerSupplyClient powerSupplyClient) {
        this.repository = repository;
        this.cpuClient = cpuClient;
        this.gpuClient = gpuClient;
        this.motherboardClient = motherboardClient;
        this.ramClient = ramClient;
        this.powerSupplyClient = powerSupplyClient;
    }

    @Transactional
    public ValidacionCompatibility crearValidacion(CompatibilityRequestDTO dto) {
        log.info("Iniciando validación de compatibilidad real para buildId: {}", dto.getBuildId());

        // Limpiar validaciones previas de esta misma build
        repository.findByBuildId(dto.getBuildId()).ifPresent(repository::delete);

        ValidacionCompatibility validacion = new ValidacionCompatibility();
        validacion.setBuildId(dto.getBuildId());

        try {
            // 1. Obtener datos técnicos reales de los demás microservicios
            CpuDTO cpu = cpuClient.findById(dto.getCpuId());
            GpuDTO gpu = gpuClient.findById(dto.getGpuId());
            MotherboardDTO mobo = motherboardClient.findById(dto.getMotherboardId());
            RamDTO ram = ramClient.findById(dto.getRamId());
            PowerSupplyDTO psu = powerSupplyClient.findById(dto.getFuenteId());

            boolean isCompatible = true;

            // --- REGLA 1: COMPATIBILIDAD DE SOCKET CPU / MOTHERBOARD ---
            DetalleCompatibility reglaSocket = new DetalleCompatibility();
            reglaSocket.setRegla("Compatibilidad de Socket CPU/Motherboard");
            if (cpu.getSocket().equalsIgnoreCase(mobo.getSocket())) {
                reglaSocket.setResultado("CUMPLE");
                reglaSocket.setMensaje("El procesador y la placa madre utilizan el socket: " + cpu.getSocket());
            } else {
                reglaSocket.setResultado("NO_CUMPLE");
                reglaSocket.setMensaje(String.format("Socket incompatible. CPU usa %s pero Placa usa %s", cpu.getSocket(), mobo.getSocket()));
                isCompatible = false;
            }
            validacion.addDetalle(reglaSocket);

            // --- REGLA 2: COMPATIBILIDAD DE MEMORIA RAM ---
            DetalleCompatibility reglaRam = new DetalleCompatibility();
            reglaRam.setRegla("Compatibilidad Tipo de Memoria RAM");
            if (ram.getTipoDdr().equalsIgnoreCase(mobo.getTipoRamSoportada())) {
                reglaRam.setResultado("CUMPLE");
                reglaRam.setMensaje("La placa madre y la memoria utilizan el estándar " + ram.getTipoDdr());
            } else {
                reglaRam.setResultado("NO_CUMPLE");
                reglaRam.setMensaje(String.format("RAM Incompatible. Placa soporta %s pero la RAM es %s", mobo.getTipoRamSoportada(), ram.getTipoDdr()));
                isCompatible = false;
            }
            validacion.addDetalle(reglaRam);

            // --- REGLA 3: CAPACIDAD MÁXIMA DE RAM ---
            DetalleCompatibility reglaMaxRam = new DetalleCompatibility();
            reglaMaxRam.setRegla("Capacidad Máxima de Memoria RAM");
            if (ram.getCapacidadGb() <= mobo.getMaxRamGb()) {
                reglaMaxRam.setResultado("CUMPLE");
                reglaMaxRam.setMensaje("La capacidad de RAM seleccionada está dentro del límite de la placa.");
            } else {
                reglaMaxRam.setResultado("NO_CUMPLE");
                reglaMaxRam.setMensaje("La RAM seleccionada supera el máximo soportado por la placa madre.");
                isCompatible = false;
            }
            validacion.addDetalle(reglaMaxRam);

            // --- REGLA 4: CONSUMO Y FUENTE DE PODER ---
            int consumoEstimado = cpu.getTdpWatts() + gpu.getTdpWatts() + 50; // +50W para placa, ssd, fans
            int margenSeguridad = psu.getPotenciaWatts() - consumoEstimado;

            DetalleCompatibility reglaFuente = new DetalleCompatibility();
            reglaFuente.setRegla("Capacidad de Fuente de Poder (TDP)");

            // Requerimos que la fuente tenga al menos 100W de sobra sobre el consumo estimado
            if (margenSeguridad >= 100) {
                reglaFuente.setResultado("CUMPLE");
                reglaFuente.setMensaje("La fuente soporta el consumo estimado de " + consumoEstimado + "W con margen suficiente.");
            } else {
                reglaFuente.setResultado("NO_CUMPLE");
                reglaFuente.setMensaje("Fuente insuficiente o margen de seguridad muy bajo. Consumo estimado: " + consumoEstimado + "W");
                isCompatible = false;
            }
            validacion.addDetalle(reglaFuente);

            // Setear datos finales del registro general
            validacion.setCompatible(isCompatible);
            validacion.setConsumoEstimadoWatts(consumoEstimado);
            validacion.setMargenFuente(margenSeguridad + "W");
            validacion.setObservaciones(isCompatible ? "Todos los componentes son compatibles." : "Existen conflictos de hardware. Revise los detalles.");

            ValidacionCompatibility guardada = repository.save(validacion);
            log.info("Validación concluida exitosamente. Resultado Final: {}", isCompatible);
            return guardada;

        } catch (Exception e) {
            log.error("Fallo al comunicarse con microservicios de catálogo: {}", e.getMessage());
            throw new CompatibilityException("Error al verificar componentes. Asegúrese de que los servicios de hardware estén encendidos.");
        }
    }

    @Transactional(readOnly = true)
    public List<ValidacionCompatibility> listarTodas() {
        return repository.findAll();
    }

    @Transactional(readOnly = true)
    public ValidacionCompatibility buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new CompatibilityException("Validación no encontrada con ID: " + id));
    }

    @Transactional(readOnly = true)
    public ValidacionCompatibility buscarPorBuildId(Long buildId) {
        return repository.findByBuildId(buildId)
                .orElseThrow(() -> new CompatibilityException("Validación no encontrada para la build ID: " + buildId));
    }

    @Transactional
    public void eliminarValidacion(Long id) {
        ValidacionCompatibility validacion = buscarPorId(id);
        repository.delete(validacion);
        log.info("Validación con ID {} eliminada", id);
    }
}