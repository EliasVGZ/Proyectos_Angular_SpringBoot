package com.eliasvgz.Citas_Peluqueria.servicio;


import com.eliasvgz.Citas_Peluqueria.models.Servicio;
import com.eliasvgz.Citas_Peluqueria.repository.ServicioRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ServicioService {

    private final ServicioRepository servicioRepository;

    //Todo Obtener los servicios

    public Iterable<Servicio> listarServicios(){
        return servicioRepository.findAll();
    }

    public void guardarServicio(Servicio servicio) {
        servicioRepository.save(servicio);
    }
}
