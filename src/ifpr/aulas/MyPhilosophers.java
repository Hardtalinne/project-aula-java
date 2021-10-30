package ifpr.aulas;

class Fork {
    TheSemaphore fork = new MySemaphore(1);

    public Fork(){

    }

    public boolean take(){
        return fork.try_acquire();
    }

    public void release(){
        fork.release();
    }
}

class Philosopher extends Thread {
    Debugger debug = new Debugger(getName());

    Fork left, right;
    long id;
    String name;


    public Philosopher(Fork left, Fork right, String name) {
        this.left = left;
        this.right = right;
        this.name = name;
    }

    public boolean takeForks() {
        debug.debug("try take forks....", name);
        if (left.take()){
            if (right.take()) {
                return true;
            }
            left.release();
        }
        return false;
    }

    public void eat(){
        debug.do_something(3,"eating....");
    }

    public void run() {
        id = Thread.currentThread().getId();
        int control = 0;
        debug.do_something(3, "running...");
        while ( control < 3) {
            debug.do_something(3,"sleeping...");

            if(takeForks()) {
                this.eat();
                left.release();
                right.release();
                control++;
            }
        }
    }
}


public class MyPhilosophers {
    public static void main(String[] args) {
        Fork f1 = new Fork();
        Fork f2 = new Fork();

        Philosopher aristoteles = new Philosopher(f1,f2, "Aristoteles");
        Philosopher kant = new Philosopher(f1,f2, "kant");
        Philosopher platao = new Philosopher(f1,f2, "platao");
        Philosopher hegel = new Philosopher(f1,f2, "hegel");
        Philosopher nietzsche = new Philosopher(f1,f2, "nietzsche");

        aristoteles.start();
        kant.start();
        platao.start();
        hegel.start();
        nietzsche.start();

    }
}
