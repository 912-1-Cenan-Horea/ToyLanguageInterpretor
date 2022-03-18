package model.container;

import java.util.Collection;
import java.util.List;

public interface IList<T> extends Iterable<T>,List<T> {

    boolean add(T val);
    T get(int idx);
    IList<T> clone();
    Collection<T> getContent();
}
