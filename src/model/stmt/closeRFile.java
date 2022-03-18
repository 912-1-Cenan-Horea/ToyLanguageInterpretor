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

public class closeRFile implements IStmt {
    Exp e;

    public closeRFile(Exp _e) {
        e = _e;
    }

    @Override
    public PrgState execute(PrgState state) throws Exception {
        IDict<String, Value> symTbl = state.getSymTable();
        IHeap hp = state.getHeap();
        if (!(e.eval(symTbl, hp).getType() instanceof StringType)) {
            throw new ExpressionException("Expression " + e.toString() + " does not evaluate to a string!\n");
        }
        String file = ((StringValue) e.eval(symTbl, hp)).getVal();
        if (!state.getFileTable().isDefined(file)) {
            throw new ExpressionException("Symbol " + file + " is not defined in the file table!\n");
        }
        BufferedReader b = state.getFileTable().lookup(file);
        b.close();
        state.getFileTable().remove(file);
        return null;
    }

    @Override
    public IDict<String, Type> typecheck(IDict<String, Type> typeEnv) throws Exception {
        Type typ = e.typecheck(typeEnv);
        if (typ.equals(new StringType()))
            return typeEnv;
        else throw new Exception("Type of CLOSE R FILE expression is not a string");
    }

    @Override
    public String toString() {
        return "closeRFile(" + e.toString() + ")";
    }

    @Override
    public IStmt clone() {
        return new closeRFile(e.clone());
    }
}
