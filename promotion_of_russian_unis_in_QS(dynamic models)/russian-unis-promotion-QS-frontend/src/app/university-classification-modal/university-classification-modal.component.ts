import {Component, Input, OnInit, TemplateRef, ViewChild} from '@angular/core';
import {NgbModal, NgbModalConfig} from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'app-university-classification-modal',
  templateUrl: './university-classification-modal.component.html',
  styleUrls: ['./university-classification-modal.component.css']
})
export class UniversityClassificationModalComponent implements OnInit {
  @ViewChild('size') sizeModal: TemplateRef<any>;
  @ViewChild('subjectRange') subjectRangeModal: TemplateRef<any>;
  @ViewChild('age') ageModal: TemplateRef<any>;
  @ViewChild('researchIntensity') researchIntensityModal: TemplateRef<any>;
  classificationName: string;
  classificationSize = [
    {
      type: 'XL',
      size: 'Extra Large',
      students: 'More than 30.000'
    },
    {
      type: 'L',
      size: 'Large',
      students: 'More than 12.000'
    },
    {
      type: 'M',
      size: 'Medium',
      students: 'More than 5.000'
    },
    {
      type: 'S',
      size: 'Small',
      students: 'Fewer than 5.000'
    }
  ];
  classificationSubjectRange = [
    {
      type: 'FC',
      focus: 'Full comprehensive',
      facultyArea: 'All faculty areas + medicine'
    },
    {
      type: 'CO',
      focus: 'Comprehensive',
      facultyArea: 'All 5 faculty areas'
    },
    {
      type: 'FO',
      focus: 'Focused',
      facultyArea: 'More than 2 faculties'
    },
    {
      type: 'SP',
      focus: 'Specialist',
      facultyArea: '2 or 1 faculty areas'
    }
  ];
  classificationAge = [
    {
      type: 5,
      classification: 'Historic',
      age: 'More than 100 years old'
    },
    {
      type: 4,
      classification: 'Mature',
      age: '50 - 100 years old'
    },
    {
      type: 3,
      classification: 'Established',
      age: '25 - 50 years old'
    },
    {
      type: 2,
      classification: 'Young',
      age: '10 - 25 years old'
    },
    {
      type: 1,
      classification: 'New',
      age: 'Less than 10 years old'
    },
  ];

  classificationResearchIntensity = [
    {
      type: 'VH',
      researchIntensity: 'Very High'
    },
    {
      type: 'HI',
      researchIntensity: 'High'
    },
    {
      type: 'MD',
      researchIntensity: 'Medium'
    },
    {
      type: 'LO',
      researchIntensity: 'Low'
    }
  ];
  constructor(
    config: NgbModalConfig,
    private modalService: NgbModal
  ) { }

  ngOnInit(): void {
  }



  openModal(classification: string) {
    this.classificationName = classification;
    this.modalService.open(this.modelTypeResolver(classification), {size: 'xl', centered: true});
  }

  private modelTypeResolver(classificationName: string) {
    switch (classificationName) {
      case 'Size Classification': {
        return this.sizeModal;
      }
      case 'Subject Range Classification': {
        return this.subjectRangeModal;
      }
      case 'Age Classification': {
        return this.ageModal;
      }
      case 'Research Intensity Classification': {
        return this.researchIntensityModal;
      }
    }
  }

}
