package com.Cartelera.cartelera.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.Cartelera.cartelera.Model.Eventos;
import com.Cartelera.cartelera.Repository.EventosRepository;

@Service
@Transactional
public class EventoService {
    @Autowired
    EventosRepository eventosRepository;

    // Listar eventos
    public List<Eventos> listarEventos() {
        return eventosRepository.findAll();
    }

    // Buscar por id
    public Eventos buscarporid(Long id) {
        return eventosRepository.findById(id).orElseThrow(() -> new RuntimeException(
                "El evento con el " + id + "no existe"));
    }
 
    // Guardar Evento
    public Eventos guardarEventos(Eventos eventos) {
        return eventosRepository.save(eventos);
    }

    // Editar Eventos
    public Eventos editarEventos(Long id, Eventos eventos) {
        if (!eventosRepository.existsById(id)) {
            throw new RuntimeException("No existe un evento que contenga ese id");
        }

        Eventos eventosExistentes = buscarporid(id);

        if (eventos.getTitulo().isBlank()) {
            throw new RuntimeException("No puedes dejar vacio el titulo del evento");
        }

        if (eventos.getLugar().isBlank()) {
            throw new RuntimeException("No puedes dejar el campo de lugar vacio");
        }

        if (eventos.getDescripcion().isBlank()) {
            throw new RuntimeException("No puedes dejar el campo descripción vacio");
        }
        if (eventos.getPrecio() <= 0) {
            throw new RuntimeException("El precio no puede ser ni un numero negativo o cero");
        }

        eventosExistentes.setTitulo(eventos.getTitulo());
        eventosExistentes.setDescripcion(eventos.getDescripcion());
        eventosExistentes.setImagen(eventos.getImagen());
        eventosExistentes.setPrecio(eventos.getPrecio());
        eventosExistentes.setDia_semana(eventos.getDia_semana());
        if (eventos.getHora_inicio() == null) {
            eventosExistentes.setHora_inicio(eventosExistentes.getHora_inicio());
        } else {
            eventosExistentes.setHora_inicio(eventos.getHora_inicio());
        }

        if (eventos.getHora_fin() == null) {
            eventosExistentes.setHora_fin(eventosExistentes.getHora_fin());
        } else {
            eventosExistentes.setHora_fin(eventos.getHora_fin());
        }
        eventosExistentes.setLugar(eventos.getLugar());

        return eventosRepository.save(eventosExistentes);

    }

    public List<Eventos> mostrarporDias(String dia) {
        return eventosRepository.buscarEventosporDia(dia);
    }

    // Eliminar Evento
    public void Eliminarevento(Long id) {
        if (!eventosRepository.existsById(id)) {
            throw new RuntimeException("No existe ningun evento con ese id");
        }

        eventosRepository.deleteById(id);
    }

}
