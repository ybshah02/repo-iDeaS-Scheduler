import {Component, Input, OnInit} from '@angular/core';
import {Client} from '../client';
import {ActivatedRoute, Router} from '@angular/router';
import {LearningSessionService} from '../learning-session.service';
import {LearningSession} from '../learning-session';
import {ClientService} from '../client-service.service';

@Component({
  selector: 'app-client-modify-form',
  templateUrl: './client-modify-form.component.html',
  styleUrls: ['./client-modify-form.component.css']
})
export class ClientModifyFormComponent {

  client: Client;
  @Input() id: number;

  constructor(private route: ActivatedRoute, private router: Router, private clientService: ClientService) {
    this.client = new Client();
  }

  onSubmit(id: number){
    this.clientService.updateClient(id, this.client).subscribe(result => this.reloadPage());
  }

  reloadPage(){
    window.location.reload();
  }

}
