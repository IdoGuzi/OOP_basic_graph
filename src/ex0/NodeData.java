package ex0;

import java.util.Collection;
import java.util.HashMap;

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

    @Override
    public int getKey() {
        return key;
    }

    @Override
    public Collection<node_data> getNi() {
        return neighbor.values();
    }

    @Override
    public boolean hasNi(int key) {
        if (neighbor.get(key)!=null) return true;
        return false;
    }

    @Override
    public void addNi(node_data t) {
        neighbor.put(t.getKey(),t);
    }

    @Override
    public void removeNode(node_data node) {
        if (!neighbor.containsKey(node.getKey())) return;
        neighbor.remove(node.getKey());
    }

    @Override
    public String getInfo() {
        return info;
    }

    @Override
    public void setInfo(String s) {
        info=s;
    }

    @Override
    public int getTag() {
        return tag;
    }

    @Override
    public void setTag(int t) {
        tag=t;
    }
}
