/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package neutraltimer;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.TimerTask;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.util.Timer;
import javax.swing.border.EmptyBorder;
/**
 *
 * @author Cameron
 */
public class NeutralTimer extends JFrame {
    
    private Point initialClick;
    
    //Ally Red Buff Variables
    private ImageIcon redBuffIO = new ImageIcon(NeutralTimer.class.getResource("/redBuff.png"));
    private JLabel redBuff = new JLabel(redBuffIO);
    private Timer redTimer = new Timer("Red Buff Timer");
    public static JLabel redBuffTime = new JLabel("0:00");
    
    //Ally Blue Buff Variables
    private ImageIcon blueBuffIO = new ImageIcon(NeutralTimer.class.getResource("/blueBuff.png"));
    private JLabel blueBuff = new JLabel(blueBuffIO);
    private Timer blueTimer = new Timer("Blue Buff Timer");
    public static JLabel blueBuffTime = new JLabel("0:00");
    
    //Enemy Red Buff Variables
    private ImageIcon redBuffIOE = new ImageIcon(NeutralTimer.class.getResource("/redBuffE.png"));
    private JLabel redBuffE = new JLabel(redBuffIOE);
    private Timer redTimerE = new Timer("Enemy Red Buff Timer");
    public static JLabel redBuffTimeE = new JLabel("0:00");
    
    //Enemy Blue Buff Variables
    private ImageIcon blueBuffIOE = new ImageIcon(NeutralTimer.class.getResource("/blueBuffE.png"));
    private JLabel blueBuffE = new JLabel(blueBuffIOE);
    private Timer blueTimerE = new Timer("Enemy Blue Buff Timer");
    public static JLabel blueBuffTimeE = new JLabel("0:00");
    
    //Dragon Variables
    private ImageIcon dragonIO = new ImageIcon(NeutralTimer.class.getResource("/dragon.png"));
    private JLabel dragon = new JLabel(dragonIO);
    private Timer dragonTimer = new Timer("Dragon Timer");
    public static JLabel dragonTime = new JLabel("0:00");
    
