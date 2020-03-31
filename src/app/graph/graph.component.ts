import { Component, OnInit } from '@angular/core';
import { GraphService } from 'src/service/GraphService';
import { ChartDataSets, ChartOptions } from 'chart.js';
import { Color, Label } from 'ng2-charts';

@Component({
    selector: "app-graph",
    templateUrl: "./graph.component.html",
    styleUrls: ["./graph.component.css"]
})
export class GraphComponent implements OnInit{

    
  lineChartData: ChartDataSets[] = [
    { data: [85, 72, 78, 75, 77, 75], label: 'Crude oil prices' },
  ];

  lineChartLabels: Label[] = ['January', 'February', 'March', 'April', 'May', 'June'];

  lineChartOptions = {
    responsive: true,
  };

  lineChartColors: Color[] = [
    {
      borderColor: 'black',
      backgroundColor: 'rgba(255,255,0,0.28)',
    },
  ];

  lineChartLegend = true;
  lineChartPlugins = [];
  lineChartType = 'line';
  

    constructor(private service : GraphService){}

    ngOnInit(){
        
    }
}