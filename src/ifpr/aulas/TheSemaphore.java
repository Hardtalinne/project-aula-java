package ifpr.aulas;

public interface TheSemaphore {
    void acquire();
    void release();
    boolean is_full();
    boolean is_empty();
    boolean try_acquire();
    boolean try_release();
    int available();
}

class MySemaphore implements TheSemaphore{
    int count = 0;
    int max = 1;

    public MySemaphore(int resources){
        max = resources;
        count = 0;
    }

    public MySemaphore(int resources, int max) {
        this.max = max;
        this.count = resources;

    }

    private void mywait() {
        try { wait();}
        catch (InterruptedException ignore){}
    }

    public synchronized  void acquire(){
        while (is_full())
            mywait();
        count++;
        notifyAll();
    }

    public synchronized  void release(){
        while (is_empty())
            mywait();
        count--;
        notifyAll();
    }

    public synchronized boolean is_full(){
        if (count >= max)
            return true;
        return false;
    }

    public synchronized boolean is_empty(){
        if (count <= 0)
            return true;
        return false;
    }

    public synchronized boolean try_acquire(){
        if (is_full())
            return false;
        acquire();
        return true;
    }

    public boolean try_release(){
        if (is_empty())
            return false;
        release();
        return true;
    }

    public int available(){return max - count;}
}
