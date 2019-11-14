WoW-Checklist-Backend
=====================

This is the backend, written with Spring Boot, for the DHainzl/ng-wow-checklist application.

## Development

Pretty Standard Spring Boot Application with OAuth2 enabled to communicate with the battle.net APIs. See the examples below for more information on how to run it.

It is intended to be run inside a docker container, so it contains a `Dockerfile` to produce the final image.

### Configurable Properties

See the `application.properties` file for examples how this application is configured. You can overwrite the keys starting with `app.` to configure some details.

Additionally, by default it uses a `local` profile on your machine to load the `application-local.properties` file which configures a manually generated SSL certificate so this application can run for debugging purposes locally. You can create your own key by removing the keystore.p12 and generating a new one:

```
keytool -genkey -alias tomcat -storetype PKCS12 -keyalg RSA -keysize 2048 -keystore keystore.p12 -validity 3650
```

### Environment variables

In order to run this application, you need to define the following environment variables:

```
SECURITY_OAUTH2_CLIENT_CLIENTID=<Client id from battle.net>
SECURITY_OAUTH2_CLIENT_CLIENTSECRET=<Client Secret from battle.net>
```

Additionally, when you run the app locally you need to define the password for the keystore:

```
SERVER_SSL_KEY-STORE-PASSWORD=<password>
```

### Building and releasing

In order to push a new version of the docker image, you first need to build the app in production mode:

```
mvn clean install -P prod
```

Then you can build your file and ultimately push it to a docker registry. Note that you will most likely run into an endless loop when doing the authentication via http, so the app needs to be served via https.
Example of how this might look like:

```
docker build . -t dhainzl/wow-checklist-backend
docker run --rm --name wow-checklist-backend -e SECURITY_OAUTH2_CLIENT_CLIENTID=REDACTED -e APP_OAUTH2_CLIENT_SECRET=REDACTED -p 8080:8080 dhainzl/wow-checklist-backend
docker stop dhainzl/wow-checklist-backend
docker push dhainzl/wow-checklist-backend
```

## Example URLs

* `/login` -> does the OAuth2 workflow and returns to `${app.return-urls}`.
* `/api/characters/<region>` -> Lists all WoW characters of the authenticated user in `<region>`.
* `/api/<region>/<realm>/<name>/profile` -> Returns information about the given character.