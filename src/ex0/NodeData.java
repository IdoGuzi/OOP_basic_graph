package ex0;

import java.util.Collection;

public class NodeData implements node_data{
    @Override
    public int getKey() {
        return 0;
    }

    @Override
    public Collection<node_data> getNi() {
        return null;
    }

    @Override
    public boolean hasNi(int key) {
        return false;
    }

    @Override
    public void addNi(node_data t) {

    }

    @Override
    public void removeNode(node_data node) {

    }

    @Override
    public String getInfo() {
        return null;
    }

    @Override
    public void setInfo(String s) {

    }

    @Override
    public int getTag() {
        return 0;
    }

    @Override
    public void setTag(int t) {

    }
}
