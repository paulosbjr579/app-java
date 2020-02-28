#!/bin/bash


echo "Start application..."
java $JAVA_OPTS $MEMORY_JVM -Xshareclasses -Xquickstart -jar api.war