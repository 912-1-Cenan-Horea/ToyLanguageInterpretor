package model.stmt;

import model.container.IDict;
import model.container.IHeap;
import model.container.IList;
import model.expression.Exp;
import model.prgstate.PrgState;
import model.type.Type;
import model.value.Value;

public class PrintStmt implements IStmt, Cloneable {
    Exp exp;

    public PrintStmt(Exp e) {
        exp = e;
    }

    @Override
    public PrgState execute(PrgState state) throws Exception {
        IHeap hp = state.getHeap();
        IList<Value> out = state.getOutput();
        IDict<String, Value> symTbl = state.getSymTable();
        out.add(exp.eval(symTbl, hp));
        return null;
    }

    @Override
    public IDict<String, Type> typecheck(IDict<String, Type> typeEnv) throws Exception {
        exp.typecheck(typeEnv);
        return typeEnv;
    }

    @Override
    public String toString() {
        return "print(" + exp.toString() + ")";
    }

    @Override
    public PrintStmt clone() {
        return new PrintStmt(exp.clone());

    }
}
