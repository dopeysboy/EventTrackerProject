import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Game } from 'src/app/models/game';
import { GameService } from 'src/app/services/game.service';

@Component({
  selector: 'app-game-list',
  templateUrl: './game-list.component.html',
  styleUrls: ['./game-list.component.css']
})
export class GameListComponent implements OnInit {

  games: Game[] | undefined;
  selected: Game | undefined;
  gameThumbnailUrl : string = '../assets/images/gameThumbnailBig.png';
  editedGame: Game | undefined;

  loadAllGames(): void{
    this.gameService.index().subscribe({
      next: (results)=>{
        this.selected = undefined;
        this.editedGame = undefined;
        this.games = results;
      },
      error: (err) => {
        console.error(err);
        console.error('GameList.loadAllGames(): error retrieving games');
      }
    })
  }

  displaySingleGame(game: Game): void{
    this.selected = game;
    this.games = undefined;
  }

  returnToList(): void{
    this.selected = undefined;
    this.loadAllGames();
  }

  editGame(): void{
    this.editedGame = this.selected;
  }

  submitEditGame(newGame: Game): void{
    if(newGame.id){
      this.gameService.update(newGame.id, newGame).subscribe({
        next: (results) => {
          this.loadAllGames();
        },
        error: (err) => {
          console.error(err);
          console.error('GameListComponent.submitEditGame(): there was an error updating your game');
        }
      });
    }
  }

  destroyGame(game: Game):void{

  }

  constructor(private currentRoute: ActivatedRoute, private gameService: GameService) {

  }

  ngOnInit(): void {
    let input = this.currentRoute.snapshot.paramMap.get('id');
    if(input && !isNaN(Number.parseInt(input))){
      this.gameService.show(Number.parseInt(input)).subscribe({
        next: (results) => {
          this.selected = results;
        },
        error: (err) => {
          console.error(err);
          console.error('GameListComponent.ngOnInit(): error retrieving game');
          this.loadAllGames();
        }
      });
    } else {
      this.loadAllGames();
    }
  }
}
