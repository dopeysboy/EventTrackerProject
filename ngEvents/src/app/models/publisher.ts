import { Game } from "./game";

export class Publisher {
  id: number | undefined;
  name: string | undefined;
  games: Game[] | undefined;

  constructor(id?: number, name?: string, games?: Game[]){
    this.id = id;
    this.name = name;
    this.games = games;
  }
}
