package model.type;

import model.value.StringValue;
import model.value.Value;

public class StringType implements Type {
    @Override
    public Value default_value() {
        return new StringValue("");
    }

    @Override
    public Type clone() {
        return new StringType();
    }

    @Override
    public boolean equals(Object another) {
        if (another instanceof StringType) return true;
        else return false;
    }

    @Override
    public String toString() {
        return "String";
    }
}
