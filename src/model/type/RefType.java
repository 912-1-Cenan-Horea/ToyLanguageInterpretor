package model.type;

import model.value.RefValue;
import model.value.Value;

public class RefType implements Type {
    Type inner;

    public RefType(Type inner) {
        this.inner = inner;
    }

    public Type getInner() {
        return inner;
    }

    public boolean equals(Object another) {
        if (another instanceof RefType)
            return inner.equals(((RefType) another).getInner());
        else return false;
    }

    @Override
    public Value default_value() {
        return null;
    }

    public String toString() {
        return "Ref(" + inner.toString() + ")";
    }

    Value defaultValue() {
        return new RefValue(0, inner);
    }
    public RefType clone()
    {
        return new RefType(inner.clone());
    }
}