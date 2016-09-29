 
package rwlock;


/*
 * Lese/skrive lås. Laget ved hjelp av Java monitor. 
 * Kun en ad gangen kan skrive og ikke samtidig som noen leser.. 
 * Flere kan lese samtidig. 
 */

public class RWlock {
   
   /*
    * Denne klassen representerer nøkkel til en lås som brukes for 
    * å "låse opp" etter bruk. 
    */
   public class Key {
       private boolean released = false;    
       private boolean write;
   
       public Key(boolean w)
          {write = w; }
   
       /* Kan bare brukes en gang */
       public void release() {
          if (!released)
             RWlock.this.release(write);
            released = true;
       }
   }
   
   
   private boolean wlocked = false; /* Write locked */
   private int readers = 0;         /* Number of readers */
   
   
   
   
   /* 
    * Det er viktig at disse metodene er 'synchronized'. 
    * Hvorfor? 
    */
   
   public synchronized Key getLock(boolean write) 
      throws InterruptedException
   {
       while (wlocked)
          wait(); 
         if (write) {
            while (readers > 0 && !wlocked)    /* <-- OBS! vi trenger å legge til en test på wlocked her. Hvorfor? */
               wait();
            wlocked = true; 
         }
         else
            readers++;

         return new Key(write); 
   }
   
   
   
   
   private synchronized void release(boolean write) {
      if (write)
         wlocked = false;
      else
         readers--;
      notifyAll(); 
   }   
}
