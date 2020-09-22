import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs/Observable';
import {LearningGroup} from './learning-group';

@Injectable()
export class LearningGroupService {

  private LGurl: string;

  constructor(private http: HttpClient) {
    this.LGurl = 'http://localhost:8080/learning-groups';
  }
  public findAll(): Observable<LearningGroup[]> {
    return this.http.get<LearningGroup[]>(this.LGurl);
  }

  public findById(id: number){
    return this.http.get<LearningGroup>(this.LGurl + '/' + id);
  }

  public addLG(lg: LearningGroup){
    return this.http.post<LearningGroup>(this.LGurl, lg);
  }

  public deleteLG(id: number) {
    return this.http.delete(this.LGurl + '/' + id);
  }

  public updateLG(id: number, lg: LearningGroup){
    return this.http.put(this.LGurl + '/' + id, lg);
  }
}
