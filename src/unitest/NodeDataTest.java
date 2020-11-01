package unitest;

import ex0.*;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

/**
 * this class is a UnitTest class for the NodeData class
 * that ensure the class methods are working as intended.
 */
class NodeDataTest {
    private static Random rand = new Random();

    /**
     * basic tests for some of the methods.
     */
    @Test
    void generalTesting(){
        node_data v, n = new NodeData();
        for (int i=0;i<10;i++){
            v = new NodeData();
            n.addNi(v);
            v.addNi(n);
            assertEquals(v.getKey(), i+1);
            assertEquals(n.getNi().size(), i+1);
            assertEquals(v.getNi().size(), 1);
            assertTrue(n.hasNi(v.getKey()));
            assertTrue(v.hasNi(n.getKey()));
            assertEquals(n.hasNi(50), false);
        }

    }

    /**
     * basic testing for the getters and setters.
     */
    @Test
    void settersGetters(){
        node_data n = new NodeData();
        n.setInfo("node");
        if (n.getInfo()!="node")    fail("Error: shouldn't fail, string are equals");
        n.setInfo("hello, world!");
        if (n.getInfo()!="hello, world!")   fail("Error: shouldn't fail, string are equals");
        if (n.getTag()!=0)      fail("Error: tag should be 0 on initaliztion");
        n.setTag(1);
        if (n.getTag()!=1)      fail("Error: tag should be 1");

    }

    /**
     * test function for the getKey method.
     */
    @Test
    void getKey(){
        ArrayList<node_data> a = factory(47);
        for (int i=0;i<a.size();i++){
            assertEquals(a.get(i).getKey(), i);
        }
    }

    /**
     * test function for the getNi method.
     */
    @Test
    void getNi(){
        ArrayList<node_data> a = factory(20);
        ArrayList<Integer> ni = new ArrayList<>();
        for (int i=1;i<a.size();i++){
            int n = nextRnd(0,100);
            if (n>=50) {
                a.get(0).addNi(a.get(i));
                ni.add(i);
            }
        }
        for (int i=0;i<ni.size();i++){
            assertEquals(true, a.get(0).hasNi(ni.get(i)));
        }

    }

    /**
     * test function for the addNi method.
     */
    @Test
    void addNi(){
        ArrayList<node_data> a = factory(34);
        assertEquals(a.get(0).getNi().size(),0);
        for (int i=1;i<a.size();i++){
            a.get(0).addNi(a.get(i));
            assertEquals(a.get(0).getNi().size(),i);
        }
    }

    /**
     * test function for the removeNode method.
     */
    @Test
    void removeNode(){
        ArrayList<node_data> a = factory(32);
        node_data n = a.get(0);
        for (int i=1;i<a.size();i++){
            n.addNi(a.get(i));
        }
        assertEquals(a.get(0).getNi().size(), a.size()-1);
        for (int i=1;i<a.size();i++){
            boolean before = n.hasNi(i);
            assertEquals(before, true);
            n.removeNode(a.get(i));
            assertEquals(n.hasNi(a.get(i).getKey()), false);
        }
    }

    /**
     * this function construct an arraylist of nodes
     * to clean some of the test function.
     * @param nodes - number of nodes to cconstruct.
     * @return an arraylist with nodes.
     */
    private ArrayList<node_data> factory(int nodes){
        ArrayList<node_data> arr = new ArrayList<>();
        for (int i=0;i<nodes;i++){
            arr.add(i,new NodeData(i));
        }
        return arr;
    }

    private int nextRnd(int min,int max){
        double d = rand.nextDouble();
        double dx = max-min;
        double ans = d*dx+min;
        return (int)ans;
    }


}