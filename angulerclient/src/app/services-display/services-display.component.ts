import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {LearningSessionService} from '../learning-session.service';
import {LearningSession} from '../learning-session';
import {LearningGroup} from '../learning-group';
import {LearningGroupService} from '../learning-group.service';

@Component({
  selector: 'app-services-display',
  templateUrl: './services-display.component.html',
  styleUrls: ['./services-display.component.css']
})
export class ServicesDisplayComponent implements OnInit {

  learningSessions: LearningSession[];
  learningGroups: LearningGroup[];
  showLSAddComponent: boolean;
  showLSEditComponent: boolean;
  lsToEdit: number;

  showLGAddComponent: boolean;
  showLGEditComponent: boolean;
  lgToEdit: number;

  constructor(private route: ActivatedRoute,
              private router: Router,
              private learningSessionService: LearningSessionService,
              private learningGroupService: LearningGroupService) {
  }

  ngOnInit() {
    this.learningSessionService.findAll().subscribe(data => {
      this.learningSessions = data;
    });

    this.learningGroupService.findAll().subscribe(data => {
      this.learningGroups = data;
    });
  }

  toggleLSAddComponent() {
    this.showLSAddComponent = !this.showLSAddComponent;
    this.showLSEditComponent = false;
  }

  toggleLSEditComponent(id: number) {
    this.showLSEditComponent = !this.showLSEditComponent;
    this.lsToEdit = id;
    this.showLSAddComponent = false;
  }

  toggleLGAddComponent() {
    this.showLGAddComponent = !this.showLGAddComponent;
    this.showLGEditComponent = false;
  }

  toggleLGEditComponent(id: number) {
    this.showLGEditComponent = !this.showLGEditComponent;
    this.lsToEdit = id;
    this.showLGAddComponent = false;
  }

  getLearningGroup(idToCheck: number){
    for (const learningGroup of this.learningGroups){
      if (learningGroup.id === idToCheck) {
        return learningGroup.name;
      }
    }
  }

  onDeleteLS(id: number) {
    this.learningSessionService.deleteLS(id).subscribe(result => this.reloadPage());
  }

  onDeleteLG(id: number) {
    this.learningGroupService.deleteLG(id).subscribe(result => this.reloadPage());
  }

  reloadPage(){
    window.location.reload();
  }

}
