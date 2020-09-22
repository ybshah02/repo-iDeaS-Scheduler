import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { AppComponent } from './app.component';
import { ResourceService } from './resource-service.service';
import { DashboardComponent } from './dashboard/dashboard.component';
import { RegionService } from './region-service.service';
import { PortalComponent } from './portal/portal.component';
import { LearningGroupService } from './learning-group.service';
import { LgFormComponent } from './lg-form/lg-form.component';
import { LgModifyFormComponent } from './lg-modify-form/lg-modify-form.component';
import { LearningSessionService } from './learning-session.service';
import { LsFormComponent } from './ls-form/ls-form.component';
import { LsModifyFormComponent } from './ls-modify-form/ls-modify-form.component';
import { RegionDisplayComponent } from './region-display/region-display.component';
import { RegionFormComponent } from './region-form/region-form.component';
import { RegionModifyFormComponent } from './region-modify-form/region-modify-form.component';
import { ClientFormComponent } from './client-form/client-form.component';
import { ClientModifyFormComponent } from './client-modify-form/client-modify-form.component';
import { ClientDisplayComponent } from './client-display/client-display.component';
import { ResourcesDisplayComponent } from './resources-display/resources-display.component';
import { ClientService } from './client-service.service';
import { ResourcesFormComponent } from './resources-form/resources-form.component';
import { ResourcesModifyFormComponent } from './resources-modify-form/resources-modify-form.component';
import { ResourceLanguageService } from './resource-language-service.service';
import { CapabilitiesDisplayComponent } from './capabilities-display/capabilities-display.component';
import { CapabilitiesService } from './capabilities.service';
import { ServicesDisplayComponent } from './services-display/services-display.component';
import { ActivatedResourceService } from './activated-resource-service.service';
import { ActivateResourceFormComponent } from './activate-resource-form/activate-resource-form.component';
import { CalendarComponent } from './calendar/calendar.component';
import { CalendarModule, DateAdapter } from 'angular-calendar';
import { adapterFactory } from 'angular-calendar/date-adapters/date-fns';
import { FlatpickrModule } from 'angularx-flatpickr';
import { NgbModalModule, NgbModule } from '@ng-bootstrap/ng-bootstrap';

@NgModule({
  declarations: [
    AppComponent,
    DashboardComponent,
    PortalComponent,
    LgFormComponent,
    LgModifyFormComponent,
    LsFormComponent,
    LsModifyFormComponent,
    RegionDisplayComponent,
    RegionFormComponent,
    RegionModifyFormComponent,
    ClientFormComponent,
    ClientModifyFormComponent,
    ClientDisplayComponent,
    ResourcesDisplayComponent,
    ResourcesFormComponent,
    ResourcesModifyFormComponent,
    CapabilitiesDisplayComponent,
    ServicesDisplayComponent,
    ActivateResourceFormComponent,
    CalendarComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule,
    NgbModalModule,
    FlatpickrModule.forRoot(),
    CalendarModule.forRoot({
      provide: DateAdapter,
      useFactory: adapterFactory,
    }),
    NgbModule,
  ],
  providers: [ResourceService, ResourceLanguageService, RegionService, LearningGroupService, LearningSessionService,
    RegionService, ClientService, CapabilitiesService, ActivatedResourceService],
  bootstrap: [AppComponent]
})
export class AppModule { }
