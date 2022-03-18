package model.stmt;

import model.container.IDict;
import model.container.IHeap;
import model.container.IList;
import model.container.IStack;
import model.expression.Exp;
import model.prgstate.PrgState;
import model.type.BoolType;
import model.type.Type;
import model.value.BoolValue;
import model.value.Value;

public class IfStmt implements IStmt {
    Exp exp;
    IStmt thenS;
    IStmt elseS;

    public IfStmt(Exp e, IStmt t, IStmt el) {
        exp = e;
        thenS = t;
        elseS = el;
    }


    @Override
    public PrgState execute(PrgState state) throws Exception {
        IStack<IStmt> stk = state.getStk();
        IHeap hp = state.getHeap();
        IDict<String, Value> symTbl = state.getSymTable();
        if (!exp.eval(symTbl, hp).getType().equals(new BoolType()))
            throw new Exception("conditional expr is not a boolean!\n");
        else if (((BoolValue) exp.eval(symTbl, hp)).isTrue()) {
            stk.push(thenS);
        } else {
            stk.push(elseS);
        }

        return null;
    }

    @Override
    public IDict<String, Type> typecheck(IDict<String, Type> typeEnv) throws Exception {
        Type typexp = exp.typecheck(typeEnv);
        if (typexp.equals(new BoolType())) {
            thenS.typecheck(typeEnv.clone());
            elseS.typecheck(typeEnv.clone());
            return typeEnv;
        } else throw new Exception("The condition of IF has not the type bool");
    }

    @Override
    public IfStmt clone() {
        return new IfStmt(exp.clone(), thenS.clone(), elseS.clone());
    }

    @Override
    public String toString() {
        return "IF(" + exp.toString() + ") THEN(" + thenS.toString() + ")ELSE(" + elseS.toString() + ")";
    }
}
