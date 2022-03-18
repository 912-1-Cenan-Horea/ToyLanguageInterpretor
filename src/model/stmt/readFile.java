package model.stmt;

import model.Exception.ExpressionException;
import model.container.IDict;
import model.container.IHeap;
import model.expression.Exp;
import model.prgstate.PrgState;
import model.type.IntType;
import model.type.Type;
import model.value.IntValue;
import model.value.StringValue;
import model.value.Value;

import java.io.BufferedReader;

public class readFile implements IStmt{
    Exp e;
    String name;
    public readFile(Exp _e,String _name)
    {
        e = _e;
        name=_name;
    }


    @Override
    public PrgState execute(PrgState state) throws Exception {
        IDict<String, Value> symTbl = state.getSymTable();
        IHeap hp = state.getHeap();
        if(!symTbl.isDefined(name))
            throw new ExpressionException("Symbol "+name+" is not defined!\n");
        if(!(symTbl.lookup(name).getType() instanceof IntType))
        {
            throw new ExpressionException(name+" is not of type int!\n");
        }
        if(!(e.eval(symTbl,hp) instanceof StringValue))
        {
            throw new ExpressionException("Expression "+e.toString()+" is not a string!\n");
        }
        String file = ((StringValue)e.eval(symTbl,hp)).getVal();
        if(!state.getFileTable().isDefined(file))
        {
            throw new ExpressionException("File symbol "+file+" is not defined!\n");
        }
        BufferedReader b = state.getFileTable().lookup(file);
        String line = b.readLine();
        int value;
        if(line.isEmpty())
            value=0;
        else
        {
            value = Integer.parseInt(line);
        }
        symTbl.update(name,new IntValue(value));
        return null;
    }

    @Override
    public IDict<String, Type> typecheck(IDict<String, Type> typeEnv) throws Exception {
        e.typecheck(typeEnv);
        return typeEnv;
    }

    @Override
    public String toString() {
        return "readFile("+e.toString()+","+name+")";
    }

    @Override
    public IStmt clone() {
        return new readFile(e.clone(),name);
    }
}
