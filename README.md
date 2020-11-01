# OOP-Ex0

first assignment in OOP course.
focusing on implementation of undirected unweighted graph
and basic algorithms operations on them such as:
- is the graph connected.
- finding the shortest path between 2 vertices.
- finding the distance of the shortest path between 2 vertices.

## Installation

can be downloaded from this repository as a zip,
or by git.

```bash
git clone https://github.com/IdoGuzi/OOP_graph.git
```

## Usage


usage should be with the interfaces and not the class for easier expirience.

```java
node_data n = new NodeData();
node_data v = new NodeData(5);
graph g = new Graph_DS();
g.addNode(n);
graph_algorithms ga = new Graph_Algo();
ga.init(g);
ga.isConnected();
```

## Contributing

this repository is for university course and isn't planed to be extended
beyond this course assignments.

for special requests, please open an issue to disccus the matter.



