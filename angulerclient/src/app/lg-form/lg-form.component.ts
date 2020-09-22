import { Component, OnInit } from '@angular/core';
import {LearningGroupService} from '../learning-group.service';
import {LearningGroup} from '../learning-group';
import {ActivatedRoute, Router} from '@angular/router';

@Component({
  selector: 'app-lg-form',
  templateUrl: './lg-form.component.html',
  styleUrls: ['./lg-form.component.css']
})
export class LgFormComponent {

  learningGroup: LearningGroup;

  constructor(private route: ActivatedRoute, private router: Router, private learningGroupService: LearningGroupService) {
    this.learningGroup = new LearningGroup();
  }

  onSubmit(){
    this.learningGroupService.addLG(this.learningGroup).subscribe(result => this.reloadPage());
  }

  reloadPage(){
    window.location.reload();
  }

}
