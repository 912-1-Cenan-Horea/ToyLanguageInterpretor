package model.expression;

import model.container.IDict;
import model.container.IHeap;
import model.type.IntType;
import model.type.Type;
import model.value.IntValue;
import model.value.Value;

public class ArithExp implements Exp {
    Exp e1;
    Exp e2;
    int op; //1-plus, 2-minus, 3-star, 4-divide
    char opchar;

    public ArithExp(Character _op, Exp _e1, Exp _e2) throws Exception {

        if (_op.equals('+'))
            op = 1;
        else if (_op.equals('-'))
            op = 2;
        else if (_op.equals('*'))
            op = 3;
        else if (_op.equals('/'))
            op = 4;
        else throw new ArithmeticException("Operator " + _op + " is invalid for arithmetic expression\n");
        opchar = _op;
        e1 = _e1;
        e2 = _e2;
    }

    @Override
    public Value eval(IDict<String, Value> symTbl, IHeap hp) throws Exception {
        Value v1, v2;
        v1 = e1.eval(symTbl, hp);
        if (v1.getType().equals(new IntType())) {
            v2 = e2.eval(symTbl, hp);
            if (v2.getType().equals(new IntType())) {
                IntValue i1 = (IntValue) v1;
                IntValue i2 = (IntValue) v2;
                int n1, n2;
                n1 = i1.getVal();
                n2 = i2.getVal();
                if (op == 1) return new IntValue(n1 + n2);
                if (op == 2) return new IntValue(n1 - n2);
                if (op == 3) return new IntValue(n1 * n2);
                if (op == 4) if (n2 == 0) throw new ArithmeticException("Division by zero\n");
                else return new IntValue(n1 / n2);
                else throw new ArithmeticException("Invalid arithmetic operator\n");
            } else throw new ArithmeticException("Second operand is not an integer\n");
        } else throw new ArithmeticException("First operand is not an integer\n");

    }

    @Override
    public Type typecheck(IDict<String, Type> typeEnv) throws Exception {
        Type typ1, typ2;
        typ1 = e1.typecheck(typeEnv);
        typ2 = e2.typecheck(typeEnv);
        if (typ1.equals(new IntType())) {
            if (typ2.equals(new IntType())) {
                return new IntType();
            } else throw new Exception("second operand is not an integer");
        } else throw new Exception("first operand is not an integer");
    }



    @Override
    public String toString() {
        String _op = "";
        if (op == 1) _op = "+";
        if (op == 2) _op = "-";
        if (op == 3) _op = "*";
        if (op == 4) _op = "/";
        return e1 + _op + e2;
    }

    public ArithExp clone() {
        try {
            return new ArithExp(opchar, e1.clone(), e2.clone());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
