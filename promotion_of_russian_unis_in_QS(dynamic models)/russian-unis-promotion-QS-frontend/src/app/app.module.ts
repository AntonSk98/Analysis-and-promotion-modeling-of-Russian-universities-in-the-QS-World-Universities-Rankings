import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { MainPageComponent } from './main-page/main-page.component';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';
import { UniversityGeneralInformationComponent } from './university-general-information/university-general-information.component';
import { PageNotFoundComponent } from './page-not-found/page-not-found.component';
import { UniversityClassificationModalComponent } from './university-classification-modal/university-classification-modal.component';
import {BrowserAnimationsModule, NoopAnimationsModule} from '@angular/platform-browser/animations';
import { DynamicModelingComponent } from './dynamic-modeling/dynamic-modeling.component';
import {ChartsModule} from 'ng2-charts';
import {NgbAlertModule, NgbTooltipModule} from '@ng-bootstrap/ng-bootstrap';
import {FormsModule} from '@angular/forms';

@NgModule({
  declarations: [
    AppComponent,
    MainPageComponent,
    UniversityGeneralInformationComponent,
    PageNotFoundComponent,
    UniversityClassificationModalComponent,
    DynamicModelingComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FontAwesomeModule,
    BrowserAnimationsModule,
    ChartsModule,
    NgbTooltipModule,
    FormsModule,
    NgbAlertModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
