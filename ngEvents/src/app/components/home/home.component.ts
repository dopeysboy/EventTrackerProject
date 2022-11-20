import { Component, OnInit } from '@angular/core';
import { Game } from 'src/app/models/game';
import { GameService } from 'src/app/services/game.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  games: Game[] = [];

  loadAllGames(){
    this.gameService.index().subscribe({
      next: (results)=>{
        this.games = results;
      },
      error: (err) => {
        console.error(err);
        console.error('COMPONENT.loadAllGames(): error retrieving games');
      }
    })
  }

  constructor(private gameService: GameService) { }

  ngOnInit(): void {
    this.loadAllGames();
  }

}
