#!/bin/bash

echo "+----------------------------------+"
echo "|            Namy setup            |"
echo "+----------------------------------+"

if [ -d "data"] ; then
    echo "*> Uncompress data/ mongo directory..."
    tar xvzf data.tar.gz
fi

echo "*> build namy jar package..."
./mvnw package

echo "*> launch everything..."
docker-compose up --build
