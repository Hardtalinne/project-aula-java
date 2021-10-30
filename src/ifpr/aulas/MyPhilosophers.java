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
    Debugger debug = new Debugger("Philosopher");

    Fork left, right;
    long id;

    public Philosopher(Fork left, Fork right) {
        this.left = left;
        this.right = right;
    }

    public boolean takeForks() {
        debug.debug("try take forks....");
        if (left.take()){
            if (right.take()) {
                return true;
            }
            left.release();
        }
        return false;
    }

    public void eat(){
        debug.do_something(5,"eating....");
    }


    public void run() {
        id = Thread.currentThread().getId();
        debug.do_something(5, "running...");
        while (true) {
            debug.do_something(5,"sleeping...");

            if(takeForks()) {
                this.eat();
                left.release();
                right.release();
            }
        }
    }
}


public class MyPhilosophers {
    public static void main(String[] args) {
        Fork f1 = new Fork();
        Fork f2 = new Fork();

        Philosopher aristoteles = new Philosopher(f1,f2);
        Philosopher kant = new Philosopher(f1,f2);
        Philosopher platao = new Philosopher(f1,f2);
        Philosopher hegel = new Philosopher(f1,f2);
        Philosopher nietzsche = new Philosopher(f1,f2);

        aristoteles.start();
        kant.start();
        platao.start();
        hegel.start();
        nietzsche.start();

    }
}
