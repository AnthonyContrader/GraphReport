import { Component, OnInit, OnChanges, Input, ViewChild, ElementRef, Output, EventEmitter, TemplateRef } from '@angular/core';
import { DiapositivaDTO } from 'src/dto/diapositiva.dto';
import { DiapositivaService } from 'src/service/diapositiva.service';
import  Konva  from 'Konva';
import { DiapoFullDTO } from 'src/dto/diapoFull.dto';
import { Colore } from 'src/dto/colore.obj';
import { faTrashAlt, faPlus } from '@fortawesome/free-solid-svg-icons'
import { TestoService } from 'src/service/testo.service';
import { DiapoGraphService } from 'src/service/diapoGraph.service';
import { TestoDTO } from 'src/dto/testo.dto';
import { DiapoGraphDTO } from 'src/dto/diapograph.dto';
import { MatDialog } from '@angular/material/dialog';
import { GraphDTO } from 'src/dto/graph.dto';
import { GraphService } from 'src/service/graph.service';

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
    @ViewChild("dialogImport") modal : TemplateRef<any>;

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
    testoLayer = new Konva.Layer();


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

    dialogImportGraph;

    constructor(private dialog: MatDialog) {
        
    }

    ngOnInit(): void {}

    ngOnChanges(){
        this.txtSelected = -1;
        if(this.diapofull.diapositiva.id!=null){
            this.mannangil.emit(JSON.stringify(this.diapofull));
        }
        this.drawPage();
        if(this.toExp){
            this.esportaPNG();
        }
    }

    drawPage(){
        let windowWidth = window.innerWidth-577;
        this.diapofull = this.diapo;
        //if(this.lavagna!=undefined){ //appena l'html è pronto disegna la slide
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

            this.drawText();
             
    }

    drawText(){
        this.testoLayer.destroy();
        this.testoLayer = new Konva.Layer();
        if(this.diapofull.testi.length>0){
            this.diapofull.testi.forEach((txt,i) => {
                let pos = txt.posizione.split("_");
                let testoReact = new Konva.Text({
                    id: i.toString(),
                    x: (this.stage.width()/100)*Number.parseFloat(pos[0]),
                    y: (this.stage.height()/100)*Number.parseFloat(pos[1]),
                    text: txt.text,
                    fontSize: txt.fontSize,
                    fontFamily: this.fontList[txt.fontStyle],
                    fill: this.toRGB(txt.colore),
                    draggable: true 
                });
                testoReact.on('mouseenter', () => {
                    this.stage.container().style.cursor = 'move';
                });
                testoReact.on('mouseleave', () => {
                    this.stage.container().style.cursor = 'default';
                });
                testoReact.on("dragmove", evt => { //mentre lo si sposta verificare i
                    let x = Math.max(0, Math.min(this.stage.width()-evt.target.width(), evt.target.x()));
                    let y = Math.max(0, Math.min(this.stage.height()-evt.target.height(), evt.target.y()));
                    evt.target.x(x);
                    evt.target.y(y);
                });
                testoReact.on("dragend", evt => { //quando viene spostato il titolo ne salva la nuova posizione
                    this.diapofull.testi[Number.parseInt(evt.target.attrs.id)].posizione= ((evt.target.attrs.x*100)/this.stage.width()) + "_" + ((evt.target.attrs.y*100)/this.stage.height());
                });
                this.testoLayer.add(testoReact);                
            });
        }
        this.stage.add(this.testoLayer);
        this.testoLayer.draw();
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
        this.diapofull.testi.push(new TestoDTO(this.diapofull.diapositiva));
        this.txtSelected=this.diapofull.testi.length-1;       
        this.drawText();
    }

    delText(){
        this.diapofull.testiDel.push(this.diapofull.testi[this.txtSelected].id);
        this.diapofull.testi.splice(this.txtSelected,1);
        this.txtSelected=-1;
        this.drawText();
    }

    newGraph(){
        this.diapofull.grafici.push(new DiapoGraphDTO(this.diapofull.diapositiva));
    }

    updateTesto(){
        this.testoLayer.find('#'+this.txtSelected)[0].setAttr('text',this.diapofull.testi[this.txtSelected].text);
        this.testoLayer.draw();
    }

    cambiaColoreTesto(newColore){
        this.diapofull.testi[this.txtSelected].colore=this.fromHEX(newColore,this.diapofull.testi[this.txtSelected].colore.alpha);
        this.testoLayer.find('#'+this.txtSelected)[0].setAttr("fill",this.toRGB(this.diapofull.testi[this.txtSelected].colore));
        this.testoLayer.draw();
    }

    cambiaOpacitaTesto(newOpacita: number){
        this.diapofull.testi[this.txtSelected].colore.alpha=newOpacita;
        this.testoLayer.find('#'+this.txtSelected)[0].setAttr("opacity",newOpacita/100);
        this.testoLayer.draw();
    }
    cambiaFontStyleTesto(){
        this.testoLayer.find('#'+this.txtSelected)[0].setAttr("fontFamily",this.fontList[this.diapofull.testi[this.txtSelected].fontStyle]);
        this.testoLayer.draw();
    }

    cambiaSizeTesto(){
        this.testoLayer.find('#'+this.txtSelected)[0].setAttr("fontSize",this.diapofull.testi[this.txtSelected].fontSize);
        this.testoLayer.draw();
    }

    openModalImportGraph(){
        this.dialogImportGraph = this.dialog.open(this.modal);
    }

    addGraph(id){
        this.dialogImportGraph.close();
        alert(id)

    }

}
