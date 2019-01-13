#!/usr/bin/env bash

function free_up_port {
    netstat -vanp tcp | grep 4444 -q
    if [ $? -eq 1 ]
    then
        echo -------- PORT 4444 IS OPEN --------
    else
        echo -------- KILLING PORT 4444 --------
        pid=$(lsof -i tcp:4444 -t)
        kill -TERM ${pid} || kill -KILL ${pid}
    fi
}

function start_hub {
    free_up_port
    echo Starting Selenium Hub...
    cd ./src/test/resources
    java -jar ./selenium-server-standalone-3.12.0.jar -role hub
}

start_hub
