package model.type;

import model.value.Value;

public interface Type extends Cloneable{
    public boolean equals(Object another);
    Value default_value();
    Type clone();

}
