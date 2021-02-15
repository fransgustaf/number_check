#!/bin/bash

cd src/
if [ -z $1 ]; then
    echo "Usage:"
    echo "./run test"
    echo "./run build"
    echo "./run <number to check>"
    exit 0
elif [ $1 == "test" ]; then
    echo "Running test"
    echo "Might not work on Windows"
    javac -cp ../lib/junit-platform-console-standalone-1.8.0-M1.jar TestRunner.java Tests.java InputNumber.java LenientDate.java
    java -cp ../lib/junit-platform-console-standalone-1.8.0-M1.jar:. TestRunner
    exit 0
elif [ $1 == "build" ]; then
    echo "Running build"
    javac Main.java InputNumber.java LenientDate.java
    exit 0
else
    java Main $1
fi
cd - > /dev/null
