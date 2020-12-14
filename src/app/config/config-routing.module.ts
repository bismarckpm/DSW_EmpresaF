import { PasswordProfileComponent } from './profile/password-profile.component';
import { ProfileComponent } from './profile/profile.component';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Routes, RouterModule } from '@angular/router';
import { QuestionsSetupComponent } from './../config/questions-setup/questions-setup.component';
import { MenuConfigComponent } from './../config/menus/menu-config.component';
import { MenuCategoryComponent } from './../config/menus/menu-category.component';
import { MenuSubcategoryComponent } from './../config/menus/menu-subcategory.component';
import { MenuPollComponent } from './../config/menus/menu-poll.component';
import { MenuBrandComponent } from './../config/menus/menu-brand.component';
import { MenuCQuestionComponent } from './../config/menus/menu-question.component';
import { MenuStudiesComponent } from './../config/menus/menu-studies.component';
import { MenuUsersComponent } from './../config/menus/menu-users.component';
import { GlobalMenuComponent } from '././global-menu.component';
import { AddBrandComponent } from 'src/app/config/create/addBrand.component';
import { AddCategoryComponent } from 'src/app/config/create/addCategory.component';
import { AddSubcategoryComponent } from 'src/app/config/create/addSubcategory.component';
import { AddPollComponent } from 'src/app/config/create/addPoll.component';
import { AddQuestionComponent } from 'src/app/config/create/addQuestion.component';
import { AddUserComponent } from 'src/app/config/create/addUser.component';
import { UpdateBrandComponent } from 'src/app/config/update/updateBrand.component';
import { UpdateCategoryComponent } from 'src/app/config/update/updateCategory.component';
import { UpdatePollComponent } from 'src/app/config/update/updatePoll.component';
import { UpdateQuestionComponent } from 'src/app/config/update/updateQuestion.component';
import { UpdateStudiesComponent } from 'src/app/config/update/updateStudies.component';
import { UpdateSubcategoryComponent } from 'src/app/config/update/updateSubcategory.component';
import { UpdateUserComponent } from 'src/app/config/update/updateUser.component';

const routes: Routes = [
  {
    path: "",
    component:GlobalMenuComponent,
    children: [
    { path: "question", component: QuestionsSetupComponent},
    { path: "menuconfig", component: MenuConfigComponent},
    { path: "menucategory", component: MenuCategoryComponent},
    { path: "menusubcategory", component: MenuSubcategoryComponent},
    { path: "menupoll", component: MenuPollComponent},
    { path: "menubrand", component: MenuBrandComponent},
    { path: "menuquestion", component: MenuCQuestionComponent},
    { path: "menustudies", component: MenuStudiesComponent},
    { path: "menuusers", component: MenuUsersComponent},
    { path: "addBrand", component: AddBrandComponent},
    { path: "addCategory", component: AddCategoryComponent},
    { path: "addSubCategory", component: AddSubcategoryComponent},
    { path: "addPoll", component: AddPollComponent},
    { path: "addQuestion", component: AddQuestionComponent},
    { path: "addUser", component: AddUserComponent},
    { path: "updateBrand", component: UpdateBrandComponent},
    { path: "updateCategory", component: UpdateCategoryComponent},
    { path: "updatePoll", component: UpdatePollComponent},
    { path: "updateQuestion", component: UpdateQuestionComponent},
    { path: "updateStudies", component: UpdateStudiesComponent},
    { path: "updateSubcategory", component: UpdateSubcategoryComponent},
    { path: "updateUser", component: UpdateUserComponent},
    { path: "profile", component: ProfileComponent},
    { path: "passwordProfile", component: PasswordProfileComponent}
    ],
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ConfigRoutingModule { }
