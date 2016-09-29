package philosopher;

public class Philosopher extends Thread
{
    private Table.Fork leftFork, rightFork;
    private String name; 
 

    public Philosopher(String n, Table.Fork lf, Table.Fork rf) {
         name = n;
         leftFork=lf; rightFork=rf;
         start();
    }

 
    public void eat() {
        System.out.println(name+": Now eating...");
        try { 
           sleep(1000);
        } catch (Exception e) {}
           System.out.println(name+": Now thinking...");
        }

 
       @Override
       public void run() {
          this.setName("Philosopher: "+name);
          while (true){ 
             leftFork.take();
             System.out.println(name+": Having right fork. Taking left..");
             rightFork.take();
             eat();
             rightFork.leave();
             leftFork.leave();
         }
      }
}
