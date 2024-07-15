package com.eliasvgz.contactlistapi.repository;

import com.eliasvgz.contactlistapi.entity.Contact;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ContactRepository extends CrudRepository<Contact, Integer>{

    //TODO Buscar contacto por email
    Optional<Contact> findByEmail(String email);


}
