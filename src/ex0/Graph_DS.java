package ex0;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

/**
 * this class implement the interface @graph
 * that stand in the requirements of the interface.
 * @author Ido Guzi.
 */
public class Graph_DS implements graph{
    private int nodeCount, edgeCount, modeCount;
    private HashMap<Integer, node_data> nodes;

    public Graph_DS(){
        nodeCount=0;
        edgeCount=0;
        modeCount=0;
        nodes = new HashMap<>();
    }

    public Graph_DS(graph g){
        this.nodeCount=g.nodeSize();
        this.edgeCount=g.edgeSize();
        this.modeCount=g.getMC();
        this.nodes = new HashMap<>();
        node_data n;
        Iterator<node_data> it = g.getV().iterator();
        while (it.hasNext()){
            n = it.next();
            nodes.put(n.getKey(), new NodeData(n));
        }
        it = g.getV().iterator();
        while (it.hasNext()){
            n = it.next();
            Iterator<node_data> it2 = n.getNi().iterator();
            while (it2.hasNext()) {
                node_data v = it2.next();
                this.connect(n.getKey(), v.getKey());

            }
        }
    }

    /**
     * return the node in the graph with the associated key.
     * @param key - the node_id
     * @return the node_data by the node_id, null if none.
     */
    @Override
    public node_data getNode(int key) {
        return nodes.get(key);
    }

    /**
     * return true if the two node connected with an edge.
     * @param node1 - id of the first node
     * @param node2 - id of the second node
     * @return true if the nodes are connected, else false.
     */
    @Override
    public boolean hasEdge(int node1, int node2) {
        node_data n1 = getNode(node1), n2 = getNode(node2);
        if (n1==null || n2==null) return false;
        if (node1==node2) return false;
        if (n1.hasNi(node2) && n2.hasNi(node1)) return true;
        if ((n1.hasNi(node2) && !n2.hasNi(node1)) ||
                (n2.hasNi(node1) && !n1.hasNi(node2)))
                    throw new RuntimeException("Error: graph should be undirectional");
        return false;
    }

    /**
     * add the given node to this graph.
     * @param n - the node to add to the graph
     */
    @Override
    public void addNode(node_data n) {
        nodes.put(n.getKey(),n);
        nodeCount++;
        modeCount++;
    }

    /**
     * connecting the nodes with the given id with an edge,
     * if not  already connected.
     * @param node1 - id of the first node
     * @param node2 - id of the second node
     */
    @Override
    public void connect(int node1, int node2) {
        node_data n1 = getNode(node1), n2 = getNode(node2);
        if (n1==null || n2==null) return;
        if (node1==node2) return;
        if (hasEdge(node1,node2)) return;
        if ((n1.hasNi(node2) && !n2.hasNi(node1)) ||
                (n2.hasNi(node1) && !n1.hasNi(node2))){
            throw new RuntimeException("Error: both should be connected or not connected");
        }
        n1.addNi(n2);
        n2.addNi(n1);
        edgeCount++;
        modeCount++;
    }

    /**
     * return a shallow copy of a collection with the graph's nodes.
     * @return a collenction of node_data that existing in the graph.
     */
    @Override
    public Collection<node_data> getV() {
        return nodes.values();
    }

    /**
     * return a collection of the nodes that have edges with the node with node_id.
     * @param node_id - id of a node
     * @return collection of node_data that connected to the node with @node_id
     */
    @Override
    public Collection<node_data> getV(int node_id) {
        node_data n = getNode(node_id);
        return n.getNi();
    }

    /**
     * deleting the node with the given id from the graph,
     * deleting the edge connecting to this node as well.
     * @param key - id of the node to delete
     * @return the node that been deleted.
     */
    @Override
    public node_data removeNode(int key) {
        node_data n = getNode(key);
        if (n==null) return null;
        ArrayList<Integer> adj = new ArrayList<>();
        Iterator<node_data> it = n.getNi().iterator();
        while (it.hasNext()){
            node_data t = it.next();
            adj.add(t.getKey());
        }
        for (int i=0;i<adj.size();i++){
            this.removeEdge(n.getKey(),adj.get(i));
        }
        nodes.remove(key);
        nodeCount--;
        modeCount++;
        return n;
    }

    /**
     * deleting the edge connecting the given nodes,
     * if edge doesn't exist nothing will happen.
     * @param node1 - id of the first node
     * @param node2 - id of the second node
     */
    @Override
    public void removeEdge(int node1, int node2) {
        if (!hasEdge(node1,node2)) return;
        node_data n1 = getNode(node1), n2 = getNode(node2);
        n1.removeNode(n2);
        n2.removeNode(n1);
        edgeCount--;
        modeCount++;
    }

    /**
     * returning the number of nodes in the graph.
     * @return an integer of the amount of nodes in the graph.
     */
    @Override
    public int nodeSize() {
        return nodeCount;
    }

    /**
     * return the number of edges in the graph.
     * @return an integer of the amount of edges in the graph.
     */
    @Override
    public int edgeSize() {
        return edgeCount;
    }

    /**
     * return the mode count of the graph,
     * used for testing changes in the graph.
     * @return an integer of the current mode of the graph.
     */
    @Override
    public int getMC() {
        return modeCount;
    }

}
