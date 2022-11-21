import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { isEmpty } from 'rxjs';
import { Game } from 'src/app/models/game';
import { GameService } from 'src/app/services/game.service';

@Component({
  selector: 'app-search-results',
  templateUrl: './search-results.component.html',
  styleUrls: ['./search-results.component.css']
})
export class SearchResultsComponent implements OnInit {

  term: string | null = null;
  results: Game[] | undefined;

  keywordSearch(term: string): void {
    this.gameService.getGameKeyword(term).subscribe({
      next: (results) => {
        if(results.length != 0){
          this.results = results;
        }
      },
      error: (err) => {
        console.error(err);
        console.error('SearchResultsComponent.keywordSearch(): error retrieving games');
      }
    });
  }

  displaySingleGame(game: Game): void{
    let id = game.id;
    this.router.navigateByUrl(`games/${id}`);
  }

  showAll(): void {
    this.router.navigateByUrl('games');
  }

  constructor(private route: ActivatedRoute, private router: Router, private gameService: GameService) { }

  ngOnInit(): void {

    this.term = this.route.snapshot.paramMap.get('term');
    if(this.term){
      this.keywordSearch(this.term);
    } else {
      this.router.navigateByUrl('noSearchTermError');
    }
  }

}
