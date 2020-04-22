import {Component, Input, OnChanges, OnInit, ViewChild} from '@angular/core';
import {ActivatedRoute} from '@angular/router';
import {faArrowCircleLeft, faEdit, faCalculator} from '@fortawesome/free-solid-svg-icons';
import {ChartDataSets} from 'chart.js';
import {Color, Label} from 'ng2-charts';


@Component({
  selector: 'app-dynamic-modeling',
  templateUrl: './dynamic-modeling.component.html',
  styleUrls: ['./dynamic-modeling.component.css']
})
export class DynamicModelingComponent implements OnInit {
  constructor(
    private route: ActivatedRoute
  ) { }

  universityPromotion = new UniversityPromotion();
  exitArrow = faArrowCircleLeft;
  edit = faEdit;
  apply = faCalculator;
  tableData: TableData[];
  isLoading = true;
  promotionCoefficient: number;
  userPromotionCoefficient: number;
  test = new LineChart();
  coefficientEdited = false;
  showWarningMessage = true;
  tooltipMsg = 'To calculate promotion manually, please click this button and set a promotion coefficient in the range of 0 to 1';
  ngOnInit(): void {
    this.universityPromotion.id  = this.route.snapshot.paramMap.get('id');
    this.universityPromotion.university = this.route.snapshot.paramMap.get('university');
    this.universityPromotion.dynamicType = this.route.snapshot.paramMap.get('modelingType');
    this.promotionCoefficient = 0.34;
    this.userPromotionCoefficient = 0.34;
    setTimeout(() => {
      this.isLoading = false;
      this.tableData = [
        {
          date: '2020',
          value: 68.25
        },
        {
          date: '2020.50',
          value: 68.275
        },
        {
          date: '2020.5',
          value: 73.6
        },
        {
          date: '2020.75',
          value: 78.19
        },
        {
          date: '2021',
          value: 79
        },
        {
          date: '2021.25',
          value: 80.32
        },
        {
          date: '2021.5',
          value: 80.98
        },
        {
          date: '2021.75',
          value: 81.64
        },
        {
          date: '2022',
          value: 82.9
        },
        {
          date: '2022.25',
          value: 84.99
        },
        {
          date: '2022.5',
          value: 86.75
        },
        {
          date: '2022.75',
          value: 96.99
        },
        {
          date: '2023',
          value: 100
        }
      ];
      this.test.lineChartData  = [
        { data: this.tableData.map(value => value.value), label: `${this.universityPromotion.dynamicType} promotion` },
      ];
      this.test.lineChartLabels = this.tableData.map(value => value.date);
    }, 5000);
  }

  onClickEdited() {
    this.coefficientEdited = !this.coefficientEdited;
  }

  onClickRecalculate() {
    this.coefficientEdited = !this.coefficientEdited;
    this.showWarningMessage = true;
    console.log(this.userPromotionCoefficient);
  }

  closeWarningMessage() {
    this.showWarningMessage = false;
  }
}

class UniversityPromotion {
  id: string;
  university: string;
  dynamicType: string;
}

class TableData {
  date: string;
  value: number;
}

class LineChart {
  lineChartData: ChartDataSets[] = [
    { data: [], label: '' }];

  lineChartLabels: Label[];

  lineChartColors: Color[] = [
    {
      borderColor: 'black',
      backgroundColor: '#F4B300'
    },
  ];
}


