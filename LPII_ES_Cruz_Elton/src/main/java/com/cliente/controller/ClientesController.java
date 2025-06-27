package com.cliente.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.cliente.dtos.ClienteFilter;
import com.cliente.dtos.ResultadoResponse;
import com.cliente.models.Cliente;
import com.cliente.services.ClienteService;
import com.cliente.services.TipoService;
import com.cliente.utils.Alert;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/clientes")
public class ClientesController {

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private TipoService tipoClienteService;

    @GetMapping("/filtrado")
    public String filtrado(@ModelAttribute ClienteFilter filtro, Model model) {
        List<Cliente> lstCliente = clienteService.search(filtro);

        model.addAttribute("tipos", tipoClienteService.getAll());
        model.addAttribute("filtro", filtro);
        model.addAttribute("lstCliente", lstCliente);

        return "clientes/filtrado";
    }

    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        model.addAttribute("tipos", tipoClienteService.getAll());
        model.addAttribute("cliente", new Cliente());
        return "clientes/nuevo";
    }

    @PostMapping("/registrar")
    public String registrar(@Valid @ModelAttribute Cliente cliente, BindingResult bindingResult, Model model,
                            RedirectAttributes flash) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("tipos", tipoClienteService.getAll());
            model.addAttribute("alert", Alert.sweetAlertInfo("Falta completar información"));
            return "clientes/nuevo";
        }

        ResultadoResponse response = clienteService.create(cliente);

        if (!response.success) {
            model.addAttribute("tipos", tipoClienteService.getAll());
            model.addAttribute("alert", Alert.sweetAlertError(response.mensaje));
            return "clientes/nuevo";
        }

        String toast = Alert.sweetToast(response.mensaje, "success", 5000);
        flash.addFlashAttribute("toast", toast);
        return "redirect:/clientes/filtrado";
    }

    @GetMapping("/edicion/{id}")
    public String edicion(@PathVariable("id") String id, Model model) {
        Cliente cliente = clienteService.getOne(id);
        model.addAttribute("tipos", tipoClienteService.getAll());
        model.addAttribute("cliente", cliente);
        return "clientes/edicion";
    }

    @PostMapping("/guardar")
    public String guardar(@Valid @ModelAttribute Cliente cliente, BindingResult bindingResult, Model model,
                          RedirectAttributes flash) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("tipos", tipoClienteService.getAll());
            model.addAttribute("alert", Alert.sweetAlertInfo("Falta completar información"));
            return "clientes/edicion";
        }

        ResultadoResponse response = clienteService.update(cliente);

        if (!response.success) {
            model.addAttribute("tipos", tipoClienteService.getAll());
            model.addAttribute("alert", Alert.sweetAlertError(response.mensaje));
            return "clientes/edicion";
        }

        String toast = Alert.sweetToast(response.mensaje, "success", 5000);
        flash.addFlashAttribute("toast", toast);
        return "redirect:/clientes/filtrado";
    }
}

