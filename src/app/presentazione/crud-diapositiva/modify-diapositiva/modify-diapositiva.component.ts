import { Component, OnInit, OnChanges, AfterViewInit, Input, ViewChild, ElementRef, Output, EventEmitter } from '@angular/core';
import { DiapositivaDTO } from 'src/dto/diapositiva.dto';
import { DiapositivaService } from 'src/service/diapositiva.service';
import  Konva  from 'Konva';
import { style } from '@angular/animations';
import { DiapoFullDTO } from 'src/dto/diapoFull.dto';

@Component({
  selector: 'app-modify-diapositiva',
  templateUrl: './modify-diapositiva.component.html',
  styleUrls: ['./modify-diapositiva.component.css']
})
export class ModifyDiapositivaComponent implements OnInit {

    @Input("diapo") diapo : DiapoFullDTO;
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
    
    diapofull: DiapoFullDTO = new DiapoFullDTO(new DiapositivaDTO(null,{ red : 255, green: 255, blue: 255, alpha: 255},0,false,"",false,null,null,null,{ red : 255, green: 255, blue: 255, alpha: 255},null));

    stage;
    sfondo;
    sfondoRect;
    titolo;
    titoloReact;

    fontList : string[] = [ 
        'Arial',
        'Calibri',
        'Cambria',
        'Courier',
        'Franklin',
        'Georgia',
        'Helvetica',
        'Helveticaneue',
        'Verdana'
     ]

    constructor(private service: DiapositivaService) {
        
    }

    ngOnInit(): void {}

    ngOnChanges(){
        if(this.diapofull!=undefined){
            this.mannangil.emit(JSON.stringify(this.diapofull));
            this.drawPage();
        }
    }

    ngAfterViewInit(){
        this.drawPage();
    }

    drawPage(){
        this.diapofull = this.diapo;
        if(this.lavagna!=undefined){ //appena l'html è pronto disegna la slide
            let ratio = this.diapofull.diapositiva.ratio.split(':');
            this.stage = new Konva.Stage({
                container: 'konva',
                width: window.innerWidth-610, //leggermente meno larga del contenitore
                height: (((window.innerWidth-610)*Number.parseInt(ratio[1]))/Number.parseInt(ratio[0])) //mantenendo le proporzioni
            });
            this.sfondo = new Konva.Layer();
            this.sfondoRect =  new Konva.Rect({ //disegna un rettangolo di sfondo per il colore
                x:0,
                y:0,
                width: window.innerWidth-610, 
                height: (((window.innerWidth-610)*Number.parseInt(ratio[1]))/Number.parseInt(ratio[0])),
                fill: this.toRGB(this.diapofull.diapositiva.sfondo),
                opacity: this.diapofull.diapositiva.sfondo.alpha/100,
                listening: true
            })
            this.stage.add(this.sfondo);
            this.sfondo.add(this.sfondoRect);
            this.sfondo.draw();

            //stampa del titolo
            let pos = this.diapofull.diapositiva.posizioneT.split("_");
            this.titolo = new Konva.Layer();
            this.titoloReact = new Konva.Text({
                x: (this.stage.width()/100)*Number.parseFloat(pos[0]),
                y: (this.stage.height()/100)*Number.parseFloat(pos[1]),
                text: this.diapofull.diapositiva.titolo,
                fontSize: this.diapofull.diapositiva.dimensioneT,
                fontFamily: this.fontList[this.diapofull.diapositiva.fontFamily],
                rotation: this.diapofull.diapositiva.fontRotation,
                fontStyle: this.diapofull.diapositiva.fontStyle,
                strokeWidth: this.diapofull.diapositiva.borderSize,
                stroke: this.toRGB(this.diapofull.diapositiva.borderColor),
                fill: this.toRGB(this.diapofull.diapositiva.coloreT),
                visible: this.diapofull.diapositiva.isTitolo,
                draggable: true
            });
            this.titoloReact.on('mouseenter', () => {
                this.stage.container().style.cursor = 'move';
            });
            this.titoloReact.on('mouseleave', () => {
                this.stage.container().style.cursor = 'default';
            });
            this.titoloReact.on("dragmove", () => { //mentre lo si sposta verificare i
                let x = Math.max(0, Math.min(this.stage.width()-this.titoloReact.width(), this.titoloReact.x()));
                let y = Math.max(0, Math.min(this.stage.height()-this.titoloReact.height(), this.titoloReact.y()));
                this.titoloReact.x(x);
                this.titoloReact.y(y);
                });
                this.titoloReact.on("dragend", x => { //quando viene spostato il titolo ne salva la nuova posizione
                this.diapofull.diapositiva.posizioneT= ((x.target.attrs.x*100)/this.stage.width()) + "_" + ((x.target.attrs.y*100)/this.stage.height());
                });
            this.stage.add(this.titolo);
            this.titolo.add(this.titoloReact);
            this.titolo.draw();
            
        }   
    }

    cambiaSfondo(newColore: string){
        this.diapofull.diapositiva.sfondo=this.fromHEX(newColore,this.diapofull.diapositiva.sfondo.alpha);
        this.sfondoRect.fill(this.toRGB(this.diapofull.diapositiva.sfondo));
        this.sfondo.draw();
    }

    cambiaOpacita(newOpacita: number){
        this.diapofull.diapositiva.sfondo.alpha=newOpacita;
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
        this.titoloReact.text(this.diapofull.diapositiva.titolo);
        this.stage.draw();
    }

    nascondiTitolo(){
        this.titoloReact.visible(this.diapofull.diapositiva.isTitolo);
        this.stage.draw();
    }

    cambiaFontFamily(){
        this.titoloReact.fontFamily(this.fontList[this.diapofull.diapositiva.fontFamily]);
        this.titoloReact.draw();
        this.titolo.draw();
    }

    cambiaFontStyle(){
        this.titoloReact.fontStyle(this.diapofull.diapositiva.fontStyle);
        this.titolo.draw();
    }

    cambiaRotation(){
        this.titoloReact.rotation(this.diapofull.diapositiva.fontRotation);
        this.titolo.draw();
    }

    cambiaSize(){
        this.titoloReact.fontSize(this.diapofull.diapositiva.dimensioneT);
        this.titolo.draw();
    }

    cambiaColore(newColore){
        this.diapofull.diapositiva.coloreT=this.fromHEX(newColore,this.diapofull.diapositiva.coloreT.alpha);
        this.titoloReact.fill(this.toRGB(this.diapofull.diapositiva.coloreT));
        this.titolo.draw();
    }

    cambiaOpacitaTitolo(newOpacita: number){
        this.diapofull.diapositiva.coloreT.alpha=newOpacita;
        this.titoloReact.opacity(newOpacita/100);
        this.titolo.draw();
    }

    
    cambiaBorderSize(){
        this.titoloReact.strokeWidth(this.diapofull.diapositiva.borderSize);
        this.titolo.draw();
    }

    cambiaBorderColor(newColore){
        this.diapofull.diapositiva.borderColor=this.fromHEX(newColore,100);
        this.titoloReact.stroke(this.toRGB(this.diapofull.diapositiva.borderColor));
        this.titolo.draw();
    }


}
