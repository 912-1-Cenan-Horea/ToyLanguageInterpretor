package model.stmt;

import model.Exception.ExpressionException;
import model.container.IDict;
import model.container.IHeap;
import model.expression.Exp;
import model.prgstate.PrgState;
import model.type.StringType;
import model.type.Type;
import model.value.StringValue;
import model.value.Value;

import java.io.BufferedReader;
import java.io.FileReader;

public class openRFile implements IStmt {
    Exp e;

    public openRFile(Exp exp) {
        e = exp;
    }

    @Override
    public PrgState execute(PrgState state) throws Exception {
        IDict<String, Value> symTbl = state.getSymTable();
        IHeap hp = state.getHeap();

        if (!(e.eval(symTbl, hp).getType() instanceof StringType))
            throw new ExpressionException("Provided argument for openRFile is not a String!\n");
        String file = ((StringValue) e.eval(symTbl, hp)).getVal();

        if (state.getFileTable().isDefined(file)) {
            throw new ExpressionException("File symbol " + file + " is already defined!\n");
        }
        BufferedReader buff = new BufferedReader(new FileReader(file));
        state.getFileTable().update(file, buff);
        return null;
    }

    @Override
    public IDict<String, Type> typecheck(IDict<String, Type> typeEnv) throws Exception {
        Type typ = e.typecheck(typeEnv);
        return typeEnv;

    }

    @Override
    public String toString() {
        return "openRFile(" + e.toString() + ")";
    }

    @Override
    public openRFile clone() {
        return new openRFile(e.clone());
    }

}
