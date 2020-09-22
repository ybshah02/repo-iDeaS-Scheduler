import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Resource } from './resource';
import { Observable } from 'rxjs/Observable';

@Injectable()
export class ResourceService {

  private resourcesUrl: string;

  constructor(private http: HttpClient) {
    this.resourcesUrl = 'http://localhost:8080/resources';
  }

  public findAll(): Observable<Resource[]> {
    return this.http.get<Resource[]>(this.resourcesUrl);
  }

  public findById(id: number): Observable<Resource> {
    return this.http.get<Resource>(this.resourcesUrl + '/' + id);
  }

  public addResource(resource: Resource) {
    return this.http.post(this.resourcesUrl, resource);
  }

  public deleteResource(id: number) {
    return this.http.delete(this.resourcesUrl + '/' + id);
  }

  public updateResource(id: number, resource: Resource){
    return this.http.put(this.resourcesUrl + '/' + id, resource);
  }

}
