import { Component, OnInit } from '@angular/core';
import { UnitamisuraService } from 'src/service/unitamisura.service';
import { UnitaMisuraDTO } from 'src/dto/unitamisuradto';

@Component({
  selector: 'app-unitamisure',
  templateUrl: './unitamisure.component.html',
  styleUrls: ['./unitamisure.component.css']
})
export class UnitamisureComponent implements OnInit {

  public usertype: string;
  unitamisure: UnitaMisuraDTO[];
  unitamisurainsert: UnitaMisuraDTO = new UnitaMisuraDTO();

  constructor(private service: UnitamisuraService) { }

  ngOnInit(): void {
    this.getUnitamisura();
    this.usertype = localStorage.getItem('usertype').toString();
  }

  getUnitamisura() {
    this.service.getAll().subscribe(unitamisure => this.unitamisure = unitamisure);
  }

  delete(unitamisura: UnitaMisuraDTO) {
    this.service.delete(unitamisura.id).subscribe(() => this.getUnitamisura());
  }

  update(unitamisura: UnitaMisuraDTO) {
    this.service.update(unitamisura).subscribe(() => this.getUnitamisura());
  }

  insert(unitamisura: UnitaMisuraDTO) {
    let x = this.unitamisurainsert.nome.trim();
    if(x != null){
      if(x != ""){
        this.service.insert(unitamisura).subscribe(() => this.getUnitamisura());
      }
    }
  }

}
