#!/usr/bin/env bash

function free_up_port {
    netstat -vanp tcp | grep 5566 -q
    if [ $? -eq 1 ]
    then
        echo -------- PORT 5566 IS OPEN --------
    else
        echo -------- KILLING PORT 5566 --------
        pid=$(lsof -i tcp:4444 -t)
        kill -TERM ${pid} || kill -KILL ${pid}
    fi
}

function start_node {
    free_up_port
    echo Starting Selenium Node...
    cd ./src/test/resources
    java -jar ./selenium-server-standalone-3.12.0.jar -role webdriver -hub http://169.254.24.100:4444/grid/register -port 5567
}

start_node
