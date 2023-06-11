#!/bin/bash

function cleanup(){
  kubectl delete ns test
  kubectl create ns test
}

function config(){
  kubectl create configmap script-configmap \
    --from-file=environment=src/test/k6/environment.js \
    --from-file=script=src/test/k6/script.js \
    --from-file=samplesUC=src/test/k6/usecases/samplesUC.js \
    -n test
}

function deploy(){
  kubectl apply -f helm/spring-demo/templates/spring-demo.yaml -n test
  kubectl apply -f helm/spring-demo/templates/k6.yaml -n test
}

cleanup
config
deploy