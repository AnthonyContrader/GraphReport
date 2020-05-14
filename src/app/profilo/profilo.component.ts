import { Component, OnInit, OnChanges, Input } from '@angular/core';
import { NgbModal, ModalDismissReasons } from '@ng-bootstrap/ng-bootstrap';
import { UserService } from 'src/service/user.service';
import { UserDTO } from 'src/dto/user.dto';
import { faEnvelopeOpenText, faGlobeAmericas, faKey } from '@fortawesome/free-solid-svg-icons';
import { FormGroup, FormControl, Validators, FormBuilder } from '@angular/forms';
import { Router } from '@angular/router';

@Component({
  selector: 'app-profilo',
  templateUrl: './profilo.component.html',
  styleUrls: ['./profilo.component.css']
})
export class ProfiloComponent implements OnInit {
  
  @Input('utente') id:string;
  
  stamp: boolean = false;

  iconMail = faEnvelopeOpenText;
  iconLang = faGlobeAmericas;
  iconAuth = faKey;

  formTitle: FormGroup;

  modify: boolean = false;
  imageDefault = "assets\\imgProf\\imgDef.png";
  utente: UserDTO;

  constructor(private modalService: NgbModal, private userS: UserService,private fb: FormBuilder, private router : Router ) {

  }

  ngOnInit() {
  }

  ngOnChanges(){
    if(this.id!=undefined){
      this.userS.find(this.id).subscribe(x => {
        this.utente=x.body;
        this.utente.imageUrl = this.utente.imageUrl || this.imageDefault;
        this.mappaCampi();
        this.stamp = true;
      });
    } 
  }

  abilita(){
    this.formTitle.get('nome').enable();
    this.formTitle.get('cognome').enable();
    this.formTitle.get('email').enable();
    this.formTitle.get('lang').enable();
    this.modify=true;
  }

  disabilita(){
    this.formTitle.get('nome').disable();
    this.formTitle.get('cognome').disable();
    this.formTitle.get('email').disable();
    this.formTitle.get('lang').disable();
    this.modify=false;
  }

  update(){
    this.utente.firstName=this.formTitle.get('nome').value;
    this.utente.lastName=this.formTitle.get('cognome').value;
    this.utente.email=this.formTitle.get('email').value;
    this.utente.langKey=this.formTitle.get('lang').value;
    this.userS.update(this.utente).subscribe(
      x => alert('ok'),
      y => alert('non ok')
    );
    this.disabilita();
  }

  mappaCampi(){
    this.formTitle = new FormGroup({
      nome: new FormControl({value:this.utente.firstName,disabled:true},[Validators.required,Validators.minLength(3)]),
      cognome: new FormControl({value:this.utente.lastName,disabled:true},[Validators.required,Validators.minLength(3)]),
      email: new FormControl({value:this.utente.email,disabled:true},[Validators.required,Validators.email]),
      lang: new FormControl({value:this.utente.langKey,disabled:true}),
    });
  }

  annulla(){
    this.disabilita();
    this.mappaCampi();
  }

    open(content) {
    this.modalService.open(content); /* , {ariaLabelledBy: 'modal-basic-title'}).result.then((result) => {
      this.closeResult = `Closed with: ${result}`;
    }, (reason) => {
      this.closeResult = `Dismissed ${this.getDismissReason(reason)}`;
    }); */
  }

  openLg(content) {
    this.modalService.open(content, { size: 'lg' });
  }
  /* private getDismissReason(reason: any): string {
    if (reason === ModalDismissReasons.ESC) {
      return 'by pressing ESC';
    } else if (reason === ModalDismissReasons.BACKDROP_CLICK) {
      return 'by clicking on a backdrop';
    } else {
      return `with: ${reason}`;
    } 
  } */

elimina(){
  let result = confirm("Sei sicuro di voler Eliminare Definitivamente l'account?");
  if(result){
  this.userS.deleteByLogin(this.utente.login).subscribe(() =>
  this.modalService.dismissAll());
  this.router.navigate(['/login']);
  }
}

}


