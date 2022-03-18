package model.stmt;

import model.container.IDict;
import model.prgstate.PrgState;
import model.type.Type;

public interface IStmt {

    PrgState execute(PrgState state) throws Exception;

    IDict<String, Type> typecheck(IDict<String, Type> typeEnv) throws Exception;

    IStmt clone();
}
