import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { QuestionsSetupComponent } from './../config/questions-setup/questions-setup.component';
import { MenuConfigComponent } from './../config/menus/menu-config.component';
import { MenuCategoryComponent } from './../config/menus/menu-category.component';
import { MenuSubcategoryComponent } from './../config/menus/menu-subcategory.component';
import { MenuPollComponent } from './../config/menus/menu-poll.component';
import { MenuBrandComponent } from './../config/menus/menu-brand.component';
import { MenuCQuestionComponent } from './../config/menus/menu-question.component';
import { MenuStudiesComponent } from './../config/menus/menu-studies.component';
import { MenuUsersComponent } from './../config/menus/menu-users.component';
import { SharedModule } from './../shared/shared.module';
import { ConfigRoutingModule } from './config-routing.module';
import { AddBrandComponent } from 'src/app/config/create/addBrand.component';
import { AddCategoryComponent } from 'src/app/config/create/addCategory.component';
import { AddPollComponent } from 'src/app/config/create/addPoll.component';
import { AddQuestionComponent } from 'src/app/config/create/addQuestion.component';
import { AddStudyComponent } from 'src/app/config/create/addStudy.component';
import { AddSubcategoryComponent } from 'src/app/config/create/addSubcategory.component';
import { AddUserComponent } from 'src/app/config/create/addUser.component';
import { GlobalMenuComponent } from './global-menu.component';
import { UpdateBrandComponent } from 'src/app/config/update/updateBrand.component';
import { UpdateCategoryComponent } from 'src/app/config/update/updateCategory.component';
import { UpdatePollComponent } from 'src/app/config/update/updatePoll.component';
import { UpdateQuestionComponent } from 'src/app/config/update/updateQuestion.component';
import { UpdateStudiesComponent } from 'src/app/config/update/updateStudies.component';
import { UpdateSubcategoryComponent } from 'src/app/config/update/updateSubcategory.component';
import { UpdateUserComponent } from 'src/app/config/update/updateUser.component';
import { RequestStudyComponent } from 'src/app/config/menus/menu-requestStudy.component';
import { UpdateClientComponent } from 'src/app/config/update/updateClient.component';
import { UpdateRespondentComponent } from 'src/app/config/update/updateRespondent.component';
import { AssignStudyComponent } from './menus/assign-study/assign-study.component';
import { ProfileComponent } from './profile/profile.component';
import { PasswordProfileComponent } from './profile/password-profile.component';
import { AddPollQuestionComponent } from 'src/app/config/update/AddPollQuestion.component';
import { ResultsComponent } from './menus/results/results.component';

@NgModule({
  declarations: [QuestionsSetupComponent,MenuConfigComponent,MenuCategoryComponent,MenuSubcategoryComponent,
    MenuPollComponent,MenuBrandComponent,MenuCQuestionComponent,MenuStudiesComponent,MenuUsersComponent,
    AddBrandComponent,AddCategoryComponent,AddPollComponent,AddQuestionComponent,AddStudyComponent,AddSubcategoryComponent,
    GlobalMenuComponent,AddUserComponent,UpdateBrandComponent,UpdateCategoryComponent,UpdatePollComponent,UpdateQuestionComponent,
    UpdateStudiesComponent,UpdateSubcategoryComponent,UpdateUserComponent,RequestStudyComponent,UpdateClientComponent,UpdateRespondentComponent
    , AssignStudyComponent, ProfileComponent, PasswordProfileComponent,AddPollQuestionComponent, ResultsComponent

  ],
  imports: [
    ConfigRoutingModule,
    CommonModule,
    SharedModule,
  ]
})
export class ConfigModule { }
