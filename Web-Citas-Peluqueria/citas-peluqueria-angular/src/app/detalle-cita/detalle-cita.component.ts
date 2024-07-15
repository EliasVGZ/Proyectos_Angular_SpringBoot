import { Component, inject, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { CommonModule } from '@angular/common';  // Importa CommonModule
import { CitasService } from '../services/citas.service';
import { Citas } from '../model/citas.interface';

@Component({
  selector: 'app-detalle-cita',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './detalle-cita.component.html',
})
export default class DetalleCitaComponent implements OnInit {

  cita: Citas | undefined;
  private router = inject(Router); //Sirve para inyectar el router


  constructor(private route: ActivatedRoute, private citasService: CitasService) { }

  ngOnInit(): void {
    const id = Number(this.route.snapshot.paramMap.get('id'));
    if (id) {
      this.citasService.get(id).subscribe(
        (response: Citas) => {
          this.cita = response;
        },
        (error) => {
          console.error('Error al obtener la cita', error);
        }
      );
    }
  }

  irAPrincipal(): void {
    this.router.navigate(['/']);  // Navegar a la página principal, ajusta la ruta según sea necesario
  }
}
