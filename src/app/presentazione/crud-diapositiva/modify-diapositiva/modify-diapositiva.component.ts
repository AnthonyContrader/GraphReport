import { Component, OnInit, OnChanges, AfterViewInit, Input, ViewChild, ElementRef, Output, EventEmitter } from '@angular/core';
import { DiapositivaDTO } from 'src/dto/diapositiva.dto';
import { DiapositivaService } from 'src/service/diapositiva.service';
import  Konva  from 'Konva';

@Component({
  selector: 'app-modify-diapositiva',
  templateUrl: './modify-diapositiva.component.html',
  styleUrls: ['./modify-diapositiva.component.css']
})
export class ModifyDiapositivaComponent implements OnInit {

    @Input("diapo") diapo : DiapositivaDTO;
    @Output("awand") mannangil = new EventEmitter();
    @ViewChild("lavagna") lavagna : ElementRef<HTMLDivElement>;

    toRGB = (x:{ red : number, green: number, blue: number, alpha: number}) => {
        return "rgba(" + x.red + "," + x.green + "," + x.blue +")";
    }

    toHEX = (x:{ red : number, green: number, blue: number, alpha: number}) => {
        return "#"+x.red.toString(16) + x.green.toString(16) + x.blue.toString(16);
    }

    fromHEX = (colore,alpha) => { 
        let a = colore.match(/\w\w/g).map(x => parseInt(x, 16));
        return {red: a[0], green: a[1], blue: a[2], alpha: alpha };
    }
    
    diapositiva : DiapositivaDTO = new DiapositivaDTO(null,{ red : 255, green: 255, blue: 255, alpha: 255},0,false,"",false,null,null,null,{ red : 255, green: 255, blue: 255, alpha: 255},null);

    stage;
    sfondo;
    sfondoRect;
    titolo;
    titoloReact;

    constructor(private service: DiapositivaService) {
        
    }

    ngOnInit(): void {}

    ngOnChanges(){
        if(this.diapositiva!=undefined){
            this.mannangil.emit(JSON.stringify(this.diapositiva));
            this.drawPage();
        }
    }

    ngAfterViewInit(){
        this.drawPage();
    }

    drawPage(){
        this.diapositiva = this.diapo;
        if(this.lavagna!=undefined){ //appena l'html è pronto disegna la slide
            let ratio = this.diapositiva.ratio.split(':');
            this.stage = new Konva.Stage({
                container: 'konva',
                width: this.lavagna.nativeElement.offsetWidth-20, //leggermente meno larga del contenitore
                height: (((this.lavagna.nativeElement.offsetWidth-20)*Number.parseInt(ratio[1]))/Number.parseInt(ratio[0])) //mantenendo le proporzioni
            });
            this.sfondo = new Konva.Layer();
            this.sfondoRect =  new Konva.Rect({ //disegna un rettangolo di sfondo per il colore
                x:0,
                y:0,
                width: this.lavagna.nativeElement.offsetWidth-20, 
                height: (((this.lavagna.nativeElement.offsetWidth-20)*Number.parseInt(ratio[1]))/Number.parseInt(ratio[0])),
                fill: this.toRGB(this.diapositiva.sfondo),
                opacity: this.diapositiva.sfondo.alpha/100,
                listening: true
            })
            this.stage.add(this.sfondo);
            this.sfondo.add(this.sfondoRect);
            this.sfondo.draw();

            if(this.diapositiva.isTitolo){ //se c'è un titolo stampalo
                let pos = this.diapositiva.posizioneT.split("_");
                this.titolo = new Konva.Layer();
                this.titoloReact = new Konva.Text({
                    x: (this.stage.width()/100)*Number.parseFloat(pos[0]),
                    y: (this.stage.height()/100)*Number.parseFloat(pos[1]),
                    text: this.diapositiva.titolo,
                    fontSize: Number.parseInt(this.diapositiva.dimensioneT),
                    fontFamily: 'Calibri',
                    fill: this.toRGB(this.diapositiva.coloreT),
                    draggable: true
                });
                this.titoloReact.on("dragmove", () => { //mentre lo si sposta verificare i
                    let x = Math.max(0, Math.min(this.stage.width()-this.titoloReact.width(), this.titoloReact.x()));
                    let y = Math.max(0, Math.min(this.stage.height()-this.titoloReact.height(), this.titoloReact.y()));
                    this.titoloReact.x(x);
                    this.titoloReact.y(y);
                 });
                 this.titoloReact.on("dragend", x => { //quando viene spostato il titolo ne salva la nuova posizione
                    this.diapositiva.posizioneT= ((x.target.attrs.x*100)/this.stage.width()) + "_" + ((x.target.attrs.y*100)/this.stage.height());
                 });
                this.stage.add(this.titolo);
                this.titolo.add(this.titoloReact);
                this.titolo.draw();
            }

        }   
    }

    cambiaSfondo(newColore: string){
        this.diapositiva.sfondo=this.fromHEX(newColore,this.diapositiva.sfondo.alpha);
        this.sfondoRect.fill(this.toRGB(this.diapositiva.sfondo));
        this.sfondo.draw();
    }

    cambiaOpacita(newOpacita: number){
        this.diapositiva.sfondo.alpha=newOpacita;
        this.sfondoRect.opacity(newOpacita/100);
        this.sfondo.draw();
    }

    cambiaRatio(newRatio: string){
        let a = newRatio.split(":");
        this.stage.height((this.stage.width()*Number.parseInt(a[1]))/Number.parseInt(a[0]));
        this.sfondoRect.height((this.sfondoRect.width()*Number.parseInt(a[1]))/Number.parseInt(a[0]));
        this.stage.draw();
        
    }

    cambiaTitolo(){
        this.titoloReact.text(this.diapositiva.titolo);
        this.stage.draw();
    }

    nascondiTitolo(){
        this.titoloReact.visible(this.diapositiva.isTitolo);
        this.stage.draw();
    }

}
