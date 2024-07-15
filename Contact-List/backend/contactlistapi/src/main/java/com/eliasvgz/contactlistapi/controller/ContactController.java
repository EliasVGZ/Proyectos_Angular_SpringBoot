package com.eliasvgz.contactlistapi.controller;

import com.eliasvgz.contactlistapi.dto.ContactDTO;
import com.eliasvgz.contactlistapi.entity.Contact;
import com.eliasvgz.contactlistapi.servicio.ContactService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin //Permite que se pueda acceder a la API desde cualquier origen
@AllArgsConstructor
@RequestMapping("/api/contacts")
@RestController
public class ContactController {


    private ContactService contactServicio;

    //TODO CREATE
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Contact createContact(@Validated @RequestBody ContactDTO contactDTO){
        return contactServicio.crearContacto(contactDTO);
    }

    //TODO LISTAR CONTACTOS
    @GetMapping
    public List<Contact> list(){
        return (List<Contact>) contactServicio.listarContactos();
    }

    //TODO LISTA UN CONTACTO
    @GetMapping("/{id}")
    public Contact getContact(@PathVariable Integer id){
        return contactServicio.buscarContacto(id);
    }


    //TODO DELETE
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Contact deleteContact(@PathVariable Integer id){
        return contactServicio.borrarContacto(id);
    }

    //TODO UPDATE
    @PutMapping("/{id}")
    public Contact updateContact(@PathVariable Integer id, @Validated @RequestBody ContactDTO contactDTO){

        return contactServicio.actualizarContacto(id, contactDTO);
    }





}
