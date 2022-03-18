package model.stmt;

import model.Exception.StatementException;
import model.container.IDict;
import model.container.IHeap;
import model.container.IStack;
import model.expression.Exp;
import model.prgstate.PrgState;
import model.type.BoolType;
import model.type.Type;
import model.value.BoolValue;
import model.value.Value;

public class WhileStmt implements IStmt {
    Exp e;
    IStmt st;

    public WhileStmt(Exp _e, IStmt s) {
        e = _e;
        st = s;
    }

    @Override
    public PrgState execute(PrgState state) throws Exception {
        IDict<String, Value> symTbl = state.getSymTable();
        IHeap hp = state.getHeap();
        IStack<IStmt> stk = state.getStk();
        if (!((e.eval(symTbl, hp)) instanceof BoolValue))
            throw new StatementException("Expression " + e.toString() + " is not of type bool");
        boolean is_true = ((BoolValue) e.eval(symTbl, hp)).getVal();
        if (is_true) {
            stk.push(this);
            stk.push(st);
        }
        return null;
    }

    @Override
    public IDict<String, Type> typecheck(IDict<String, Type> typeEnv) throws Exception {
        Type typexp = e.typecheck(typeEnv);
        if (typexp.equals(new BoolType())) {
            st.typecheck(typeEnv.clone());
            return typeEnv;
        } else throw new Exception("The condition of WHILE has not the type bool");
    }

    @Override
    public String toString() {
        return "While(" + e.toString() + "){" + st.toString() + "}";
    }

    @Override
    public IStmt clone() {
        return new WhileStmt(e.clone(), st.clone());
    }
}
