package com.Cartelera.cartelera.Model;

import java.time.LocalTime;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

@Entity
@Table(name = "eventos")
public class Eventos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "dia_semana", nullable = false)
    @NotBlank(message = "El dia de la semana es obligatorio")
    private String dia_semana;
    @NotBlank(message = "El titulo es obligatorio")
    @Pattern(regexp = "^[a-zA-Z0-9 ]+$", message = "El titulo solo puede contener letras, números y espacios")
    @Column(name = "titulo", nullable = false)
    private String titulo;
    @NotBlank(message = "La descripcion es un campo obligatorio")
    @Pattern(regexp = "^[a-zA-Z0-9 ]+$", message = "La descripcion solo puede contener letras, números y espacios")
    @Column(name = "descripcion", nullable = false)
    private String descripcion;
    @Column(name = "hora_inicio", nullable = false)
    private LocalTime hora_inicio;
    @Column(name = "hora_fin", nullable = false)
    private LocalTime hora_fin;
    @NotBlank(message = "El lugar debe ser especificado")
    @Column(name = "lugar", nullable = false)
    @Pattern(regexp = "^[a-zA-Z0-9 ]+$", message = "El lugar solo puede contener letras, números y espacios")
    private String lugar;
    @Column(name = "precio", nullable = false)
    private double precio;
    @Column(name = "imagen", nullable = false, columnDefinition = "BYTEA")
    private byte[] imagen;
    @Column(name = "imagen_tipo", nullable = false)
    private String imagenTipo;

    public Eventos() {
    }

    public Eventos(Long id, @NotBlank(message = "El dia de la semana es obligatorio") String dia_semana,
            @NotBlank(message = "El titulo es obligatorio") String titulo,
            @NotBlank(message = "La descripcion es un campo obligatorio") String descripcion, LocalTime hora_inicio,
            LocalTime hora_fin, @NotBlank(message = "El lugar debe ser especificado") String lugar, double precio,
            byte[] imagen, String imagenTipo) {
        this.id = id;
        this.dia_semana = dia_semana;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.hora_inicio = hora_inicio;
        this.hora_fin = hora_fin;
        this.lugar = lugar;
        this.precio = precio;
        this.imagen = imagen;
        this.imagenTipo = imagenTipo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDia_semana() {
        return dia_semana;
    }

    public void setDia_semana(String dia_semana) {
        this.dia_semana = dia_semana;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public LocalTime getHora_inicio() {
        return hora_inicio;
    }

    public void setHora_inicio(LocalTime hora_inicio) {
        this.hora_inicio = hora_inicio;
    }

    public LocalTime getHora_fin() {
        return hora_fin;
    }

    public void setHora_fin(LocalTime hora_fin) {
        this.hora_fin = hora_fin;
    }

    public String getLugar() {
        return lugar;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public byte[] getImagen() {
        return imagen;
    }

    public void setImagen(byte[] imagen) {
        this.imagen = imagen;
    }

    public String getImagenTipo() {
        return imagenTipo;
    }

    public void setImagenTipo(String imagenTipo) {
        this.imagenTipo = imagenTipo;
    }

}
