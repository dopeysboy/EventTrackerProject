import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-navigation',
  templateUrl: './navigation.component.html',
  styleUrls: ['./navigation.component.css']
})
export class NavigationComponent implements OnInit {

  term: string = '';

  searchSite(term:string){
    //TODO: keyword search of games and/or publishers?
  }

  constructor() { }

  ngOnInit(): void {
  }

}
