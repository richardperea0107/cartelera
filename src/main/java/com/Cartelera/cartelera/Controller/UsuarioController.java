package com.Cartelera.cartelera.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.Cartelera.cartelera.Model.Usuarios;
import com.Cartelera.cartelera.Service.UsuarioService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequestMapping("/usuarios")
public class UsuarioController {
    @Autowired
    UsuarioService usuarioService;

    @GetMapping("/listar")
    public String listarUsuarios(Model model) {
        List<Usuarios> listaUsuarios = usuarioService.mostrarUsuarios();
        model.addAttribute("usuarios", listaUsuarios);
        return "usuarios/usuarioslist";
    }

    @GetMapping("/crear")
    public String crearUsuarios(Model model) {
        Usuarios usuario = new Usuarios();
        model.addAttribute("usuario", usuario);

        return "usuarios/usuariocreate";
    }

    @PostMapping("/guardar")
    public String guardarUsuarios(@ModelAttribute Usuarios usuario) {
        usuario.setRol("ROLE_ADMIN");
        usuarioService.guardarUsuarios(usuario);

        return "redirect:/usuarios/listar";

    }

    @GetMapping("editar/{id}")
    public String editarUsuario(@PathVariable Long id, Model model) {
        try {
            Usuarios usuario = usuarioService.buscarUsuarios(id);
            model.addAttribute("usuario", usuario);
            model.addAttribute("titulo", "Editar Usuario");
            return "usuarios/usuarioedit";
        } catch (Exception e) {
            model.addAttribute("mensaje", "Error al cargar el usuario");
            model.addAttribute("tipoMensaje", "danger");
            return "redirect:/usuarios/listar";
        }
    }

    @PostMapping("/actualizar")
    public String actualizarUsuario(@ModelAttribute Usuarios usuario, RedirectAttributes redirectAttributes) {
        try {
            usuarioService.editarUsuarios(usuario.getId(), usuario);
            redirectAttributes.addFlashAttribute("mensaje", "Usuario actualizado correctamente");
            redirectAttributes.addFlashAttribute("tipoMensaje", "success");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("mensaje", "Error al actualizar el usuario");
            redirectAttributes.addFlashAttribute("tipoMensaje", "danger");
        }
        return "redirect:/usuarios/listar";
    }

    @GetMapping("eliminar/{id}")
    public String elimanarUsuario(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            usuarioService.EliminarUsuario(id);
            redirectAttributes.addFlashAttribute("mensaje", "Usuario eliminado correctamente");
            redirectAttributes.addFlashAttribute("tipoMensaje", "succes");

        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("mensaje", "Error al eliminar el usuario");
            redirectAttributes.addFlashAttribute("tipoMensaje", "danger");
        }
        return "redirect:/usuarios/listar";
    }

}
