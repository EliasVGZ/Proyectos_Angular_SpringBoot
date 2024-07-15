package com.eliasvgz.Citas_Peluqueria.controller;

import com.eliasvgz.Citas_Peluqueria.models.Servicio;
import com.eliasvgz.Citas_Peluqueria.servicio.ServicioService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/servicios")
@CrossOrigin
@AllArgsConstructor
public class ServicioController {


    private ServicioService servicioService;


    //Todo Listar servicios
    @GetMapping
    public Iterable<Servicio> listarServicios(){
        return servicioService.listarServicios();
    }
}
