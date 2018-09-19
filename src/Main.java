import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Chris Cavazos on 3/27/2017.
 */
public class Main {
    static Timer timer;
    static Clock clock=new Clock();
    static JPanel panel= new JPanel();
    static JCheckBox cb =new JCheckBox();
    static JLabel timeL= new JLabel("-");
    static JLabel cntL= new JLabel("-");
    static JLabel elapL = new JLabel("");
    static JLabel minL= new JLabel("-");
    static JLabel maxL= new JLabel("-");
    static JLabel sndL = new JLabel(":SND");
    static JButton res = new JButton("RESET");

    static int interval;
    static int delay = 1000;
    static int period = 1000;
    static int clickCnt = 0;



    public static void main(String[] args) {
        clock.start();
        cb.setSelected(true);

        timeL.setText(setInterval()+"");
        JFrame frame = new JFrame();
        panel.setLayout(new MigLayout("","[70px][70px][70px][30px]"));
        panel.removeAll();
        panel.add(cb,"split 2");
        panel.add(sndL);
        panel.add(timeL,"center");
        panel.add(minL);
        panel.add(res,"wrap,span 1 2");
        panel.add(cntL);
        panel.add(elapL,"center");
        panel.add(maxL);
        panel.revalidate();
        panel.repaint();



        startTimer();
        res.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                startTimer();
                update();
                clickCnt++;

            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
        frame.add(panel);
        Dimension d = new Dimension(250,100);
        Dimension d2 = new Dimension(20,100);
        res.setSize(d2);
        res.setPreferredSize(d2);
        panel.setPreferredSize(d);
        frame.setPreferredSize(d);
        frame.setSize(d);
        panel.setSize(d);

        frame.setVisible(true);
        frame.setAlwaysOnTop(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
    static void startTimer(){
        reset();
        timer.scheduleAtFixedRate(new TimerTask() {
            public void run() {
                update();
            }
        }, delay, period);
        panel.setBackground(Color.WHITE);
    }
    private static void reset(){
        interval=300;
        if(timer !=null)
            timer.cancel();
        timer=new Timer();
    }

    static private void update(){
        clock.end();
        timeL.setText(setInterval()+"");
        cntL.setText("CNT: "+clickCnt);
        int a= (int) (clickCnt*2.5);
        int b= (int) (clickCnt*4.5);
        elapL.setText(clock.runtime());
        minL.setText("MIN: "+a);
        maxL.setText("MAX: "+b);
        panel.revalidate();
        panel.repaint();
    }
    private static final int setInterval() {
        if (interval == 1) {
            timer.cancel();
            panel.setBackground(Color.RED);
            if(cb.isSelected()){
                Toolkit.getDefaultToolkit().beep();
            }
        }
        return --interval;
    }
}
