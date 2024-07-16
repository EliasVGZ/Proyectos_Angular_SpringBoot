import { Peluqueros } from "./citas.peluqueros";
import { Servicios } from "./citas.servicios";

export interface Citas{
    id: number;
    nombre: String;
    telefono: String;
    fecha: String;
    horaInicio: String;
    horaFin: String;
    peluquero: Peluqueros;
    servicio: Servicios;
}