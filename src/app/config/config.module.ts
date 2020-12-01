import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { QuestionsSetupComponent } from './../config/questions-setup/questions-setup.component';
import { MenuConfigComponent } from './../config/menus/menu-config.component';
import { MenuCategoryComponent } from './../config/menus/menu-category.component';
import { MenuSubcategoryComponent } from './../config/menus/menu-subcategory.component';
import { MenuPollComponent } from './../config/menus/menu-poll.component';
import { MenuBrandComponent } from './../config/menus/menu-brand.component';
import { MenuTypesComponent } from './../config/menus/menu-types.component';
import { MenuPresentationComponent } from './../config/menus/menu-presentation.component';
import { MenuCQuestionComponent } from './../config/menus/menu-question.component';
import { MenuStudiesComponent } from './../config/menus/menu-studies.component';
import { MenuUsersComponent } from './../config/menus/menu-users.component';
import { SharedModule } from './../shared/shared.module';
import { ConfigRoutingModule } from './config-routing.module';

@NgModule({
  declarations: [QuestionsSetupComponent,MenuConfigComponent,MenuCategoryComponent,MenuSubcategoryComponent,
    MenuPollComponent,MenuBrandComponent,MenuTypesComponent,MenuPresentationComponent,MenuCQuestionComponent,
    MenuStudiesComponent,MenuUsersComponent
  ],
  imports: [
    ConfigRoutingModule,
    CommonModule,
    SharedModule
  ]
})
export class ConfigModule { }
