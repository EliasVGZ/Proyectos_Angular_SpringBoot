package com.eliasvgz.contactlistapi.servicio;

import com.eliasvgz.contactlistapi.dto.ContactDTO;
import com.eliasvgz.contactlistapi.entity.Contact;
import com.eliasvgz.contactlistapi.exception.ResourceNotFoundException;
import com.eliasvgz.contactlistapi.repository.ContactRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@Service
public class ContactService {

    private final ContactRepository contactRepository;
    private final ModelMapper mapper;




    //Todo Crear contacto
    public Contact crearContacto(ContactDTO contactDTO){
        Contact contact = mapper.map(contactDTO, Contact.class);
        contact.setCreatedAt(LocalDateTime.now());
        return contactRepository.save(contact);
    }
    //TODO Listar contactos
    public List<Contact> listarContactos(){
        return (List<Contact>) contactRepository.findAll();
    }

    //Todo Buscar contacto
    public Contact buscarContacto(Integer id){
        return contactRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
    }

    //TODO Borrar contacto
    public Contact borrarContacto(Integer id){
        Contact contacto = buscarContacto(id);
        if(contacto != null){
            contactRepository.delete(contacto);
        }
        return contacto;
    }

    //TODO Actualizar contacto
    public Contact actualizarContacto(Integer id, ContactDTO contactDTO){
        Contact contactoBBDD = buscarContacto(id);
        mapper.map(contactDTO, contactoBBDD);
        return contactRepository.save(contactoBBDD);
    }

    //TODO Buscar contacto por email
    public Contact buscarContactoPorEmail(String email){
        return contactRepository.findByEmail(email).orElse(null);
    }





}
