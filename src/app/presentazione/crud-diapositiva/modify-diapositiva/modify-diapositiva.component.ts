import { Component, OnInit, OnChanges, Input, ViewChild, ElementRef, Output, EventEmitter } from '@angular/core';
import { DiapositivaDTO } from 'src/dto/diapositiva.dto';
import { DiapositivaService } from 'src/service/diapositiva.service';
import  Konva  from 'Konva';
import { DiapoFullDTO } from 'src/dto/diapoFull.dto';
import { Colore } from 'src/dto/colore.obj';
import { faTrashAlt, faPlus } from '@fortawesome/free-solid-svg-icons'
import { TestoService } from 'src/service/testo.service';
import { DiapoGraphService } from 'src/service/diapoGraph.service';
import { TestoDTO } from 'src/dto/testo.dto';

@Component({
  selector: 'app-modify-diapositiva',
  templateUrl: './modify-diapositiva.component.html',
  styleUrls: ['./modify-diapositiva.component.css']
})
export class ModifyDiapositivaComponent implements OnInit,OnChanges {

    @Input("diapo") diapo : DiapoFullDTO;
    @Input("esporta") toExp : boolean;
    @Output("awand") mannangil = new EventEmitter();
    @Output("image") toImage = new EventEmitter();
    @ViewChild("lavagna") lavagna : ElementRef<HTMLDivElement>;

    toRGB = (x:{ red : number, green: number, blue: number, alpha: number}) => {
        return "rgb(" + x.red + "," + x.green + "," + x.blue +")";
    }

    toHEX = (x: Colore) => {
        let red= x.red.toString(16);
        if(red.length<2) red="0"+red;
        let green= x.green.toString(16);
        if(green.length<2) green="0"+green;
        let blue= x.blue.toString(16);
        if(blue.length<2) blue="0"+blue;
        return ("#"+ red + green + blue).toLowerCase();
    }

    fromHEX = (colore,alpha) => { 
        let a = colore.match(/\w\w/g).map(x => parseInt(x, 16));
        return new Colore(a[0],a[1],a[2],alpha);
    }
    
    diapofull: DiapoFullDTO = new DiapoFullDTO(new DiapositivaDTO(null,{ red : 255, green: 255, blue: 255, alpha: 100},0,false,"16_9",false,"","1_1",5,{ red : 255, green: 255, blue: 255, alpha: 100},null));

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

    trash= faTrashAlt;
    new = faPlus;

    txtSelected:number=-1;

    constructor(private service: DiapositivaService, private serviceTesto: TestoService, private serviceGrafico: DiapoGraphService) {
        
    }

    ngOnInit(): void {}

    ngOnChanges(){
        if(this.diapofull.diapositiva.id!=null){
            this.mannangil.emit(JSON.stringify(this.diapofull));
        }
        this.serviceTesto.getAllByDiapositiva(this.diapo.diapositiva.id).subscribe(x => {
            this.diapo.testi = x;
            this.serviceGrafico.getAllByDiapositiva(this.diapo.diapositiva.id).subscribe(y => {
                this.diapo.grafici = y;
                this.drawPage();
                if(this.toExp){
                    this.esportaPNG();
                }
            });
        });
    }

    drawPage(){
        let windowWidth = window.innerWidth-577;
        this.diapofull = this.diapo;
        if(this.lavagna!=undefined){ //appena l'html è pronto disegna la slide
            let ratio = this.diapofull.diapositiva.ratio.split(':');
            this.stage = new Konva.Stage({
                container: 'konva',
                width: windowWidth, //leggermente meno larga del contenitore
                height: ((windowWidth*Number.parseInt(ratio[1]))/Number.parseInt(ratio[0])) //mantenendo le proporzioni
            });
            this.sfondo = new Konva.Layer();
            this.sfondoRect =  new Konva.Rect({ //disegna un rettangolo di sfondo per il colore
                x:0,
                y:0,
                width: windowWidth, 
                height: ((windowWidth*Number.parseInt(ratio[1]))/Number.parseInt(ratio[0])),
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

    esportaPNG(){
        this.toImage.emit(this.stage.toDataURL());
    }

    newText(){
        this.serviceTesto.insert(new TestoDTO(this.diapofull.diapositiva)).subscribe(x => this.diapofull.testi.push(x));
    }

}
