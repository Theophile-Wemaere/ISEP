#!/bin/bash

if [[ -f logs.txt ]]
then
  rm logs.txt
fi
touch logs.txt
java-run --package | tee logs.txt
