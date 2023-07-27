#!/usr/bin/env bash

echo mongodb-plus framework compile and deploy...

# mvnd
mvn clean -DskipTests source:jar deploy