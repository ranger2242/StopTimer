import java.util.TimerTask;

/**
 * Created by Chris Cavazos on 3/27/2017.
 */
class Main {
    static final Clock clock = new Clock();
    private static Window window;
    public static void main(String[] args) {
        clock.start();
        window = new Window();
        startCycle();

    }

    static void startCycle(){
        clock.reset();
        clock.setUpdateCycle(new TimerTask() {
            public void run() {
                window.update();
            }
        });
    }




}
