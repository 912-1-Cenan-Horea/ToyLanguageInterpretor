package model.type;

import model.value.BoolValue;
import model.value.Value;

public class BoolType implements Type, Cloneable {
    @Override
    public boolean equals(Object another) {
        if (another instanceof BoolType) return true;
        else return false;
    }

    @Override
    public Value default_value() {
        return new BoolValue();
    }

    @Override
    public String toString() {
        return "bool";
    }

    @Override
    public BoolType clone() {
        return new BoolType();
    }
}
