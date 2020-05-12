import {Component, Input, OnChanges, OnInit, ViewChild} from '@angular/core';
import {ActivatedRoute} from '@angular/router';
import {faArrowCircleLeft, faEdit, faCalculator, faFileExcel, faFileCsv, faFileDownload, faTrash} from '@fortawesome/free-solid-svg-icons';
import {ChartDataSets} from 'chart.js';
import {Color, Label} from 'ng2-charts';
import {ModelingPromotionService} from './service/modeling-promotion.service';
import {PromotionData} from '../models/promotionData';
import {NotificationUtilsComponent} from '../notification-utils/notification-utils.component';
import {CriteriaQS} from '../models/criteriaQS';


@Component({
  selector: 'app-dynamic-modeling',
  templateUrl: './dynamic-modeling.component.html',
  styleUrls: ['./dynamic-modeling.component.css']
})
export class DynamicModelingComponent implements OnInit {

  @ViewChild(NotificationUtilsComponent) notifications: NotificationUtilsComponent;

  constructor(
    private route: ActivatedRoute,
    private modelingPromotionService: ModelingPromotionService
  ) {
  }

  universityPromotion = new UniversityPromotion();
  exitArrow = faArrowCircleLeft;
  edit = faEdit;
  documentHtml = faFileDownload;
  documentExcel = faFileExcel;
  calculate = faCalculator;
  clear = faTrash;
  promotionData: PromotionData[];
  isLoading = true;
  lineChart = new LineChart();
  controlPanelData = new ControlPanelData();
  tableParams = new TableParams();
  criteriaQs = new CriteriaQS();

  promotionCoefficient: number;
  isAdvancedModeActive = false;
  numberOfLaunches: number;
  isHTMLExporting = false;
  isExcelExporting = false;
  isPromotionDeleting = false;


  ngOnInit(): void {
    this.getParams();
    this.calculateDefaultPromotion();
  }


  get promotionDataSlice(): PromotionData[] {
    return this.promotionData
      .slice((this.tableParams.page - 1) * this.tableParams.pageSize,
        (this.tableParams.page - 1) * this.tableParams.pageSize + this.tableParams.pageSize);
  }


  getParams() {
    this.universityPromotion.id = this.route.snapshot.paramMap.get('id');
    this.universityPromotion.university = this.route.snapshot.paramMap.get('university');
    this.universityPromotion.criterionName = this.route.snapshot.paramMap.get('modelingType');
  }

  calculateDefaultPromotion() {
    const uniName = this.universityPromotion.university;
    const criterionName = this.universityPromotion.criterionName;
    const startDate = 2020;
    const targetDate = 2023;
    const promotionStep = 0.125;
    this.launchPromotion(uniName, criterionName, startDate, targetDate, promotionStep);
  }

  launchPromotion(uniName: string, criterionName: string, startDate: number, targetDate: number, promotionStep: number, promotionCoefficient?: number) {
    this.modelingPromotionService.calculatePromotionData(uniName, criterionName, startDate, targetDate, promotionStep, promotionCoefficient)
      .subscribe((promotionData: PromotionData[]) => {
        if (!promotionCoefficient) {
          this.modelingPromotionService.getPromotionCoefficient(uniName, criterionName, startDate, targetDate, promotionStep)
            .subscribe((promotionCoefficient: number) => {
              this.promotionCoefficient = promotionCoefficient;
              this.controlPanelData.setPromotionCoefficient(promotionCoefficient);
              this.controlPanelData.setPromotionCoefficientList(promotionCoefficient);
            }, () => {
              this.notifications.errorMessage();
            });
        }
        this.modelingPromotionService.getNumberOfLaunches(uniName, criterionName)
          .subscribe((launchesNumber: number) => {
            setTimeout(() => {
              this.numberOfLaunches = launchesNumber;
              this.promotionData = promotionData;
              this.tableParams.defineTablePaginationParams(1, 15, promotionData.length);
              this.lineChart.lineChartData = this.lineChart.setLineChartData(this.filterDataForLineChart(promotionData), this.universityPromotion.criterionName);
              this.lineChart.lineChartLabels = this.lineChart.setLineChartLabels(this.filterDataForLineChart(promotionData));
              this.isLoading = false;
              this.notifications.successMessage(
                'Dynamic modeling',
                'Promotion has been calculated'
              );
            }, 500);
          }, () => {
            this.notifications.errorMessage();
          });
      }, () => {
        this.notifications.errorMessage();
      });
  }

