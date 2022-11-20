package com.skilldistillery.eventtracker.controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin({"*", "http://localhost/"})
public class IndexController {

	@GetMapping("api")
	public String showAPIEndpoints() {
		return "| CRUD Op. | HTTP Verb | URI                                          | Request Body             | Response Body                                                |\n"
				+ "|----------|-----------|----------------------------------------------|--------------------------|--------------------------------------------------------------|\n"
				+ "|          | GET       | `/api`                                       |                          | API endpoints                                                |\n"
				+ "| Read     | GET       | `/api/games`                                 |                          | List of all Games                                            |\n"
				+ "| Read     | GET       | `/api/games/id/{id}`                         |                          | The Game associated with given id                            |\n"
				+ "| Read     | GET       | `/api/games/genre/{name}`                    |                          | List of Games with the named Genre                           |\n"
				+ "| Read     | GET       | `/api/games/keyword/{keyword}`               |                          | List of Games that descriptions or titles match the keyword  |\n"
				+ "| Read     | GET       | `/api/games/mp/{isMp}`                       |                          | List of Games that are or are not multiplayer                |\n"
				+ "| Read     | GET       | `/api/games/msrp/{low}/{high}`               |                          | List of Games whose MSRP falls in the range provided         |\n"
				+ "| Read     | GET       | `/api/games/publisher/{name}`                |                          | List of Games with the named Publisher                       |\n"
				+ "| Create   | POST      | `/api/games`                                 | JSON for new Game        | JSON of created Game                                         |\n"
				+ "| Update   | PUT       | `/api/games/id/{id}`                         | JSON to update Game      | JSON of updated Game                                         |\n"
				+ "| Update   | PUT       | `/api/games/id/{gId}/publisher/add/{pId}`    |                          | JSON of updated Game                                         |\n"
				+ "| Update   | PUT       | `/api/games/id/{gId}/publisher/remove/{pId}` |                          | JSON of updated Game                                         |\n"
				+ "| Update   | PUT       | `/api/games/id/{gId}/genre/add/{gId}`        |                          | JSON of updated Game                                         |\n"
				+ "| Update   | PUT       | `/api/games/id/{id}/genre/remove/{gId}`      |                          | JSON of updated Game                                         |\n"
				+ "| Delete   | DELETE    | `/api/games/id/{id}`                         |                          |                                                              |\n"
				+ "| Read     | GET       | `/api/genres`                                |                          | List of all Genres                                           |\n"
				+ "| Read     | GET       | `/api/genres/id/{id}`                        |                          | Genre associated with given id                               |\n"
				+ "| Read     | GET       | `/api/genres/{keyword}`                      |                          | Genres whose name matches the keyword                        |\n"
				+ "| Create   | POST      | `/api/genres`                                | JSON for new Genre       | JSON of created Genre                                        |\n"
				+ "| Update   | PUT       | `/api/genres/id/{id}`                        | JSON to update Genre     | JSON of updated Genre                                        |\n"
				+ "| Delete   | DELETE    | `/api/genres/id/{id}`                        |                          |                                                              |\n"
				+ "| Read     | GET       | `/api/publishers`                            |                          | List of all Publishers                                       |\n"
				+ "| Read     | GET       | `/api/publishers/id/{id}`                    |                          | Publisher associated with given id                           |\n"
				+ "| Read     | GET       | `/api/publishers/{keyword}`                  |                          | Publishers whose name matches the keyword                    |\n"
				+ "| Create   | POST      | `/api/publishers`                            | JSON for new Publisher   | JSON of created Publisher                                    |\n"
				+ "| Update   | PUT       | `/api/publishers/id/{id}`                    | JSON to update Publisher | JSON of updated Publisher                                    |\n"
				+ "| Delete   | DELETE    | `/api/publishers/id/{id}`                    |                          |                                                              |\n"
				+ "| Read     | GET       | `/api/sales`                                 |                          | List of all Sales                                            |\n"
				+ "| Read     | GET       | `/api/sales/id/{id}`                         |                          | Sale associated with given id                                |\n"
				+ "| Read     | GET       | `/api/sales/{keyword}`                       |                          | Sales whose name or description matches the keyword          |\n"
				+ "| Read     | GET       | `/api/sales/discount/{low}/{high}`           |                          | Sales whose discount percentage falls in the range           |\n"
				+ "| Read     | GET       | `/api/sales/during`                          | JSON for LocalDateTime   | Sales whose DateStart is before the LDT and DateEnd is after |\n"
				+ "| Create   | POST      | `/api/sales`                                 | JSON for new Sale        | JSON of created Sale                                         |\n"
				+ "| Update   | PUT       | `/api/sales/id/{id}`                         | JSON to update Publisher | JSON of updated Publisher                                    |\n"
				+ "| Update   | PUT       | `/api/sales/id/{sId}/add/game/{gId}`         |                          | Updated Sale                                                 |\n"
				+ "| Update   | PUT       | `/api/sales/id/{sId}/remove/game/{gId}`      |                          | Updated Sale                                                 |\n"
				+ "| Delete   | DELETE    | `api/sales/id/{id}`                          |                          |                                                              |";
	}
}
