package com.eliasvgz.Citas_Peluqueria.controller;

import com.eliasvgz.Citas_Peluqueria.models.Peluquero;
import com.eliasvgz.Citas_Peluqueria.servicio.PeluqueroService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/peluqueros")
@AllArgsConstructor
@CrossOrigin //Permite que se pueda acceder a la API desde cualquier origen


public class PeluqueroController {


    private PeluqueroService peluqueroService;


    //TODO Listar peluqueros
    @GetMapping
    public Iterable<Peluquero> listarPeluqueros(){
        return peluqueroService.listarPeluqueros();
    }
}
