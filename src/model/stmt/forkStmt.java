package model.stmt;

import model.container.*;
import model.prgstate.PrgState;
import model.type.Type;
import model.value.Value;

import java.io.BufferedReader;
import java.util.Map;

public class forkStmt implements IStmt {
    IStmt stmt;


    public forkStmt(IStmt _stmt) {
        stmt = _stmt;
    }

    @Override
    public PrgState execute(PrgState state) throws Exception {
        MyDict<String, Value> new_symtbl = new MyDict<String, Value>((state.getSymTable()).clone());
        MyStack<IStmt> stk = new MyStack<IStmt>();
        return new PrgState(stk, new_symtbl, state.getOutput(), state.getFileTable(), stmt.clone(), state.getHeap());
        // return new PrgState(stk,new_symtbl,out,fileTable,state.getHeap());
    }

    @Override
    public IDict<String, Type> typecheck(IDict<String, Type> typeEnv) throws Exception {
        stmt.typecheck(typeEnv);
        return typeEnv;
    }

    @Override
    public String toString() {
        return "fork[" + stmt +
                ']';
    }

    @Override
    public IStmt clone() {
        return new forkStmt(stmt.clone());
    }
}
