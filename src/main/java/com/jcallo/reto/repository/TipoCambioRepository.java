package com.jcallo.reto.repository;

import com.jcallo.reto.entity.TipoCambio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TipoCambioRepository extends JpaRepository<TipoCambio, Long> {
    //List<TipoCambio> findByOrigDest(String monedaOrigen, String monedaDestino);
    
    @Query("SELECT t FROM TipoCambio t where t.monedaOrigen = :origen AND t.monedaDestino = :destino")
    public Optional<TipoCambio> findByOrigDest(@Param("origen") String monedaOrigen, 
                                                    @Param("destino") String monedaDestino);
}
