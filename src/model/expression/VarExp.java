package model.expression;

import model.container.IDict;
import model.container.IHeap;
import model.type.Type;
import model.value.Value;

public class VarExp implements Exp {
    String id;

    public VarExp(String v) {
        id = v;
    }

    @Override
    public Value eval(IDict<String, Value> symTbl, IHeap hp) throws Exception {
        if (!symTbl.isDefined(id))
            throw new ArithmeticException("Symbol " + id + " is not defined\n");
        return symTbl.lookup(id);
    }

    @Override
    public Type typecheck(IDict<String, Type> typeEnv) throws Exception {
        return typeEnv.lookup(id);
    }

    @Override
    public VarExp clone() {
        return new VarExp(id);
    }

    @Override
    public String toString() {
        return id;
    }
}
