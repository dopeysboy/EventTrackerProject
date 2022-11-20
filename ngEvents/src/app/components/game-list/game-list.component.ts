import { Component, OnInit } from '@angular/core';
import { Game } from 'src/app/models/game';
import { GameService } from 'src/app/services/game.service';

@Component({
  selector: 'app-game-list',
  templateUrl: './game-list.component.html',
  styleUrls: ['./game-list.component.css']
})
export class GameListComponent implements OnInit {

  games: Game[] = [];

  loadAllGames(){
    this.gameService.index().subscribe({
      next: (results)=>{
        this.games = results;
      },
      error: (err) => {
        console.error(err);
        console.error('GameList.loadAllGames(): error retrieving games');
      }
    })
  }

  constructor(private gameService: GameService) { }

  ngOnInit(): void {
    this.loadAllGames();
  }
}
