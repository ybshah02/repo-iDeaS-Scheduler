import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Capabilities} from './capabilities';
import {Observable} from 'rxjs';

@Injectable()
export class CapabilitiesService {

  private capabilitiesUrl: string;

  constructor(private http: HttpClient) {
    this.capabilitiesUrl = 'http://localhost:8080/trainers';
  }

  public findAll(): Observable<Capabilities[]> {
    return this.http.get<Capabilities[]>(this.capabilitiesUrl + '/capabilities');
  }

  public findAllByTrainerId(id: number): Observable<Capabilities[]>{
    return this.http.get<Capabilities[]>(this.capabilitiesUrl + '/' + id + '/capabilities');
  }

  public addCapabilities(capabilities: Capabilities[]){
    return this.http.post(this.capabilitiesUrl + '/capabilities', capabilities);
  }
}
