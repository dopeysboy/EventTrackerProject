import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-navigation',
  templateUrl: './navigation.component.html',
  styleUrls: ['./navigation.component.css']
})
export class NavigationComponent implements OnInit {

  term: string = '';

  searchSite(term:string){
    //TODO: keyword search of games and/or publishers?
    this.router.navigateByUrl(`search/${term}`);
  }

  constructor(private router: Router) { }

  ngOnInit(): void {
  }

}
