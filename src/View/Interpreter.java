package View;

import command.ExitCommand;
import command.RunExample;
import controller.Controller;
import model.container.MyDict;
import model.container.MyHeap;
import model.container.MyList;
import model.container.MyStack;
import model.expression.*;
import model.prgstate.PrgState;
import model.stmt.*;
import model.type.*;
import model.value.BoolValue;
import model.value.IntValue;
import model.value.StringValue;
import model.value.Value;
import repository.Repo;

import java.io.BufferedReader;

class Interpreter {
    static PrgState p1() throws Exception {
//        int v;
//        v = 2;
//        Print(v) is represented as:

        IStmt ex1 = new CompStmt(new VarDeclStmt("v", new IntType()), new CompStmt(new AssignStmt("v", new ValueExp(new IntValue(2))), new PrintStmt(new VarExp("v"))));
        MyDict<String, Value> dict = new MyDict<String, Value>();
        MyList<Value> lst = new MyList<Value>();
        MyStack<IStmt> stk = new MyStack<IStmt>();
        MyDict<String, BufferedReader> fileTable = new MyDict<String, BufferedReader>();
        MyHeap h = new MyHeap();
        ex1.typecheck(new MyDict<String, Type>());
        return new PrgState(stk, dict, lst, fileTable, ex1, h);
    }

    static PrgState p2() throws Exception {
        ///int a;
        // int b;
        // a=2+3*5;
        // b=a+1;
        // Print(b)
        IStmt ex2 = new CompStmt(new VarDeclStmt("a", new IntType()), new CompStmt(new VarDeclStmt("b", new IntType()), new CompStmt(new AssignStmt("a", new ArithExp('+', new ValueExp(new IntValue(2)), new ArithExp('*', new ValueExp(new IntValue(3)), new ValueExp(new IntValue(5))))), new CompStmt(new AssignStmt("b", new ArithExp('+', new VarExp("a"), new ValueExp(new IntValue(1)))), new PrintStmt(new VarExp("b"))))));
        MyDict<String, Value> dict = new MyDict<String, Value>();
        MyList<Value> lst = new MyList<Value>();
        MyStack<IStmt> stk = new MyStack<IStmt>();
        MyDict<String, BufferedReader> fileTable = new MyDict<String, BufferedReader>();
        MyHeap h = new MyHeap();
        ex2.typecheck(new MyDict<String, Type>());
        return new PrgState(stk, dict, lst, fileTable, ex2, h);
    }

    static PrgState p3() throws Exception {
        ///bool a;
        // int v;
        // a=true;
        // (If a Then v=2
        //       Else v=3);
        // Print(v)   is represented as
        IStmt ex3 = new CompStmt(new VarDeclStmt("a", new BoolType()), new CompStmt(new VarDeclStmt("v", new IntType()), new CompStmt(new AssignStmt("a", new ValueExp(new BoolValue(true))), new CompStmt(new IfStmt(new VarExp("a"), new AssignStmt("v", new ValueExp(new IntValue(2))), new AssignStmt("v", new ValueExp(new IntValue(3)))), new PrintStmt(new VarExp("v"))))));
        MyDict<String, Value> dict = new MyDict<String, Value>();
        MyList<Value> lst = new MyList<Value>();
        MyStack<IStmt> stk = new MyStack<IStmt>();
        MyDict<String, BufferedReader> fileTable = new MyDict<String, BufferedReader>();
        MyHeap h = new MyHeap();
        ex3.typecheck(new MyDict<String, Type>());
        return new PrgState(stk, dict, lst, fileTable, ex3, h);


    }

