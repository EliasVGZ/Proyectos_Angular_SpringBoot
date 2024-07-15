import { Component, OnInit, inject } from '@angular/core';
import { ContactService } from '../services/contact.service';
import { DatePipe } from '@angular/common';
import { RouterModule } from '@angular/router';
import { Contact } from '../model/contact.interface';

@Component({
  selector: 'app-contact-list',
  standalone: true,
  imports: [DatePipe, RouterModule],
  templateUrl: './contact-list.component.html',
  styleUrl: './contact-list.component.css'
})
export default class ContactListComponent implements OnInit{

  private contactService = inject(ContactService);

  //Guardar los contactos

  contacts: Contact[] = [];

  ngOnInit(): void {//Cargar los contactos al iniciar
    this.loadAll();
  }

  loadAll(){//Cargar todos los contactos
    this.contactService.list()
      .subscribe(contactos => {
        this.contacts = contactos;
        console.log(this.contacts);
      });

  }

  deleteContact(id: number){//Eliminar un contacto
    this.contactService.delete(id)
      .subscribe(() => {
        this.loadAll();
      });
  }

 




}
