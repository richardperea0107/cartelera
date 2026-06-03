package com.Cartelera.cartelera.Service;


import java.util.Collections;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.Cartelera.cartelera.Model.Usuarios;
import com.Cartelera.cartelera.Repository.UsuarioRepository;

@Service
@Transactional
public class UsuarioDetallesService implements UserDetailsService {
    private final UsuarioRepository usuarioRepository;

    public UsuarioDetallesService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuarios usuario = usuarioRepository.findByUser(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado: " + username));
        
        List<GrantedAuthority> authorities = Collections.singletonList(new SimpleGrantedAuthority(
            usuario.getRol()));
                
        return org.springframework.security.core.userdetails.User.builder()
                .username(usuario.getUser())
                .password(usuario.getContrasena())
                .authorities(authorities)
                .build();
    }
}
