#!/bin/bash

# To book and execute on a dedicated node
salloc -t 60 -N 8 -A edu24.dd2443 -p shared

# Load the Java module on the cluster
ml PDC/23.12
ml easybuild-user/4.9.1
ml java/17.0.4

# Compile the Java program
javac *.java

# Run my Java Sort program
java MyPDCCode
