import {Injectable} from '@angular/core';
import {HttpClient, HttpParams} from '@angular/common/http';
import {Observable} from 'rxjs';
import {PromotionData} from '../../models/promotionData';

@Injectable({
  providedIn: 'root'
})
export class ModelingPromotionService {

  constructor(
    private httpClient: HttpClient
  ) {
  }

  calculatePromotionData(universityName: string, promotionCriterion: string, startDate: number, targetDate: number,
                         promotionStep: number, promotionCoefficient?: number): Observable<PromotionData[]> {
    const dynamicPromotionUrl = 'http://localhost:8081/russian_unis_promotion_QS_backend_war/api/promote/';
    let httpParams = new HttpParams()
      .set('university_name', universityName)
      .set('promotion_criterion', promotionCriterion)
      .set('start_date', String(startDate))
      .set('target_date', String(targetDate))
      .set('promotion_step', String(promotionStep));

    if (promotionCoefficient){
      httpParams = httpParams.set('promotion_coefficient', String(promotionCoefficient));
    }

    return this.httpClient.get<PromotionData[]>(dynamicPromotionUrl, {
      params: httpParams
    })
  }

  getPromotionCoefficient(universityName: string, promotionCriterion: string, startDate: number, targetDate: number,
                          promotionStep: number): Observable<number> {
    const promotionCoefficientUrl = 'http://localhost:8081/russian_unis_promotion_QS_backend_war/api/get/promotion_coefficient';
    const httpParams = new HttpParams()
      .set('university_name', universityName)
      .set('promotion_criterion', promotionCriterion)
      .set('start_date', String(startDate))
      .set('target_date', String(targetDate))
      .set('promotion_step', String(promotionStep));
    return this.httpClient.get<number>(promotionCoefficientUrl, {
      params: httpParams
    })
  }

  getNumberOfLaunches(universityName: string, promotionCriterion: string): Observable<number> {
    const numberOfLaunchesUrl = 'http://localhost:8081/russian_unis_promotion_QS_backend_war/api/get/university_promotion_launches';
    const httpParams = new HttpParams()
      .set('university_name', universityName)
      .set('promotion_criterion', promotionCriterion);
    return this.httpClient.get<number>(numberOfLaunchesUrl, {
      params: httpParams
    })
  }

  exportDataIntoHtmlFile(universityName: string, criterionName: string): Observable<any> {
    const exportHtmlUrl = 'http://localhost:8081/russian_unis_promotion_QS_backend_war/export/data/into/html';
    return this.httpClient.post(exportHtmlUrl, {
      universityName: universityName,
      criterionName: criterionName
    })
  }

  exportDataIntoExcelFile(universityName: string, criterionName: string): Observable<any> {
    const exportExcelUrl = 'http://localhost:8081/russian_unis_promotion_QS_backend_war/export/data/into/excel';
    return this.httpClient.post(exportExcelUrl, {
      universityName: universityName,
      criterionName: criterionName
    })
  }

  deletePromotionData(universityName: string, criterionName: string) {
    const deleteDataUrl = 'http://localhost:8081/russian_unis_promotion_QS_backend_war/api/delete/promotion_data';
    const httpParams = new HttpParams()
      .set("university_name", universityName)
      .set("promotion_criterion", criterionName);
    return this.httpClient.delete(deleteDataUrl, {
      params: httpParams
    })
  }
}
