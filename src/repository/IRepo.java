package repository;

import model.container.IList;
import model.prgstate.PrgState;

import java.util.List;

public interface IRepo {
    void logPrgStateExec(PrgState prg) throws Exception;

    List<PrgState> getPrgList();

    void setPrgList(List<PrgState> l);

    void print_before();

    void print_after();
}
