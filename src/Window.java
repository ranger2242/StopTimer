import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Chris Cavazos on 9/19/2018.
 */
class Window {
    static final JFrame frame = new JFrame();
    private final JPanel panel = new JPanel();
    private final JCheckBox checkBox = new JCheckBox();
    private final JLabel timeLbl = new JLabel("-");
    private final JLabel countLbl = new JLabel("-");
    private final JLabel elapsedLbl = new JLabel("0:00:00");
    private final JLabel minLbl = new JLabel("-");
    private final JLabel maxLbl = new JLabel("-");
    private final JLabel soundLbl = new JLabel("SOUND");
    private final JButton resetBtn = new JButton("RESET");
    private final JButton aboutBtn = new JButton("ABOUT");

    private final Timer mainClock;

    private int cnt = 0;
    static Popup popup = null;

    Window() {
        checkBox.setSelected(true);
        setSizes();
        makeFrame();
        setTheme();
        mainClock= new Timer();
        mainClock.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                Main.clock.end();
                updateClock(Main.clock.runtime());

            }
        },1000,1000);
    }

    private void setSizes() {
        Dimension d = new Dimension(70, 14);
        checkBox.setPreferredSize(d);
        checkBox.setSize(d);
        countLbl.setPreferredSize(d);
        countLbl.setSize(d);
        minLbl.setPreferredSize(d);
        minLbl.setSize(d);
        soundLbl.setPreferredSize(d);
        soundLbl.setSize(d);
        timeLbl.setPreferredSize(d);
        timeLbl.setSize(d);
        elapsedLbl.setPreferredSize(d);
        elapsedLbl.setSize(d);
        maxLbl.setPreferredSize(d);
        maxLbl.setSize(d);

        Dimension d3 = new Dimension(70, 14);
        resetBtn.setSize(d3);
        aboutBtn.setSize(d3);
        resetBtn.setPreferredSize(d3);
        aboutBtn.setPreferredSize(d3);



        Dimension d2 = new Dimension(300, 110);

        panel.setPreferredSize(d2);
        frame.setPreferredSize(d2);
        frame.setSize(d2);
        panel.setSize(d2);

    }

    private void alertSelf() {
        panel.setBackground(Theme.alert);

        if (checkBox.isSelected()) {
            Toolkit.getDefaultToolkit().beep();
        }
    }

    private void makeFrame() {
        timeLbl.setText(Clock.getInterval() + "");
        panel.setLayout(new MigLayout("" , "[70px][70px][70px][30px]"));
        panel.removeAll();
        panel.add(timeLbl, "pad 0 24 0 0");
        panel.add(minLbl);
        panel.add(soundLbl, "split 2");
        panel.add(checkBox);
        panel.add(resetBtn, "wrap");
        panel.add(elapsedLbl, "center");
        panel.add(maxLbl);
        panel.add(countLbl);
        panel.add(aboutBtn);
        panel.revalidate();
        panel.repaint();
        resetBtn.addMouseListener(btnClick);
        aboutBtn.addMouseListener(abtClick);
        frame.addComponentListener(frameClick);
        frame.add(panel);
        frame.setTitle("StopTimer 0.2b");
        frame.setVisible(true);
        frame.setAlwaysOnTop(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    private void setTheme() {
        timeLbl.setForeground(Theme.text);
        countLbl.setForeground(Theme.text);
        elapsedLbl.setForeground(Theme.text);
        minLbl.setForeground(Theme.text);
        maxLbl.setForeground(Theme.text);
        soundLbl.setForeground(Theme.text);
        resetBtn.setForeground(Theme.text);
        panel.setBackground(Theme.panel);
        checkBox.setBackground(Theme.panel);
        resetBtn.setBackground(Theme.btn);
        aboutBtn.setBackground(Theme.btn);
        aboutBtn.setForeground(Theme.text);
        changeFont(panel,Font.getFont("Courier New"));

    }

    private static void changeFont(Component component, Font font) {

        component.setFont ( font );
        if ( component instanceof Container ) {
            for ( Component child : ( ( Container ) component ).getComponents () ) {
                changeFont ( child, font );
            }
        }
    }

    private void reset() {
        setTheme();
        Main.startCycle();
        cnt++;
    }
    private void updateClock(String time){
        elapsedLbl.setText(time);
        panel.revalidate();
        panel.repaint();
    }
    void update() {
        float inv = Clock.getInterval();
        if (inv <= 0) {
            alertSelf();
            Clock.stop();
        }
        timeLbl.setText(inv + "");

        countLbl.setText("CNT: " + cnt);
        int a = cnt * 3;
        int b = cnt * 5;
        minLbl.setText("MIN: " + a);
        maxLbl.setText("MAX: " + b);
        panel.revalidate();
        panel.repaint();
    }

    private final MouseListener btnClick = new MouseListener() {
        @Override
        public void mouseClicked(MouseEvent e) {reset();}

        @Override
        public void mousePressed(MouseEvent e) {}

        @Override
        public void mouseReleased(MouseEvent e) {}

        @Override
        public void mouseEntered(MouseEvent e) {}

        @Override
        public void mouseExited(MouseEvent e) {}
    };
    private final MouseListener abtClick = new MouseListener() {
        @Override
        public void mouseClicked(MouseEvent e) {
            if(popup!=null){
                popup.frame.dispose();
            }
            popup=new Popup(frame.getX()+25,frame.getY());}

        @Override
        public void mousePressed(MouseEvent e) {}

        @Override
        public void mouseReleased(MouseEvent e) {}

        @Override
        public void mouseEntered(MouseEvent e) {}

        @Override
        public void mouseExited(MouseEvent e) {}
    };
    private final ComponentListener frameClick = new ComponentListener() {
        @Override
        public void componentResized(ComponentEvent e) {

        }

        @Override
        public void componentMoved(ComponentEvent e) {
            if(popup!=null){

                popup.setPos(frame.getX()+25,frame.getY()+100);
            }
        }

        @Override
        public void componentShown(ComponentEvent e) {

        }

        @Override
        public void componentHidden(ComponentEvent e) {

        }
    };
}
