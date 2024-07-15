package com.eliasvgz.contactlistapi.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "contactos")
@Getter
@Setter
@RequiredArgsConstructor //Genera un constructor con los atributos marcados como @NonNull
@NoArgsConstructor //Genera un constructor sin argumentos

public class Contact {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NonNull @Column(name = "nombre", length = 30)
    private String name;
    @NonNull @Column(name = "email", length = 30)
    private String email;
    @NonNull @Column(name = "created_at")
    private LocalDateTime createdAt;


}

