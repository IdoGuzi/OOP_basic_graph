package unitest;

import ex0.*;
import org.junit.jupiter.api.Test;

import java.util.Iterator;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

/**\
 * this class is a UnitTest for the @Graph_Algo,
 * the functions in this class testing the method in @Graph_Algo
 * to make sure they work correctly.
 * @author Ido Guzi
 */
class Graph_AlgoTest {
    private static Random rand=new Random();

    /**
     * test function for the init method.
     * checking a graph is assigned to this Graph_Algo
     */
    @Test
    void init() {
        graph g = factory(30,75);
        graph_algorithms ga = new Graph_Algo();
        assertEquals(true, assertExecption(ga,0,1));
        ga.init(g);
        assertEquals(false, assertExecption(ga,0,1));

    }

    /**
     * test function for the copy method.
     * assumptions: none.
     */
    @Test
    void copy() {
        graph g = factory(30,75);
        graph_algorithms ga = new Graph_Algo(g);
        graph g2 = ga.copy();
        for (int i=0;i<30;i++){
            assertNotEquals(g.getNode(i),g2.getNode(i));
        }
        for (int i=0;i<5;i++){
            g2.removeNode(i);
            g2.addNode(new NodeData(30+i));
            assertNotNull(g.getNode(i));
            assertNull(g.getNode(30+i));
        }
        assertNotEquals(g.edgeSize(),g2.edgeSize());
    }

    /**
     * test function for the isConnected method.
     */
    @Test
    void isConnected() {
        graph_algorithms ga = new Graph_Algo();
        for (int i=0;i<100;i++) {
            int a = nextRnd(2, 300);
            int b = nextRnd(0, a-1);
            graph notCon = factory(a, b);
            ga.init(notCon);
            assertEquals(false, ga.isConnected());
        }
        for (int i=0;i<100;i++) {
            int a = nextRnd(1, 300);
            //random number that promise the graph will be connected.
            int min=((a-1)*(a-1)-a+1)/2+1,max=((a*a)-a)/2;
            int b = nextRnd(min, max);
            graph con = factory(a, b);
            ga.init(con);
            assertEquals(true, ga.isConnected());
        }
    }

    /**
     * test function for the shortestPathDist method.
     */
    @Test
    void shortestPathDist() {
        graph_algorithms ga = new Graph_Algo();
        graph g = new Graph_DS();
        ga.init(g);
        for (int i=0;i<100;i++){
            g.addNode(new NodeData(i));
            g.connect(0,i);
        }
        for (int i=1;i<100;i++) {
            assertEquals(1, ga.shortestPathDist(0, i));
        }

    }

    /**
     * test function for the shortestPathDist method.
     */
    @Test
    void shortestPath() {
        graph g = factory(57, 0);
        graph_algorithms ga = new Graph_Algo();
        ga.init(g);
        for (int i=0;i<56;i++){
            g.connect(i,i+1);
        }
        List<node_data> path = ga.shortestPath(0,56);
        for (int i=0;i<path.size();i++){
            assertEquals(i, path.get(i).getKey());
        }
        g.connect(0,56);
        for (int i=56;i>55;i--){
            path = ga.shortestPath(0,i);
            assertEquals(56-i+1,path.size()-1);
            for (int j=1;j<path.size();j++) {
                assertEquals(56-j+1, path.get(j).getKey());
            }
        }

    }


    /**
     * create a graph with #nodes and #edges
     * @param nodes - number of nodes
     * @param edges - number of edges
     * @return a graph object
     */
    private graph factory(int nodes, int edges){
        graph g = new Graph_DS();
        for (int i=0;i<nodes;i++){
            g.addNode(new NodeData(i));
        }
        int count = 0;
        while(g.edgeSize() < edges) {
            int a = nextRnd(0, nodes);
            int b = nextRnd(0, nodes);
            boolean beforeCon = g.hasEdge(a, b);
            g.connect(a, b);
            if (!beforeCon && g.hasEdge(a, b)) count++;
            node_data n = g.getNode(a), v = g.getNode(b);
            if (a!=b && !g.hasEdge(a,b)) {fail("Error: didn't connect");}
            boolean nhv=n.hasNi(b),vhn=v.hasNi(a);
            if ((nhv && !vhn) || (vhn && !nhv)) fail("ERROR: impossible");

        }
        if (count != edges) fail("Error: didn't add to amount, count="+count+", edges="+edges);
        if (g.edgeSize()!=edges) fail("Error: didn't add to amount");
        return g;
    }

    private boolean assertExecption(graph_algorithms ga, int s,int d){
        try {
            ga.shortestPath(s,d);
        }catch (Exception e){
            return true;
        }
        return false;
    }

    private int nextRnd(int min,int max){
        double d = rand.nextDouble();
        double dx = max-min;
        double ans = d*dx+min;
        return (int)ans;
    }

}