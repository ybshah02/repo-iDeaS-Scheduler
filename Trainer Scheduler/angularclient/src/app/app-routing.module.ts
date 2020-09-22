import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {DashboardComponent} from './dashboard/dashboard.component';
import {PortalComponent} from './portal/portal.component';
import {RegionDisplayComponent} from './region-display/region-display.component';
import {ClientDisplayComponent} from './client-display/client-display.component';
import {ResourcesDisplayComponent} from './resources-display/resources-display.component';
import {ServicesDisplayComponent} from './services-display/services-display.component';
import {CalendarComponent} from './calendar/calendar.component';

const routes: Routes = [
  {path: '', redirectTo: 'portal', pathMatch: 'full'},
  {path: 'portal', component: PortalComponent},
  {path: 'dashboard', component: DashboardComponent},
  {path: 'services', component: ServicesDisplayComponent},
  {path: 'regions', component: RegionDisplayComponent},
  {path: 'resources', component: ResourcesDisplayComponent},
  {path: 'clients', component: ClientDisplayComponent},
  {path: 'calendar', component: CalendarComponent}

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
