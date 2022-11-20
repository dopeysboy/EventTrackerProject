import { Genre } from "./genre";
import { Publisher } from "./publisher";
import { Sale } from "./sale";

export class Game {
  id: number | undefined;
  title: string | undefined;
  msrp: number | undefined;
  description: string | undefined;
  onlineMp: boolean | undefined;

  publishers: Publisher[] | undefined;
  genres: Genre[] | undefined;
  sales: Sale[] | undefined;

  constructor(id?:number, title?:string, msrp?:number, description?:string, onlineMp?:boolean, publishers?: Publisher[], genres?: Genre[], sales?: Sale[]){
    this.id = id;
    this.title = title;
    this.msrp = msrp;
    this.description = description;
    this.onlineMp = onlineMp;
    this.publishers = publishers;
    this.genres = genres;
    this.sales = sales;
  }
}
