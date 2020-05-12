import {Component, OnInit, TemplateRef, ViewChild} from '@angular/core';
import {ActivatedRoute} from '@angular/router';
import {faArrowCircleLeft} from '@fortawesome/free-solid-svg-icons';
import {faSchool} from '@fortawesome/free-solid-svg-icons';
import {faMoneyCheckAlt} from '@fortawesome/free-solid-svg-icons';
import {faBookReader} from '@fortawesome/free-solid-svg-icons';
import {faLaptop} from '@fortawesome/free-solid-svg-icons';
import {faFan} from '@fortawesome/free-solid-svg-icons';
import {faThumbsUp} from '@fortawesome/free-solid-svg-icons';
import {faCalculator} from '@fortawesome/free-solid-svg-icons';
import {faArrowCircleRight} from '@fortawesome/free-solid-svg-icons';
import {UniversityClassificationModalComponent} from '../university-classification-modal/university-classification-modal.component';
import {animate, state, style, transition, trigger} from '@angular/animations';
import {CriteriaQS} from '../models/criteriaQS';
import {GeneralInformationService} from './service/general-information.service';
import {UniversityCriterion} from '../models/universityCriterion';
import {UniversityClassification} from '../models/universityClassification';

@Component({
  selector: 'app-university-general-information',
  templateUrl: './university-general-information.component.html',
  styleUrls: ['./university-general-information.component.css'],
  animations: [
    trigger('toggleMenu', [
      state('open', style({
        width: '30%',
        opacity: '1'
      })),
      state('closed', style({
        width: '0',
        opacity: '0',
        visibility: 'hidden',
      })),
      transition('open => closed', [
        animate('0.5s')
      ]),
      transition('closed => open', [
        animate('0.5s')
      ]),
    ])
  ]
})
export class UniversityGeneralInformationComponent implements OnInit {

  constructor(
    private route: ActivatedRoute,
    private generalInformationService: GeneralInformationService
  ) { }

  @ViewChild(UniversityClassificationModalComponent) modal: UniversityClassificationModalComponent;

  exitArrow = faArrowCircleLeft;
  academic = faSchool;
  employer = faMoneyCheckAlt;
  faculty = faLaptop;
  citation = faBookReader;
  intFaculty = faThumbsUp;
  intStudent = faFan;
  overall = faCalculator;
  left = faArrowCircleLeft;
  right = faArrowCircleRight;

  pageNumber = 0;

  universityCriteriaLoading = true;
  universityClassificationLoading = true;

  universityCriteriaInfo: UniversityCriterion[];
  universityClassificationInfo: UniversityClassification[];

  universityId: string;
  universityName: string;
  showMenu = true;
  criterionQS = new CriteriaQS();

  ngOnInit(): void {
    this.universityId = this.route.snapshot.paramMap.get('id');
    this.universityName = this.route.snapshot.paramMap.get('university');

    this.generalInformationService.getUniversityClassificationInfo(Number(this.universityId)).subscribe((classifications: UniversityClassification[]) => {
      setTimeout(() => {
        this.universityClassificationInfo = classifications;
        this.universityClassificationLoading = false;
      }, 500)
    });

    this.generalInformationService.getUniversityCriteriaInfo(Number(this.universityId)).subscribe((criteria: UniversityCriterion[]) => {
      setTimeout(() => {
        this.universityCriteriaInfo = criteria;
        this.universityCriteriaLoading = false;
      }, 500);
    });
  }

  toggleMenu() {
    this.showMenu = !this.showMenu;
  }

  nextTable() {
    this.pageNumber % 2 === 0 ? this.pageNumber++ : this.pageNumber --;
  }


  openModal(classification: string) {
    this.modal.openModal(classification);
  }
}
