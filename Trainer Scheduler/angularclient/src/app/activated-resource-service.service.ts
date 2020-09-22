import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {ActivatedResource} from './activated-resource';

@Injectable({
  providedIn: 'root'
})
export class ActivatedResourceService {

  private schedulingUrl: string;

  constructor(private http: HttpClient) {
    this.schedulingUrl = 'http://localhost:8080/activated-resources';
  }

  public getActivatedResources(): Observable<ActivatedResource[]> {
    return this.http.get<ActivatedResource[]>(this.schedulingUrl);
  }

  public activateResource(activatedResource: ActivatedResource) {
    return this.http.post(this.schedulingUrl, activatedResource);
  }

  public disableResource(regionId, resourceId: number) {
    return this.http.delete(this.schedulingUrl + '/' + regionId + '/' + resourceId);
  }
}
