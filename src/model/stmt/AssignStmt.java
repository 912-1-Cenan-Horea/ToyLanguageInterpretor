package model.stmt;

import model.container.IDict;
import model.container.IHeap;
import model.container.IStack;
import model.expression.Exp;
import model.prgstate.PrgState;
import model.type.Type;
import model.value.Value;

public class AssignStmt implements IStmt {
    String id;
    Exp exp;

    public AssignStmt(String _id, Exp e) {
        id = _id;
        exp = e;
    }


    @Override
    public PrgState execute(PrgState state) throws Exception {
        IDict<String, Value> symTbl = state.getSymTable();
        IHeap hp = state.getHeap();
        if (symTbl.isDefined(id)) {
            Value val = exp.eval(symTbl, hp);
            Type typId = (symTbl.lookup(id)).getType();
            if (val.getType().equals(typId))
                symTbl.update(id, val);
            else
                throw new Exception("declared type of variable" + id + " and type of  the assigned expression do not match");
        } else
            throw new Exception("the used variable" + id + " was not declared before");
        return null;
    }

    @Override
    public IDict<String, Type> typecheck(IDict<String, Type> typeEnv) throws Exception {
        Type typevar = typeEnv.lookup(id);
        Type typexp = exp.typecheck(typeEnv);
        if (typevar.equals(typexp)) return typeEnv;
        else throw new Exception("Assignment: right hand side and left hand side have different types ");
    }



    @Override
    public AssignStmt clone() {
        return new AssignStmt(id, exp.clone());
    }

    @Override
    public String toString() {
        return id + "=" + exp.toString();
    }
}
