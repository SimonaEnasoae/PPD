package lab3.others;

import lab3a.IListPol;

public class Task implements Runnable {

    public IListPol list;
    public Monom monom;
    public Task(IListPol list,Monom monom) {
        this.monom=monom;
        this.list=list;
    }

    public void run() {
        //System.out.println(monom);
        list.insert(monom.getExp(),monom.getCoef());
       // System.out.println("Task " +"" + " is running.");
    }
}