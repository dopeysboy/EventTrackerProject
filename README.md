<a name="readme-top"></a>
<h1 align="center">Event Tracker API</h1>
<h2 align="center">Table of Contents</h2>
<ul>
    <li><a href="#readme-description">Description</a></li>
    <li><a href="#readme-built">Built With</a></li>
    <li><a href="#api-endpoints">API Endpoints</a></li>
    <li><a href="#readme-learned">Things I learned</a></li>
    <li><a href="#readme-future">Next Time</a></li>
    <li><a href="#readme-note">Things of Note</a></li>
    <li><a href="#readme-contact">Contact me</a></li>
</ul>
<a name="readme-description"></a>
<h2 align="center">Description</h2>
<p>This project is the guts of a future project where a front end will be built to utilize the API and entities that have been built. This project is able to perform full CRUD operations on Game, Sale, Publisher, and Genre objects, as well as extensive search methods for those objects. It is designed to keep track of different Sales that are happening for Games and allow user input to keep track of different sales.</p>
<p align="right">(<a href="#readme-top">back to top</a>)</p>
<a name="readme-built"></a>
<h2 align="center">Built With</h2>
<p>This application was built with java, SQL, SpringBoot, Postman, git/github, Spring Tools Suite, and MySQLWorkbench</p>
<a name="api-endpoints"></a>
<h2 align="center">API Endpoints</h2>

| CRUD Op. | HTTP Verb | URI                                          | Request Body             | Response Body                                                |
|----------|-----------|----------------------------------------------|--------------------------|--------------------------------------------------------------|
|          | GET       | `/api`                                       |                          | API endpoints                                                |
| Read     | GET       | `/api/games`                                 |                          | List of all Games                                            |
| Read     | GET       | `/api/games/id/{id}`                         |                          | The Game associated with given id                            |
| Read     | GET       | `/api/games/genre/{name}`                    |                          | List of Games with the named Genre                           |
| Read     | GET       | `/api/games/keyword/{keyword}`               |                          | List of Games that descriptions or titles match the keyword  |
| Read     | GET       | `/api/games/mp/{isMp}`                       |                          | List of Games that are or are not multiplayer                |
| Read     | GET       | `/api/games/msrp/{low}/{high}`               |                          | List of Games whose MSRP falls in the range provided         |
| Read     | GET       | `/api/games/publisher/{name}`                |                          | List of Games with the named Publisher                       |
| Create   | POST      | `/api/games`                                 | JSON for new Game        | JSON of created Game                                         |
| Update   | PUT       | `/api/games/id/{id}`                         | JSON to update Game      | JSON of updated Game                                         |
| Update   | PUT       | `/api/games/id/{gId}/publisher/add/{pId}`    |                          | JSON of updated Game                                         |
| Update   | PUT       | `/api/games/id/{gId}/publisher/remove/{pId}` |                          | JSON of updated Game                                         |
| Update   | PUT       | `/api/games/id/{gId}/genre/add/{gId}`        |                          | JSON of updated Game                                         |
| Update   | PUT       | `/api/games/id/{id}/genre/remove/{gId}`      |                          | JSON of updated Game                                         |
| Delete   | DELETE    | `/api/games/id/{id}`                         |                          |                                                              |
| Read     | GET       | `/api/genres`                                |                          | List of all Genres                                           |
| Read     | GET       | `/api/genres/id/{id}`                        |                          | Genre associated with given id                               |
| Read     | GET       | `/api/genres/{keyword}`                      |                          | Genres whose name matches the keyword                        |
| Create   | POST      | `/api/genres`                                | JSON for new Genre       | JSON of created Genre                                        |
| Update   | PUT       | `/api/genres/id/{id}`                        | JSON to update Genre     | JSON of updated Genre                                        |
| Delete   | DELETE    | `/api/genres/id/{id}`                        |                          |                                                              |
| Read     | GET       | `/api/publishers`                            |                          | List of all Publishers                                       |
| Read     | GET       | `/api/publishers/id/{id}`                    |                          | Publisher associated with given id                           |
| Read     | GET       | `/api/publishers/{keyword}`                  |                          | Publishers whose name matches the keyword                    |
| Create   | POST      | `/api/publishers`                            | JSON for new Publisher   | JSON of created Publisher                                    |
| Update   | PUT       | `/api/publishers/id/{id}`                    | JSON to update Publisher | JSON of updated Publisher                                    |
| Delete   | DELETE    | `/api/publishers/id/{id}`                    |                          |                                                              |
| Read     | GET       | `/api/sales`                                 |                          | List of all Sales                                            |
| Read     | GET       | `/api/sales/id/{id}`                         |                          | Sale associated with given id                                |
| Read     | GET       | `/api/sales/{keyword}`                       |                          | Sales whose name or description matches the keyword          |
| Read     | GET       | `/api/sales/discount/{low}/{high}`           |                          | Sales whose discount percentage falls in the range           |
| Read     | GET       | `/api/sales/during`                          | JSON for LocalDateTime   | Sales whose DateStart is before the LDT and DateEnd is after |
| Create   | POST      | `/api/sales`                                 | JSON for new Sale        | JSON of created Sale                                         |
| Update   | PUT       | `/api/sales/id/{id}`                         | JSON to update Publisher | JSON of updated Publisher                                    |
| Update   | PUT       | `/api/sales/id/{sId}/add/game/{gId}`         |                          | Updated Sale                                                 |
| Update   | PUT       | `/api/sales/id/{sId}/remove/game/{gId}`      |                          | Updated Sale                                                 |
| Delete   | DELETE    | `api/sales/id/{id}`                          |                          |                                                              |<p align="right">(<a href="#readme-top">back to top</a>)</p>
<a name="readme-learned"></a>
<h2 align="center">Things learned</h2>
<p>I got much more comfortable with Postman during this project and feel confident in using the application.</p>
<p align="right">(<a href="#readme-top">back to top</a>)</p>
<a name="readme-future"></a>
<h2 align="center">Things to do next time</h2>
<p>Given more time, I would have liked to add more data to the database, and try to wrap my head around the Steam API so I could pull sale and Game data from Steam.</p>
<p align="right">(<a href="#readme-top">back to top</a>)</p>
<a name="readme-note"></a>
<h2 align="center">Things of Note</h2>
<p>Of note, this project does not have a front-end, and is purely a back-end project. To demonstrate this application would require the use of the terminal with curl or another application like Postman.</p>
<p align="right">(<a href="#readme-top">back to top</a>)</p>
<a name="readme-contact"></a>
<h2 align="center">Contact me</h2>
<li>You can contact me at:</li>
<ul>
  <li><a href="https://www.github.com/dopeysboy">github</a></li>
  <li><a href="https://www.linkedin.com/in/tyler-j-tanner">LinkedIn</a></li>
</ul>
<p align="right">(<a href="#readme-top">back to top</a>)</p>
