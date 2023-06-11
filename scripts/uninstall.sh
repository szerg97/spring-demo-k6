#!/bin/bash

function uninstall(){
  helm uninstall test -n test
}

uninstall