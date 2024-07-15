import { Component, OnInit, inject } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { ActivatedRoute, Router, RouterModule } from '@angular/router';
import { ContactService } from '../services/contact.service';
import { Contact } from '../model/contact.interface';

@Component({
  selector: 'app-contact-form',
  standalone: true,
  imports: [RouterModule, ReactiveFormsModule],
  templateUrl: './contact-form.component.html',
  styleUrl: './contact-form.component.css'
})
export default class ContactFormComponent implements OnInit{

  private fb= inject(FormBuilder); //Sirve para inyectar el formulario
  private contactService = inject(ContactService); //Sirve para inyectar el servicio
  private router = inject(Router); //Sirve para inyectar el router
  private route = inject(ActivatedRoute); //Sirve para inyectar la ruta

  form?: FormGroup;
  contact?: Contact;


  ngOnInit(): void {

    const id = this.route.snapshot.paramMap.get('id');
    
    if(id){
      this.contactService.get(parseInt(id))
        .subscribe(contact => {
          this.contact = contact;//Si existe el contacto se actualiza
          console.log('Contact updated', contact);

          this.form = this.fb.group({
            name: [contact.name, Validators.required],
            email: [contact.email, [Validators.required, Validators.email]]
          });
        });
    }else{
      this.form = this.fb.group({

        name: ['', Validators.required],
        email: ['', [Validators.required, Validators.email]]
      });
    
    }

  }
  //Funcion para crear un contacto
  save(){

    if(this.form?.invalid){
      this.form.markAllAsTouched();
      return;
    }


    const contactForm = this.form!.value;

    if(this.contact){
      this.contactService.update(this.contact.id, contactForm)
      .subscribe(() => {
        this.router.navigate(['/']);
      });

    }else{
      this.contactService.create(contactForm)
      .subscribe(() => {
        this.router.navigate(['/']);
      });
    }

    

  }

}
