package com.Cartelera.cartelera.Controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.web.server.header.XFrameOptionsServerHttpHeadersWriter.Mode;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.RequestBody;
import com.Cartelera.cartelera.Model.Eventos;
import com.Cartelera.cartelera.Service.EventoService;
//import com.fasterxml.jackson.annotation.JsonCreator.Mode;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequestMapping("/eventos")
public class EventosController {

    @Autowired
    EventoService eventoService;

    @GetMapping("/listar")
    public String listarEventos(Model model) {
        List<Eventos> listaEventos = eventoService.listarEventos();
        model.addAttribute("eventos", listaEventos);
        return "eventos/eventoslist";
    }


    @GetMapping("/crear")
    public String crearEventos(Model model) {
        Eventos evento = new Eventos();
        model.addAttribute("evento", evento);
        return "eventos/eventoscreate";
    }

    @PostMapping("/guardar")
    public String guardarEvento(@ModelAttribute Eventos evento, @RequestParam("imagenFile") MultipartFile imagenFile)
            throws IOException {

        if (!imagenFile.isEmpty()) {
            evento.setImagen(imagenFile.getBytes());
            evento.setImagenTipo(imagenFile.getContentType());
        }

        eventoService.guardarEventos(evento);

        return "redirect:/eventos/listar";
    }

    @GetMapping("/imagen/{id}")
    @ResponseBody
    public ResponseEntity<byte[]> obtenerImagen(@PathVariable Long id) {
        Eventos eventos = eventoService.buscarporid(id);
        MediaType mediaType = MediaType.parseMediaType(eventos.getImagenTipo());
        return ResponseEntity.ok()
                .contentType(mediaType)
                .body(eventos.getImagen());
    }

    // Se usa el pathvariable cuando el parametro va dentro de la url
    @GetMapping("/editar/{id}")
    public String editarEventos(@PathVariable Long id, Model model) {
        try {
            Eventos eventos = eventoService.buscarporid(id);
            model.addAttribute("evento", eventos);
            model.addAttribute("titulo", "Editar Evento");
            return "eventos/eventosedit";
        } catch (Exception e) {
            model.addAttribute("mensaje", "Error al cargar el evento");
            model.addAttribute("tipoMensaje", "danger");
            return "redirect:/usuarios/listar";
        }

    }

    @PostMapping("/actualizar")
    public String actualizarEvento(@ModelAttribute Eventos eventos,
            @RequestParam("imagenFile") MultipartFile imagenFile, RedirectAttributes redirectAttributes)
            throws IOException {

        Eventos eventosExistente = eventoService.buscarporid(eventos.getId());
        try {
            if (imagenFile.isEmpty()) {
                eventos.setImagen(eventosExistente.getImagen());
                eventos.setImagenTipo(eventosExistente.getImagenTipo());
            } else {
                eventos.setImagen(imagenFile.getBytes());
                eventos.setImagenTipo(imagenFile.getContentType());
            }
            eventoService.editarEventos(eventos.getId(),eventos);
            redirectAttributes.addFlashAttribute("mensaje", "Evento editado correctamente");
            redirectAttributes.addFlashAttribute("tipoMensaje", "succes");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("mensaje", "Error al editar el evento");
            redirectAttributes.addFlashAttribute("tipoMensaje", "danger");
        }

        return "redirect:/eventos/listar";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarEventos(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            eventoService.Eliminarevento(id);
            redirectAttributes.addFlashAttribute("mensaje", "Evento eliminado correctamente");
            redirectAttributes.addFlashAttribute("tipoMensaje", "succes");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("mensaje", "Error al eliminar el evento");
            redirectAttributes.addFlashAttribute("tipoMensaje", "danger");
        }
        return "redirect:/eventos/listar";
    }

}
