import { Injectable } from '@angular/core';
import {Observable} from 'rxjs';
import {TopRussianUnisInQS} from '../../models/topRussianUnisInQS';
import {HttpClient} from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class MainPageService {

  constructor(
    private httpClient: HttpClient
  ) { }

  getAllTopRussianUniversitiesInQS(): Observable<TopRussianUnisInQS[]> {
    const getAllTopRussianUnisInQSUrl = 'http://localhost:8081/russian_unis_promotion_QS_backend_war/get/top_russian_universities_in_QS';
    return this.httpClient.get<TopRussianUnisInQS[]>(getAllTopRussianUnisInQSUrl);
  }
}
