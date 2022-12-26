# Pancake Resource Locator
Modular URL Shortener

## Requirement
* MySQL
* Java web application server
* Jakarta EE (Previously JavaEE)

## Running
To run api server you need to provide these environment variable
* `DB_URL` JDBC database url
* `DB_USERNAME` Database username
* `DB_PASSWORD` Database user password
* `JWT_SECRET` Server secret value

To run redirect server you need to provide these environment variable
* `DB_URL` JDBC database url
* `DB_USERNAME` Database username
* `DB_PASSWORD` Database user password

### Database
Pancake Resource Locator is using `flyway` for database migration.

Before running migration supply database information using enviroment variable
* `DB_URL` JDBC database url
* `DB_USERNAME` Database username
* `DB_PASSWORD` Database user password

Run this command below to migrate database
```bash
./gradlew database:migrate
```

## Structure
![Big Picture](./resources/big%20picture.svg)
* See `server-core` directory for core server api
* See `api-server` directory for API server app
* See `web` directory for Pancake Resource Locator web app
* See `redirect-server` directory for redirection server app

## License
Pancake Resource Locator is licensed under GPLv3

See `LICENSE` file for full text
