import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs/Observable';
import {Client} from './client';

@Injectable()
export class ClientService {

  private clientUrl: string;

  constructor(private http: HttpClient) {
    this.clientUrl = 'http://localhost:8080/clients';
  }

  public findAll(): Observable<Client[]> {
    return this.http.get<Client[]>(this.clientUrl);
  }

  public findById(id: number){
    return this.http.get<Client>(this.clientUrl + '/' + id);
  }

  public addClient(client: Client){
    return this.http.post<Client>(this.clientUrl, client);
  }

  public deleteClient(id: number) {
    return this.http.delete(this.clientUrl + '/' + id);
  }

  public updateClient(id: number, client: Client){
    return this.http.put(this.clientUrl + '/' + id, client);
  }
}
