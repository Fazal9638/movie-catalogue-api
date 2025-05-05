<h1>Movie Catalogue API</h1>

<p>A RESTful Spring Boot application to manage a movie catalog, allowing creation, updating, deletion, and querying of <strong>Movies</strong>, <strong>Directors</strong>, and <strong>Ratings</strong>.</p>
<p>This project is part of the Kukri Sports technical test.</p>

<hr/>

<h2>Tech Stack</h2>
<ul>
  <li>Java 8</li>
  <li>Spring Boot</li>
  <li>Spring Data JPA</li>
  <li>H2 (file-based) Database</li>
  <li>Maven</li>
  <li>JUnit 5</li>
</ul>

<h2>Features</h2>
<ul>
  <li>CRUD APIs for Movies, Directors, and Ratings</li>
  <li>Search movies by Director or Rating</li>
  <li>REST-based architecture</li>
  <li>Data persists across application restarts</li>
  <li>Javadoc documentation</li>
  <li>Ready for testing with H2 console</li>
</ul>

<h2>Getting Started</h2>
<h3>Prerequisites</h3>
<ul>
  <li>Java 8+</li>
  <li>Maven</li>
</ul>

<h3>Run the App</h3>
<pre><code>mvn spring-boot:run</code></pre>
<p>Once running, access the app at: <strong>http://localhost:8080</strong></p>

<h2>API Endpoints</h2>

<h3>Director Endpoints</h3>
<table>
  <tr><th>Method</th><th>Endpoint</th><th>Description</th></tr>
  <tr><td>POST</td><td>/api/directors</td><td>Create a new director</td></tr>
  <tr><td>PUT</td><td>/api/directors/{id}</td><td>Update a director</td></tr>
  <tr><td>GET</td><td>/api/directors/{id}</td><td>Get a director by ID</td></tr>
  <tr><td>GET</td><td>/api/directors</td><td>List all directors</td></tr>
  <tr><td>DELETE</td><td>/api/directors/{id}</td><td>Delete a director</td></tr>
</table>

<h3>Rating Endpoints</h3>
<table>
  <tr><th>Method</th><th>Endpoint</th><th>Description</th></tr>
  <tr><td>POST</td><td>/api/ratings</td><td>Create a new rating</td></tr>
  <tr><td>PUT</td><td>/api/ratings/{id}</td><td>Update a rating</td></tr>
  <tr><td>GET</td><td>/api/ratings/{id}</td><td>Get a rating by ID</td></tr>
  <tr><td>GET</td><td>/api/ratings</td><td>List all ratings</td></tr>
  <tr><td>DELETE</td><td>/api/ratings/{id}</td><td>Delete a rating</td></tr>
</table>

<h3>Movie Endpoints</h3>
<table>
  <tr><th>Method</th><th>Endpoint</th><th>Description</th></tr>
  <tr><td>POST</td><td>/api/movies</td><td>Create a new movie</td></tr>
  <tr><td>PUT</td><td>/api/movies/{id}</td><td>Update a movie</td></tr>
  <tr><td>GET</td><td>/api/movies/{id}</td><td>Get a movie by ID</td></tr>
  <tr><td>GET</td><td>/api/movies</td><td>List all movies</td></tr>
  <tr><td>DELETE</td><td>/api/movies/{id}</td><td>Delete a movie</td></tr>
  <tr><td>GET</td><td>/api/movies/director/{directorId}</td><td>Get movies by director</td></tr>
  <tr><td>GET</td><td>/api/movies/rating-above/{rating}</td><td>Get movies with rating above</td></tr>
</table>

<h2>Database Configuration</h2>
<p>This app uses a <strong>file-based H2 database</strong> to store data persistently across restarts.</p>

<pre><code>
spring.datasource.url=jdbc:h2:file:./data/movie-catalogue-db
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
spring.h2.console.enabled=true
spring.h2.console.path=/h2-console
</code></pre>

<h3>Why H2 File-Based?</h3>
<ul>
  <li>✅ Lightweight and requires no setup</li>
  <li>✅ Portable and easy to share</li>
  <li>✅ Persistent storage under <code>./data/</code></li>
  <li>✅ Developer-friendly with browser console</li>
</ul>

<p>Access H2 Console at: <strong>http://localhost:8080/h2-console</strong><br/>
JDBC URL: <code>jdbc:h2:file:./data/movie-catalogue-db</code></p>

<h2>Testing</h2>
<p>Run all unit tests using:</p>
<pre><code>mvn test</code></pre>

<h2>Documentation</h2>
<ul>
  <li>Javadoc is available throughout the codebase</li>
  <li>Controllers are well documented</li>
  <li>Code is clean and structured</li>
</ul>

<h2>Project Structure</h2>
<pre><code>
com.kukrisports.movie.catalogue
│
├── model            // Entities and DTOs
├── exception        // exception
├── repository       // JPA Repositories
├── service          // Business logic
├── controller       // REST Controllers
├── common           // Response wrappers
└── config           // Spring configurations
</code></pre>

<h2>Deployment</h2>
<p>Build the project:</p>
<pre><code>mvn clean install</code></pre>
<p>Run the jar:</p>
<pre><code>java -jar target/movie-catalogue-api-0.0.1-SNAPSHOT.jar</code></pre>

<h2>Author</h2>
<ul>
  <li><strong>Fazal Babaria</strong></li>
</ul>
