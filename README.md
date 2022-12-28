# Pancake Resource Locator
Modular URL Shortener

## Requirement
* MySQL
* Java web application server supporting Jakarta EE (Previsouly Java EE)
* Java 8+

## Build
To build projects run
```bash
./gradlew build
```

## Running

### API Server
To run api server you need to provide these environment variable
* `DB_URL` JDBC database url
* `DB_USERNAME` Database username
* `DB_PASSWORD` Database user password
* `JWT_SECRET` Server secret value

### Redirect Server
To run redirect server you need to provide these environment variable
* `DB_URL` JDBC database url
* `DB_USERNAME` Database username
* `DB_PASSWORD` Database user password

### Database
Pancake Resource Locator is using `flyway` for database migration

Before running migration, supply database informations using enviroment variable
* `DB_URL` JDBC database url
* `DB_USERNAME` Database username
* `DB_PASSWORD` Database user password

Run this command to perform migration
```bash
./gradlew database:migrate
```

## Structure
![Big Picture](./resources/big%20picture.svg)
* See `server-core` directory for core server api
* See `api-server` directory for API server impl
* See `web` directory for Pancake Resource Locator web impl
* See `redirect-server` directory for redirection server impl

## License
Pancake Resource Locator is licensed under GPLv3
