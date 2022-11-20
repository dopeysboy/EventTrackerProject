import { Game } from "./game";

export class Sale {
  id: number | undefined;
  dateStart: string | undefined;
  dateEnd: string | undefined;
  percentDiscount: number | undefined;
  name: string | undefined;
  description: string | undefined;
  games: Game[] | undefined;

  constructor(id?: number, dateStart?: string, dateEnd?: string, percentDiscount?: number, name?: string, description?: string, games?: Game[]){
    this.id = id;
    this.dateStart = dateStart;
    this.dateEnd = dateEnd;
    this.percentDiscount = percentDiscount;
    this.name = name;
    this.description = description;
    this.games = games;
  }
}
