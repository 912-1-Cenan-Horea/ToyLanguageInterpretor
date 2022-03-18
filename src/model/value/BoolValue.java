package model.value;

import model.type.BoolType;
import model.type.Type;

public class BoolValue implements Value{
    boolean value;

    public BoolValue(boolean i) {
        value = i;
    }

    public BoolValue() {
        value = false;
    }

    public boolean isTrue() {
        return value;
    }

    public boolean isFalse() {
        return !value;
    }

    public boolean getVal() {
        return value;
    }

    @Override
    public Type getType() {
        return new BoolType();
    }

    @Override
    public String toString() {
        if (value)
            return "True";
        return "False";
    }

    @Override
    public BoolValue clone() {
        return new BoolValue(value);
    }

    public boolean equals(Object o) {
        if (!(o instanceof BoolValue )) return false;
        return value == ((BoolValue) o).getVal();
    } 
}
