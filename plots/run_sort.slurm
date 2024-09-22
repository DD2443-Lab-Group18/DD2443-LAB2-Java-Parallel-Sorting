#!/bin/bash

# Load the Java module on the cluster
ml PDC/23.12
ml easybuild-user/4.9.1
ml java/17.0.4

# Compile the Java program
javac *.java

# Run my Java Sort program
java MyPDCCode
