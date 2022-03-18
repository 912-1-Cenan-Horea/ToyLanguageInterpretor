package model.stmt;

import model.Exception.StatementException;
import model.container.IDict;
import model.container.IHeap;
import model.expression.Exp;
import model.prgstate.PrgState;
import model.type.Type;
import model.value.RefValue;
import model.value.Value;

public class wH implements IStmt {
    String id;
    Exp e;

    public wH(String _id, Exp _e) {
        id = _id;
        e = _e;
    }

    @Override
    public PrgState execute(PrgState state) throws Exception {
        IDict<String, Value> symTbl = state.getSymTable();
        IHeap hp = state.getHeap();
        if (!symTbl.isDefined(id))
            throw new StatementException("Symbol " + id + " is not defined");
        if (!(symTbl.lookup(id) instanceof RefValue))
            throw new StatementException("Symbol " + id + " is not of type Ref");
        RefValue location = ((RefValue) symTbl.lookup(id));
        if (!hp.isDefined(location.getAddr()))
            throw new StatementException("Adress " + Integer.toString(location.getAddr()) + " is not valid");
        Value v = e.eval(symTbl, hp);
        if (!(v.getType().equals(location.getLocationType()))) {
            throw new StatementException("Expression " + e.toString() + " does not have the required " + v.getType().toString() + " type");
        }
        hp.update(location.getAddr(), v);
        return null;
    }

    @Override
    public IDict<String, Type> typecheck(IDict<String, Type> typeEnv) throws Exception {
        e.typecheck(typeEnv);
        return typeEnv;
    }

    @Override
    public String toString() {
        return "wH(" + id + "," + e.toString() + ")";
    }

    @Override
    public IStmt clone() {
        return new wH(id, e.clone());
    }
}
