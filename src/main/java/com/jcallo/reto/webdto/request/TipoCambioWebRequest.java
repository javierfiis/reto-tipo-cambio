package com.jcallo.reto.webdto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class  TipoCambioWebRequest {
	private double montoOrigen;
	private String monedaOrigen;
	private String monedaDestino;
}


