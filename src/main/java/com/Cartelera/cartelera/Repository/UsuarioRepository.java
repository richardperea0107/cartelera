package com.Cartelera.cartelera.Repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Cartelera.cartelera.Model.Usuarios;



public interface UsuarioRepository extends JpaRepository<Usuarios,Long>{
    Optional<Usuarios>findByUser(String user);
}
