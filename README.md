## TopJava graduation project


Design and implement a REST API using Hibernate/Spring/SpringMVC (or Spring-Boot) without frontend.
The task is:
Build a voting system for deciding where to have lunch.

    2 types of users: admin and regular users
    Admin can input a restaurant and it's lunch menu of the day (2-5 items usually, just a dish name and price)
    Menu changes each day (admins do the updates)
    Users can vote on which restaurant they want to have lunch at
    Only one vote counted per user
    If user votes again the same day:
        If it is before 11:00 we asume that he changed his mind.
        If it is after 11:00 then it is too late, vote can't be changed

Each restaurant provides new menu each day.
As a result, provide a link to github repository.
It should contain the code and README.md with API documentation and curl commands to get data for voting and vote.

## Running the application
 * Build package with Maven `mvn package`
 * Deploy `graduation.war` with Tomcat at `localhost:8080/graduation`
 * Run REST requests listed below
### Application description

### Curl commands REST API
### Admin API
#### Admin Profile API

| Role | Path                 |  Method | Description   |
|------|----------------------|---------|---------------|
| Admin| [`/rest/admin/users`]|   `GET` | Get all users |
* **Example:**

`curl -s http://localhost:8080/graduation/rest/admin/users --user admin@email.com:password`

| Role | Path                      |  Method | Description    |
|------|---------------------------|---------|----------------|
| Admin| [`/rest/admin/users/{id}`]|   `GET` | Get user by id |
* **Example:**

`curl -s http://localhost:8080/graduation/rest/admin/users/100000 --user admin@email.com:password`

| Role | Path                      |  Method | Description    |
|------|---------------------------|---------|----------------|
| Admin| [`/rest/admin/users/by/{e-mail}`]|   `GET` | Get user by e-mail |
* **Example:**

`curl -s http://localhost:8080/graduation/rest/admin/users/by/user@email.ru --user admin@email.com:password`

| Role | Path                 |  Method  | Description |
|------|----------------------|----------|-------------|
| Admin| [`/rest/admin/users`]|   `POST` | Create user |
* **Example:**

`curl -s -X POST -d '{"name":"New_user","email":"newuser@email.com","password":"password","roles":["ROLE_USER"]}' -H 'Content-Type:application/json' http://localhost:8080/graduation/rest/admin/users --user admin@email.com:password`

| Role | Path                      |  Method | Description |
|------|---------------------------|---------|-------------|
| Admin| [`/rest/admin/users/{id}`]|   `PUT` | Update user |
* **Example:**

`curl -s -X PUT -d '{"id":100000,"name":"Updated","email":"admin@email.com","roles":["ROLE_USER","ROLE_ADMIN"]}' -H 'Content-type: application/json' http://localhost:8080/graduation/rest/admin/users/100000 --user admin@email.com:password`


| Role | Path                      |  Method | Description |
|------|---------------------------|---------|-------------|
| Admin| [`/rest/admin/users/{id}`]|`DELETE` | Delete user |
* **Example:**

`curl -s -X DELETE http://localhost:8080/graduation/rest/admin/users/100001 --user admin@email.com:password`


| Role | Path                      |  Method   | Description        |
|------|---------------------------|-----------|--------------------|
| Admin| [`/rest/admin/users/{id}`]|   `PATCH` | En-/disable user   |

* **URL Params**\
    enabled=boolean
* **Example:**

`curl -s -X PATCH http://localhost:8080/graduation/rest/admin/users/100001?enabled=false --user admin@email.com:password`

#### Admin Restaurant API

| Role | Path                         |  Method | Description         |
|------|------------------------------|---------|---------------------|
| Admin| [`/rest/admin/restaurants`]  |   `GET` | Get all restaurants |
* **Example:**

`curl -s http://localhost:8080/graduation/rest/admin/restaurants --user admin@email.com:password`

| Role | Path                              |  Method | Description          |
|------|-----------------------------------|---------|----------------------|
| Admin| [`/rest/admin/restaurants/{id}`]  |   `GET` | Get restaurant by id |
* **Example:**

`curl -s http://localhost:8080/graduation/rest/admin/restaurants/100002 --user admin@email.com:password`

| Role | Path                       |  Method  | Description       |
|------|----------------------------|----------|-------------------|
| Admin| [`/rest/admin/restaurants`]|   `POST` | Create restaurant |
* **Example:**

`curl -s -X POST -d '{"name":"New restaurant"}' -H 'Content-Type:application/json' http://localhost:8080/graduation/rest/admin/restaurants --user admin@email.com:password`

| Role | Path                            |  Method | Description       |
|------|---------------------------------|---------|-------------------|
| Admin| [`/rest/admin/restaurants/{id}`]|   `PUT` | Update restaurant |
* **Example:**

`curl -s -X PUT -d '{"id":100002,"name":"Updated"}' -H 'Content-type: application/json' http://localhost:8080/graduation/rest/admin/restaurants/100002 --user admin@email.com:password`

