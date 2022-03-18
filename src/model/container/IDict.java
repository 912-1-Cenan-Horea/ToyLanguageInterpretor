package model.container;

import model.value.Value;

import java.util.Map;

public interface IDict<K, V> extends Iterable<K> {

    boolean isDefined(K id);

    V lookup(K id);

    void update(K id, V val);

    void remove(K id);

    IDict<K, V> clone();

    boolean isEmpty();

    Map<K, V> getContent();
}
