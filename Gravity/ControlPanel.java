import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Scanner;
import java.awt.Dimension;

/**
 * Write a description of class ControlPanel here.
 * 
 * @author Michael Zhang
 * @version 14 April 2016
 */
public class ControlPanel extends JPanel
{
    /** description of instance variable x (add comment for each instance variable) */
    private JLabel displayVel;
    private JButton set;
    private JButton start;
    private JButton stop;
    private JButton clear;
    
    private DrawingPanel panel;
    private SpaceSystem s;
    
    /**
     * Default constructor for objects of class ControlPanel
     */
    public ControlPanel(DrawingPanel panel)
    {
        this.panel = panel;
        
        this.displayVel = new JLabel("Velocity:                   ");
        this.set = new JButton("Set parameters");
        this.start = new JButton("Start simulation");
        this.stop = new JButton("Pause simulation");
        this.clear = new JButton("Clear systems");
        
        this.add(displayVel);
        this.add(set);
        this.add(start);
        this.add(stop);
        this.add(clear);
        
        set.addActionListener(new SetListener());
        start.addActionListener(new StartListener());
        stop.addActionListener(new StopListener());
        clear.addActionListener(new ClearListener());
    }
    
    public void setVelocityLabel(double v)
    {
        this.displayVel.setText("Velocity: " + v + " m/s");
    }
    
    public Dimension getPreferredSize()
    {
        return (new Dimension(200, 700));
    }

    
    public class SetListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            Double mass = Double.parseDouble(JOptionPane.showInputDialog("Set the system's mass."));
            Double radius = Double.parseDouble(JOptionPane.showInputDialog("Set the system's radius."));
            
            panel.addSystem(mass, radius);
        }
    }
    
    public class StartListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            panel.setStarter(true);
        }
    }
    
    public class StopListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            panel.setStarter(false);
        }
    }
    
    public class ClearListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            panel.setStarter(false);
            panel.clearSystems();
        }
    }
}