    static PrgState p4() throws Exception {
        IStmt ex4 = new CompStmt(new VarDeclStmt("varf", new StringType()), new CompStmt(new AssignStmt("varf", new ValueExp(new StringValue("test.in"))), new CompStmt(new openRFile(new VarExp("varf")), new CompStmt(new VarDeclStmt("varc", new IntType()), new CompStmt(new readFile(new VarExp("varf"), "varc"), new CompStmt(new PrintStmt(new VarExp("varc")), new CompStmt(new readFile(new VarExp("varf"), "varc"), new CompStmt(new PrintStmt(new VarExp("varc")), new closeRFile(new VarExp("varf"))))))))));
        MyDict<String, Value> dict = new MyDict<String, Value>();
        MyList<Value> lst = new MyList<Value>();
        MyStack<IStmt> stk = new MyStack<IStmt>();
        MyDict<String, BufferedReader> fileTable = new MyDict<String, BufferedReader>();
        MyHeap h = new MyHeap();
        ex4.typecheck(new MyDict<String, Type>());
        return new PrgState(stk, dict, lst, fileTable, ex4, h);
    }

    public static PrgState p5() throws Exception {
        IStmt ex5 = new CompStmt(new VarDeclStmt("v", new RefType(new IntType())), new CompStmt(new newStmt("v", new ValueExp(new IntValue(20))), new CompStmt(new VarDeclStmt("a", new RefType(new RefType(new IntType()))), new CompStmt(new newStmt("a", new VarExp("v")), new CompStmt(new PrintStmt(new VarExp("v")), new PrintStmt(new VarExp("a")))))));
        MyDict<String, Value> dict = new MyDict<String, Value>();
        MyList<Value> lst = new MyList<Value>();
        MyStack<IStmt> stk = new MyStack<IStmt>();
        MyDict<String, BufferedReader> fileTable = new MyDict<String, BufferedReader>();
        MyHeap h = new MyHeap();
        ex5.typecheck(new MyDict<String, Type>());
        return new PrgState(stk, dict, lst, fileTable, ex5, h);
    }

    public static PrgState p6() throws Exception {

        IStmt ex6 = new CompStmt(new VarDeclStmt("v", new RefType(new IntType())), new CompStmt(new newStmt("v", new ValueExp(new IntValue(20))), new CompStmt(new VarDeclStmt("a", new RefType(new RefType(new IntType()))), new CompStmt(new newStmt("a", new VarExp("v")), new CompStmt(new PrintStmt(new rH(new VarExp("v"))), new PrintStmt(new ArithExp('+', new rH(new rH(new VarExp("a"))), new ValueExp(new IntValue(5)))))))));
        MyDict<String, Value> dict = new MyDict<String, Value>();
        MyList<Value> lst = new MyList<Value>();
        MyStack<IStmt> stk = new MyStack<IStmt>();
        MyDict<String, BufferedReader> fileTable = new MyDict<String, BufferedReader>();
        MyHeap h = new MyHeap();
        ex6.typecheck(new MyDict<String, Type>());
        return new PrgState(stk, dict, lst, fileTable, ex6, h);
    }

    public static PrgState p7() throws Exception {
        IStmt ex7 = new CompStmt(new VarDeclStmt("v", new RefType(new IntType())), new CompStmt(new newStmt("v", new ValueExp(new IntValue(20))), new CompStmt(new PrintStmt(new rH(new VarExp("v"))), new CompStmt(new wH("v", new ValueExp(new IntValue(30))), new PrintStmt(new ArithExp('+', new rH(new VarExp("v")), new ValueExp(new IntValue(5))))))));
        MyDict<String, Value> dict = new MyDict<String, Value>();
        MyList<Value> lst = new MyList<Value>();
        MyStack<IStmt> stk = new MyStack<IStmt>();
        MyDict<String, BufferedReader> fileTable = new MyDict<String, BufferedReader>();
        MyHeap h = new MyHeap();
        ex7.typecheck(new MyDict<String, Type>());
        return new PrgState(stk, dict, lst, fileTable, ex7, h);
    }

    public static PrgState p8() throws Exception {
        IStmt ex8 = new CompStmt(new VarDeclStmt("v", new RefType(new IntType())), new CompStmt(new newStmt("v", new ValueExp(new IntValue(20))), new CompStmt(new VarDeclStmt("a", new RefType(new RefType(new IntType()))), new CompStmt(new newStmt("a", new VarExp("v")), new CompStmt(new newStmt("v", new ValueExp(new IntValue(30))), new PrintStmt(new rH(new rH(new VarExp("a")))))))));
        MyDict<String, Value> dict = new MyDict<String, Value>();
        MyList<Value> lst = new MyList<Value>();
        MyStack<IStmt> stk = new MyStack<IStmt>();
        MyDict<String, BufferedReader> fileTable = new MyDict<String, BufferedReader>();
        MyHeap h = new MyHeap();
        ex8.typecheck(new MyDict<String, Type>());
        return new PrgState(stk, dict, lst, fileTable, ex8, h);
    }

