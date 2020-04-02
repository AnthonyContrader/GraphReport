import { Component, OnInit } from '@angular/core';
import { DataSetService } from 'src/service/DataSetService';

@Component({
  selector: 'app-importcsv',
  templateUrl: './importcsv.component.html',
  styleUrls: ['./importcsv.component.css']
})
export class ImportcsvComponent implements OnInit {

  constructor(private service: DataSetService) { }

  ngOnInit(): void {
  }

}
