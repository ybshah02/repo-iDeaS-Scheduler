import {Component, Input, OnInit} from '@angular/core';
import {LearningGroup} from '../learning-group';
import {ActivatedRoute, Router} from '@angular/router';
import {LearningGroupService} from '../learning-group.service';

@Component({
  selector: 'app-lg-modify-form',
  templateUrl: './lg-modify-form.component.html',
  styleUrls: ['./lg-modify-form.component.css']
})
export class LgModifyFormComponent implements OnInit{
  learningGroups: LearningGroup[];
  learningGroup: LearningGroup;
  @Input() id: number;

  constructor(private route: ActivatedRoute, private router: Router, private learningGroupService: LearningGroupService) {
    this.learningGroup = new LearningGroup();
  }

  ngOnInit() {
    this.learningGroupService.findAll().subscribe(data => {
      this.learningGroups = data;
    });
  }

  getName(id: number) {
    for (const lg of this.learningGroups) {
      if (lg.id === id) {
        return lg.name;
      }
    }
  }

  onSubmit(id: number){
    this.learningGroupService.updateLG(id, this.learningGroup).subscribe(result => this.reloadPage());
  }

  reloadPage(){
    window.location.reload();
  }

}
