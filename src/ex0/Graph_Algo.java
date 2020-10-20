package ex0;

import java.util.*;

public class Graph_Algo implements graph_algorithms{
    private graph g;

    public Graph_Algo(){
        g = new Graph_DS();
    }

    public Graph_Algo(graph g){
        this.g=g;
    }

    @Override
    public void init(graph g) {
        this.g=g;
    }

    @Override
    public graph copy() {
        graph cop = new Graph_DS(g);
        return cop;
    }

    @Override
    public boolean isConnected() {
        clearTags();
        Iterator<node_data> it = g.getV().iterator();
        LinkedList<node_data> queue = new LinkedList<node_data>();
        node_data n = it.next();
        queue.add(n);
        n.setTag(1);
        while (!queue.isEmpty()) {
            n = queue.poll();
            it = n.getNi().iterator();
            while (it.hasNext()) {
                node_data v = it.next();
                if (v.getTag() == 0) {
                    v.setTag(1);
                    queue.add(v);
                }
            }
            n.setTag(2);
        }
        it = g.getV().iterator();
        while(it.hasNext()){
            n = it.next();
            if (n.getTag()!=2) return false;
        }
        return true;
    }

    @Override
    public int shortestPathDist(int src, int dest) {
        List<node_data> path = shortestPath(src, dest);
        return path.size();
    }

    @Override
    public List<node_data> shortestPath(int src, int dest) {
        if (g.getNode(src)==null || g.getNode(dest)==null) {
            throw new RuntimeException("ERROR: invalid input");
        }
        return BFS(src,dest);
    }



    //********** private *********
    private void clearTags(){
        //make all nodes black
        Collection<node_data> nodes = g.getV();
        Iterator<node_data> it = nodes.iterator();
        while (it.hasNext()){
            node_data n = it.next();
            n.setTag(0);
        }
    }

    private List<node_data> BFS(int src, int dst){
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
        node_data d = g.getNode(dst);
        LinkedList<node_data> path = new LinkedList<node_data>();
        while (d!=null){
            path.addFirst(d);
            d = parent.get(d);
        }
        return path;

    }


}
