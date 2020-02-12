package com.jcallo.reto.webdto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class TipoCambioWebResponse {
	private double montoOrigen;
    private String monedaOrigen;
    private String monedaDestino;
    private double montoTipoCambio;
    private double factorTipoCambio;
}
