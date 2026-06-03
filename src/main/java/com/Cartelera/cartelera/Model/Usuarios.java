package com.Cartelera.cartelera.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "usuarios")
public class Usuarios {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "El nombre no puede estar en blanco")
    @Pattern(regexp = "^[A-Za-záéíóúÁÉÍÓÚüÜñÑ\\s]+$", message = "El nombre solo puede contener letras y espacios")
    @Column(name = "nombre", nullable = false)
    private String nombre;
    @NotBlank(message = "El apellido no puede estar en blanco")
    @Pattern(regexp = "^[A-Za-záéíóúÁÉÍÓÚüÜñÑ\\s]+$", message = "El apellido solo puede contener letras y espacios")
    @Column(name = "apellidos", nullable = false)
    private String apellidos;
    @NotBlank(message = "El usuario es obligatorio")
    @Column(name = "usuario", nullable = false)
    private String user;
    @NotBlank(message = "La contraseña es obligatoria")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{8,}$", message = "La contraseña debe contener al menos: 1 número, 1 minúscula, 1 mayúscula, 1 carácter especial y no espacios")
    @Column(name = "contrasena", nullable = false, length = 255)
    private String contrasena;
    @Column(name = "telefono", nullable = true)
    private int telefono;
    @NotBlank(message = "El rol es un campo obligatorio")
    @Column(name = "rol", nullable = false)
    private String rol;

    public Usuarios() {
    }

    public Usuarios(Long id, @NotBlank(message = "El nombre no puede estar en blanco") String nombre,
            @NotBlank(message = "El apellido no puede estar en blanco") String apellidos,
            @NotBlank(message = "El usuario es obligatorio") String user,
            @NotBlank(message = "La contraseña es obligatoria") @Size(min = 8, max = 15, message = "La contraseña debe tener entre 8 y 15 caracteres") @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{8,}$", message = "La contraseña debe contener al menos: 1 número, 1 minúscula, 1 mayúscula, 1 carácter especial y no espacios") String contrasena,
            int telefono, @NotBlank(message = "El rol es un campo obligatorio") String rol) {
        this.id = id;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.user = user;
        this.contrasena = contrasena;
        this.telefono = telefono;
        this.rol = rol;
    }


    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

  
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    
}
