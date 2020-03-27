import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-admin-menu',
  templateUrl: './admin-menu.component.html',
  styleUrls: ['./admin-menu.component.css']
})
export class AdminMenuComponent implements OnInit {

  isUserCollapsed = false;
  isClientCollapsed = false;
  isAccountCollapsed = false;
  isCategoriaCollapsed = false;
  isUnitaCollapsed = false;
  isDatasetCollapsed = false;
  isGraphCollapsed = false;

  constructor(private router: Router) {
  }

  ngOnInit() {
  }

  logout() {
    localStorage.clear();
    this.router.navigateByUrl('');
  }

  userscollapse() {
    if (this.isUserCollapsed === false) {
      this.closeAll();
      this.isUserCollapsed = true;
    } else { this.isUserCollapsed = false; }
  }

  accountcollapse() {
    if (this.isAccountCollapsed === false) {
      this.closeAll();
      this.isAccountCollapsed = true;
    } else { this.isAccountCollapsed = false; }
  }

  categoriascollapse() {
    if (this.isCategoriaCollapsed === false) {
      this.closeAll();
      this.isCategoriaCollapsed = true;
    } else { this.isCategoriaCollapsed = false; }
  }

  unitamisuracollapse() {
    if (this.isUnitaCollapsed === false) {
      this.closeAll();
      this.isUnitaCollapsed = true;
    } else { this.isUnitaCollapsed = false; }
  }

  datasetscollapse() {
    if (this.isDatasetCollapsed === false) {
      this.closeAll();
      this.isDatasetCollapsed = true;
    } else { this.isDatasetCollapsed = false; }
  }

  graphcollapse() {
    if (this.isGraphCollapsed === false) {
      this.closeAll();
      this.isGraphCollapsed = true;
    } else { this.isGraphCollapsed = false; }
  }

  closeAll(){
    this.isUserCollapsed = false;
    this.isClientCollapsed = false;
    this.isAccountCollapsed = false;
    this.isCategoriaCollapsed = false;
    this.isUnitaCollapsed = false;
    this.isDatasetCollapsed = false;
    this.isGraphCollapsed = false;
  }
}
