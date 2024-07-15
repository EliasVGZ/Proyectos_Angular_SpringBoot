package com.eliasvgz.Citas_Peluqueria.controller;

import com.eliasvgz.Citas_Peluqueria.models.Cita;
import com.eliasvgz.Citas_Peluqueria.models.Peluquero;
import com.eliasvgz.Citas_Peluqueria.models.Servicio;
import com.eliasvgz.Citas_Peluqueria.servicio.CitaServicio;
import com.eliasvgz.Citas_Peluqueria.servicio.PeluqueroService;
import com.eliasvgz.Citas_Peluqueria.servicio.ServicioService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/citas")
@CrossOrigin //Permite que se pueda acceder a la API desde cualquier origen
@AllArgsConstructor
public class CitaController {

    @Autowired
    private final CitaServicio citaServicio;

    // Crear cita
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Transactional
    public ResponseEntity<Cita> crearCita(@RequestBody Cita cita) {
        Cita citaGuardada = citaServicio.crearCita(cita);
        return new ResponseEntity<>(citaGuardada, HttpStatus.CREATED);
    }


    // Listar citas
    @GetMapping
    public Iterable<Cita> listarCitas() {
        return citaServicio.listarCitas();
    }

    // Buscar cita
    @GetMapping("/{id}")
    public ResponseEntity<Cita> buscarCita(@PathVariable Long id) {
        Cita cita = citaServicio.buscarCita(id);
        if (cita == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(cita, HttpStatus.OK);
    }

    // Borrar cita
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void borrarCita(@PathVariable Long id) {
        citaServicio.borrarCita(id);
    }
}