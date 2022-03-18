package model.stmt;

import model.container.IDict;
import model.container.IStack;
import model.prgstate.PrgState;
import model.type.Type;

public class CompStmt implements IStmt {
    IStmt first;
    IStmt second;

    public CompStmt(IStmt f, IStmt s) {
        first = f;
        second = s;
    }



    @Override
    public String toString() {
        return "(" + first.toString() + ";" + second.toString() + ")";
    }

    @Override
    public PrgState execute(PrgState state) throws Exception {
        IStack<IStmt> stk = state.getStk();
        stk.push(second);
        stk.push(first);
        return null;
    }

    @Override
    public IDict<String, Type> typecheck(IDict<String, Type> typeEnv) throws Exception {
        return second.typecheck(first.typecheck(typeEnv));
    }

    @Override
    public CompStmt clone() {
        return new CompStmt(first.clone(),second.clone());
    }

    public IStmt getSecond() {
        return second;
    }

    public IStmt getFirst() {
        return first;
    }
}
