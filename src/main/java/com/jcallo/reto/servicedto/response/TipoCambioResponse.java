package com.jcallo.reto.servicedto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TipoCambioResponse {
    private Double montoOrigen;
    private Double montoDestino;
    private Double factor;
}

