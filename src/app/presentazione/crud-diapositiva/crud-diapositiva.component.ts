import { Component, OnInit } from '@angular/core';
import { DiapositivaService } from 'src/service/diapositiva.service';

@Component({
  selector: 'app-crud-diapositiva',
  templateUrl: './crud-diapositiva.component.html',
  styleUrls: ['./crud-diapositiva.component.css']
})
export class CrudDiapositivaComponent implements OnInit {

  constructor(private service: DiapositivaService) { }

  ngOnInit(): void {
  }

}
