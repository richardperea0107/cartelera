package com.Cartelera.cartelera.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.Cartelera.cartelera.Model.Eventos;
import com.Cartelera.cartelera.Service.EventoService;
//import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class InicioController {
    @Autowired
    private EventoService eventoService;

    @GetMapping("/")
    public String Inicio(Model model){
        List<Eventos> eventos = eventoService.listarEventos();
        model.addAttribute("eventos", eventos);
        return "inicio";
    }

}
