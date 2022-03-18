package model.expression;

import model.Exception.ExpressionException;
import model.container.IDict;
import model.container.IHeap;
import model.type.RefType;
import model.type.Type;
import model.value.RefValue;
import model.value.Value;

public class rH implements Exp {
    Exp e;

    public rH(Exp _e) {
        e = _e;
    }

    @Override
    public Value eval(IDict<String, Value> symTbl, IHeap hp) throws Exception {
        Value v = e.eval(symTbl, hp);
        if (!(v instanceof RefValue))
            throw new ExpressionException("Expression " + e.toString() + " is not of refference type");
        int addr = ((RefValue) v).getAddr();
        if (!hp.isDefined(addr))
            throw new ExpressionException("Adress " + Integer.toString(addr) + " is not allocated");
        return hp.lookup(addr);

    }

    @Override
    public Type typecheck(IDict<String, Type> typeEnv) throws Exception {
        Type typ = e.typecheck(typeEnv);
        if (typ instanceof RefType) {
            RefType reft = (RefType) typ;
            return reft.getInner();
        } else throw new Exception("the rH argument is not a Ref Type");
    }

    @Override
    public String toString() {
        return "rH(" + e.toString() + ")";
    }

    @Override
    public Exp clone() {
        return new rH(e.clone());
    }
}
