#NAMY

This project contains more than 33.000 firstName (essentially french) and associated metadata.

## try it !
git clone this project
```$bash
git clone https://github.com/kerbart/namy
```

in the project folder `namy` uncompress mongo data

```$bash
tar xvzf data.tar.gz
```

build the java project

```$bash
./mvnw package
```

run everything with docker-compose

```$bash
docker-compose up --build
```