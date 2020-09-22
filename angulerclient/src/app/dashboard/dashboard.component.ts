import {Component, OnInit} from '@angular/core';
import {RegionService} from '../region-service.service';
import {ActivatedRoute, Router} from '@angular/router';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})

export class DashboardComponent {

  constructor(private route: ActivatedRoute, private router: Router, private regionService: RegionService) {}
}


