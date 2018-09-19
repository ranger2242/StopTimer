import java.text.DecimalFormat;
import java.text.NumberFormat;

/**
 * Created by Chris Cavazos on 7/17/2016.
 */
public class Clock {
    NumberFormat f = new DecimalFormat("00");
    protected long start=0;
    protected long end=0;
    protected String name="";
    public Clock(String n){
        name=n;
    }

    public Clock() {

    }

    public void start(){
        start=System.nanoTime();
    }
    public void end(){
        end=System.nanoTime();
    }
    public String getElapsed(){
        return f.format(getElapsedD());
    }
    public double getElapsedD(){
        return (end-start)/( 1000000000.0);
    }


    public String runtime(){
        int s= (int) (getElapsedD()%60);
        int m= (int) ((getElapsedD()/60)%60);
        int h= (int) ((getElapsedD()/3600));

        return h+":"+f.format(m)+":"+f.format(s);
    }

    public void print() {
        System.out.println(runtime());
    }
    public void printS() {
        System.out.println(getElapsed());
    }
}
