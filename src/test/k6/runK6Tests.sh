#!/bin/sh
JSON_RESULT=$1
JSON_REPORT=report

function cleanup(){
  rm -f ./${JSON_RESULT}.json
  echo 'Deleted result: '${JSON_RESULT}'.json'
  rm -f ./${JSON_REPORT}
  echo 'Deleted report: '${JSON_REPORT}'.json'
}

function runTest(){
  echo 'Running K6 tests... '
  k6 run --out json=${JSON_RESULT}.json script.js
  echo 'Results can be found in '${JSON_RESULT}'.json'
  echo 'Report can be found in '${JSON_REPORT}'.json'
}

cleanup
runTest