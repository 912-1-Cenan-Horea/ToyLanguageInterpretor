package model.stmt;

import model.container.IDict;
import model.container.IStack;
import model.prgstate.PrgState;
import model.type.*;
import model.value.*;

public class VarDeclStmt implements IStmt, Cloneable {
    String name;
    Type type;

    public VarDeclStmt(String _name, Type _type) {
        name = _name;
        type = _type;
    }

    @Override
    public PrgState execute(PrgState state) throws Exception {
        IDict<String, Value> symTbl = state.getSymTable();
        if (symTbl.isDefined(name))
            throw new Exception("Symbol " + name + " is already defined");
        else {
            Value val;
            if (type.equals(new BoolType()))
                val = new BoolValue();
            else if (type.equals(new IntType()))
                val = new IntValue();
            else if (type.equals(new StringType()))
                val = new StringValue("");
            else if (type instanceof RefType)
                val = new RefValue(0, ((RefType) type).getInner());
            else
                throw new Exception("Invalid type of value\n");
            symTbl.update(name, val);
        }
        return null;
    }

    @Override
    public IDict<String, Type> typecheck(IDict<String, Type> typeEnv) throws Exception {
        typeEnv.update(name, type);
        return typeEnv;
    }

    @Override
    public String toString() {
        return type + " " + name;
    }

    @Override
    public VarDeclStmt clone() {
        return new VarDeclStmt(name, type);
    }
}

