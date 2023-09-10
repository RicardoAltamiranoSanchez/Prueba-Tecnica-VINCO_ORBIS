package com.vinco_orbis.app.Controllers;

import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/index")
public class home {

    @GetMapping
    public Map<String, String> obtenerMensajeGet() {
        Map<String, String> respuesta = new HashMap<>();
        respuesta.put("mensaje", "Esto es un GET");
        return respuesta;
    }

    @PostMapping
    public Map<String, String> obtenerMensajePost() {
        Map<String, String> respuesta = new HashMap<>();
        respuesta.put("mensaje", "Esto es un POST");
        return respuesta;
    }

    @PutMapping
    public Map<String, String> actualizarMensajePut() {
        Map<String, String> respuesta = new HashMap<>();
        respuesta.put("mensaje", "Esto es un PUT (UPDATE)");
        return respuesta;
    }

    @DeleteMapping
    public Map<String, String> eliminarMensajeDelete() {
        Map<String, String> respuesta = new HashMap<>();
        respuesta.put("mensaje", "Esto es un DELETE");
        return respuesta;
    }

    @GetMapping("/busqueda")
    public Map<String, String> buscarMensaje() {
        Map<String, String> respuesta = new HashMap<>();
        respuesta.put("mensaje", "Esto es un SEARCH");
        return respuesta;
    }
}
