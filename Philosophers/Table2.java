package philosopher;

import java.util.concurrent.Semaphore;

public class Table2 {
    
    /* En gaffel er en lås. Kun en kan ha gaffel ad gangen */
    public class Fork {
       private Semaphore s = new Semaphore(1, true);
       public int number; 
     
       public Fork(int n)
           { number = n; }
    
       public void take() {
           s.acquireUninterruptibly();
       }
     
       public void leave() { 
           s.release(); 
        }
    }
   
    public Fork[] fork; 




    /* Dekke bordet for n filosofer */
    public Table2(int n) {
       /* Create forks */
       fork = new Fork[n];
       for (int i=0; i<n; i++)
          fork[i]=new Fork(i); 
    }




    public static void main(String[] arg) {
       Table2 table = new Table2(5); 
       Philosopher2 p1 = new Philosopher2("1:Platon     ", table.fork[0], table.fork[1]);
       Philosopher2 p2 = new Philosopher2("2:Aristoteles", table.fork[1], table.fork[2]);
       Philosopher2 p3 = new Philosopher2("3:Descartes  ", table.fork[2], table.fork[3]);
       Philosopher2 p4 = new Philosopher2("4:Kant       ", table.fork[3], table.fork[4]);
       Philosopher2 p5 = new Philosopher2("5:Sokrates   ", table.fork[4], table.fork[0]);
    }
    
}
