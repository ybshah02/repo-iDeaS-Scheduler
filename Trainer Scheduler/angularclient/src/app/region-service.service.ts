import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Region } from './region';
import { Observable } from 'rxjs/Observable';
import {LearningSession} from './learning-session';

@Injectable()
export class RegionService {

  private regionsUrl: string;

  constructor(private http: HttpClient) {
    this.regionsUrl = 'http://localhost:8080/regions';
  }

  public findAll(): Observable<Region[]> {
    return this.http.get<Region[]>(this.regionsUrl);
  }

  public addRegion(region: Region) {
    return this.http.post<Region>(this.regionsUrl, region);
  }

  public deleteRegion(id: number) {
    return this.http.delete(this.regionsUrl + '/' + id);
  }

  public updateRegion(id: number, region: Region){
    return this.http.put(this.regionsUrl + '/' + id, region);
  }

}
