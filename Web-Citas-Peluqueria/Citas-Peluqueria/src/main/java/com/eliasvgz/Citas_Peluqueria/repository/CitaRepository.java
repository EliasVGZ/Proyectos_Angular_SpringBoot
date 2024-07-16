package com.eliasvgz.Citas_Peluqueria.repository;

import com.eliasvgz.Citas_Peluqueria.models.Cita;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface CitaRepository extends CrudRepository<Cita, Long> {

    List<Cita> findByFecha(LocalDate fecha);
}
