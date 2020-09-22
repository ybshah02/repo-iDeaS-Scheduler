import { Component, OnInit } from '@angular/core';
import {Region} from '../region';
import {ActivatedRoute, Router} from '@angular/router';
import {RegionService} from '../region-service.service';

@Component({
  selector: 'app-region-form',
  templateUrl: './region-form.component.html',
  styleUrls: ['./region-form.component.css']
})
export class RegionFormComponent {

  newRegion: Region;

  constructor(private route: ActivatedRoute, private router: Router, private regionService: RegionService) {
    this.newRegion = new Region();
  }

  onSubmit(){
    this.regionService.addRegion(this.newRegion).subscribe(result => this.reloadPage());
  }

  reloadPage(){
    window.location.reload();
  }

}
