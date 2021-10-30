package ifpr.aulas;

import java.time.LocalDateTime;
import java.util.Random;
import java.time.format.DateTimeFormatter;

public class Debugger {

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss.SSSS");
    Random random = new Random();
    String owner;

    public Debugger(String owner) {this.owner = owner;}

    public void do_something(int max_rand, String message) {
        int sleep = random.nextInt(max_rand) + 1;
        String msg = message;
        msg += "waiting: " + sleep;
        this.debug(msg, owner);

        try{Thread.sleep(sleep * 1000);}
        catch (InterruptedException ie) {ie.printStackTrace();}
    }

    public void do_something(int max_rand) {
        do_something(max_rand, "doing something. waiting: ");
    }

        public void do_something() {
            this.debug("doing something...", owner);
            try{Thread.sleep(1000);}
            catch (InterruptedException ie) {ie.printStackTrace();}
        }

        public void newline( ) {
            System.out.println();
        }

        public void debug(String str, String owner){
        String time = LocalDateTime.now().format(formatter);
        long tid = Thread.currentThread().getId();
        String nameThread = Thread.currentThread().getName();

        String msg = String.format("%s - %s - %s:%d - %s", time, nameThread,owner, tid, str);
            System.out.println(msg);
        }
}
