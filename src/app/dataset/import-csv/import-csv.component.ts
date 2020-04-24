import { Component, OnInit, ViewChild } from '@angular/core';
import { faTrashAlt } from '@fortawesome/free-solid-svg-icons';
import { CategoriaDTO } from 'src/dto/categoria.dto';
import { UnitaService } from 'src/service/unita.service';
import { UnitaMisuraDTO } from 'src/dto/unitamisura.dto';
import { DataSetDTO } from 'src/dto/dataSet.dto';
import { DataSetService } from 'src/service/DataSetService';

@Component({
  selector: 'app-import-csv',
  templateUrl: './import-csv.component.html',
  styleUrls: ['./import-csv.component.css']
})
export class ImportCsvComponent implements OnInit {

  user = JSON.parse(localStorage.getItem('identity') || sessionStorage.getItem("identity")).id;

  stamp: boolean = false;

  delete = faTrashAlt;

  csvRecords: any[] = [];
  splitOn : string = ";";

  file: any;
  categorie: CategoriaDTO[];
  umScelte : number[] = [];
  listUm : UnitaMisuraDTO[][] = [];

  titolo : string;
  um=0;

  constructor(private catService: UnitaService,private dsService: DataSetService) {
    this.catService.getCategoria().subscribe(x => this.categorie = x);
  }

  ngOnInit(): void {
  }

  toClick($event){
    this.file=$event.target.files;
    this.readIt();
  }

  toDrop(file:any){
    this.file=file;
    this.readIt();
  }

  readIt(){
    this.csvRecords=[];
    if (this.file[0].name.endsWith('.csv')) {
      var reader = new FileReader();
      reader.readAsText(this.file[0]);
      reader.onload = (data) => {
        let csvData = reader.result;
        let csvRecordsArray = (csvData as string).split(/\r\n|\n/);
        csvRecordsArray.forEach(x => {
          let row = x.split(this.splitOn);
          if(row!=undefined && row!=null)
            if(!(row.length==1 && row[0]==""))
                this.csvRecords.push(row);
        });
        this.stamp=true;
      }
    }
  }

  elimina(quale:number){
    if(quale>0){
      quale=quale-1;
      this.csvRecords.forEach(x => x.splice(quale,1));
    }else{
      quale=(quale*-1)-1;
      this.csvRecords.splice(quale,1);
    }
  }

  caricaUm(dove,cosa){
    this.catService.getAllByCategoria(cosa).subscribe(x => this.listUm[dove]=x);
  }

  annulla(){
    this.stamp=false;
    this.csvRecords=[];
    this.umScelte=[];
  }

  submit(){
    let valori: string = "";
    let listdto: DataSetDTO[] = [];
    if(this.um==0){
      for(let c=0;c<this.csvRecords[0].length;c++){
        valori="";
        for(let r=0;r<this.csvRecords.length;r++){
          if(this.csvRecords[r][c]!=undefined)
            valori+=this.csvRecords[r][c].toString()+"_";
          else
            valori+="_";
        }
        listdto.push(new DataSetDTO(null,this.titolo,valori.substring(0,valori.length-1),"",this.user,this.umScelte[c]));
      }

    }else{
      this.csvRecords.forEach((x,i,a) => {
        valori="";
        x.forEach(y => valori+=y.toString()+"_" ); 
        listdto.push(new DataSetDTO(null,this.titolo,valori.substring(0,valori.length-1),"",this.user,this.umScelte[i]));
      });
    }

    this.dsService.insertList(listdto).subscribe();
  }

  addClass(n){
    document.getElementById("col"+n).classList.add("colselected");
  }

  removeClass(n){
    document.getElementById("col"+n).classList.remove("colselected");
  }

}
