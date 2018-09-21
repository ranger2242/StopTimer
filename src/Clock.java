import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Chris Cavazos on 7/17/2016.
 */
class Clock {
    private static Timer timer;
    private static float interval;
    private final NumberFormat f = new DecimalFormat("00");

    private long start = 0;
    private long end = 0;

    Clock() {
    }

    static float getInterval() {
        NumberFormat f2 = new DecimalFormat("#0.00");
        float a=Float.parseFloat(f2.format(interval));
        interval-=(.1f);
        return a;
    }

    static void stop() {
        timer.cancel();
    }


    void start() {
        start = System.nanoTime();
    }

    void end() {
        end = System.nanoTime();
    }

    private int getElapsed() {
        return (int) ((end - start) / (1000000000.0));
    }


    String runtime() {
        int t=getElapsed();
        int s = t% 60;
        int m = (t/ 60) % 60;
        int h = (t/ 3600);

        return h + ":" + f.format(m) + ":" + f.format(s);
    }

    void reset() {
        interval = 300;
        if (timer != null)
            timer.cancel();
        timer = new Timer();
    }

    void setUpdateCycle(TimerTask task) {
        int delay = 100;
        int period = 100;
        timer.scheduleAtFixedRate(task, delay, period);

    }
}
