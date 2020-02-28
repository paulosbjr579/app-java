#!/bin/bash


echo "Install dependencies..."
mvn clean install

echo "Copy file api.war..."
cp ./target/api.war /target