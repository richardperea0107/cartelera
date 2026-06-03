package com.Cartelera.cartelera.Repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.Cartelera.cartelera.Model.Eventos;

@Repository
public interface EventosRepository extends JpaRepository<Eventos, Long> {
    
    //Cuando se usa el @query es necesario usa el @param para pasar los parametros
    @Query("SELECT e FROM Eventos e WHERE e.dia_semana = :dia")
    public List<Eventos> buscarEventosporDia(@Param("dia") String dia);
}
