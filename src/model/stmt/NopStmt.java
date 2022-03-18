package model.stmt;

import model.container.IDict;
import model.prgstate.PrgState;
import model.type.Type;

public class NopStmt implements IStmt{
    @Override
    public PrgState execute(PrgState state) {
        return null;
    }

    @Override
    public IDict<String, Type> typecheck(IDict<String, Type> typeEnv) throws Exception {
        return typeEnv;
    }

    @Override
    public NopStmt clone() {
        return new NopStmt();
    }
}
