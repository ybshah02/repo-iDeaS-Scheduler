import { Component, OnInit } from '@angular/core';
import {Client} from '../client';
import {ActivatedRoute, Router} from '@angular/router';
import {ClientService} from '../client-service.service';
import {Region} from '../region';
import {RegionService} from '../region-service.service';


@Component({
  selector: 'app-client-display',
  templateUrl: './client-display.component.html',
  styleUrls: ['./client-display.component.css']
})
export class ClientDisplayComponent implements OnInit {

  clients: Client[];
  regions: Region[];
  showAddComponent: boolean;
  showEditComponent: boolean;
  clientToEdit: number;

  constructor(private route: ActivatedRoute,
              private router: Router,
              private clientService: ClientService,
              private regionService: RegionService
  ) {
  }

  ngOnInit(){
    this.clientService.findAll().subscribe(data => {
      this.clients = data;
    });
    this.regionService.findAll().subscribe(data => {
      this.regions = data;
    });
  }

  toggleAddComponent() {
    this.showAddComponent = !this.showAddComponent;
    this.showEditComponent = false;
  }

  public toggleEditComponent(id: number) {
    this.showEditComponent = !this.showEditComponent;
    this.clientToEdit = id;
    this.showAddComponent = false;
  }

  getRegion(id: number){
    for (const region of this.regions){
      if (region.id === id){
        return region.regionName;
      }
    }
  }

  onDelete(id: number) {
    this.clientService.deleteClient(id).subscribe(result => this.reloadPage());
  }

  reloadPage(){
    window.location.reload();
  }

}
