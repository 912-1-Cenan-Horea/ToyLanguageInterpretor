package model.value;

import model.type.IntType;
import model.type.Type;

import java.util.Objects;

public class IntValue implements Value {
    int value;

    public IntValue() {
        value = 0;
    }

    public IntValue(int x) {
        value = x;
    }


    @Override
    public Type getType() {
        return new IntType();
    }

    public int getVal() {
        return value;
    }

    @Override
    public String toString() {
        return Integer.toString(value);
    }

    @Override
    public IntValue clone() {
        return new IntValue(value);
    }

    public boolean equals(Object o) {
        if (!(o instanceof IntValue intValue)) return false;
        return value == ((IntValue) o).getVal();
    }

}
