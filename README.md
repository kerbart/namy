# NAMY

This project contains more than 33.000 firstnames (essentially french) and associated metadata.

## try it (fast)
git clone this project
```$bash
git clone https://github.com/kerbart/namy
```

simply run
```$bash
    ./run.sh
```

## try it (step by step) !
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

## search firstname metadata
Open http://localhost:8080/
Type a firstname, hit enter. you are in !

