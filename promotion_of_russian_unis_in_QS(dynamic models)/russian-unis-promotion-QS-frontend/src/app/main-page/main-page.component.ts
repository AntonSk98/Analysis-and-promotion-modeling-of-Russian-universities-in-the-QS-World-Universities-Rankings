import { Component, OnInit } from '@angular/core';
import {faUniversity} from '@fortawesome/free-solid-svg-icons';
import {MainPageService} from './service/main-page.service';
import {TopRussianUnisInQS} from '../models/topRussianUnisInQS';

@Component({
  selector: 'app-main-page',
  templateUrl: './main-page.component.html',
  styleUrls: ['./main-page.component.css']
})
export class MainPageComponent implements OnInit {
  rightArrow = faUniversity;
  topRussianUniversitiesInQS: TopRussianUnisInQS[];
  isLoading = true;

  constructor(
    private mainPageService: MainPageService
  ) { }

  ngOnInit(): void {
    this.mainPageService.getAllTopRussianUniversitiesInQS().subscribe((topRussianUnis: TopRussianUnisInQS[]) => {
      setTimeout(() => {
        this.isLoading = false;
        this.topRussianUniversitiesInQS = topRussianUnis;
      }, 500);
    });
  }

}
