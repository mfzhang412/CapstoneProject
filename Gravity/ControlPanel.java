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
    
    private DrawingPanel panel;
    private SpaceSystem s;
    
    private double mass;
    private double radius;
    
    /**
     * Default constructor for objects of class ControlPanel
     */
    public ControlPanel(DrawingPanel panel)
    {
        this.panel = panel;
        
        this.massOption = new JButton("Set Mass");
        this.radiusOption = new JButton("Set Radius");
        
        this.add(massOption);
        this.add(radiusOption);
        
        MassListener m = new MassListener();
        RadiusListener r = new RadiusListener();
        
        massOption.addActionListener(new MassListener());
        radiusOption.addActionListener(new RadiusListener());
    }

    public class MassListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            System.out.print("Enter in a valid mass: ");
            Scanner scan = new Scanner(System.in);
            mass = scan.nextDouble();
            System.out.println("");
            s.setMass(mass);
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
            s.setRadius(radius);
        }
    }
}
