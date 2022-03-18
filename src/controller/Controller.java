package controller;

import model.container.IHeap;
import model.container.IStack;
import model.prgstate.PrgState;
import model.stmt.IStmt;
import model.type.RefType;
import model.value.RefValue;
import model.value.Value;
import repository.IRepo;

import java.util.*;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

public class Controller {
    IRepo repo;
    ExecutorService executor;

    public Controller(IRepo r) {
        repo = r;
    }

    Map<Integer, Value> GarbageCollector(List<Integer> symTableAddr, Map<Integer, Value> heap) {
        return heap.entrySet().stream()
                .filter(e -> {
                    return symTableAddr.contains(e.getKey());
                })
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }


    List<Integer> getAddrFromSymTable(Collection<Value> symTableValues, IHeap hp) {

        return symTableValues.stream()
                .filter(v -> v instanceof RefValue)
                .map(v -> {
                    List<Integer> l = new ArrayList<Integer>();
                    RefValue v1 = (RefValue) v;
                    l.add(v1.getAddr());
                    while ((v1.getLocationType() instanceof RefType)) {
                        if (v1.getAddr() != 0) {   /// Initialized refference, we check all the adresses it points too
                            v1 = (RefValue) hp.lookup(v1.getAddr());
                            l.add(v1.getAddr());
                        } else break;

                    }

                    return l;
                }).flatMap(Collection::stream)
                .collect(Collectors.toList());

    }

    List<PrgState> removeCompletedPrg(List<PrgState> inPrgList) {
        return inPrgList.stream()
                .filter(PrgState::isNotCompleted).collect(Collectors.toList());
    }


    void oneStepForAllPrg(List<PrgState> prgList) throws InterruptedException {
        //before the execution, print the PrgState List into the log file
        repo.print_before();
        prgList.forEach(prg -> {
            try {
                repo.logPrgStateExec(prg);
                System.out.println(prg);
            } catch (Exception e) {
                System.out.println(e.toString());
                ;
            }
        });
        List<Callable<PrgState>> callList = prgList.stream().map((PrgState p) -> (Callable<PrgState>) (() -> {
            return p.oneStep();
        })).collect(Collectors.toList());
        List<PrgState> newPrgList = executor.invokeAll(callList).stream().map(future -> {
            try {
                return future.get();
            } catch (Exception e) {
                return null;
            }
        }).filter(p -> p != null).collect(Collectors.toList());
        prgList.addAll(newPrgList);
        repo.print_after();
        prgList.forEach(prg -> {
            try {
                repo.logPrgStateExec(prg);
                System.out.println(prg);
            } catch (Exception e) {
                System.out.println(e.toString());
            }
        });
        repo.setPrgList(prgList);
    }

    public void allStep() throws InterruptedException {
        executor = Executors.newFixedThreadPool(2);
        //remove the completed programs
        List<PrgState> prgList = removeCompletedPrg(repo.getPrgList());
        while (prgList.size() > 0) {
            List<Integer> l = new ArrayList<Integer>();
            for (PrgState p : prgList)
                l.addAll(getAddrFromSymTable(p.getSymTable().getContent().values(), p.getHeap()));
            prgList.get(0).getHeap().setContent(GarbageCollector(l, prgList.get(0).getHeap().getContent()));
            oneStepForAllPrg(prgList);
            //remove the completed programs
            prgList = removeCompletedPrg(repo.getPrgList());
        }
        executor.shutdownNow();
        //HERE the repository still contains at least one Completed Prg
        // and its List<PrgState> is not empty. Note that oneStepForAllPrg calls the method
        // setPrgList of repository in order to change the repository
        // update the repository state
        repo.setPrgList(prgList);
    }
}
