import { Component, OnInit} from '@angular/core';
import { DataSetDTO } from 'src/dto/dataSet.dto';
import { DataSetService } from 'src/service/DataSetService';
import { ExportToCsv } from 'export-to-csv';
import { faMinus, faFileExport } from '@fortawesome/free-solid-svg-icons'
import { UnitaMisuraDTO } from 'src/dto/unitamisura.dto';
import { UnitaService } from 'src/service/unita.service';

@Component({
  selector: 'app-export-csv',
  templateUrl: './export-csv.component.html',
  styleUrls: ['./export-csv.component.css']
})
export class ExportCsvComponent implements OnInit {

  //dataSet: DataSetDTO;

  user = JSON.parse(localStorage.getItem('identity') || sessionStorage.getItem("identity")).id;
  listDataSet: DataSetDTO[];
  titolo: string;
  listaFiltrata: any[];
  daModificare: DataSetDTO;
  listModify: DataSetDTO[] = [];
  toOpen: boolean = false;
  listUnita: UnitaMisuraDTO[] = [];
  elimina = faMinus;
  export = faFileExport;
  selezionato: number = -1;
  matriceExport: string[][] = [];


  constructor(private dataService: DataSetService, private umService: UnitaService){
  }


  ngOnInit(): void{
    this.getDataSet();
  }

  getDataSet(){
    this.dataService.getDatasetByUser(this.user).subscribe(
      listDataSet =>{
        this.listDataSet = listDataSet;
        this.listaFiltrata = listDataSet.map(x => x.titolo).filter((x,i,a) => i == a.indexOf(x));
  });
  }

  modifica(i){
    this.selezionato = i;
    return this.dataService.getDatasetByUserTitolo(this.user, this.listaFiltrata[i])
    .subscribe(listModify => {
      this.listModify = listModify;
      this.umNomi();
    });
  }

  umNomi(){
    this.listUnita = [];
    this.listModify.map(x => x.idUnita).filter((x,i,a) => i == a.indexOf(x)).forEach((x) =>
      this.listUnita.push(new UnitaMisuraDTO(x, null, null))
    );
    this.umService.getListNomi(this.listUnita).subscribe(listNomi =>{
      this.listUnita = listNomi;
      this.toOpen = true;
    });
  }

  getNome = daCercare => {
    return this.listUnita[this.listUnita.map(y => y.id).indexOf(daCercare)].nome;
  }

  delColonna(i){
    return this.listModify.splice(i,1);
  }


esportaCsv(){
 let options = {
    fieldSeparator: ',',
    quoteStrings: '',
    decimalseparator: '.',
    showLabels: false,
    headers: [],
    showTitle: true,
    title: this.listModify[0].titolo,
    useBom: false,
    removeNewLines: true,
    keys: ['approved', 'age','name' ]
  };
  let csvExporter = new ExportToCsv(options);
  this.matriceExport = [];
  this.listModify.forEach(x => {
    let a = x.valori.split("_");
    a.splice(0,0,this.getNome(x.idUnita));
    this.matriceExport.push(a);

  });
  csvExporter.generateCsv(this.matriceExport);
}


}
