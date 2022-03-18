package model.value;

import model.type.StringType;
import model.type.Type;

public class StringValue implements Value {
    String value;

    public StringValue(String _s) {
        value = _s;
    }

    @Override
    public Type getType() {
        return new StringType();
    }

    public String getVal() {
        return value;
    }

    @Override
    public Value clone() {
        return new StringValue(value);
    }

    public boolean equals(Object o) {
        if (!(o instanceof StringValue)) return false;
        return value == ((StringValue) o).getVal();
    }

    @Override
    public String toString() {
        return "\""+getVal()+"\"";
    }
}
