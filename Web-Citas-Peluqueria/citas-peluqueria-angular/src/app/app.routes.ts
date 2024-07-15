import { Routes } from '@angular/router';

export const routes: Routes = [

    {
        path: '',
        loadComponent: () => import('./citas/citas.component')
    },
    {
        path: 'new',
        loadComponent: () => import('./citas-form/citas-form.component')
    },
    {
        path: 'citas/:id',
        loadComponent: () => import('./detalle-cita/detalle-cita.component')
    }


];
