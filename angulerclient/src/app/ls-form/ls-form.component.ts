import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {LearningSession} from '../learning-session';
import {LearningSessionService} from '../learning-session.service';
import {LearningGroup} from '../learning-group';
import {LearningGroupService} from '../learning-group.service';

@Component({
  selector: 'app-ls-form',
  templateUrl: './ls-form.component.html',
  styleUrls: ['./ls-form.component.css']
})
export class LsFormComponent implements OnInit{

  learningSession: LearningSession;
  learningGroups: LearningGroup[];
  showAddComponent: boolean;

  constructor(private route: ActivatedRoute,
              private router: Router,
              private learningSessionService: LearningSessionService,
              private learningGroupService: LearningGroupService) {
    this.learningSession = new LearningSession();
  }

  ngOnInit() {
    this.learningGroupService.findAll().subscribe(data => {
      this.learningGroups = data;
    });
  }

  onSubmit(){
    this.learningSessionService.addLS(this.learningSession).subscribe(result => this.reloadPage());
  }

  reloadPage(){
    window.location.reload();
  }

}
