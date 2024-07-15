package com.eliasvgz.Citas_Peluqueria.servicio;

import com.eliasvgz.Citas_Peluqueria.models.Peluquero;
import com.eliasvgz.Citas_Peluqueria.repository.PeluqueroRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

@Service
@AllArgsConstructor
public class PeluqueroService {

    private final PeluqueroRepository peluqueroRepository;


    //Obtener todos los peluqueros
    public Iterable<Peluquero> listarPeluqueros(){
        return peluqueroRepository.findAll();
    }


}
