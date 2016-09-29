package philosopher;

import java.util.concurrent.Semaphore;

public class Table {
    
	
	/* En gaffel er en lås. Kun en kan ha gaffel ad gangen */
	public class Fork {
	    private Semaphore s = new Semaphore(1, true);
    	
		public void take() {
	  	 	s.acquireUninterruptibly();
    	}
    	
    	public void leave() { 
    		s.release(); 
        }
    }
   
	public Fork[] fork; 
	
	
	
	
	/* Dekke bordet for n filosofer */
	public Table(int n) {
		
		/* Create forks */
		fork = new Fork[n];
		for (int i=0; i<n; i++)
			fork[i]=new Fork(); 
	}
	
	
	
	public static void main(String[] arg) {
		Table table = new Table(5); 
		Philosopher p1 = new Philosopher("1:Platon     ", table.fork[0], table.fork[1]);
		Philosopher p2 = new Philosopher("2:Aristoteles", table.fork[1], table.fork[2]);
		Philosopher p3 = new Philosopher("3:Descartes  ", table.fork[2], table.fork[3]);
		Philosopher p4 = new Philosopher("4:Kant       ", table.fork[3], table.fork[4]);
		Philosopher p5 = new Philosopher("5:Sokrates   ", table.fork[4], table.fork[0]);
	}
	
}
