import {Component, Input, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {LearningSessionService} from '../learning-session.service';
import {LearningSession} from '../learning-session';

@Component({
  selector: 'app-ls-modify-form',
  templateUrl: './ls-modify-form.component.html',
  styleUrls: ['./ls-modify-form.component.css']
})
export class LsModifyFormComponent implements OnInit{
  learningSessions: LearningSession[];
  learningSession: LearningSession;
  @Input() id: number;

  constructor(private route: ActivatedRoute, private router: Router, private learningSessionService: LearningSessionService) {
    this.learningSession = new LearningSession();
  }

  ngOnInit() {
    this.learningSessionService.findAll().subscribe(data => {
      this.learningSessions = data;
    });
  }

  getName(id: number) {
    for (const ls of this.learningSessions) {
      if (ls.id === id) {
        return ls.name;
      }
    }
  }

  onSubmit(id: number){
    this.learningSessionService.updateLS(id, this.learningSession).subscribe(result => this.reloadPage());
  }

  reloadPage(){
    window.location.reload();
  }

}