| Role | Path                            |  Method | Description       |
|------|---------------------------------|---------|-------------------|
| Admin| [`/rest/admin/restaurants/{id}`]|`DELETE` | Delete restaurant |
* **Example:**

`curl -s -X DELETE http://localhost:8080/graduation/rest/admin/restaurants/100002 --user admin@email.com:password`

| Role | Path                       |  Method  | Description       |
|------|----------------------------|----------|-------------------|
| Admin| [`/rest/admin/restaurants/{id}/menus`]|   `POST` | Create menu |
* **Example:**

`curl -s -X POST -d '{"name":"New menu"}' -H 'Content-Type:application/json' http://localhost:8080/graduation/rest/admin/restaurants/100002/menus --user admin@email.com:password`

| Role | Path                            |  Method | Description       |
|------|---------------------------------|---------|-------------------|
| Admin| [`/rest/admin/restaurants/{id}/menus/{id}`]|   `PUT` | Update menu |
* **Example:**

`curl -s -X PUT -d '{"id":100002,"name":"Updated"}' -H 'Content-type: application/json' http://localhost:8080/graduation/rest/admin/restaurants/100002/menus/100005 --user admin@email.com:password`

| Role | Path                            |  Method | Description       |
|------|---------------------------------|---------|-------------------|
| Admin| [`/rest/admin/restaurants/{id}/menus/{id}`]|`DELETE` | Delete menu |
* **Example:**

`curl -s -X DELETE http://localhost:8080/graduation/rest/admin/restaurants/100002/menus/100005 --user admin@email.com:password`

| Role | Path                       |  Method  | Description       |
|------|----------------------------|----------|-------------------|
| Admin| [`/rest/admin/restaurants/{id}/menus/{id}/dishes`]|   `POST` | Create dish |
* **Example:**

`curl -s -X POST -d '{"name":"New dish"}' -H 'Content-Type:application/json' http://localhost:8080/graduation/rest/admin/restaurants/100002/menus/100005/dishes --user admin@email.com:password`

| Role | Path                            |  Method | Description       |
|------|---------------------------------|---------|-------------------|
| Admin| [`/rest/admin/restaurants/{id}/menus/{id}/dishes/{id}`]|   `PUT` | Update dish |
* **Example:**

`curl -s -X PUT -d '{"id":100010,"name":"Updated"}' -H 'Content-type: application/json' http://localhost:8080/graduation/rest/admin/restaurants/100002/menus/100005/dishes/{100010} --user admin@email.com:password`

| Role | Path                            |  Method | Description       |
|------|---------------------------------|---------|-------------------|
| Admin| [`/rest/admin/restaurants/{id}/menus/{id}/dishes/{id}`]|`DELETE` | Delete dish |
* **Example:**

`curl -s -X DELETE http://localhost:8080/graduation/rest/admin/restaurants/100002/menus/100005/dishes/100010 --user admin@email.com:password`


### User API
#### User Profile API

| Role | Path                 |  Method  | Description |
|------|----------------------|----------|-------------|
| User| [`/rest/profile/register`]|   `POST` | Register new user |
* **Example:**

`curl -s -X POST -d '{"name":"New_user","email":"newuser@email.com","password":"password","roles":["ROLE_USER"]}' -H 'Content-Type:application/json' http://localhost:8080/graduation/rest/profile/register --user admin@email.com:password`

| Role | Path                      |  Method | Description |
|------|---------------------------|---------|-------------|
| User| [`/rest/profile/{id}`]|   `PUT` | Update user |
* **Example:**

`curl -s -X PUT -d '{"id":100001,"name":"Updated","email":"user@email.com","roles":["ROLE_USER"]}' -H 'Content-type: application/json' http://localhost:8080/graduation/rest/profile/100001 --user user@email.com:password`


| Role | Path                      |  Method | Description |
|------|---------------------------|---------|-------------|
| User| [`/rest/profile/{id}`]|`DELETE` | Delete user |
* **Example:**

`curl -s -X DELETE http://localhost:8080/graduation/rest/profile/100001 --user user@email.com:password`

#### User Restaurant API
| Role | Path                         |  Method | Description         |
|------|------------------------------|---------|---------------------|
| User| [`/rest/user/restaurants`]  |   `GET` | Get all restaurants with dishes for today |
* **Example:**

`curl -s http://localhost:8080/graduation/rest/user/restaurants --user user@email.com:password`

#### User Vote API
| Role | Path                      |  Method | Description |
|------|---------------------------|---------|-------------|
| User| [`/rest/user/votes`]|`POST` | Vote for restaurant |
* **Example:**

`curl -s -X POST http://localhost:8080/graduation/rest/user/votes?restaurantId=100002 --user user@email.com:password`

| Role | Path                         |  Method | Description         |
|------|------------------------------|---------|---------------------|
| User| [`/rest/user/votes`]  |   `GET` | Get all votes for day |
* **URL Params**\
    date=yyyy-mm-dd
* **Example:**

`curl -s http://localhost:8080/graduation/rest/user/votes?date=2020-06-25 --user user@email.com:password`
