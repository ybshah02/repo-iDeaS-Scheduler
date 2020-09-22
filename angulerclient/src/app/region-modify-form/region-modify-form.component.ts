import {Component, Input, OnInit} from '@angular/core';
import {Region} from '../region';
import {ActivatedRoute, Router} from '@angular/router';
import {RegionService} from '../region-service.service';

@Component({
  selector: 'app-region-modify-form',
  templateUrl: './region-modify-form.component.html',
  styleUrls: ['./region-modify-form.component.css'],
})
export class RegionModifyFormComponent implements OnInit{

  newRegion: Region;
  regions: Region[];
  @Input() id: number;

  constructor(private route: ActivatedRoute, private router: Router, private regionService: RegionService) {
    this.newRegion = new Region();
  }

  ngOnInit() {
    this.regionService.findAll().subscribe(data => {
      this.regions = data;
    });
  }

  getName(id: number) {
    for (const region of this.regions) {
      if (region.id === id) {
        return region.regionName;
      }
    }
  }

  onSubmit(id: number){
    this.regionService.updateRegion(id, this.newRegion).subscribe(result => this.reloadPage());
  }

  reloadPage(){
    window.location.reload();
  }

}
