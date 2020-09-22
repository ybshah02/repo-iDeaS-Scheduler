import {Component, OnInit} from '@angular/core';
import {Region} from '../region';
import {ActivatedRoute, Router} from '@angular/router';
import {RegionService} from '../region-service.service';

@Component({
  selector: 'app-region-display',
  templateUrl: './region-display.component.html',
  styleUrls: ['./region-display.component.css']
})
export class RegionDisplayComponent implements OnInit {

  regions: Region[];
  showAddComponent: boolean;
  showEditComponent: boolean;
  regionToEdit: number;

  constructor(private route: ActivatedRoute, private router: Router, private regionService: RegionService) {
  }

  ngOnInit() {
    this.regionService.findAll().subscribe(data => {
      this.regions = data;
    });
  }

  toggleAddComponent() {
    this.showAddComponent = !this.showAddComponent;
    this.showEditComponent = false;
  }

  toggleEditComponent(id: number){
    this.showEditComponent = !this.showEditComponent;
    this.regionToEdit = id;
    this.showAddComponent = false;
  }

  onDelete(id: number) {
    this.regionService.deleteRegion(id).subscribe(result => this.reloadPage());
  }

  reloadPage() {
    window.location.reload();
  }
}
