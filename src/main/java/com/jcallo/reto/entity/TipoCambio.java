package com.jcallo.reto.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;
@Entity
@Table(name = "tipo_cambio")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TipoCambio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "moneda_origen")
    private String monedaOrigen;
    
    @Column(name = "moneda_destino")
    private String monedaDestino;
    
    @Column(name = "factor")
    private Double factor;
}