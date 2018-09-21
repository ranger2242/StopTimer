import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Chris Cavazos on 9/20/2018.
 */
class Popup {
    final JFrame frame = new JFrame("About");
    private Timer timer;
    Popup(int x, int y) {
        JPanel panel = new JPanel(new MigLayout(""));
        JLabel l1 = new JLabel("Made by Chris Cavazos");
        JLabel l2 = new JLabel("Report bugs on github");
        JLabel l3 = new JLabel("https://github.com/ranger2242/StopTimer");
        JLabel l4 = new JLabel("Hire me to write your programs.");

        Dimension d = new Dimension(250,90);
        frame.setSize(d);
        frame.setPreferredSize(d);
        l1.setForeground(Theme.text);
        l2.setForeground(Theme.text);
        l3.setForeground(Theme.text);
        l4.setForeground(Theme.text);

        panel.setSize(d);
        panel.setPreferredSize(d);
        panel.add(l1,"center,wrap");
        panel.add(l2,"center,wrap");
        panel.add(l3,"center,wrap");
        panel.add(l4,"center,wrap");

        panel.setBackground(Theme.panel);
        panel.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                frame.dispose();
                Window.popup=null;
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
        frame.setLocation(x,y);
        frame.setUndecorated(true);
        frame.setContentPane(panel);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        frame.setAutoRequestFocus(true);

        if (timer != null)
            timer.cancel();
        timer = new java.util.Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if(frame.getY()<Window.frame.getY()+100){
                    frame.setLocation(frame.getX(), frame.getY()+2);
                    frame.revalidate();
                    frame.repaint();
                }
            }
        },10,10);
    }

    void setPos(int x, int y) {
        frame.setLocation(x,y);
        frame.repaint();
        frame.revalidate();
    }
}
