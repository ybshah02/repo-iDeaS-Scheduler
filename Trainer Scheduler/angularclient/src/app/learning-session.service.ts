import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs/Observable';
import {LearningSession} from './learning-session';

@Injectable()
export class LearningSessionService {

  private LSurl: string;

  constructor(private http: HttpClient) {
    this.LSurl = 'http://localhost:8080/learning-sessions';
  }
  public findAll(): Observable<LearningSession[]> {
    return this.http.get<LearningSession[]>(this.LSurl);
  }

  public addLS(ls: LearningSession){
    return this.http.post<LearningSession>(this.LSurl, ls);
  }

  public deleteLS(id: number) {
    return this.http.delete(this.LSurl + '/' + id);
  }

  public updateLS(id: number, ls: LearningSession){
    return this.http.put(this.LSurl + '/' + id, ls);
  }
}
