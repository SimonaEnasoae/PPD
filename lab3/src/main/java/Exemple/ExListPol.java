package Exemple;

import lab3.others.Monom;
import lab3a.ListV1;
import lab3a.ListV2;

import java.util.Vector;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Buffer {
    private Vector<Monom> buffer;
    private int cap;
    private AtomicBoolean finish = new AtomicBoolean(false);


    Buffer(int cap) {
        buffer = new Vector<Monom>();
        this.cap = cap;
    }

    public synchronized AtomicBoolean getFinish() {
        return finish;
    }

    public synchronized void setFinish(AtomicBoolean finish) {
        this.finish = finish;
        System.out.println("====sfarsit====");
        notifyAll();
    }


    public int size() {
        return buffer.size();
    }

    private boolean isEmpty() {
        return buffer.isEmpty();
    }

    private void add(Monom x) {
        buffer.add(x);
    }

    private Monom remove() {
        if (buffer.isEmpty())
            return null;
        Monom x = buffer.firstElement();
        buffer.remove(buffer.firstElement());
        return x;
    }

    public synchronized void produce(Monom x, String name) throws InterruptedException {
        while (buffer.size() == this.cap ) {
            if(getFinish().get()) return;
            System.out.println("Bufferul e plin. Astept..."  + name);
            wait();
        }
        buffer.add(x);
        System.out.println("Am adaugat " + x + " " + name);
        notifyAll();
    }

    public synchronized Monom consume() throws InterruptedException {
        while (buffer.size() == 0 && !finish.get()) {
            System.out.println("Bufferul e gol. Astept...");
            wait();
        }
        Monom x = this.remove();
        System.out.println("Am sters " + x);
        notifyAll();
        return x;
    }

}

class ConsumerBuf extends Thread {
    private Buffer buffer;
    private String name;
    private ListV2 listV2;

    ConsumerBuf(Buffer buffer, String name, ListV2 listV2) {
        this.buffer = buffer;
        this.name = name;
        this.listV2 =listV2;
    }

    @Override
    public void run() {
       // while (!buffer.getFinish().get()|| buffer.size()!=0) {
        while (!buffer.getFinish().get()|| buffer.size()!=0) {
            try {
                Monom x = buffer.consume();
                if(x!=null) {
                    listV2.insert(x.getExp(), x.getCoef());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

         //   if(buffer.getFinish().get()) break;
        }
        System.out.println("Consumatorul "+name + " a terminat");
    }
}

public class ExListPol {
    public static void main(String args[]) throws InterruptedException {
        Buffer b = new Buffer(2000);
        ProducerBuf p1 = new ProducerBuf(b, "P1",10);
        Lock lock =new ReentrantLock();
        ListV2 listV2 =new ListV2(lock);
       // ProducerBuf p2 = new ProducerBuf(b, "P2",10);
       // ProducerBuf p3 = new ProducerBuf(b, "P3");
        ConsumerBuf c1 = new ConsumerBuf(b, "C1",listV2);
        ConsumerBuf c2 = new ConsumerBuf(b, "C2",listV2);
        ConsumerBuf c3 = new ConsumerBuf(b, "C3",listV2);
        c1.start();
        c2.start();
        c3.start();

        p1.start();
       // p2.start();
        p1.join();
       // p2.start();
        c1.join();
        c2.join();
        c3.join();

        System.out.println(b.size());
        listV2.printInFile("out6.txt");

    }
}