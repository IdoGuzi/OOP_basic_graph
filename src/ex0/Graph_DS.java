package ex0;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

public class Graph_DS implements graph{
    private int nodeCount, edgeCount, modeCount;
    private HashMap<Integer, node_data> nodes;

    public Graph_DS(){
        nodeCount=0;
        edgeCount=0;
        modeCount=0;
        nodes = new HashMap<>();
    }



    @Override
    public node_data getNode(int key) {
        return nodes.get(key);
    }

    @Override
    public boolean hasEdge(int node1, int node2) {
        node_data n1 = getNode(node1), n2 = getNode(node2);
        if (n1.hasNi(node2) && n2.hasNi(node1)) return true;
        return false;
    }

    @Override
    public void addNode(node_data n) {
        nodes.put(n.getKey(),n);
        nodeCount++;
        modeCount++;
    }

    @Override
    public void connect(int node1, int node2) {
        if (hasEdge(node1,node2)) return;
        node_data n1 = getNode(node1), n2 = getNode(node2);
        n1.addNi(n2);
        n2.addNi(n1);
        edgeCount++;
        modeCount++;
    }

    @Override
    public Collection<node_data> getV() {
        return nodes.values();
    }

    @Override
    public Collection<node_data> getV(int node_id) {
        node_data n = getNode(node_id);
        return n.getNi();
    }

    @Override
    public node_data removeNode(int key) {
        node_data n = getNode(key);
        if (n==null) return null;
        Collection<node_data> edges = n.getNi();
        Iterator<node_data> it = edges.iterator();
        while (it.hasNext()){
            node_data t = it.next();
            t.removeNode(n);
            n.removeNode(t);
        }
        nodes.remove(key);
        nodeCount--;
        modeCount++;
        return n;
    }

    @Override
    public void removeEdge(int node1, int node2) {
        if (hasEdge(node1,node2)) return;
        node_data n1 = getNode(node1), n2 = getNode(node2);
        n1.removeNode(n2);
        n2.removeNode(n1);
        edgeCount--;
        modeCount++;
    }

    @Override
    public int nodeSize() {
        return nodeCount;
    }

    @Override
    public int edgeSize() {
        return edgeCount;
    }

    @Override
    public int getMC() {
        return modeCount;
    }

}