    //Baron Variables
    private ImageIcon baronIO = new ImageIcon(NeutralTimer.class.getResource("/baron.png"));
    private JLabel baron = new JLabel(baronIO);
    private Timer baronTimer = new Timer("Baron Timer");
    public static JLabel baronTime = new JLabel("0:00");

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) 
    {
        new NeutralTimer();
    }
    
    NeutralTimer()
    {
        setTitle("Neutral Timer");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setUndecorated(true);
        setAlwaysOnTop(true);
        setVisible(true);
        
        
        addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                initialClick = e.getPoint();
                getComponentAt(initialClick);
            }
        });
        
        addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseDragged(MouseEvent e) {
                // get location of Window
                int thisX = getLocation().x;
                int thisY = getLocation().y;

                if ((initialClick.y <= 5) || (initialClick.x <= 5) || (initialClick.x >= getWidth()-5) || (initialClick.y >= getHeight()-5))
                {
                    // Determine how much the mouse moved since the initial click
                    int xMoved = (thisX + e.getX()) - (thisX + initialClick.x);
                    int yMoved = (thisY + e.getY()) - (thisY + initialClick.y);

                    // Move window to this position
                    int X = thisX + xMoved;
                    int Y = thisY + yMoved;
                    setLocation(X, Y);
                }
            }
            
            public void mouseMoved(MouseEvent e)
            {
               if ((e.getPoint().y <= 5) || (e.getPoint().x <= 5) || (e.getPoint().x >= getWidth()-5) || (e.getPoint().y >= getHeight()-5))
               {   
                    setCursor(Cursor.getPredefinedCursor(Cursor.MOVE_CURSOR));
               }
               else
               {
                    setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
               }
            }
        });
        //Jpanel
        JPanel panel = new JPanel();
        //panel.setBorder(new EmptyBorder(3,3,0,0));
        panel.setBackground(Color.PINK);
        
        //Red Buff
        CampTimer rbt = new CampTimer("red");
        redTimer.schedule(rbt, 1000, 1000);
        
        //Blue Buff
        CampTimer bbt = new CampTimer("blue");
        blueTimer.schedule(bbt, 1000, 1000);
        
        //Enemy Red Buff
        CampTimer erbt = new CampTimer("redE");
        redTimerE.schedule(erbt, 1000, 1000);
        
        //Enemy Blue Buff
        CampTimer ebbt = new CampTimer("blueE");
        blueTimerE.schedule(ebbt, 1000, 1000);
        
        //Dragon
        CampTimer dt = new CampTimer("dragon");
        dragonTimer.schedule(dt, 1000, 1000);
        
        //Baron
        CampTimer bt = new CampTimer("baron");
        baronTimer.schedule(bt, 1000, 1000);
        
        //Set image layouts
        redBuff.setLayout(new BorderLayout());
        blueBuff.setLayout(new BorderLayout());
        dragon.setLayout(new BorderLayout());
        baron.setLayout(new BorderLayout());
        redBuffE.setLayout(new BorderLayout());
        blueBuffE.setLayout(new BorderLayout());
        
        //Add to panel
        panel.add(redBuff);
        panel.add(blueBuff);
        panel.add(dragon);
        panel.add(baron);
        panel.add(redBuffE);
        panel.add(blueBuffE);
        
        //Add panel to frame
        add(panel);
        
        //Formatting red buff
        redBuffTime.setForeground(Color.PINK);
        redBuffTime.setHorizontalAlignment(JLabel.CENTER);
        redBuffTime.setFont(new Font(redBuffTime.getName(), Font.BOLD, 13));
        redBuff.add(redBuffTime, BorderLayout.SOUTH);
        
        //Formatting blue buff
        blueBuffTime.setForeground(Color.YELLOW);
        blueBuffTime.setHorizontalAlignment(JLabel.CENTER);
        blueBuffTime.setFont(new Font(blueBuffTime.getName(), Font.BOLD, 14));
        blueBuff.add(blueBuffTime);
        
        //Formatting enemy red buff
        blueBuffTimeE.setForeground(Color.CYAN);
        blueBuffTimeE.setHorizontalAlignment(JLabel.CENTER);
        blueBuffTimeE.setFont(new Font(blueBuffTimeE.getName(), Font.BOLD, 15));
        blueBuffE.add(blueBuffTimeE);
        
        //Formatting enemy red buff
        redBuffTimeE.setForeground(Color.MAGENTA);
        redBuffTimeE.setHorizontalAlignment(JLabel.CENTER);
        redBuffTimeE.setFont(new Font(redBuffTimeE.getName(), Font.BOLD, 16));
        redBuffE.add(redBuffTimeE);
        
        //Formatting dragon
        dragonTime.setForeground(Color.GREEN);
        dragonTime.setHorizontalAlignment(JLabel.CENTER);
        dragonTime.setFont(new Font(dragonTime.getName(), Font.BOLD, 17));
        dragon.add(dragonTime);
        
        //Formatting baron
        baronTime.setForeground(Color.WHITE);
        baronTime.setHorizontalAlignment(JLabel.CENTER);
        baronTime.setFont(new Font(baronTime.getName(), Font.BOLD, 18));
        baron.add(baronTime, BorderLayout.SOUTH);
        
        setLocationRelativeTo(null);
        pack();
    }
}

class CampTimer extends TimerTask
{
    public int timeBuffLeft = 300;
    public int timeDragonLeft = 360;
    public int timeBaronLeft = 420;
    public String task;
    
    public CampTimer(String task)
    {
        this.task = task;
    }
    
    public void run()
    {
        if (this.timeBuffLeft > 0 && timeDragonLeft > 0 && timeBaronLeft > 0)
        {
            if (task.compareTo("red") == 0)
            {
                timeBuffLeft--;
                NeutralTimer.redBuffTime.setText(formatTime(timeBuffLeft));
            }
            else if (task.compareTo("blue") == 0)
            {
                timeBuffLeft--;
                NeutralTimer.blueBuffTime.setText(formatTime(timeBuffLeft));
            }
            else if (task.compareTo("redE") == 0)
            {
                timeBuffLeft--;
                NeutralTimer.redBuffTimeE.setText(formatTime(timeBuffLeft));
            }
            else if (task.compareTo("blueE") == 0)
            {
                timeBuffLeft--;
                NeutralTimer.blueBuffTimeE.setText(formatTime(timeBuffLeft));
            }
            else if (task.compareTo("dragon") == 0)
            {
                timeDragonLeft--;
                NeutralTimer.dragonTime.setText(formatTime(timeDragonLeft));
            }
            else if (task.compareTo("baron") == 0)
            {
                timeBaronLeft--;
                NeutralTimer.baronTime.setText(formatTime(timeBaronLeft));
            }
        }
        else
        {
            this.cancel();
        }
    }
    
    public String formatTime(int time)
    {
        String str = "";
        
        str += "" + time/60;
        str += ":";

        if (time % 60 == 0)
            str += "00";
        else if (time % 60 < 10)
            str += "0" + (time%60);
        else
            str += "" + (time%60);
            
        return str;
    }
}