    public static PrgState p9() throws Exception {
        IStmt ex9 = new CompStmt(new VarDeclStmt("v", new IntType()), new CompStmt(new AssignStmt("v", new ValueExp(new IntValue(4))), new CompStmt(new WhileStmt(new RelationalExp(new VarExp("v"), new ValueExp(new IntValue(0)), ">"), new CompStmt(new PrintStmt(new VarExp("v")), new AssignStmt("v", new ArithExp('-', new VarExp("v"), new ValueExp(new IntValue(1)))))), new PrintStmt(new VarExp("v")))));
        MyDict<String, Value> dict = new MyDict<String, Value>();
        MyList<Value> lst = new MyList<Value>();
        MyStack<IStmt> stk = new MyStack<IStmt>();
        MyDict<String, BufferedReader> fileTable = new MyDict<String, BufferedReader>();
        MyHeap h = new MyHeap();
        ex9.typecheck(new MyDict<String, Type>());
        return new PrgState(stk, dict, lst, fileTable, ex9, h);
    }

    public static PrgState p10() throws Exception {
        forkStmt frk = new forkStmt(new CompStmt(new wH("a", new ValueExp(new IntValue(30))), new CompStmt(new AssignStmt("v", new ValueExp(new IntValue(32))), new CompStmt(new PrintStmt(new VarExp("v")), new PrintStmt(new rH(new VarExp("a")))))));
        IStmt ex10 = new CompStmt(new VarDeclStmt("v", new IntType()), new CompStmt(new VarDeclStmt("a", new RefType(new IntType())), new CompStmt(new AssignStmt("v", new ValueExp(new IntValue(10))), new CompStmt(new newStmt("a", new ValueExp(new IntValue(22))), new CompStmt(frk, new CompStmt(new PrintStmt(new VarExp("v")), new PrintStmt(new rH(new VarExp("a")))))))));
        MyDict<String, Value> dict = new MyDict<String, Value>();
        MyList<Value> lst = new MyList<Value>();
        MyStack<IStmt> stk = new MyStack<IStmt>();
        MyDict<String, BufferedReader> fileTable = new MyDict<String, BufferedReader>();
        MyHeap h = new MyHeap();
        ex10.typecheck(new MyDict<String, Type>());
        return new PrgState(stk, dict, lst, fileTable, ex10, h);
    }

    public static PrgState p11() throws Exception {
        forkStmt frk1 = new forkStmt(new newStmt("a", new VarExp("v")));
        IStmt ex11 = new CompStmt(new VarDeclStmt("v", new RefType(new IntType())), new CompStmt(new VarDeclStmt("a", new RefType(new RefType(new IntType()))), new CompStmt(new newStmt("v", new ValueExp(new IntValue(20))), new CompStmt(frk1, new CompStmt(new newStmt("v", new ValueExp(new IntValue(100))), new PrintStmt(new rH(new rH(new VarExp("a")))))))));
        MyDict<String, Value> dict = new MyDict<String, Value>();
        MyList<Value> lst = new MyList<Value>();
        MyStack<IStmt> stk = new MyStack<IStmt>();
        MyDict<String, BufferedReader> fileTable = new MyDict<String, BufferedReader>();
        MyHeap h = new MyHeap();
        ex11.typecheck(new MyDict<String, Type>());
        return new PrgState(stk, dict, lst, fileTable, ex11, h);
    }


