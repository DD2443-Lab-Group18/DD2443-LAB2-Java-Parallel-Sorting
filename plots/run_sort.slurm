#!/bin/bash

# Load the Java module on the cluster
ml easybuild-user/4.9.1
ml java/17.0.4

# Compile the Java program
javac Sorter.java Auxiliary.java SequentialSort.java ForkJoinPoolSort.java MyPDCCode.java

# Run my Java Sort program
java MyPDCCode
