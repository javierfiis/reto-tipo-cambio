package com.jcallo.reto.service;

import java.util.List;

import com.jcallo.reto.servicedto.request.TipoCambioRequest;
import com.jcallo.reto.servicedto.response.TipoCambioResponse;

import io.reactivex.Single;

public interface TipoCambioService {
	//para obtener un registro en particular
	Single<TipoCambioResponse> getTipoCambio(TipoCambioRequest tcr);
	/*para listar los tipos de  cambio registrados*/
	Single<List<TipoCambioResponse>> getAllTipoCambios(int limit, int page);
}
