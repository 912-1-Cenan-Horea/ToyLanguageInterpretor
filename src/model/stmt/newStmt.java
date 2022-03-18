package model.stmt;

import model.Exception.StatementException;
import model.container.IDict;
import model.container.IHeap;
import model.expression.Exp;
import model.prgstate.PrgState;
import model.type.RefType;
import model.type.Type;
import model.value.RefValue;
import model.value.Value;

public class newStmt implements IStmt {

    String id;
    Exp e;

    public newStmt(String _id, Exp _e) {
        id = _id;
        e = _e;
    }

    @Override
    public PrgState execute(PrgState state) throws Exception {
        IDict<String, Value> symtbl = state.getSymTable();
        IHeap hp = state.getHeap();
        if (!symtbl.isDefined(id))
            throw new StatementException("Symbol " + id + " is not defined\n");

        if (!(symtbl.lookup(id).getType() instanceof RefType))
            throw new StatementException("Symbol " + id + " is not of type RefType\n");

        Value v = e.eval(symtbl, hp).clone();
        Type t = ((RefValue) symtbl.lookup(id)).getLocationType();
        if (!(v.getType().equals(t)))
            throw new StatementException("Incompatible types beetween " + v.toString() + " and " + t.toString());
        IHeap h = state.getHeap();
        int k = h.add(v);
        RefValue r = (RefValue) symtbl.lookup(id);
        r.setAddress(k);
        return null;
    }

    @Override
    public IDict<String, Type> typecheck(IDict<String, Type> typeEnv) throws Exception {
        Type typevar = typeEnv.lookup(id);
        Type typexp = e.typecheck(typeEnv);
        if (typevar.equals(new RefType(typexp)))
            return typeEnv;
        else throw new Exception("NEW stmt: right hand side and left hand side have different types ");
    }

    @Override
    public String toString() {
        return "new(" + id + "," + e.toString() + ")";
    }

    @Override
    public IStmt clone() {
        return new newStmt(id, e.clone());
    }
}
