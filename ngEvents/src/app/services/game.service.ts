import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { catchError, Observable, throwError } from 'rxjs';
import { Game } from '../models/game';

@Injectable({
  providedIn: 'root'
})
export class GameService {
  baseUrl = 'http://localhost:8084/';
  url = this.baseUrl + 'api/games';

  index(): Observable<Game[]> {
    return this.http.get<Game[]>(this.url).pipe(
      catchError( (err: any) => {
        console.log(err);
        return throwError(
          () => new Error('GameService.index(): error retrieving games')
        )
      })
    );
  }

  create(game: Game): Observable<Game>{
    return this.http.post<Game>(this.url, game).pipe(
      catchError( (err: any) => {
        console.error(err);
        return throwError(
          () => new Error('GameService.create(): error creating game')
        )
      })
    );
  }

  show(gameId: number): Observable<Game>{
    return this.http.get<Game>(`${this.url}/${gameId}`).pipe(
      catchError( (err: any) => {
        console.error(err);
        return throwError(
          () => new Error('GameService.show(): error retrieving game')
        )
      })
    )
  }

  getGameGenre(genre: string): Observable<Game[]>{
    return this.http.get<Game[]>(`${this.url}/genre/${genre}`).pipe(
      catchError( (err: any) => {
        console.error(err);
        return throwError(
          () => new Error('GameService.getGameGenre(): error retrieving games')
        )
      })
    );
  }

  update(gameId: number, game: Game): Observable<Game>{
    return this.http.put<Game>(`${this.url}/id/${gameId}`, game).pipe(
      catchError( (err: any) => {
        console.error(err);
        return throwError(
          () => new Error('GameService.update(): error updating game')
        )
      })
    );
  }

  destroy(gameId: number): Observable<void>{
    return this.http.delete<void>(`${this.url}/id/${gameId}`).pipe(
      catchError( (err: any) => {
        console.error(err);
        return throwError(
          () => new Error('GameService.destroy(): error destroying game')
        )
      })
    );
  }

  addPublisherToGame(gameId: number, publisherId: number): Observable<Game>{
    return this.http.put<Game>(`${this.url}/id/${gameId}/publisher/add/${publisherId}`, {}).pipe(
      catchError( (err: any) => {
        console.error(err);
        return throwError(
          () => new Error('GameService.addPublisherToGame(): error updating game')
        )
      })
    );
  }


  constructor(private http: HttpClient) { }
}
