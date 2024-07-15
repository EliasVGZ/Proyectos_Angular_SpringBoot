package com.eliasvgz.Citas_Peluqueria.repository;


import com.eliasvgz.Citas_Peluqueria.models.Peluquero;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PeluqueroRepository extends CrudRepository<Peluquero, Long> {
}
