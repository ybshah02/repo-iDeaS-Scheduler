import {Component, Input, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {LearningGroup} from '../learning-group';
import {LearningGroupService} from '../learning-group.service';
import {LearningSessionService} from '../learning-session.service';
import {LearningSession} from '../learning-session';
import {CapabilitiesService} from '../capabilities.service';
import {Capabilities} from '../capabilities';
import {Resource} from '../resource';
import {ResourceService} from '../resource-service.service';

@Component({
  selector: 'app-capabilities-display',
  templateUrl: './capabilities-display.component.html',
  styleUrls: ['./capabilities-display.component.css']
})
export class CapabilitiesDisplayComponent implements OnInit {

  @Input() id: number;
  resourceToAssign: Resource;
  learningGroups: LearningGroup[];
  learningSessions: LearningSession[];
  capabilities: Array<Capabilities> = [];

  constructor(private route: ActivatedRoute,
              private router: Router,
              private learningGroupService: LearningGroupService,
              private learningSessionService: LearningSessionService,
              private capabilitiesService: CapabilitiesService,
              private resourceService: ResourceService
              ) {

  }

  ngOnInit() {
    this.learningGroupService.findAll().subscribe(allLearningGroups => {
      this.learningGroups = allLearningGroups;
    });
    this.learningSessionService.findAll().subscribe(allLearningSessions => {
      this.learningSessions = allLearningSessions;
    });
    this.resourceService.findById(this.id).subscribe(data => {
      this.resourceToAssign = data;
    });
  }

  getLGName(id: number){
    for (const learningGroup of this.learningGroups){
      if (learningGroup.id === id){
        return learningGroup.name;
      }
    }
  }

  addSelection(learningSessionId: number, checked: boolean){
    const capabilityToAdd = new Capabilities();
    capabilityToAdd.resourceId = this.id;
    capabilityToAdd.learningSessionId = learningSessionId;
    if (checked) {
      capabilityToAdd.capable = 1;
      this.capabilities.push(capabilityToAdd);
    }else{
      capabilityToAdd.capable = 0;
      const index: number = this.capabilities.indexOf(capabilityToAdd);
      if (index !== -1){
        this.capabilities.splice(index, 1);
      }
    }
  }

  onSave(){
    this.capabilitiesService.addCapabilities(this.capabilities).subscribe(result => this.reloadPage());
  }

  reloadPage() {
    window.location.reload();
  }
}
