package com.jcallo.reto.service;

import com.jcallo.reto.entity.TipoCambio;
import com.jcallo.reto.repository.TipoCambioRepository;
import com.jcallo.reto.servicedto.response.TipoCambioResponse;
import com.jcallo.reto.servicedto.request.TipoCambioRequest;

import io.reactivex.Single;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TipoCambioServiceImpl implements TipoCambioService {

    @Autowired
    private TipoCambioRepository tipoCambioRepository;

    @Override
    public Single<TipoCambioResponse> getTipoCambio(TipoCambioRequest tcr){
    	return getTipoCambioRepository(tcr);
    }
    
    private Single<TipoCambioResponse> getTipoCambioRepository(TipoCambioRequest tipoCambioRequest) {
        return Single.create(singleSubscriber -> {
        	//obteniendo el origen y destino
            Optional<TipoCambio> optionalTipoCambio = tipoCambioRepository.findByOrigDest(tipoCambioRequest.getMonedaOrigen(), tipoCambioRequest.getMonedaDestino());
            if (!optionalTipoCambio.isPresent()) {
            	
            	System.out.println("no encontrado" + tipoCambioRequest.getMontoOrigen()+", "+tipoCambioRequest.getMonedaOrigen() + ", "+ tipoCambioRequest.getMonedaDestino());
            	
                singleSubscriber.onError(new EntityNotFoundException());
            }else {
            	
            	System.out.println(optionalTipoCambio.get());
            	
            	TipoCambioResponse tipoCambioResponse = toTipoCambioResponse(optionalTipoCambio.get(), tipoCambioRequest.getMontoOrigen());
            	
            	System.out.println("tipoCambioResponse : "+tipoCambioResponse.getMontoOrigen() +", "+tipoCambioResponse.getMonedaOrigen()+", "+tipoCambioResponse.getMonedaDestino()+","+
            			tipoCambioResponse.getMontoTipoCambio()+", "+tipoCambioResponse.getFactorTipoCambio());
            	
                singleSubscriber.onSuccess(tipoCambioResponse);
            	
            }
        });
    }
    
    private TipoCambioResponse toTipoCambioResponse(TipoCambio tipoCambio, double montoOrigen) {
    	
    	
    	
    	TipoCambioResponse tipoCambioResponse = new TipoCambioResponse();
        BeanUtils.copyProperties(tipoCambio, tipoCambioResponse);
        
        tipoCambioResponse.setMontoOrigen(montoOrigen);
        tipoCambioResponse.setMontoTipoCambio(tipoCambio.getFactorTipoCambio() * montoOrigen);
        tipoCambioResponse.setFactorTipoCambio(tipoCambio.getFactorTipoCambio());
        
        
        
        return tipoCambioResponse;
    }
    
    /*****para lista el contenido de la tabla****/
    @Override
    public Single<List<TipoCambioResponse>> getAllTipoCambios(int limit, int page) {
        return findAllTipoCambiosInRepository(limit, page)
                .map(this::toTipoCambioResponseList);
    }

    private Single<List<TipoCambio>> findAllTipoCambiosInRepository(int limit, int page) {
        return Single.create(singleSubscriber -> {
            List<TipoCambio> tipoCambio = tipoCambioRepository.findAll(PageRequest.of(page, limit)).getContent();
            singleSubscriber.onSuccess(tipoCambio);
        });
    }

    private List<TipoCambioResponse> toTipoCambioResponseList(List<TipoCambio> tipoCambioList) {
        return tipoCambioList
                .stream()
                .map(this::toTipoCambioResponse)
                .collect(Collectors.toList());
    }

    private TipoCambioResponse toTipoCambioResponse(TipoCambio tipoCambio) {
    	TipoCambioResponse tipoCambioResponse = new TipoCambioResponse();
        BeanUtils.copyProperties(tipoCambio, tipoCambioResponse);
        //bookResponse.setAuthorName(tipoCambio.getAuthor().getName());
        return tipoCambioResponse;
    }
    
}
