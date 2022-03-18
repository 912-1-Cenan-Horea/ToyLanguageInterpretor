package model.container;

import model.Exception.ContainerException;
import model.value.Value;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class MyHeap implements IHeap {
    HashMap<Integer, Value> map;
    int first_free;

    public MyHeap() {
        map = new HashMap<Integer, Value>();
        first_free = 1;
    }

    public MyHeap(MyHeap other) {
        map = (HashMap<Integer, Value>) other.map.clone();
        first_free = other.first_free;
    }

    @Override
    public boolean isDefined(int id) {
        return map.containsKey(id);
    }

    @Override
    public Value lookup(int id) {
        return map.get(id);
    }

    @Override
    public int add(Value val) {
        int ret = first_free;
        map.put(first_free, val);
        while (map.containsKey(first_free))
            first_free++;
        return ret;
    }


    @Override
    public int get_key(Value val) {

        for (int a : map.keySet()) {
            if (map.get(a).equals(val))
                return a;
        }
        return 0;
    }

    @Override
    public void update(int id, Value val) throws ContainerException {

        if (!map.containsKey(id))
            throw new ContainerException("Key " + Integer.toString(id) + " you are trying to update doesnt exist\n");
        map.put(id, val);
    }

    @Override
    public void remove(int id) {
        map.remove(id);
        first_free = id;
    }

    @Override
    public IHeap clone() {
        return new MyHeap(this);
    }

    @Override
    public boolean isEmpty() {
        return map.isEmpty();
    }

    @Override
    public Map<Integer, Value> getContent() {
        return map;
    }

    @Override
    public void setContent(Map<Integer, Value> other) {
        map = (HashMap<Integer, Value>) other;
    }

    @Override
    public String toString() {
        return map.toString();
    }

    @Override
    public Iterator<Integer> iterator() {
        return map.keySet().iterator();
    }
}
