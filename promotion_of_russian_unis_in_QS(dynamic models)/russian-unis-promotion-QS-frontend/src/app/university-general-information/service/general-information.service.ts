import { Injectable } from '@angular/core';
import {HttpClient, HttpParams} from '@angular/common/http';
import {Observable} from 'rxjs';
import {UniversityCriterion} from '../../models/universityCriterion';
import {UniversityClassification} from '../../models/universityClassification';

@Injectable({
  providedIn: 'root'
})
export class GeneralInformationService {

  constructor(
    private httpClient: HttpClient
  ) { }

  getUniversityCriteriaInfo(universityId: number): Observable<UniversityCriterion[]> {
    const universityCriteriaUrl = 'http://localhost:8081/russian_unis_promotion_QS_backend_war/get/university_criteria';
    const httpParams = new HttpParams({
      fromString: `university_id=${universityId}`
    });
    return this.httpClient.get<UniversityCriterion[]>(universityCriteriaUrl, {
      params: httpParams
    });
  }

  getUniversityClassificationInfo(universityId: number): Observable<UniversityClassification[]> {
    const universityClassificationUrl = 'http://localhost:8081/russian_unis_promotion_QS_backend_war/get/university_classification';
    const httpParams = new HttpParams({
      fromString: `university_id=${universityId}`
    });
    return this.httpClient.get<UniversityClassification[]>(universityClassificationUrl, {
      params: httpParams
    })
  }
}
