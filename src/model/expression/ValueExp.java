package model.expression;

import model.container.IDict;
import model.container.IHeap;
import model.type.Type;
import model.value.Value;

public class ValueExp implements Exp {
    Value value;

    public ValueExp(Value val) {
        value = val;
    }

    @Override
    public Value eval(IDict<String, Value> symTbl, IHeap hp) {
        return value;
    }

    @Override
    public Type typecheck(IDict<String, Type> typeEnv) throws Exception {
        return value.getType();
    }


    @Override
    public ValueExp clone() {
        return new ValueExp(value.clone());
    }

    @Override
    public String toString() {
        return value.toString();
    }
}
