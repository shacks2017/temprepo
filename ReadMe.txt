READ ME


Yo
Directory: (personal use)
cd documents/data/university/subjects/fall '16/EECS 233/final/final/src

Compilation:
javac solve.java

or

"C:\Program Files\Java\jdk1.8.0_111\bin\javac" solve.java

Input:

Shuffle
java -Xmx1024m solve shuffle 3

replace '3' with number of times the board needs to be shuffled

8 puzzle

Breadth-First Search
java -Xmx1024m solve 8puzzle bfs 5 6 8 4 0 1 2 3 7

Breadth-First Search
java -Xmx1024m solve 8puzzle dfs 5 6 8 4 0 1 2 3 7

Replace '5 6 8 4 0 1 2 3 7' with your shuffled puzzle in the same format

FWGC

Breadth-First Search
java -Xmx1024m solve fwgc bfs

Breadth-First Search
java -Xmx1024m solve fwgc dfs


