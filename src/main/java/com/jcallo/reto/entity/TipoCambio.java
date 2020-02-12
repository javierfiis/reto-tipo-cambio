package com.jcallo.reto.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="TIPOCAMBIO")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TipoCambio {
    @Id
    @Column(name = "ID")
    private int id;

    @Column(name = "MONEDA_ORIGENN")
    private String monedaOrigen;
    
    @Column(name = "MONEDA_DESTINO")
    private String monedaDestino;
    
    @Column(name = "FACTOR_TIPO_CAMBIO")
    private Double factorTipoCambio;
}