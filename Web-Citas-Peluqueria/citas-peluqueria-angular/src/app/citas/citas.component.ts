import { Component, inject, OnInit } from '@angular/core';
import { DatePipe } from '@angular/common';
import { RouterModule } from '@angular/router';
import { CitasService } from '../services/citas.service';

@Component({
  selector: 'app-citas',
  standalone: true,
  imports: [DatePipe, RouterModule],
  templateUrl: './citas.component.html',
  styleUrls: ['./citas.component.css']
})
export default class CitasComponent implements OnInit{


  private citasService = inject(CitasService);



  ngOnInit(): void {


  }

  

}
