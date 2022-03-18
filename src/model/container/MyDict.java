package model.container;

import model.value.Value;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class MyDict<K, V> implements IDict<K, V>, Iterable<K> {
    HashMap<K, V> map;

    public MyDict() {
        map = new HashMap<K, V>();
    }

    public MyDict(Map<K, V> _map) {
        map = (HashMap<K, V>) _map;
    }

    public MyDict(IDict<K, V> other) {
        map = (HashMap<K, V>) other.getContent();
    }

    @Override
    public boolean isDefined(K id) {
        return map.containsKey(id);
    }

    @Override
    public V lookup(K id) {
        return map.get(id);
    }

    @Override
    public void update(K id, V val) {
        map.put(id, val);
    }

    @Override
    public void remove(K id) {
        map.remove(id);
    }

    @Override
    public IDict<K, V> clone() {
        return new MyDict<K, V>(this);
    }

    @Override
    public boolean isEmpty() {
        return map.isEmpty();
    }

    @Override
    public Map<K, V> getContent() {
        return map;
    }

    @Override
    public String toString() {
        return map.toString();
    }


    @Override
    public Iterator<K> iterator() {
        return map.keySet().iterator();
    }
}
