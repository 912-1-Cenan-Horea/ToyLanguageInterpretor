package model.value;

import model.type.Type;

public interface Value {
    Type getType();
    Value clone();
    public boolean equals(Object another);

}