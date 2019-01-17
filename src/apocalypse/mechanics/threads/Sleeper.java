package apocalypse.mechanics.threads;

public class Sleeper implements Runnable{

    private long millis;

    public Sleeper(long millis){
        this.millis = millis;
    }

    @Override
    public void run() {
        try {
            Thread.currentThread().sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
