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
        if (neighbor.size()>=key && neighbor.get(key-1).getKey()==key) return true;
        int indexOfNi = binarySearch(neighbor,key);
        if (indexOfNi>=-1) return true;
        return false;
    }

    @Override
    public void addNi(node_data t) {
        if (neighbor.size()>t.getKey()-1) {
            neighbor.add(t.getKey()-1, t);
        }else neighbor.add(t);
    }

    @Override
    public void removeNode(node_data node) {
        int indexOfNi = binarySearch(neighbor, node.getKey());
        if (indexOfNi!=-1) neighbor.remove(indexOfNi);
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



    //********* private ***********
    private int binarySearch(ArrayList<node_data> nodes, int key){
        int low=0, high;
        if (nodes.size()>=key) {high=key;} else{high=nodes.size()-1;}
        while (low<=high){
            int middle = (low+high)/2;
            int keyCheck = nodes.get(middle).getKey();
            if (keyCheck==key) return middle;
            if (keyCheck>key) {
                high = middle-1;
            }else low = middle+1;
        }
        return -1;
    }
}
