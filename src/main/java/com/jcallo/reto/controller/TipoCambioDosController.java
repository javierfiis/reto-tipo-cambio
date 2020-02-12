package com.jcallo.reto.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jcallo.reto.service.TipoCambioService;


@RestController
public class TipoCambioDosController {
	
	@Autowired
    private TipoCambioService tipoCambioService;
	
	@RequestMapping("/api/tipocambiodos")
	public String tipoCambio(@RequestParam(value="monto", defaultValue="1") double monto,
			@RequestParam("monedaorigen") String monedaorigen,
			@RequestParam("monedadestino") String monedadestino) {
		
		double TIPO_CAMBIO = 3.35;
		
		//buscar el el tipo de cambio equivalente entre moneda origen y destino
		
		
		double montoRetorno = monto * TIPO_CAMBIO; 
		
		
		return "monto: " + monto + ", montotipocambio:" + montoRetorno + ", monedaorigen:"+ monedaorigen + ", monedadestino:"+monedadestino;
		
	}
	
}