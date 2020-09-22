import { Component, OnInit } from '@angular/core';
import {Client} from '../client';
import {ActivatedRoute, Router} from '@angular/router';
import {ClientService} from '../client-service.service';
import {RegionService} from '../region-service.service';
import {Region} from '../region';

@Component({
  selector: 'app-client-form',
  templateUrl: './client-form.component.html',
  styleUrls: ['./client-form.component.css']
})
export class ClientFormComponent implements OnInit{

  client: Client;
  regions: Region[];

  constructor(private route: ActivatedRoute,
              private router: Router,
              private clientService: ClientService,
              private regionService: RegionService,
  ) {
    this.client = new Client();
  }

  ngOnInit(){
    this.regionService.findAll().subscribe(data => {
      this.regions = data;
    });
  }

  onSubmit(){
    this.clientService.addClient(this.client).subscribe(result => this.reloadPage());
  }

  reloadPage(){
    window.location.reload();
  }

}
