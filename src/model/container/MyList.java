package model.container;



import java.util.*;

public class MyList<T> implements IList<T> {
    List<T> lst;

    public MyList() {
        lst = new Vector<T>();

    }

    public MyList(List<T> other) {
        lst = new Vector<T>();
        lst.addAll(other);
    }

    @Override
    public boolean add(T val) {
        lst.add(val);
        return true;
    }

    @Override
    public boolean remove(Object o) {
        return false;
    }

    @Override
    public boolean containsAll( Collection<?> c) {
        return false;
    }

    @Override
    public boolean addAll( Collection<? extends T> c) {
        lst.addAll(c);
        return false;
    }

    @Override
    public boolean addAll(int index,  Collection<? extends T> c) {
        return false;
    }

    @Override
    public boolean removeAll( Collection<?> c) {
        return false;
    }

    @Override
    public boolean retainAll( Collection<?> c) {
        return false;
    }

    @Override
    public void clear() {

    }

    @Override
    public T get(int idx) {
        return lst.get(idx);
    }

    @Override
    public T set(int index, T element) {
        return null;
    }

    @Override
    public void add(int index, T element) {

    }

    @Override
    public T remove(int index) {
        return null;
    }

    @Override
    public int indexOf(Object o) {
        return 0;
    }

    @Override
    public int lastIndexOf(Object o) {
        return 0;
    }


    @Override
    public ListIterator<T> listIterator() {
        return null;
    }


    @Override
    public ListIterator<T> listIterator(int index) {
        return null;
    }


    @Override
    public List<T> subList(int fromIndex, int toIndex) {
        return null;
    }

    @Override
    public MyList<T> clone() {
        return new MyList<T>(this);
    }

    @Override
    public Collection<T> getContent() {
        return this.lst;
    }


    @Override
    public String toString() {
        return lst.toString();
    }

    @Override
    public int size() {
        return lst.size();
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public boolean contains(Object o) {
        return false;
    }


    @Override
    public Iterator<T> iterator() {
        return lst.iterator();
    }


    @Override
    public Object[] toArray() {
        return new Object[0];
    }


    @Override
    public <T1> T1[] toArray(T1[] a) {
        return null;
    }
}
