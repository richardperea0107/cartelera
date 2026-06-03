package com.Cartelera.cartelera.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.Cartelera.cartelera.Model.Usuarios;
import com.Cartelera.cartelera.Repository.UsuarioRepository;

@Service
@Transactional
public class UsuarioService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<Usuarios> mostrarUsuarios() {
        return usuarioRepository.findAll();
    }

    public Usuarios buscarUsuarios(Long id) {
        return usuarioRepository.findById(id).orElseThrow(() -> new RuntimeException(
                "El usuario con el " + id + "no existe"));
    }

    private void validarSoloLetras(String valor, String campo) {
        if (!valor.matches("^[A-Za-záéíóúÁÉÍÓÚüÜñÑ\\s]+$")) {
            throw new RuntimeException(
                    "El campo " + campo + " solo puede contener letras");
        }
    }

    private void validarTelefono(String telefono) {
        if (!telefono.matches("^[0-9]{1,10}$")) {
            throw new RuntimeException(
                    "El teléfono debe contener hasta 10 dígitos");
        }
    }

    public Usuarios guardarUsuarios(Usuarios usuarios) {
        Usuarios usuario = new Usuarios();

        usuario.setNombre(usuarios.getNombre());
        usuario.setApellidos(usuarios.getApellidos());
        usuario.setUser(usuarios.getUser());
        String contraseñaEncriptada = passwordEncoder.encode(usuarios.getContrasena());
        usuario.setContrasena(contraseñaEncriptada);
        usuario.setTelefono(usuarios.getTelefono());
        usuario.setRol(usuarios.getRol());
        validarSoloLetras(usuarios.getNombre(), "nombre");
        validarSoloLetras(usuarios.getApellidos(), "apellidos");
        validarTelefono(String.valueOf(usuarios.getTelefono()));
        return usuarioRepository.save(usuario);
    }

    public Usuarios editarUsuarios(Long id, Usuarios usuario) {
        if (!usuarioRepository.existsById(id)) {
            throw new RuntimeException("No existe un usuario que contenga ese id");
        }
        Usuarios usuariosExistente = buscarUsuarios(id);

        if (usuariosExistente.getNombre().isBlank()) {
            throw new RuntimeException("El nombre del usuario no puede estar vacio");
        }

        if (usuariosExistente.getApellidos().isBlank()) {
            throw new RuntimeException("El apellido del usuario no puede estar vacio");
        }

        if (usuariosExistente.getUser().isBlank()) {
            throw new RuntimeException("El usuario del usuario no puede estar vacio");
        }
        validarSoloLetras(usuario.getNombre(), "nombre");
        validarSoloLetras(usuario.getApellidos(), "apellidos");
        validarTelefono(String.valueOf(usuario.getTelefono()));
        usuariosExistente.setNombre(usuario.getNombre());
        usuariosExistente.setApellidos(usuario.getApellidos());
        usuariosExistente.setUser(usuario.getUser());
        usuariosExistente.setTelefono(usuario.getTelefono());
        return usuarioRepository.save(usuariosExistente);

    }

    public void EliminarUsuario(Long id) {
        if (!usuarioRepository.existsById(id)) {
            throw new RuntimeException("No existe ningun evento con ese id");
        }
        usuarioRepository.deleteById(id);
    }

}
