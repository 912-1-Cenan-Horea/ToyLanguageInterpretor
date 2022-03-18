package model.value;

import model.type.RefType;
import model.type.Type;

public class RefValue implements Value {
    int address;
    Type locationType;

    public RefValue(int _addr, Type _t) {
        address = _addr;
        locationType = _t;
    }

    public int getAddr() {
        return address;
    }

    public void setAddress(int new_addr) {
        address = new_addr;
    }


    public Type getType() {
        return new RefType(locationType);
    }

    public Type getLocationType() {
        return locationType;
    }

    @Override
    public String toString() {
        return "(" + Integer.toString(address) + "," + locationType.toString() + ")";
    }

    @Override
    public Value clone() {
        return new RefValue(address, locationType.clone());
    }
}
