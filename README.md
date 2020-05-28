# Careless

Spring boot application serving a Create-React-App build.

## Running Careless

### Running a development version of Careless in a docker image

Requirements:
- [docker](https://docs.docker.com/engine/install/)
- [docker-compose](https://docs.docker.com/compose/install/)


```sh
docker-compose up
```

### Running a development version of Careless in your local machine

Requirements:
- [Java 11](https://adoptopenjdk.net/)
- [Grade version 6 or above](https://gradle.org/install/)
- [Node version 8.10 and NPM version 5 or above](https://nodejs.org/en/)

```sh
# The back end infrastructure is a spring boot project
./gradlew install
./gradlew bootRun # App will be available in port 8080

# The front end application is a create-react-app
cd src/main/webapp
npm install
npm run start # Web app will be available in port 3000
```

### Making and Running a production build

Requirements:
- [docker](https://docs.docker.com/engine/install/)

```sh
docker build -t careless .
docker run --publish 8080:8080 --detach careless
```

Note, if running this locally, do a `./gradlew clean` to avoid copying the local build to the docker
image. This will significantly boost build time.
