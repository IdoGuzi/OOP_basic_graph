package ex0;

import java.util.*;

/**
 * this class is an implementation of the @graph_algorithms interface.
 * @author Ido Guzi
 */
public class Graph_Algo implements graph_algorithms{
    private graph g;

    public Graph_Algo(){
        g = new Graph_DS();
    }

    public Graph_Algo(graph g){
        this.g=g;
    }

    /**
     * assign a graph to this Graph_Algo.
     * @param g - graph to init
     */
    @Override
    public void init(graph g) {
        this.g=g;
    }

    /**
     * create a deep copy of the graph of this Graph_Algo
     * @return graph deep copy
     */
    @Override
    public graph copy() {
        graph cop = new Graph_DS(g);
        return cop;
    }

    /**
     * return true iff there is a path every node to every other node.
     * @return
     */
    @Override
    public boolean isConnected() {
        Collection<node_data> col = g.getV();
        if (col.size()==0 || col.size()==1) return true;
        Iterator<node_data> it = col.iterator();
        int a=it.next().getKey(),b=it.next().getKey();
        BFS(a,b);
        it = col.iterator();
        while(it.hasNext()){
            node_data n = it.next();
            if (n.getTag()!=2) return false;
        }
        return true;
    }

    /**
     * return the length of the shortest path between the given nodes,
     * -1 if path doesn't exist.
     * @param src - start node
     * @param dest - end (target) node
     * @return the length of the shortest path.
     */
    @Override
    public int shortestPathDist(int src, int dest) {
        List<node_data> path = shortestPath(src, dest);
        if (path.size()==0 || path.size()==1) return  -1;
        return path.size()-1;
    }

    /**
     * returns the shortest path between src to dest - as an ordered List of nodes.
     * @param src - start node
     * @param dest - end (target) node
     * @return a list of node_data
     */
    @Override
    public List<node_data> shortestPath(int src, int dest) {
        if (g.getNode(src)==null || g.getNode(dest)==null) {
            throw new RuntimeException("ERROR: invalid input");
        }
        HashMap<node_data, node_data> map = BFS(src,dest);
        node_data d = g.getNode(dest);
        LinkedList<node_data> path = new LinkedList<node_data>();
        while (d!=null){
            path.addFirst(d);
            d = map.get(d);
        }
        return path;
    }



    //********** private *********

    /**
     * change the tag of all the nodes in the graph,
     * should be used before doing some algorithms on the graph.
     */
    private void clearTags(){
        //make all nodes black
        Collection<node_data> nodes = g.getV();
        Iterator<node_data> it = nodes.iterator();
        while (it.hasNext()){
            node_data n = it.next();
            n.setTag(0);
        }
    }


    private HashMap<node_data,node_data> BFS(int src, int dst){
        clearTags();
        node_data s = g.getNode(src);
        HashMap<node_data, node_data> parent = new HashMap<node_data, node_data>();
        LinkedList<node_data> queue = new LinkedList<node_data>();
        parent.put(s,null);
        queue.add(s);
        s.setTag(1);
        while (!queue.isEmpty()){
            node_data u = queue.poll();
            Iterator<node_data> it = u.getNi().iterator();
            while(it.hasNext()){
                node_data v = it.next();
                if (v.getTag()==0){
                    v.setTag(1);
                    parent.put(v,u);
                    queue.add(v);
                }
            }
            u.setTag(2);
        }
        return parent;

    }


}
