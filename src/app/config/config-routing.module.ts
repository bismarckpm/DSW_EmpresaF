import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Routes, RouterModule } from '@angular/router';
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
import { GlobalMenuComponent } from '././global-menu.component';
import { AddBrandComponent } from 'src/app/config/create/addBrand.component';
import { AddCategoryComponent } from 'src/app/config/create/addCategory.component';
import { AddSubcategoryComponent } from 'src/app/config/create/addSubcategory.component';
import { AddPollComponent } from 'src/app/config/create/addPoll.component';
import { AddQuestionComponent } from 'src/app/config/create/addQuestion.component'
import { ProfileComponent } from './profile/profile.component';
import { PasswordProfileComponent } from './profile/password-profile.component';

const routes: Routes = [
  {
    path: "",
    component:GlobalMenuComponent,
    children: [
    { path: "", component: QuestionsSetupComponent},
    { path: "menuconfig", component: MenuConfigComponent},
    { path: "menucategory", component: MenuCategoryComponent},
    { path: "menusubcategory", component: MenuSubcategoryComponent},
    { path: "menupoll", component: MenuPollComponent},
    { path: "menubrand", component: MenuBrandComponent},
    { path: "menutypes", component: MenuTypesComponent},
    { path: "menupresentation", component: MenuPresentationComponent},
    { path: "menuquestion", component: MenuCQuestionComponent},
    { path: "menustudies", component: MenuStudiesComponent},
    { path: "menuusers", component: MenuUsersComponent},
    { path: "addBrand", component: AddBrandComponent},
    { path: "addCategory", component: AddCategoryComponent},
    { path: "addSubCategory", component: AddSubcategoryComponent},
    { path: "addPoll", component: AddPollComponent},
    { path: "addQuestion", component: AddQuestionComponent},
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
