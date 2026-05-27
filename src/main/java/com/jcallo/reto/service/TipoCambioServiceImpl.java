package com.jcallo.reto.service;

import com.jcallo.reto.entity.TipoCambio;
import com.jcallo.reto.repository.TipoCambioRepository;
import com.jcallo.reto.servicedto.request.TipoCambioRequest;
import com.jcallo.reto.servicedto.response.TipoCambioResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TipoCambioServiceImpl implements TipoCambioService {

    private final TipoCambioRepository repository;

    @Override
    public TipoCambioResponse getTipoCambio(TipoCambioRequest request) {
        Optional<TipoCambio> tipoCambioOpt = repository.findByMonedaOrigenAndMonedaDestino(
                request.getMonedaOrigen(), request.getMonedaDestino());

        if (tipoCambioOpt.isEmpty()) {
            throw new RuntimeException("Tipo de cambio no encontrado");
        }
    
        TipoCambio tipoCambio = tipoCambioOpt.get();
        double montoDestino = request.getMontoOrigen() * tipoCambio.getFactor();

        return TipoCambioResponse.builder()
                .montoOrigen(request.getMontoOrigen())
                .montoDestino(montoDestino)
                .factor(tipoCambio.getFactor())
                .build();
    }

    @Override
    public Iterable<TipoCambioResponse> getAllTipoCambios(int limit, int page) {
        // This is a placeholder implementation - you'll need to implement proper pagination
        List<TipoCambioResponse> responses = new ArrayList<>();
        // Add your logic here to fetch and convert data
        return responses;
    }
}

