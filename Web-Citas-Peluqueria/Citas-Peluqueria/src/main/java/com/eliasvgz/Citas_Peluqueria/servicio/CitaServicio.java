package com.eliasvgz.Citas_Peluqueria.servicio;


import com.eliasvgz.Citas_Peluqueria.models.Cita;
import com.eliasvgz.Citas_Peluqueria.repository.CitaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

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



}
