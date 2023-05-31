#!/bin/sh
JSON_RESULT=$1

function cleanup(){
  rm -f ./${JSON_RESULT}.json
  rm -f ./report.json
}

function runTest(){
  k6 run --out json=${JSON_RESULT}.json script.js
}

cleanup
runTest