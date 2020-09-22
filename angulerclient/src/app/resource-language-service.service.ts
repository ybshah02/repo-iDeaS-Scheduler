import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs/Observable';
import {ResourceLanguage} from './resource-language';
import {Languages} from './languages';
import {Proficiency} from './proficiency';
import {Resource} from './resource';

@Injectable()
export class ResourceLanguageService {

  private resourceLanguageUrl: string;

  constructor(private http: HttpClient) {
      this.resourceLanguageUrl = 'http://localhost:8080/resources';
  }

  public findAll(): Observable<ResourceLanguage[]> {
    return this.http.get<ResourceLanguage[]>(this.resourceLanguageUrl + '/resource-languages');
  }

  public addResourceLanguage(resourceLanguage: ResourceLanguage){
    return this.http.post<ResourceLanguage>(this.resourceLanguageUrl + '/resource-languages', resourceLanguage);
  }

  public deleteResourceLanguage(id: number){
    return this.http.delete(this.resourceLanguageUrl + '/' + id + '/resource-languages');
  }

  public getLanguages(): Observable<Languages[]>{
    return this.http.get<Languages[]>(this.resourceLanguageUrl + '/resource-languages/languages');
  }

  public getProficiencies(): Observable<Proficiency[]>{
    return this.http.get<Proficiency[]>(this.resourceLanguageUrl + '/resource-languages/proficiencies');
  }
}
