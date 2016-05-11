import javax.swing.JPanel;
import java.util.ArrayList;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.geom.Point2D;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JColorChooser;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseEvent;

/**
 * Write a description of class DrawingPanel here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class DrawingPanel extends JPanel
{
    private static final double G = 6.6741 * Math.pow(10, -11);
    
    /** description of instance variable x (add comment for each instance variable) */
    private ArrayList<SpaceSystem> list;
    
    Double[] xVelList;
    Double[] yVelList;
    Double[] xCentList;
    Double[] yCentList;

    /**
     * Default constructor for objects of class DrawingPanel
     */
    public DrawingPanel()
    {
        this.setBackground(Color.BLACK);
        list = new ArrayList<SpaceSystem>();
        list.add(new SpaceSystem(1.989 * Math.pow(10, 30), 50, 500, 300, 0, 0));
        xVelList = new Double[list.size()];
        yVelList = new Double[list.size()];
        xCentList = new Double[list.size()];
        yCentList = new Double[list.size()];
    }
    
    public void addSystem(double m, double r, double x, double y, double vX, double vY)
    {
        list.add(new SpaceSystem(m, r, x, y, vX, vY));
        xVelList = new Double[list.size()];
        yVelList = new Double[list.size()];
        xCentList = new Double[list.size()];
        xCentList = new Double[list.size()];
    }
    
    public ArrayList<SpaceSystem> getSystems()
    {
        return list;
    }
    
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        for (SpaceSystem sys: list)
        {
            sys.draw((Graphics2D) g);            
        }
        this.calculateNewCenters();
        //sleep for 1 second (1000 for thread.sleep(1000))
    }
    
    private void calculateNewCenters()
    {   
        for (int i = 0; i < list.size(); i++)
        {
            double xComp = 0.0;
            double yComp = 0.0;
            
            for (SpaceSystem sys: list)
            {
                if (sys != list.get(i))
                {
                    xComp += ((sys.getX() - list.get(i).getX()) * (G * sys.getMass() * list.get(i).getMass())) / ((Math.pow(sys.getX() - list.get(i).getX(), 2)) + (Math.pow(sys.getY() - list.get(i).getY(), 2)));
                    yComp += ((sys.getY() - list.get(i).getY()) * (G * sys.getMass() * list.get(i).getMass())) / ((Math.pow(sys.getX() - list.get(i).getX(), 2)) + (Math.pow(sys.getY() - list.get(i).getY(), 2)));
                }
            }
            
            double retX = list.get(i).getX() + list.get(i).getXVelocity() * 1 + .5 * (xComp / list.get(i).getMass()) * Math.pow(1, 2);
            double retY = list.get(i).getY() + list.get(i).getYVelocity() * 1 + .5 * (yComp / list.get(i).getMass()) * Math.pow(1, 2);
            
            double new_velocity_X = (xComp / list.get(i).getMass()) * 1 + list.get(i).getXVelocity();
            double new_velocity_Y = (yComp / list.get(i).getMass()) * 1 + list.get(i).getYVelocity();
            
            xVelList[i] = new_velocity_X;
            yVelList[i] = new_velocity_Y;
            xCentList[i] = retX;
            yCentList[i] = retY;
        }
        
        this.updateSystems();
    }
    
    public void updateSystems()
    {
        for (int i = 0; i < list.size(); i++)
        {
            list.get(i).move(xCentList[i], yCentList[i]);
            list.get(i).setXVelocity(xVelList[i]);
            list.get(i).setYVelocity(yVelList[i]);
        }
    }
}
