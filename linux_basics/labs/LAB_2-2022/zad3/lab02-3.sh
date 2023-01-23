#!/bin/bash

trap "echo Try harder!" SIGINT SIGTERM

while true; do
  sleep 1
done
