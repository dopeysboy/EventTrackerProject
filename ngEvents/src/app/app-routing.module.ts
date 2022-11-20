import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { GameListComponent } from './components/game-list/game-list.component';
import { HomeComponent } from './components/home/home.component';
import { NotFoundComponent } from './components/not-found/not-found.component';
import { PublishersPageComponent } from './components/publishers-page/publishers-page.component';
import { SalePageComponent } from './components/sale-page/sale-page.component';
import { SearchResultsComponent } from './components/search-results/search-results.component';
import { SearchComponent } from './components/search/search.component';

const routes: Routes = [
  {path: '', pathMatch: 'full', component: HomeComponent},
  {path: 'home', component: HomeComponent},
  {path: 'games', component: GameListComponent},
  {path: 'games/:id', component: GameListComponent},
  {path: 'sales', component: SalePageComponent},
  {path: 'sales/:id', component: SalePageComponent},
  {path: 'publishers', component: PublishersPageComponent},
  {path: 'publishers/:id', component: PublishersPageComponent},
  {path: 'search', component: SearchComponent},
  {path: 'search/:keyword', component: SearchResultsComponent},
  {path: '**', component: NotFoundComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes, {useHash: true})],
  exports: [RouterModule]
})
export class AppRoutingModule { }
