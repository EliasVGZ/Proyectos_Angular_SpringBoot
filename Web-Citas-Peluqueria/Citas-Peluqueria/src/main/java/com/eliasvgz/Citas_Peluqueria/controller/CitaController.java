package com.eliasvgz.Citas_Peluqueria.controller;

import com.eliasvgz.Citas_Peluqueria.models.Cita;
import com.eliasvgz.Citas_Peluqueria.models.Peluquero;
import com.eliasvgz.Citas_Peluqueria.models.Servicio;
import com.eliasvgz.Citas_Peluqueria.servicio.CitaServicio;
import com.eliasvgz.Citas_Peluqueria.servicio.PeluqueroService;
import com.eliasvgz.Citas_Peluqueria.servicio.ServicioService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/citas")
@CrossOrigin(origins = "http://localhost:4200") //Permite que se pueda acceder a la API desde cualquier origen
@AllArgsConstructor
public class CitaController {

    @Autowired
    private final CitaServicio citaServicio;

    // Crear cita
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Transactional
    public ResponseEntity<Cita> crearCita(@RequestBody Cita cita) {
        Cita citaGuardada = citaServicio.crearCita(cita);
        return new ResponseEntity<>(citaGuardada, HttpStatus.CREATED);
    }


    // Listar citas
    @GetMapping
    public Iterable<Cita> listarCitas() {
        return citaServicio.listarCitas();
    }

    // Buscar cita
    @GetMapping("/{id}")
    public ResponseEntity<Cita> buscarCita(@PathVariable Long id) {
        Cita cita = citaServicio.buscarCita(id);
        if (cita == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(cita, HttpStatus.OK);
    }

    // Borrar cita
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void borrarCita(@PathVariable Long id) {
        citaServicio.borrarCita(id);
    }


    // Buscar por fecha
    //Todo buscar por fecha
    @GetMapping("/horarios-disponibles")
    public ResponseEntity<List<String>> obtenerHorariosDisponibles(@RequestParam("fecha") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fecha) {
        List<Cita> reservas = citaServicio.obtenerReservasPorFecha(fecha);
        List<String> horariosDisponibles = generarHorariosDisponibles(reservas);
        return ResponseEntity.ok(horariosDisponibles);
    }

    private List<String> generarHorariosDisponibles(List<Cita> reservas) {
        List<String> horariosDisponibles = new ArrayList<>();
        LocalTime inicio = LocalTime.of(8, 0); // 8:00 AM
        LocalTime fin = LocalTime.of(18, 0);   // 6:00 PM
        int intervaloMinutos = 30;

        while (inicio.plusMinutes(intervaloMinutos).isBefore(fin.plusMinutes(intervaloMinutos))) {// Mientras la hora de inicio + 30 minutos sea antes de las 6:00 PM
            LocalTime horaInicio = inicio;
            LocalTime horaFin = inicio.plusMinutes(intervaloMinutos);

            boolean ocupado = reservas.stream()// Verifica si la hora está ocupada
                    .anyMatch(r -> {// Si alguna reserva cumple con la condición, entonces la hora está ocupada
                        try {
                            LocalTime reservaInicio = r.getHoraInicioAsLocalTime();
                            LocalTime reservaFin = r.getHoraFinalAsLocalTime();
                            return reservaInicio.isBefore(horaFin) && reservaFin.isAfter(horaInicio);
                        } catch (Exception e) {
                            return false; // Maneja cualquier excepción y considera el horario como no ocupado
                        }
                    });

            if (!ocupado) {// Si la hora no está ocupada, se agrega a la lista de horarios disponibles
                horariosDisponibles.add(String.format("%02d:%02d-%02d:%02d",// Formato de la hora: 08:00-08:30
                        horaInicio.getHour(), horaInicio.getMinute(),
                        horaFin.getHour(), horaFin.getMinute()));
            }

            inicio = inicio.plusMinutes(intervaloMinutos);
        }

        return horariosDisponibles;
    }













}