package repository;

import model.container.*;
import model.prgstate.PrgState;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Repo implements IRepo {
    List<PrgState> lst;
    int idx;
    String logFilePath;

    public Repo(String _logFilePath) {
        lst = new MyList<PrgState>();
        idx = 0;
        logFilePath = _logFilePath;
    }

    public void add(PrgState p) {
        lst.add(p);
    }


    @Override
    public void logPrgStateExec(PrgState prg) throws Exception {
        PrintWriter logFile = new PrintWriter(new BufferedWriter(new FileWriter(logFilePath, true)));
        String logString = "";
        logString += "Id: " + prg.getId();
        logString += "\nExeStack:\n";
        logString += prg.original_program_string();
        logString += "Heap:\n";
        logString += prg.heapString();
        logString += "SymTable:\n";
        logString += prg.symTableString();
        logString += "Out:\n";
        logString += prg.outString();
        logString += "FileTable:\n";
        logString += prg.fileString();
        logString += "\n-------------------------------\n";

        logFile.write(logString);
        logFile.close();
        // logFile.append(logString);

    }

    public void print_before() {
        PrintWriter logFile = null;
        try {
            logFile = new PrintWriter(new BufferedWriter(new FileWriter(logFilePath, true)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        logFile.write("---------------------BEFORE---------------------\n");
        logFile.close();
    }

    public void print_after()

    {
        PrintWriter logFile = null;
        try {
            logFile = new PrintWriter(new BufferedWriter(new FileWriter(logFilePath, true)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        logFile.write("---------------------AFTER---------------------\n");
        logFile.close();
    }

    @Override
    public List<PrgState> getPrgList() {
        return lst;
    }

    @Override
    public void setPrgList(List<PrgState> l) {
        lst = new MyList<PrgState>(l);
    }

}
