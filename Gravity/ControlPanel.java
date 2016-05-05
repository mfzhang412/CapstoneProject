import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Scanner;

/**
 * Write a description of class ControlPanel here.
 * 
 * @author Michael Zhang
 * @version 14 April 2016
 */
public class ControlPanel extends JPanel
{
    /** description of instance variable x (add comment for each instance variable) */
    private JButton massOption;
    private JButton radiusOption;
    private JButton xCoor;
    private JButton yCoor;
    private JButton set;
    
    private DrawingPanel panel;
    private SpaceSystem s;
    
    private double mass;
    private double radius;
    private double x;
    private double y;
    
    /**
     * Default constructor for objects of class ControlPanel
     */
    public ControlPanel(DrawingPanel panel)
    {
        this.panel = panel;
        
        this.massOption = new JButton("Set Mass");
        this.radiusOption = new JButton("Set Radius");
        
        this.xCoor = new JButton("Set x-coordinate of the system");
        this.yCoor = new JButton("Set y-coordinate of the system");
        
        this.set = new JButton("Set parameters");
        
        this.add(massOption);
        this.add(radiusOption);
        this.add(xCoor);
        this.add(yCoor);
        this.add(set);
        
        MassListener m = new MassListener();
        RadiusListener r = new RadiusListener();
        XListener xLi = new XListener();
        YListener yLi = new YListener();
        SetListener ok = new SetListener();
        
        massOption.addActionListener(new MassListener());
        radiusOption.addActionListener(new RadiusListener());
        xLi.addActionListener(new XListener());
        yLi.addActionlistener(new YListener());
        ok.addActionListener(new SetListener());
    }

    public class MassListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            System.out.print("Enter in a valid mass: ");
            Scanner scan = new Scanner(System.in);
            mass = scan.nextDouble();
            System.out.println("");
        }
    }

    public class RadiusListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            System.out.print("Enter in a valid volume: ");
            Scanner scan = new Scanner(System.in);
            radius = scan.nextDouble();
            System.out.println("");
        }
    }
    
    public class XListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            System.out.print("Enter in a valid x-coordinate: ");
            Scanner scan = new Scanner(System.in);
            x = scan.nextDouble();
            System.out.println("");
        }
    }
    
    public class YListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            System.out.print("Enter in a valid y-coordinate: ");
            Scanner scan = new Scanner(System.in);
            y = scan.nextDouble();
            System.out.println("");
        }
    }
    
    public class SetListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            s = new SpaceSystem(mass, radius, x, y);
            panel.addSystem(s);
        }
    }
}
