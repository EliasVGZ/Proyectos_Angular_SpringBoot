import { Component, inject, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { ActivatedRoute, Router, RouterModule } from '@angular/router';
import { Citas } from '../model/citas.interface';
import { CitasService } from '../services/citas.service';
import { forkJoin } from 'rxjs/internal/observable/forkJoin';
import { Peluqueros } from '../model/citas.peluqueros';
import { Servicios } from '../model/citas.servicios';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-citas-form',
  standalone: true,
  imports: [RouterModule, ReactiveFormsModule, CommonModule],
  templateUrl: './citas-form.component.html',
  styleUrl: './citas-form.component.css'
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
      const { id, nombre, telefono, fecha, hora, peluquero, servicio } = this.form?.value;

      const selectedPeluquero = this.getPeluqueroIdByName(peluquero);
      const selectedServicio = this.getServicioIdByName(servicio);

      // Construye el objeto cita con la estructura deseada
      const cita = {
        fecha,
        hora,
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

  getPeluqueroIdByName(nombre: string): number | undefined {
    const peluquero = this.peluqueros.find(p => p.nombre === nombre);
    return peluquero?.id;
  }
  
  getServicioIdByName(nombre: string): number | undefined {
    const servicio = this.servicios.find(s => s.nombre === nombre);
    return servicio?.id;
  }
  


}
