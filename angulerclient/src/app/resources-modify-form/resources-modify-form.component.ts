import {Component, Input, OnInit} from '@angular/core';
import {Resource} from '../resource';
import {ActivatedRoute, Router} from '@angular/router';
import {ResourceService} from '../resource-service.service';

@Component({
  selector: 'app-resources-modify-form',
  templateUrl: './resources-modify-form.component.html',
  styleUrls: ['./resources-modify-form.component.css']
})
export class ResourcesModifyFormComponent implements OnInit{

  resource: Resource;
  @Input() id: number;
  resourceToEdit: Resource;

  constructor(private route: ActivatedRoute, private router: Router, private resourceService: ResourceService) {
    this.resource = new Resource();
  }
  ngOnInit() {
    this.resourceService.findById(this.id).subscribe(resource => {
      this.resourceToEdit = resource;
    });
  }

  onSubmit(id: number){
    this.resourceService.updateResource(id, this.resource).subscribe(result => this.reloadPage());
  }

  reloadPage(){
    window.location.reload();
  }

}
