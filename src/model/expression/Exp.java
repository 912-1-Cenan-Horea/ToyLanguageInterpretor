package model.expression;

import model.container.IDict;
import model.container.IHeap;
import model.type.Type;
import model.value.Value;

public interface Exp {
    Value eval(IDict<String, Value> symTbl, IHeap hp) throws Exception;
    Type typecheck(IDict<String, Type> typeEnv) throws Exception;
    Exp clone();
}
