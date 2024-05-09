import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { TabsModule } from 'ngx-bootstrap/tabs';
import { BsDropdownModule } from 'ngx-bootstrap/dropdown';
import { ModalModule } from 'ngx-bootstrap/modal';
import { TooltipModule } from 'ngx-bootstrap/tooltip';
import { CollapseModule } from 'ngx-bootstrap/collapse';

import { NgApexchartsModule } from 'ng-apexcharts';
import { FullCalendarModule } from '@fullcalendar/angular';
import { SimplebarAngularModule } from 'simplebar-angular';
import { LightboxModule } from 'ngx-lightbox';

import { WidgetModule } from '../shared/widget/widget.module';
import { UIModule } from '../shared/ui/ui.module';

// Emoji Picker
import { PickerModule } from '@ctrl/ngx-emoji-mart';

import { PagesRoutingModule } from '../pages/pages-routing.module';

import { DashboardsModule } from '../pages/dashboards/dashboards.module';
import { EcommerceModule } from '../pages/ecommerce/ecommerce.module';
import { CryptoModule } from '../pages/crypto/crypto.module';
import { EmailModule } from '../pages/email/email.module';
import { InvoicesModule } from '../pages/invoices/invoices.module';
import { ProjectsModule } from '../pages/projects/projects.module';
import { TasksModule } from '../pages/tasks/tasks.module';
import { ContactsModule } from '../pages/contacts/contacts.module';
import { BlogModule } from "../pages/blog/blog.module";
import { UtilityModule } from '../pages/utility/utility.module';
import { UiModule } from '../pages/ui/ui.module';
import { FormModule } from '../pages/form/form.module';
import { TablesModule } from '../pages/tables/tables.module';
import { IconsModule } from '../pages/icons/icons.module';
import { ChartModule } from '../pages/chart/chart.module';
import { CalendarComponent } from '../pages/calendar/calendar.component';
import { MapsModule } from '../pages/maps/maps.module';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { ChatComponent } from '../pages/chat/chat.component';
import { FilemanagerComponent } from '../pages/filemanager/filemanager.component';

@NgModule({
  declarations: [CalendarComponent, ChatComponent, FilemanagerComponent],
  imports: [
    CommonModule,
    FormsModule,
    BsDropdownModule.forRoot(),
    ModalModule.forRoot(),
    PagesRoutingModule,
    NgApexchartsModule,
    ReactiveFormsModule,
    DashboardsModule,
    CryptoModule,
    EcommerceModule,
    EmailModule,
    InvoicesModule,
    HttpClientModule,
    ProjectsModule,
    UIModule,
    TasksModule,
    ContactsModule,
    BlogModule,
    UtilityModule,
    UiModule,
    FormModule,
    TablesModule,
    IconsModule,
    ChartModule,
    WidgetModule,
    MapsModule,
    FullCalendarModule,
    TabsModule.forRoot(),
    TooltipModule.forRoot(),
    CollapseModule.forRoot(),
    SimplebarAngularModule,
    LightboxModule,
    PickerModule,
    ReactiveFormsModule
  ],
})
export class DashResponsableModule { }
