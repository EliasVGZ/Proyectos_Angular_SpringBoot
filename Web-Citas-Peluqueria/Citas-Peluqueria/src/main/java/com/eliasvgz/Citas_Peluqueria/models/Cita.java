package com.eliasvgz.Citas_Peluqueria.models;

import jakarta.persistence.*;
import org.springframework.lang.NonNull;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
@Table(name = "citas")
public class Cita {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    @Column(name = "nombre", length = 30)
    private String nombre;

    @NonNull @Column(name = "telefono", length = 9)
    private String telefono;

    @ManyToOne
    private Peluquero peluquero;

    @ManyToOne
    private Servicio servicio;
    private LocalDate fecha;
    private String horaInicio;

    private String horaFin;

    public Cita() {
    }

    public Cita(Long id, @NonNull String nombre, @NonNull String telefono, Peluquero peluquero, Servicio servicio, LocalDate fecha, String horaInicio, String horaFinal) {
        this.id = id;
        this.nombre = nombre;
        this.telefono = telefono;
        this.peluquero = peluquero;
        this.servicio = servicio;
        this.fecha = fecha;
        this.horaInicio = horaInicio;
        this.horaFin = horaFinal;
    }

    public LocalTime getHoraInicioAsLocalTime() {
        return LocalTime.parse(this.horaInicio);
    }

    public LocalTime getHoraFinalAsLocalTime() {
        return LocalTime.parse(this.horaFin);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public Peluquero getPeluquero() {
        return peluquero;
    }

    public void setPeluquero(Peluquero peluquero) {
        this.peluquero = peluquero;
    }

    public Servicio getServicio() {
        return servicio;
    }

    public void setServicio(Servicio servicio) {
        this.servicio = servicio;
    }

    public String getHoraFin() {
        return horaFin;
    }

    public void setHoraFin(String horaFin) {
        this.horaFin = horaFin;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public String getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(String horaInicio) {
        this.horaInicio = horaInicio;
    }

    public String getHoraFinal() {
        return horaFin;
    }

    public void setHoraFinal(String horaFinal) {
        this.horaFin = horaFinal;
    }
}
