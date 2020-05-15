import { Component, OnInit} from '@angular/core';
import { DataSetDTO } from 'src/dto/dataSet.dto';
import { DataSetService } from 'src/service/DataSetService';
import { ExportToCsv } from 'export-to-csv';
import { ThrowStmt } from '@angular/compiler';

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
  listModify: DataSetDTO[];
  toOpen: boolean = false


  constructor(private dataService: DataSetService){
  }


  ngOnInit(): void{
    this.getDataSet();
  }

  getDataSet(){
    this.dataService.getDatasetByUser(this.user).subscribe(
      listDataSet =>{
        this.listDataSet = listDataSet;
        this.listaFiltrata = listDataSet.map(x => x.titolo).filter((x,i,a) => i == a.indexOf(x))
  });
  }

  modifica(i){
    this.toOpen = true;
    return this.dataService.getDatasetByUserTitolo(this.user, this.listaFiltrata[i])
    .subscribe(listModify => this.listModify = listModify);
  }




  options = {
    fieldSeparator: ',',
    quoteStrings: '"',
    decimalseparator: '.',
    showLabels: false,
    headers: [],
    showTitle: true,
    title: 'asfasf',
    useBom: false,
    removeNewLines: true,
    keys: ['approved', 'age','name' ]
  };

 data = [
    {
      name: "Vediamo",
      age: 13,
      average: 8.2,
      approved: true,
      description: "using 'Content here, content here' "
    },
    {
      name: 'Se',
      age: 11,
      average: 8.2,
      approved: true,
      description: "using 'Content here, content here' "
    },
    {
      name: 'Funziona',
      age: 10,
      average: 8.2,
      approved: true,
      description: "using 'Content here, content here' "
    }
  ];


// tslint:disable-next-line: member-ordering
csvExporter = new ExportToCsv(this.options);

esportaCsv(){
  this.csvExporter.generateCsv(this.data);
}


}
