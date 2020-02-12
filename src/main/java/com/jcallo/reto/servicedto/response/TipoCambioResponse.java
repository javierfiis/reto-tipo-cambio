package com.jcallo.reto.servicedto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TipoCambioResponse {
	private double montoOrigen;
    private String monedaOrigen;
    private String monedaDestino;
    private double montoTipoCambio;
    private double factorTipoCambio;
}

