package model.expression;

import model.Exception.ExpressionException;
import model.container.IDict;
import model.container.IHeap;
import model.type.BoolType;
import model.type.IntType;
import model.type.Type;
import model.value.BoolValue;
import model.value.IntValue;
import model.value.Value;

import java.util.ArrayList;

public class RelationalExp implements Exp {
    Exp e1;
    Exp e2;
    String operator;

    public RelationalExp(Exp _e1, Exp _e2, String _op) {
        e1 = _e1;
        e2 = _e2;
        operator = _op;
    }

    @Override
    public Value eval(IDict<String, Value> symTbl, IHeap hp) throws Exception {
        ArrayList<String> acceptedOperators = new ArrayList<String>();
        acceptedOperators.add("<");
        acceptedOperators.add("<=");
        acceptedOperators.add("==");
        acceptedOperators.add("!=");
        acceptedOperators.add(">");
        acceptedOperators.add(">=");
        if (!acceptedOperators.contains(operator))
            throw new ExpressionException("Operator " + operator + " is not a relational operator!\n");
        if (!(e1.eval(symTbl, hp) instanceof IntValue))
            throw new ExpressionException("Expression " + e1.toString() + " is not int expression!\n");
        if (!(e2.eval(symTbl, hp) instanceof IntValue))
            throw new ExpressionException("Expression " + e2.toString() + " is not int expression!\n");
        int value1 = ((IntValue) e1.eval(symTbl, hp)).getVal();
        int value2 = ((IntValue) e2.eval(symTbl, hp)).getVal();
        if (operator.equals("<"))
            return new BoolValue(value1 < value2);
        if (operator.equals("<="))
            return new BoolValue(value1 <= value2);
        if (operator.equals("=="))
            return new BoolValue(value1 == value2);
        if (operator.equals("!="))
            return new BoolValue(value1 != value2);
        if (operator.equals(">"))
            return new BoolValue(value1 > value2);
        if (operator.equals(">="))
            return new BoolValue(value1 >= value2);

        return null;
    }

    @Override
    public Type typecheck(IDict<String, Type> typeEnv) throws Exception {
        Type typ1, typ2;
        typ1 = e1.typecheck(typeEnv);
        typ2 = e2.typecheck(typeEnv);
        if (typ1.equals(new IntType())) {
            if (typ2.equals(new IntType())) {
                return new BoolType();
            } else throw new Exception("second operand is not an integer");
        } else throw new Exception("first operand is not an integer");
    }

    @Override
    public String toString() {
        return e1.toString() + operator + e2.toString();
    }

    @Override
    public Exp clone() {
        return null;
    }
}
