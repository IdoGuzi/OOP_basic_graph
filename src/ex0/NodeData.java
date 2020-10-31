package ex0;

import java.util.Collection;
import java.util.HashMap;


/**
 * this class is an implementation of the interface @node_data,
 * representing a node (vertex) in an undirectional unweighted graph.
 * @author Ido Guzi
 */
public class NodeData implements node_data{
    private int key, tag;
    private HashMap<Integer, node_data> neighbor;
    private String info;
    private static int keyCount=0;


    public NodeData(){
        key=keyCount++;
        tag=0;
        neighbor = new HashMap<>();
    }

    public NodeData(int key){
        this.key=key;
        tag=0;
        neighbor = new HashMap<>();
    }

    public NodeData(node_data n){
        this.key=n.getKey();
        this.tag=n.getTag();
        this.info=n.getInfo();
        neighbor = new HashMap<>();
    }

    /**
     * returns an int representing the key (id) of the node.
     * @return the key of the node
     */
    @Override
    public int getKey() {
        return key;
    }

    /**
     * return a collection of node_data representing the neighbor node of this node
     * @return a collection of node_data
     */
    @Override
    public Collection<node_data> getNi() {
        return neighbor.values();
    }

    /**
     * return true if there is a node_data with the specific key that is a neighbor if this node.
     * @param key
     * @return true if this and the node_data associated with key are neighbors
     */
    @Override
    public boolean hasNi(int key) {
        if (neighbor.get(key)!=null) return true;
        return false;
    }

    /**
     * adding t to the neighbor list of this node.
     * @param t
     */
    @Override
    public void addNi(node_data t) {
        neighbor.put(t.getKey(),t);
    }

    /**
     * remove node from the neighbor list of this node.
     * @param node
     */
    @Override
    public void removeNode(node_data node) {
        if (!neighbor.containsKey(node.getKey())) return;
        neighbor.remove(node.getKey());
    }

    /**
     * return meta data of this node.
     * @return a string containing data of this node.
     */
    @Override
    public String getInfo() {
        return info;
    }

    /**
     * change the string that contains data of this node.
     * @param s a string that will be the new remark of this node.
     */
    @Override
    public void setInfo(String s) {
        info=s;
    }

    /**
     * returns int that represent temporal data that can mark states of the node.
     * @return an integer that mark the node
     */
    @Override
    public int getTag() {
        return tag;
    }

    /**
     * changing the "tag" (temporal data) that can be used for all sorts of things.
     * @param t - the new value of the tag
     */
    @Override
    public void setTag(int t) {
        tag=t;
    }
}
