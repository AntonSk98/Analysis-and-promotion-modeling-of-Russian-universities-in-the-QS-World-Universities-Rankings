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
    private route: ActivatedRoute
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

  tableInfo = [];
  uniParams = [];

  universityId: string;
  universityName: string;
  showMenu = true;
  criterionQS = new CriteriaQS();

  ngOnInit(): void {
    this.universityId = this.route.snapshot.paramMap.get('id');
    this.universityName = this.route.snapshot.paramMap.get('university');
    setTimeout(() => {
      this.universityClassificationLoading = false;
      this.uniParams = [
        {
          classification: 'Size Classification',
          type: 'M'
        },
        {
          classification: 'Subject Range Classification',
          type: 'CO'
        },
        {
          classification: 'Research Intensity Classification',
          type: 'VH'
        },
        {
          classification: 'Age Classification',
          type: '5'
        }
      ];
    }, 6000);
    setTimeout(() => {
      this.universityCriteriaLoading = false;
      this.tableInfo = [
        {
          criterion: 'Academic reputation',
          year2019: 62.8,
          year2020: 60.4
        },
        {
          criterion: 'Employer reputation',
          year2019: 62.8,
          year2020: 60.4
        },
        {
          criterion: 'Faculty/Student Ratio',
          year2019: 62.8,
          year2020: 60.4
        },
        {
          criterion: 'Citations per faculty',
          year2019: 62.8,
          year2020: 60.4
        },
        {
          criterion: 'International faculty ratio',
          year2019: 62.8,
          year2020: 60.4
        },
        {
          criterion: 'International student ratio',
          year2019: 62.8,
          year2020: 60.4
        },
        {
          criterion: 'Overall Score',
          year2019: 62.8,
          year2020: 60.4
        }
      ];
    }, 3000);
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
