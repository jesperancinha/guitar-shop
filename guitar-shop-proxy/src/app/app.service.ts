import {Injectable} from "@angular/core";
import {Observable} from "rxjs";
import {HttpClient} from "@angular/common/http";

@Injectable({
  providedIn: 'root',
})
export class AppService {

  private apiUrl = 'http://localhost:8080/graphql';

  constructor(private http: HttpClient) {}

  getHello(): Observable<string> {
    return this.http.post<string>(this.apiUrl, {
      "query": "query getGuitar { hello }"
    });
  }
}
