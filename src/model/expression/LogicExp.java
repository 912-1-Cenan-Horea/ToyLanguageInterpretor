package model.expression;

import model.container.IDict;
import model.container.IHeap;
import model.type.BoolType;
import model.type.IntType;
import model.type.Type;
import model.value.BoolValue;
import model.value.Value;

public class LogicExp implements Exp {
    Exp e1;
    Exp e2;
    int op; /// 1==and,2==or

    LogicExp(Exp _e1, Exp _e2, int _op) {
        e1 = _e1;
        e2 = _e2;
        op = _op;
    }


    @Override
    public Value eval(IDict<String, Value> symTbl, IHeap hp) throws Exception {
        Value v1, v2;
        v1 = e1.eval(symTbl,hp);
        if (v1.getType().equals(new BoolType())) {
            v2 = e2.eval(symTbl,hp);
            if (v2.getType().equals(new BoolType())) {
                BoolValue b1 = (BoolValue) v1;
                BoolValue b2 = (BoolValue) v2;
                if (op == 1)
                    return new BoolValue(b1.getVal() && b2.getVal());
                else if (op == 2)
                    return new BoolValue(b1.getVal() || b2.getVal());
                else throw new Exception("Invalid logic operator");

            } else throw new ArithmeticException("Second operand is not boolean\n");
        } else throw new ArithmeticException("First operand is not of type boolean\n");
    }

    @Override
    public Type typecheck(IDict<String, Type> typeEnv) throws Exception {
        Type typ1, typ2;
        typ1 = e1.typecheck(typeEnv);
        typ2 = e2.typecheck(typeEnv);
        if (typ1.equals(new BoolType())) {
            if (typ2.equals(new BoolType())) {
                return new BoolType();
            } else throw new Exception("second operand is not boolean");
        } else throw new Exception("first operand is not boolean");
    }

    @Override
    public LogicExp clone() {
        return new LogicExp(e1.clone(),e2.clone(),op);
    }
}
