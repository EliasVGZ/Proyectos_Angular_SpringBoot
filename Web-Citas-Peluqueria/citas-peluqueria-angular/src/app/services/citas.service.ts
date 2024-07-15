import { Injectable, inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Citas } from '../model/citas.interface';
import { Servicios } from '../model/citas.servicios';
import { Peluqueros } from '../model/citas.peluqueros';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class CitasService {

  private http = inject(HttpClient); // Esto sirve para inyectar el servicio HttpClient


  list() {
    return this.http.get<Citas []>('http://localhost:8080/api/citas');
  }

  get(id: number) {
    return this.http.get<Citas>(`http://localhost:8080/api/citas/${id}`);
  }


  create(cita : any) {
    return this.http.post<any>('http://localhost:8080/api/citas', cita);
  }

  guardarCita(cita: Citas): Observable<Citas> {
    return this.http.post<Citas>('http://localhost:8080/api/citas/guardar', cita);
  }

 


  update(id:number, citas: Citas) {
    return this.http.put<Citas>(`http://localhost:8080/api/citas/${id}`, citas);
  }

  delete(id: number) {
    return this.http.delete<void>(`http://localhost:8080/api/citas/${id}`);
  }


  //GetServicios
  getServicios() {
    return this.http.get<Servicios []>('http://localhost:8080/api/servicios');
  }

  //Obtenenr todos los peluqueros
  getAllPeluqueros() {
    return this.http.get<Peluqueros []>('http://localhost:8080/api/peluqueros');
  }
  



}
