package com.jcallo.reto.service;

import com.jcallo.reto.servicedto.request.TipoCambioRequest;
import com.jcallo.reto.servicedto.response.TipoCambioResponse;

public interface TipoCambioService {

    TipoCambioResponse getTipoCambio(TipoCambioRequest request);

    Iterable<TipoCambioResponse> getAllTipoCambios(int limit, int page);
}

