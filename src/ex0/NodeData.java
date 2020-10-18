package ex0;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class NodeData implements node_data{
    private int key, tag;
    private ArrayList<node_data> neighbor;
    private String info;
    private static int keyCount=1;


    public NodeData(){
        key=keyCount++;
    }

    @Override
    public int getKey() {
        return key;
    }

    @Override
    public Collection<node_data> getNi() {
        return neighbor;
    }

    @Override
    public boolean hasNi(int key) {
        Iterator<node_data> it = neighbor.iterator();
        while(it.hasNext()){
            node_data n = it.next();
            if (n.getKey()==key) return true;
        }
        return false;
    }

    @Override
    public void addNi(node_data t) {
        neighbor.add(t);
    }

    @Override
    public void removeNode(node_data node) {
        neighbor.remove(node);
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