    public static void main(String[] args) throws Exception {

        IStmt ex1 = new CompStmt(new VarDeclStmt("v", new IntType()), new CompStmt(new AssignStmt("v", new ValueExp(new IntValue(2))), new PrintStmt(new VarExp("v"))));
        PrgState prg1 = p1();
        Repo repo1 = new Repo("log1.txt");
        repo1.add(prg1);
        Controller ctr1 = new Controller(repo1);

        IStmt ex2 = new CompStmt(new VarDeclStmt("a", new IntType()), new CompStmt(new VarDeclStmt("b", new IntType()), new CompStmt(new AssignStmt("a", new ArithExp('+', new ValueExp(new IntValue(2)), new ArithExp('*', new ValueExp(new IntValue(3)), new ValueExp(new IntValue(5))))), new CompStmt(new AssignStmt("b", new ArithExp('+', new VarExp("a"), new ValueExp(new IntValue(1)))), new PrintStmt(new VarExp("b"))))));
        PrgState prg2 = p2();
        Repo repo2 = new Repo("log2.txt");
        repo2.add(prg2);
        Controller ctr2 = new Controller(repo2);

        IStmt ex3 = new CompStmt(new VarDeclStmt("a", new BoolType()), new CompStmt(new VarDeclStmt("v", new IntType()), new CompStmt(new AssignStmt("a", new ValueExp(new BoolValue(true))), new CompStmt(new IfStmt(new VarExp("a"), new AssignStmt("v", new ValueExp(new IntValue(2))), new AssignStmt("v", new ValueExp(new IntValue(3)))), new PrintStmt(new VarExp("v"))))));
        PrgState prg3 = p3();
        Repo repo3 = new Repo("log3.txt");
        repo3.add(prg3);
        Controller ctr3 = new Controller(repo3);

        IStmt ex4 = new CompStmt(new VarDeclStmt("varf", new StringType()), new CompStmt(new AssignStmt("varf", new ValueExp(new StringValue("test.in"))), new CompStmt(new openRFile(new VarExp("varf")), new CompStmt(new VarDeclStmt("varc", new IntType()), new CompStmt(new readFile(new VarExp("varf"), "varc"), new CompStmt(new PrintStmt(new VarExp("varc")), new CompStmt(new readFile(new VarExp("varf"), "varc"), new CompStmt(new PrintStmt(new VarExp("varc")), new closeRFile(new VarExp("varf"))))))))));
        PrgState prg4 = p4();
        Repo repo4 = new Repo("log4.txt");
        repo4.add(prg4);
        Controller ctr4 = new Controller(repo4);

        IStmt ex5 = new CompStmt(new VarDeclStmt("v", new RefType(new IntType())), new CompStmt(new newStmt("v", new ValueExp(new IntValue(20))), new CompStmt(new VarDeclStmt("a", new RefType(new RefType(new IntType()))), new CompStmt(new newStmt("a", new VarExp("v")), new CompStmt(new PrintStmt(new VarExp("v")), new PrintStmt(new VarExp("a")))))));
        PrgState prg5 = p5();
        Repo repo5 = new Repo("log5.txt");
        repo5.add(prg5);
        Controller ctr5 = new Controller(repo5);

        IStmt ex6 = new CompStmt(new VarDeclStmt("v", new RefType(new IntType())), new CompStmt(new newStmt("v", new ValueExp(new IntValue(20))), new CompStmt(new VarDeclStmt("a", new RefType(new RefType(new IntType()))), new CompStmt(new newStmt("a", new VarExp("v")), new CompStmt(new PrintStmt(new rH(new VarExp("v"))), new PrintStmt(new ArithExp('+', new rH(new rH(new VarExp("a"))), new ValueExp(new IntValue(5)))))))));
        PrgState prg6 = p6();
        Repo repo6 = new Repo("log6.txt");
        repo6.add(prg6);
        Controller ctr6 = new Controller(repo6);

        IStmt ex7 = new CompStmt(new VarDeclStmt("v", new RefType(new IntType())), new CompStmt(new newStmt("v", new ValueExp(new IntValue(20))), new CompStmt(new PrintStmt(new rH(new VarExp("v"))), new CompStmt(new wH("v", new ValueExp(new IntValue(30))), new PrintStmt(new ArithExp('+', new rH(new VarExp("v")), new ValueExp(new IntValue(5))))))));
        PrgState prg7 = p7();
        Repo repo7 = new Repo("log7.txt");
        repo7.add(prg7);
        Controller ctr7 = new Controller(repo7);

        IStmt ex8 = new CompStmt(new VarDeclStmt("v", new RefType(new IntType())), new CompStmt(new newStmt("v", new ValueExp(new IntValue(20))), new CompStmt(new VarDeclStmt("a", new RefType(new RefType(new IntType()))), new CompStmt(new newStmt("a", new VarExp("v")), new CompStmt(new newStmt("v", new ValueExp(new IntValue(30))), new PrintStmt(new rH(new rH(new VarExp("a")))))))));
        PrgState prg8 = p8();
        Repo repo8 = new Repo("log8.txt");
        repo8.add(prg8);
        Controller ctr8 = new Controller(repo8);


        IStmt ex9 = new CompStmt(new VarDeclStmt("v", new IntType()), new CompStmt(new AssignStmt("v", new ValueExp(new IntValue(4))), new CompStmt(new WhileStmt(new RelationalExp(new VarExp("v"), new ValueExp(new IntValue(0)), ">"), new CompStmt(new PrintStmt(new VarExp("v")), new AssignStmt("v", new ArithExp('-', new VarExp("v"), new ValueExp(new IntValue(1)))))), new PrintStmt(new VarExp("v")))));
        PrgState prg9 = p9();
        Repo repo9 = new Repo("log9.txt");
        repo9.add(prg9);
        Controller ctr9 = new Controller(repo9);

        forkStmt frk = new forkStmt(new CompStmt(new wH("a", new ValueExp(new IntValue(30))), new CompStmt(new AssignStmt("v", new ValueExp(new IntValue(32))), new CompStmt(new PrintStmt(new VarExp("v")), new PrintStmt(new rH(new VarExp("a")))))));
        IStmt ex10 = new CompStmt(new VarDeclStmt("v", new IntType()), new CompStmt(new VarDeclStmt("a", new RefType(new IntType())), new CompStmt(new AssignStmt("v", new ValueExp(new IntValue(10))), new CompStmt(new newStmt("a", new ValueExp(new IntValue(22))), new CompStmt(frk, new CompStmt(new PrintStmt(new VarExp("v")), new PrintStmt(new rH(new VarExp("a")))))))));
        PrgState prg10 = p10();
        Repo repo10 = new Repo("log10.txt");
        repo10.add(prg10);
        Controller ctr10 = new Controller(repo10);


        forkStmt frk1 = new forkStmt(new newStmt("a", new VarExp("v")));
        IStmt ex11 = new CompStmt(new VarDeclStmt("v", new RefType(new IntType())), new CompStmt(new VarDeclStmt("a", new RefType(new RefType(new IntType()))), new CompStmt(new newStmt("v", new ValueExp(new IntValue(20))), new CompStmt(frk1, new CompStmt(new newStmt("v", new ValueExp(new IntValue(100))), new PrintStmt(new rH(new rH(new VarExp("a")))))))));
        PrgState prg11 = p11();
        Repo repo11 = new Repo("log11.txt");
        repo11.add(prg11);
        Controller ctr11 = new Controller(repo11);

        TextMenu menu = new TextMenu();
        menu.addCommand(new ExitCommand("0", "exit"));
        menu.addCommand(new RunExample("1", ex1.toString(), ctr1));
        menu.addCommand(new RunExample("2", ex2.toString(), ctr2));
        menu.addCommand(new RunExample("3", ex3.toString(), ctr3));
        menu.addCommand(new RunExample("4", ex4.toString(), ctr4));
        menu.addCommand(new RunExample("5", ex5.toString(), ctr5));
        menu.addCommand(new RunExample("6", ex6.toString(), ctr6));
        menu.addCommand(new RunExample("7", ex7.toString(), ctr7));
        menu.addCommand(new RunExample("8", ex8.toString(), ctr8));
        menu.addCommand(new RunExample("9", ex9.toString(), ctr9));
        menu.addCommand(new RunExample("10", ex10.toString(), ctr10));
        menu.addCommand(new RunExample("11", ex11.toString(), ctr11));
        menu.show();
    }


}
