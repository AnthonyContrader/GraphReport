import { Component, OnInit, Injectable } from '@angular/core';
import { SearchService } from 'src/service/searchservice';
import { CategoriaDTO } from 'src/dto/categoriadto';
import { DataSetDTO } from 'src/dto/DataSetDTO';
import { GraphDTO } from 'src/dto/GraphDTO';
import { UnitaMisuraDTO } from 'src/dto/unitamisuradto';
import { UserDTO } from 'src/dto/userdto';
import { ActivatedRoute } from '@angular/router';




@Component({
  selector: 'app-search',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.css']
})
export class SearchComponent implements OnInit {


  categorie: CategoriaDTO [];
  dataset: DataSetDTO [];
  graph: GraphDTO [];
  unitamisura: UnitaMisuraDTO [];
  user: UserDTO[];
  public scelta: number;


  constructor(private service: SearchService, private route: ActivatedRoute) {
    this.scelta = 1;
  }

  ngOnInit(): void {
    let c: string;
    this.route.paramMap.subscribe(x => { c = x.get('ricerca');
    this.service.findOnUser(c).subscribe(x => {this.user = x});
    this.service.findOnDataSet(c).subscribe(x => {this.dataset = x});
    this.service.findOnCategorie(c).subscribe(x => {this.categorie = x});
    this.service.findOnUnitaMisura(c).subscribe(x => {this.unitamisura = x});
    this.service.findOnGraph(c).subscribe(x => {this.graph = x});
  });
  }

  hoScelto(scelta){
    this.scelta = scelta;
  }



}
