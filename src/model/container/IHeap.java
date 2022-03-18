package model.container;

import model.value.Value;

import java.util.Map;

public interface IHeap extends Iterable {

    boolean isDefined(int id);

    Value lookup(int id);

    public int add(Value val);


    public int get_key(Value val);

    void update(int id, Value val) throws Exception;

    void remove(int id);

    IHeap clone();

    boolean isEmpty();

    Map<Integer, Value> getContent();

    public void setContent(Map<Integer, Value> other);
}
