package unitest;

import ex0.*;
import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.Iterator;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class Graph_DSTest {

    @Test
    void getNode() {
        graph g = factory(50,137);
        Iterator<node_data> it = g.getV().iterator();
        while (it.hasNext()) {
            node_data n = it.next();
            assertEquals(n, g.getNode(n.getKey()));
        }
    }

    @Test
    void hasEdge() {
        graph g = factory(50,150);
        int countByNi = 0;
        int countOneOne =0;
        Iterator<node_data> it = g.getV().iterator();
        while (it.hasNext()) {
            node_data n = it.next();
            Collection<node_data> col = g.getV(n.getKey());
            Iterator<node_data> iter = col.iterator();
            countByNi += col.size();
            while (iter.hasNext()){
                node_data v = iter.next();
                if (n.hasNi(v.getKey()) && v.hasNi(n.getKey())) {
                    assertEquals(g.hasEdge(n.getKey(), v.getKey()),true);
                    countOneOne++;
                }else if((n.hasNi(v.getKey()) && !v.hasNi(n.getKey()))||
                         (v.hasNi(n.getKey()) && !n.hasNi(v.getKey()))){
                            fail("Error: both should be connected.");
                }else assertEquals(g.hasEdge(n.getKey(), v.getKey()),false);
            }
        }
        //sum=sum/2;
        assertEquals(g.edgeSize(), countByNi/2);
        assertEquals(g.edgeSize(),countOneOne/2);
    }

    @Test
    void addNode() {
        graph g = new Graph_DS();
        int count =0;
        for (int i=0;i<65;i++){
            node_data n = new NodeData();
            g.addNode(n);
            count++;
            if (count!=g.nodeSize()) fail("Error: missed a node");
        }
    }

    @Test
    void connect() {
        graph g = new Graph_DS();
        int count =0;
        for (int i=0;i<65;i++){
            node_data n = new NodeData(i);
            g.addNode(n);
        }
        while(g.edgeSize() < g.nodeSize()*3) {
            int a = Graph_Ex0_Test.nextRnd(0,g.nodeSize());
            int b = Graph_Ex0_Test.nextRnd(0,g.nodeSize());
            boolean before = g.hasEdge(a,b);
            g.connect(a,b);
            if (!before && g.hasEdge(a,b)) {
                count++;
            }else if ((!before && !g.hasEdge(a,b)) &&
                    g.getNode(a).hasNi(b) && g.getNode(b).hasNi(a)){
                fail("Error: hasEdge don't work. details: hasEdge="+g.hasEdge(a,b)+", but a.hasni(b)="+g.getNode(a).hasNi(b)+", and b.hasni(a)="+g.getNode(b).hasNi(a));
            }
            if (g.edgeSize()!=count)    fail("Error: counts don't match");
        }

    }

    @Test
    void getV() {
        graph g = factory(74,200);
        Iterator<node_data> it = g.getV().iterator();
        while (it.hasNext()){
            node_data n = it.next();
            if (n!=g.getNode(n.getKey())) fail("Error: nodes should be the same");
        }
    }

    @Test
    void testGetV() {
        graph g = factory(67,154);
        Iterator<node_data> iterator = g.getV().iterator();
        while (iterator.hasNext()){
            node_data n = iterator.next();
            Iterator<node_data> it = g.getV(n.getKey()).iterator();
            while (it.hasNext()){
                node_data v = it.next();
                if (!g.hasEdge(n.getKey(), v.getKey())) fail("Error: should have edge");
            }
        }
    }

    @Test
    void removeNode() {
        graph g = factory(67,154);
        for (int i=66;i>=0;i--){
            g.removeNode(i);
            Iterator<node_data> iterator = g.getV().iterator();
            while (iterator.hasNext()) {
                node_data n = iterator.next();
                assertEquals(g.hasEdge(n.getKey(),i),false);
                assertEquals(g.getNode(i),null);
            }
        }
    }

    @Test
    void removeEdge() {
        graph g = factory(100,300);
        while (g.edgeSize()>=200) {
            int a = Graph_Ex0_Test.nextRnd(0, g.nodeSize());
            int b = Graph_Ex0_Test.nextRnd(0, g.nodeSize());
            boolean before = g.hasEdge(a,b);
            g.removeEdge(a,b);
            if (before){
                if (g.hasEdge(a,b)) fail("Error: edge should have been deleted");
            }
        }

    }

    @Test
    void nodeSize() {
        graph g = factory(30,68);
        assertEquals(g.nodeSize(),30);
        for (int i=0;i <40;i++){
            g.addNode(new NodeData(30+i));
            assertEquals(g.nodeSize(),30+i+1);
        }
    }

    @Test
    void edgeSize() {
        graph g = factory(47,0);
        int counter=0;
        while (counter<150){
            int a = Graph_Ex0_Test.nextRnd(0, g.nodeSize());
            int b = Graph_Ex0_Test.nextRnd(0, g.nodeSize());
            int beforeSize = g.edgeSize();
            boolean beforeCon = g.hasEdge(a,b);
            if (!beforeCon) {
                g.connect(a, b);
                if (g.hasEdge(a, b) && beforeSize+1!=g.edgeSize()){
                    fail("Error: didn't increment edge size but added an edge");
                }
            }
            counter++;
        }
    }


    private graph factory(int nodes, int edges){
        graph g = new Graph_DS();
        for (int i=0;i<nodes;i++){
            g.addNode(new NodeData(i));
        }
        int count = 0;
        while(g.edgeSize() < edges) {
            int a = Graph_Ex0_Test.nextRnd(0,nodes);
            int b = Graph_Ex0_Test.nextRnd(0,nodes);
            boolean beforeCon = g.hasEdge(a,b);
            g.connect(a,b);
            if (!beforeCon && g.hasEdge(a,b)) count++;
            node_data n=g.getNode(a),v=g.getNode(b);
            if (!g.hasEdge(a,b) &&
                    (n.hasNi(v.getKey()) && !v.hasNi(n.getKey())) &&
                    (v.hasNi(n.getKey()) && !n.hasNi(v.getKey()))){
                fail("Error: didn't connect");
            }
        }
        if (count != edges) fail("Error: didn't add to amount, count="+count+", edges="+edges);
        if (g.edgeSize()!=edges) fail("Error: didn't add to amount");
        return g;
    }

}