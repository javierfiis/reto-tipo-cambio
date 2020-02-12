package com.jcallo.reto.controller;

import com.jcallo.reto.service.TipoCambioService;

import com.jcallo.reto.servicedto.request.TipoCambioRequest;
import com.jcallo.reto.servicedto.response.TipoCambioResponse;
import com.jcallo.reto.webdto.request.TipoCambioWebRequest;
import com.jcallo.reto.webdto.response.BaseWebResponse;
import com.jcallo.reto.webdto.response.TipoCambioWebResponse;

import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController

public class TipoCambioController {
	@Autowired
	private TipoCambioService tipoCambioService;

	
	@PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )	
	@RequestMapping(value = "/api/tipocambio")
    public Single<ResponseEntity<BaseWebResponse>> calcular(@RequestBody TipoCambioWebRequest tcwr) {
		
		System.out.println("request [" + tcwr.getMontoOrigen() + "][" + tcwr.getMonedaOrigen() + "]["+tcwr.getMonedaDestino()+"]");
		
        return tipoCambioService.getTipoCambio(toTipoCambioWebRequest(tcwr))
                .subscribeOn(Schedulers.io())
                .map(s -> ResponseEntity
                        .created(URI.create("/api/tipocambio/" + s))
                        .body(BaseWebResponse.successNoData()));
    }

    private TipoCambioRequest toTipoCambioWebRequest(TipoCambioWebRequest tcwr) {
    	TipoCambioRequest tcr = new TipoCambioRequest();
        BeanUtils.copyProperties(tcwr, tcr);
        
        System.out.println("tcr => [" + tcr.getMontoOrigen() + "][" + tcr.getMonedaOrigen() + "]["+tcr.getMonedaDestino()+"]");
        
        return tcr;
    }
    
    
    
    
    /* obtener la lista de tipos de cambio*/
    @GetMapping(
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @RequestMapping(value = "/api/tipocambios")
    public Single<ResponseEntity<BaseWebResponse<List<TipoCambioWebResponse>>>> getAllTipoCambios(@RequestParam(value = "limit", defaultValue = "5") int limit,
                                                                                      @RequestParam(value = "page", defaultValue = "0") int page) {
        return tipoCambioService.getAllTipoCambios(limit, page)
                .subscribeOn(Schedulers.io())
                .map(tipoCambioResponses -> ResponseEntity.ok(BaseWebResponse.successWithData(toTipoCambioWebResponseList(tipoCambioResponses))));
    }

    private List<TipoCambioWebResponse> toTipoCambioWebResponseList(List<TipoCambioResponse> tipoCambioResponseList) {
        return tipoCambioResponseList
                .stream()
                .map(this::toBookWebResponse)
                .collect(Collectors.toList());
    }

    private TipoCambioWebResponse toBookWebResponse(TipoCambioResponse tipoCambioResponse) {
    	TipoCambioWebResponse tipoCambioWebResponse = new TipoCambioWebResponse();
        BeanUtils.copyProperties(tipoCambioResponse, tipoCambioWebResponse);
        return tipoCambioWebResponse;
    }
    
	
}
