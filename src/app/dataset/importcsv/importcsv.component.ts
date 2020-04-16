import { Component, OnInit, ViewChild } from '@angular/core';
import { DataSetService } from 'src/service/DataSetService';
import { UserService } from 'src/service/user.service';
// import { CategoriaService } from 'src/service/categoria.service';
// import { UnitamisuraService } from 'src/service/unitamisura.service';

@Component({
  selector: 'app-importcsv',
  templateUrl: './importcsv.component.html',
  styleUrls: ['./importcsv.component.css']
})
export class ImportcsvComponent implements OnInit {

  constructor(private service: DataSetService, private serviceus: UserService
   // , private servicecat: CategoriaService, private serviceum: UnitamisuraService
    ) { }

  ngOnInit(): void {
  }

  public csvRecords: any[] = [];
  @ViewChild('fileImportInput') fileImportInput: any;
    fileChangeListener($event: any): void {
      var files = $event.srcElement.files;
      if (files[0].name.endsWith('.csv')) {
        var input = $event.target;
        var reader = new FileReader();
        reader.readAsText(input.files[0]);
        reader.onload = (data) => {
          let csvData = reader.result;
          let csvRecordsArray = (csvData as string).split(/\r\n|\n/);
          for(let i=0;i<csvRecordsArray.length;i++){
          let rowdata = csvRecordsArray[i].match(/("[^"]*")|[^,]+/g);
          this.csvRecords.push(rowdata);
          };          
        };
        reader.onerror = function() {
          alert('Unable to read ' + input.files[0]);
        };
      } else {
        alert('Please import valid .csv file.');
        this.fileImportInput.nativeElement.value = '';
        this.csvRecords = [];
      }
    }

    public elabora(): void{
      let unitamisura1 = this.csvRecords;
      let singolaunita;
      for(var i=0;i<unitamisura1.length;i++){
        if(unitamisura1[i]==null){
        i++;
        }
        singolaunita=unitamisura1[i];  
      }
      alert(singolaunita);

    }






}
