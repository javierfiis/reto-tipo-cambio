package com.jcallo.reto.controller;

import com.jcallo.reto.service.TipoCambioService;
import com.jcallo.reto.servicedto.request.TipoCambioRequest;
import com.jcallo.reto.servicedto.response.TipoCambioResponse;
import com.jcallo.reto.webdto.request.TipoCambioWebRequest;
import com.jcallo.reto.webdto.response.BaseWebResponse;
import com.jcallo.reto.webdto.response.TipoCambioWebResponse;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;
@RestController
@RequestMapping("/api")
public class TipoCambioController {

	@Autowired
	private TipoCambioService tipoCambioService;

    @PostMapping(value = "/tipocambio",
            consumes = MediaType.APPLICATION_JSON_VALUE,
                produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BaseWebResponse<TipoCambioResponse>> calcular(@RequestBody TipoCambioWebRequest tcwr) {
        System.out.println("request [" + tcwr.getMontoOrigen() + "][" + tcwr.getMonedaOrigen() + "][" + tcwr.getMonedaDestino() + "]");

        TipoCambioRequest tcr = toTipoCambioWebRequest(tcwr);
        System.out.println("tcr => [" + tcr.getMontoOrigen() + "][" + tcr.getMonedaOrigen() + "][" + tcr.getMonedaDestino() + "]");

        TipoCambioResponse response = tipoCambioService.getTipoCambio(tcr);
        return ResponseEntity.ok(BaseWebResponse.successWithData(response));
    }

    private TipoCambioRequest toTipoCambioWebRequest(TipoCambioWebRequest tcwr) {
    	TipoCambioRequest tcr = new TipoCambioRequest();
        BeanUtils.copyProperties(tcwr, tcr);
        return tcr;
    }

    /* obtener la lista de tipos de cambio*/
    @GetMapping(value = "/tipocambios",
               produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BaseWebResponse<List<TipoCambioWebResponse>>> getAllTipoCambios(
            @RequestParam(value = "limit", defaultValue = "5") int limit,
            @RequestParam(value = "page", defaultValue = "0") int page) {
        Iterable<TipoCambioResponse> tipoCambioResponses = tipoCambioService.getAllTipoCambios(limit, page);
        List<TipoCambioWebResponse> webResponses = toTipoCambioWebResponseList(tipoCambioResponses);

        return ResponseEntity.ok(BaseWebResponse.successWithData(webResponses));
    }

    private List<TipoCambioWebResponse> toTipoCambioWebResponseList(Iterable<TipoCambioResponse> tipoCambioResponseList) {
        return ((List<TipoCambioResponse>) tipoCambioResponseList)
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

