## Synopsis

Reads a graph file and either prints a possible topological ordering of the such graph or detects/prints a cycle in the graph

##Input Sample

file1.txt  
a b  
c b  
a c  

file2.txt  
mad2104 cop3530  
cop3337 cop3530  
cop2210 cop3337  

file3.txt  
x y  
y z  
z x  



##Output
The result of running
  java -jar tsort.jar file1.txt file2.txt file3.txt
would be something like:

file1.txt: a, c, b
file2.txt: cop2210, mad2104, cop3337, cop3530
file3.txt: graph has a cycle: x->y->z->x

## Installation
 mvn clean compile assembly:single
