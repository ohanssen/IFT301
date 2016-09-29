package philosopher;

public class Philosopher2 extends Thread
{
    private Table2.Fork leftFork, rightFork;
    private String name; 
 

    public Philosopher2(String n, Table2.Fork lf, Table2.Fork rf) {
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
        while (true) { 
            /* Ta gaffelen med det laveste nummer først */
            Table2.Fork f1 = (leftFork.number < rightFork.number ? leftFork : rightFork); 
            Table2.Fork f2 = (leftFork.number < rightFork.number ? rightFork : leftFork); 
   
            f1.take();
            System.out.println(name+": Having fork#"+f1.number+" Taking fork#"+f2.number+"..");
            f2.take();
            eat();
            f1.leave();
            f2.leave();
        }
    }
}
