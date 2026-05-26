package com.BuildMyPC.msvc_quotation_service.Services;

import com.BuildMyPC.msvc_quotation_service.Exceptions.QuotationException;
import com.BuildMyPC.msvc_quotation_service.Models.Dtos.QuotationDTO;
import com.BuildMyPC.msvc_quotation_service.Models.Quotation;
import com.BuildMyPC.msvc_quotation_service.Repositories.QuotationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class QuotationService {

    private static final Logger log = LoggerFactory.getLogger(QuotationService.class);
    private final QuotationRepository repository;

    public QuotationService(QuotationRepository repository) {
        this.repository = repository;
    }


    public Quotation crearQuotation(QuotationDTO dto) {
        log.info("Iniciando creación de Quotation con id de usuario: {}", dto.getUsuarioId());
        Quotation quotation = new Quotation();
        quotation.setBuildId(dto.getBuildId());
        quotation.setUsuarioId(dto.getUsuarioId());
        quotation.setSubtotal(dto.getSubtotal());
        quotation.setDescuento(dto.getDescuento());
        quotation.setTotal(dto.getTotal());
        quotation.setEstado(dto.getEstado());
        quotation.setFechaEmision(dto.getFechaEmision());
        quotation.setFechaVencimiento(dto.getFechaVencimiento());
        quotation.setEstado("ACTIVO");

        Quotation guardada = repository.save(quotation);
        log.info("Quotation guardada exitosamente con ID: {}", guardada.getId());
        return guardada;
    }

    public List<Quotation> listarTodas() {
        return repository.findAll();
    }

    public Quotation buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new QuotationException("Quotation no encontrada con ID: " + id));
    }


    public Quotation desactivarQuotation(Long id) {
        log.info("Intentando desactivar Quotation con ID: {}", id);
        Quotation quotation = buscarPorId(id);
        quotation.setEstado("INACTIVO");
        Quotation actualizada = repository.save(quotation);
        log.info("Quotation desactivada exitosamente");
        return actualizada;
    }

}
