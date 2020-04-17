import { Component, OnInit} from '@angular/core';
import { UnitaMisuraDTO } from 'src/dto/unitamisura.dto';
import { UnitaService } from 'src/service/unita.service';


@Component({
  selector: 'app-unita',
  templateUrl: './unita.component.html',
  styleUrls: ['./unita.component.css']
})
export class UnitaComponent implements OnInit {

  listUnita: UnitaMisuraDTO[];
  newUnita: UnitaMisuraDTO = new UnitaMisuraDTO();

  constructor(private service: UnitaService) { }

  ngOnInit(): void {
    this.getUnitaMisura();
  }


  getUnitaMisura(){
    this.service.getAll().subscribe(listUnita => this.listUnita = listUnita);
  }

  delete(unitamisura: UnitaMisuraDTO){
    this.service.delete(unitamisura.id).subscribe(() => this.getUnitaMisura());
  }

  update(unitamisura: UnitaMisuraDTO){
    this.service.update(unitamisura).subscribe(() => this.getUnitaMisura());
  }

  insert(unitamisura: UnitaMisuraDTO){
    let x = this.newUnita.nome.trim();
    if(x != null){
      if(x !== ""){
        this.service.insert(unitamisura).subscribe(() => this.getUnitaMisura());
      }
    }
  }

}
