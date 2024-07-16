import { Component, inject, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { ActivatedRoute, Router, RouterModule } from '@angular/router';
import { Citas } from '../model/citas.interface';
import { CitasService } from '../services/citas.service';
import { forkJoin } from 'rxjs/internal/observable/forkJoin';
import { Peluqueros } from '../model/citas.peluqueros';
import { Servicios } from '../model/citas.servicios';
import { CommonModule } from '@angular/common';
import { Observer } from 'rxjs';
import { BsDatepickerConfig, BsDatepickerModule } from 'ngx-bootstrap/datepicker';

@Component({
  selector: 'app-citas-form',
  standalone: true,
  imports: [RouterModule, ReactiveFormsModule, CommonModule, BsDatepickerModule],
  providers: [
    {
      provide: BsDatepickerConfig,
      useFactory: () => {
        // Aquí puedes personalizar tu configuración
        return Object.assign(new BsDatepickerConfig(), {
          containerClass: 'theme-dark-blue',
          dateInputFormat: 'DD/MM/YYYY'
        });
      }
    }
  ],
  templateUrl: './citas-form.component.html',
  styleUrls: ['./citas-form.component.css']
})
export default class CitasFormComponent implements OnInit{

  private fb= inject(FormBuilder); //Sirve para inyectar el formulario
  private citasService = inject(CitasService); //Sirve para inyectar el servicio
  private router = inject(Router); //Sirve para inyectar el router
  private route = inject(ActivatedRoute); //Sirve para inyectar la ruta







  form?: FormGroup;
  citas?: Citas;
  peluqueros: Peluqueros[] = [];
  servicios: Servicios[] = [];
  servicioSeleccionado?: Servicios;
  horariosDisponibles: string[] = [];
  fechaSeleccionada: Date = new Date();



  ngOnInit(): void {

    this.cargarDatosIniciales();
    
    this.form = this.fb.group({
    
      nombre: ['', Validators.required],
      telefono: ['', [
        Validators.required,
        Validators.pattern('^[0-9]+$'),
        Validators.minLength(9),
        Validators.maxLength(9)
      ]],
      fecha: ['', Validators.required],
      hora: ['', Validators.required],
      peluquero: [''],
      servicio: ['', Validators.required]
    
    });

    


  }

  cargarDatosIniciales(){
    forkJoin({
      peluqueros: this.citasService.getAllPeluqueros(),//Se obtienen los peluqueros
      servicios: this.citasService.getServicios()
    }).subscribe(({ peluqueros, servicios }) => {

      console.log('Peluqueros:', peluqueros);
      console.log('Servicios:', servicios);

      this.peluqueros = peluqueros; // Se asignan los peluqueros
      this.servicios = servicios;

      
    });
  }

  pedirCita() {

    if(this.form?.invalid){
      this.form.markAllAsTouched();
      return;
    }


    console.log('Formulario enviado', this.form?.value);
    if (this.form?.valid) {
      // Extrae los valores actuales del formulario
      const { nombre, telefono, fecha, peluquero, servicio} = this.form?.value;

      const horarioSeleccionado = this.form.value.hora;
      const [horaInicio, horaFin] = horarioSeleccionado.split('-');


      const selectedPeluquero = this.getPeluqueroIdByName(peluquero);
      const selectedServicio = this.getServicioIdByName(servicio);

      // Construye el objeto cita con la estructura deseada
      const cita = {
        fecha,
        horaInicio,
        horaFin,
        nombre,
        telefono,
        peluquero: {
          id: selectedPeluquero || ''
        },
        servicio: {
          id: selectedServicio || ''
        }
      };
      

      console.log('JSON de cita a enviar:', JSON.stringify(cita));

      this.citasService.create(cita).subscribe((response: Citas) => {

        if(response && response.id){


        console.log('Cita creada, navegando a la página principal');
        this.router.navigate(['/citas', response.id]);
        }else{
          console.log('La respuesta no contiene un ID  de cita válido', response);
        }
      });
    } else {
      console.log('Formulario no válido');
    }
  }

  trackByFn(index: number, item: any): number {
    return item.id; // Ajusta según la propiedad única de tu objeto
  }

  

  onServicioChange(event: any) {
    const servicioNombre = event.target.value;// Se obtiene el valor del servicio seleccionado
    this.servicioSeleccionado = this.servicios.find(servicio => servicio.nombre === servicioNombre);
  }

  // Método para manejar el cambio de fecha
  onDateChange(event: Event): void {
      const fechaSeleccionada = (event.target as HTMLInputElement).value;
      this.fechaSeleccionada = new Date(fechaSeleccionada);
  
      // Llamar al método para cargar los horarios disponibles solo si hay una fecha seleccionada
      if (fechaSeleccionada) {
          this.obtenerHorariosDisponibles(fechaSeleccionada);
      } else {
          this.horariosDisponibles = []; // Limpiar horarios disponibles si no hay fecha seleccionada
      }
  }

  updateHorariosDisponibles(fecha: string) {
    const fechaObj = new Date(fecha); // Convert the fecha string to a Date object
    
    // Generar todos los horarios para el día
    const horarios = this.generarHorarios();
    // Obtener los horarios ocupados para la fecha proporcionada
    const horariosDisponibles = this.obtenerHorariosDisponibles(fecha); // Pass the Date object to the function
    
  }
  
  // Ejemplo de función para obtener horarios reservados (simulado)
  obtenerHorariosDisponibles(fecha: string): void {
    const observer: Observer<string[]> = {
      next: (horarios: string[]) => {
        this.horariosDisponibles = horarios;
      },
      error: (error) => {
        console.error('Error al obtener horarios disponibles:', error);
      },
      complete: () => {
        console.log('Obtención de horarios completada');
      }
    };

    this.citasService.getHorariosDisponibles(fecha).subscribe(observer);
  }

  

   generarHorarios(): string[] {
    const horarios: string[] = [];
    let start = 8 * 60; // 8:00 AM en minutos
    const end = 18 * 60; // 6:00 PM en minutos
  
    while (start < end) {
      const hours = Math.floor(start / 60);
      const minutes = start % 60;
  
      const formattedStart = `${hours.toString().padStart(2, '0')}:${minutes.toString().padStart(2, '0')}`;
      
      start += 30;
      
      const endHours = Math.floor(start / 60);
      const endMinutes = start % 60;
  
      const formattedEnd = `${endHours.toString().padStart(2, '0')}:${endMinutes.toString().padStart(2, '0')}`;
  
      horarios.push(`${formattedStart}-${formattedEnd}`);
    }
  
    return horarios;
  }

  


  getPeluqueroIdByName(nombre: string): number | undefined {
    const peluquero = this.peluqueros.find(p => p.nombre === nombre);
    return peluquero?.id;
  }
  
  getServicioIdByName(nombre: string): number | undefined {
    const servicio = this.servicios.find(s => s.nombre === nombre);
    return servicio?.id;
  }
  


}
