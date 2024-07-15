package com.eliasvgz.Citas_Peluqueria.repository;

import com.eliasvgz.Citas_Peluqueria.models.Servicio;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServicioRepository extends CrudRepository<Servicio, Long> {
}
