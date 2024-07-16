package com.eliasvgz.Citas_Peluqueria.servicio;


import com.eliasvgz.Citas_Peluqueria.models.Cita;
import com.eliasvgz.Citas_Peluqueria.repository.CitaRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor

public class CitaServicio {

    private final CitaRepository citaRepository;
    private PeluqueroService peluqueroService;


    //TODO Crear cita
    public Cita crearCita(Cita cita){


        return citaRepository.save(cita);
    }

    //TODO Listar citas
    public Iterable<Cita> listarCitas(){
        return citaRepository.findAll();
    }

    //TODO Buscar cita
    public Cita buscarCita(Long id){
        return citaRepository.findById(id).orElse(null);
    }

    //TODO Borrar cita
    public void borrarCita(Long id){
        citaRepository.deleteById(id);
    }

    //TODO Listar citas por fecha
    public List<Cita> obtenerReservasPorFecha(LocalDate fecha) {
        return citaRepository.findByFecha(fecha);
    }



}
