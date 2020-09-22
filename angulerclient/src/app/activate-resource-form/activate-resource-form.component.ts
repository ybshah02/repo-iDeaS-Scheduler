import {Component, Input, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {ResourceService} from '../resource-service.service';
import {Resource} from '../resource';
import {ActivatedResource} from '../activated-resource';
import {ActivatedResourceService} from '../activated-resource-service.service';

@Component({
  selector: 'app-activate-resource-form',
  templateUrl: './activate-resource-form.component.html',
  styleUrls: ['./activate-resource-form.component.css']
})
export class ActivateResourceFormComponent implements OnInit{

  @Input() id: number;
  activatedResource: ActivatedResource;
  resourceToActivate: Resource;
  tempStartDate: string;
  tempEndDate: string;

  constructor(private route: ActivatedRoute,
              private router: Router,
              private resourceService: ResourceService,
              private activatedResourceService: ActivatedResourceService) {
    this.activatedResource = new ActivatedResource();
  }
  ngOnInit() {
    this.resourceService.findById(this.id).subscribe(resource =>{
      this.resourceToActivate = resource;
    });
  }

  activateResource(){
    this.activatedResource.resourceId = this.id;
    this.activatedResource.regionId = this.resourceToActivate.regionId;
    const startDate = new Date(this.tempStartDate);
    const endDate = new Date(this.tempEndDate);
    this.activatedResource.startDate = startDate;
    this.activatedResource.endDate = endDate;
    this.activatedResourceService.activateResource(this.activatedResource).subscribe(result => this.reloadPage());
  }

  reloadPage() {
    window.location.reload();
  }

}

