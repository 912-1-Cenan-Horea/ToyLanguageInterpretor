package model.prgstate;

import model.container.*;
import model.stmt.CompStmt;
import model.stmt.IStmt;
import model.value.Value;
import org.w3c.dom.ls.LSOutput;

import java.io.BufferedReader;
import java.util.Map;

public class PrgState {
    IStack<IStmt> exeStack;
    static int id_count = 0;
    int id;


    IDict<String, Value> symTable;
    IList<Value> out;
    IStmt originalProgram;
    IDict<String, BufferedReader> fileTable;
    IHeap heap;

    public PrgState(IStack<IStmt> stk, IDict<String, Value> symtbl, IList<Value> ot, IDict<String, BufferedReader> ft, IStmt prg, IHeap _heap) {
        exeStack = stk;
        symTable = symtbl;
        out = ot;
        fileTable = ft;
        heap = _heap;
        id = get_id();
        //originalProgram = deepCopy(prg);//recreate the entire original prg
        stk.push(prg);
    }

    public PrgState(MyStack<IStmt> stk, MyDict<String, Value> new_symtbl) {
    }

    synchronized static int get_id() {
        id_count++;
        return id_count - 1;

    }

    public IStack<IStmt> getStk() {
        return exeStack;
    }

    public void setExeStack(IStack<IStmt> s) {
        exeStack = s;
    }

    public void setSymTable(IDict<String, Value> symTable) {
        this.symTable = symTable;
    }

    public void setOut(IList<Value> out) {
        this.out = out;
    }

    public void setOriginalProgram(IStmt originalProgram) {
        this.originalProgram = originalProgram;
    }

    public IHeap getHeap() {
        return heap;
    }

    public void setHeap(IHeap heap) {
        this.heap = heap;
    }

    public IDict<String, Value> getSymTable() {
        return symTable;
    }

    public IDict<String, BufferedReader> getFileTable() {
        return fileTable;
    }

    public IList<Value> getOutput() {
        return out;
    }

    @Override
    public String toString() {
        return "PrgState{" +
                "\nid=" + id +
                "\nexeStack=" + exeStack +
                ", \nsymTable=" + symTable +
                ", \nout=" + out +
                ", \nfileTbl=" + fileTable +
                ", \noriginalProgram=" + originalProgram +
                ", \nHeap=" + heap +
                "}\n---------------------------------------------------\n";
    }

    public String original_program_string() {
        String exeStackString = "";
        IStack<IStmt> exe = this.getStk().clone();
        while (!exe.isEmpty()) {
            IStmt st = exe.pop();
            if (st.getClass() == CompStmt.class) {
                exe.push(((CompStmt) st).getSecond());
                exeStackString += ((CompStmt) st).getFirst().toString();
            } else {
                exeStackString += st.toString();
            }
            exeStackString += '\n';
        }
        return exeStackString;
    }

    public String symTableString() {
        String symString = "";
        IDict<String, Value> s = getSymTable().clone();
        for (String k : s) {
            symString += k += "=" + s.lookup(k).toString() + "\n";
        }
        return symString;

    }

    public String outString() {
        String outString = "";
        for (Value v : getOutput()) {
            outString += v.toString() + "\n";
        }
        return outString;
    }

    public String heapString() {
        String heapString = "";
        IHeap s = getHeap().clone();
        for (Object k : s) {
            heapString += k += "->" + s.lookup((int) (k)).toString() + "\n";
        }
        return heapString;
    }

    public Boolean isNotCompleted() {
        return !exeStack.isEmpty();
    }

    public PrgState oneStep() throws Exception {
        if (exeStack.isEmpty())
            throw new Exception("prgstate stack is empty");
        IStmt crtStmt = exeStack.pop();
        return crtStmt.execute(this);
    }

    public int getId() {
        return id;
    }

    public String fileString() {
        String outString = getFileTable().toString();
        return outString;
    }
}