  exportDataIntoHtmlFile() {
    this.isHTMLExporting = true;
    this.modelingPromotionService.exportDataIntoHtmlFile(this.universityPromotion.university, this.universityPromotion.criterionName)
      .subscribe(() => {
        this.isHTMLExporting = false;
        this.notifications.successMessage(
          'Successful html export',
          'The file is in Downloads'
        );
      }, () => {
        this.isHTMLExporting = false;
        this.notifications.errorMessage();
      });
  }

  exportDataIntoExcelFile() {
    this.isExcelExporting = true;
    this.modelingPromotionService.exportDataIntoExcelFile(this.universityPromotion.university, this.universityPromotion.criterionName)
      .subscribe(() => {
        this.isExcelExporting = false;
        this.notifications.successMessage(
          'Successful excel export',
          'The file is in Downloads'
        );
      }, () => {
        this.notifications.errorMessage();
        this.isExcelExporting = false;
      });
  }

  deletePromotionData() {
    this.isPromotionDeleting = true;
    this.modelingPromotionService.deletePromotionData(this.universityPromotion.university, this.universityPromotion.criterionName)
      .subscribe(() => {
        this.notifications.successMessage(
          'Bye-bye my models...',
          'All models are deleted'
        );
        setTimeout(() => location.reload(), 5000);
      }, () => {
        this.notifications.errorMessage();
      });
  }

  filterDataForLineChart(promotionData: PromotionData[]): PromotionData[] {
    return promotionData.filter(value => {
      if (value.year % 0.125 === 0) {
        return value;
      }
    });
  }

  runCustomizedDynamicModeling() {
    this.notifications.clearAll();
    this.isLoading = true;
    this.launchPromotion(
      this.universityPromotion.university,
      this.universityPromotion.criterionName,
      2020,
      this.controlPanelData.promotionDateButtonSelected,
      this.controlPanelData.promotionStepButtonSelected,
      this.promotionCoefficient === this.controlPanelData.promotionCoefficient ? null : this.controlPanelData.promotionCoefficient
    );
  }

  resetPromotionCoefficient() {
    this.controlPanelData.setPromotionCoefficient(this.promotionCoefficient);
  }
}

class UniversityPromotion {
  id: string;
  university: string;
  criterionName: string;
}

class ControlPanelData {
  promotionDateButtonSelected = 2023;
  targetDates = [2023, 2024, 2025, 2026, 2027, 2028, 2029, 2030];
  promotionStepButtonSelected = 0.125;
  promotionSteps = [0.0625, 0.125, 0.25, 0.5, 1];
  promotionCoefficient: number;
  promotionCoefficientList = [];

  setPromotionCoefficientList(autoCalculatedCoefficient: number) {
    this.promotionCoefficientList = [];
    let minimalValue = autoCalculatedCoefficient - 0.05;
    if (minimalValue < 0) {
      minimalValue = 0;
    }

    for (let i = 0; i <= 0.1; i += 0.01) {
      this.promotionCoefficientList.push((minimalValue + i).toFixed(2));
    }
  }

  setPromotionDateButtonSelected(value: number) {
    this.promotionDateButtonSelected = value;
  }

  setPromotionStepButtonSelected(value: number) {
    this.promotionStepButtonSelected = value;
  }

  setPromotionCoefficient(coefficient: number) {
    this.promotionCoefficient = coefficient;
  }
}

class TableParams {
  page: number;
  pageSize: number;
  collectionSize: number;

  defineTablePaginationParams(page: number, pageSize: number, collectionSize: number) {
    this.page = page;
    this.pageSize = pageSize;
    this.collectionSize = collectionSize;
  }
}

class LineChart {
  lineChartData: ChartDataSets[] = [
    {data: [], label: ''}];

  lineChartLabels: Label[];

  lineChartColors: Color[] = [
    {
      borderColor: 'black',
      backgroundColor: '#F4B300'
    },
  ];

  setLineChartData(promotionData: PromotionData[], criterionName: string): ChartDataSets[] {
    return [
      {
        data: promotionData.map(value => value.promotionValue),
        label: `${criterionName} promotion`
      },
    ];
  }

  setLineChartLabels(promotionData: PromotionData[]): Label[] {
    return promotionData.map((value: PromotionData) => {
      return String(value.year);
    });
  }
}

