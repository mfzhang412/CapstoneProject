import javax.swing.JPanel;
import java.util.ArrayList;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.geom.Point2D;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
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
    protected ControlPanel controls;
    
    private ArrayList<SpaceSystem> list;
    
    protected Double[] xVelList;
    protected Double[] yVelList;
    protected Double[] xCentList;
    protected Double[] yCentList;

    protected double mass;
    protected double radius;
    
    protected Graphics g;
    
    /**
     * Default constructor for objects of class DrawingPanel
     */
    public DrawingPanel()
    {   
        this.setBackground(Color.BLACK);
        
        list = new ArrayList<SpaceSystem>();
        list.add(new SpaceSystem(1076545000876.0, 25, 600, 300, 0, -5));
        list.add(new SpaceSystem(1000000000000.0, 50, 500, 300, 3, 13));
        list.add(new SpaceSystem(10000091.0, 15, 200, 400, -7, 3));
        list.add(new SpaceSystem(3087098471023.0, 34, 400, 100, 0, 0));
        
        xVelList = new Double[list.size()];
        yVelList = new Double[list.size()];
        xCentList = new Double[list.size()];
        yCentList = new Double[list.size()];
    }
    
    public void readControls(ControlPanel c)
    {
        this.controls = c;
    }
    
    //     public void addSystem(double m, double r, double x, double y, double vX, double vY)
    //     {
    //         SpaceSystem newSystem = new SpaceSystem(m, r, x, y, vX, vY);
    //         list.add(newSystem);
    //         
    //         xVelList = new Double[list.size()];
    //         yVelList = new Double[list.size()];
    //         xCentList = new Double[list.size()];
    //         yCentList = new Double[list.size()];        
    //     }
    
    
    
    
    
    //
    public void addSystem(double m, double r)
    {
        this.mass = m;
        this.radius = r;
    }
    //
    
    
    
    
    
    public ArrayList<SpaceSystem> getSystems()
    {
        return list;
    }
    
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        
        this.g = g;
        
        for (SpaceSystem sys: list)
        {
            sys.draw((Graphics2D) g);
        }
        
        repaint();
        
        try
        {
            Thread.sleep(100);
        }
        catch (InterruptedException e)
        {
        }
        
        this.calculateNewCenters();
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
                    xComp += ((sys.getXVal() - list.get(i).getXVal()) * (G * sys.getMass() * list.get(i).getMass())) / ((Math.pow(sys.getXVal() - list.get(i).getXVal(), 2)) + (Math.pow(sys.getYVal() - list.get(i).getYVal(), 2)));
                    yComp += ((sys.getYVal() - list.get(i).getYVal()) * (G * sys.getMass() * list.get(i).getMass())) / ((Math.pow(sys.getXVal() - list.get(i).getXVal(), 2)) + (Math.pow(sys.getYVal() - list.get(i).getYVal(), 2)));
                }
            }
            
            double retX = list.get(i).getXVal() + list.get(i).getXVelocity() * 1 + .5 * (xComp / list.get(i).getMass()) * Math.pow(1, 2);
            double retY = list.get(i).getYVal() + list.get(i).getYVelocity() * 1 + .5 * (yComp / list.get(i).getMass()) * Math.pow(1, 2);
            
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
    
    public class CreationListener implements MouseListener
    {
        protected double x;
        protected double y;
        
        public void mousePressed(MouseEvent e)
        {
            this.x = e.getX();
            this.y = e.getY();
            
            Ellipse2D temp = new Ellipse2D.Double(x - radius, y - radius, radius * 2, radius * 2);
            ((Graphics2D) g).setColor(Color.WHITE);
            ((Graphics2D) g).fill(temp);
            ((Graphics2D) g).draw(temp);
            
            repaint();
        }
        
        public void mouseReleased(MouseEvent e)
        {
            double dx = e.getX() - x;
            double dy = e.getY() - y;
            
            SpaceSystem newSystem = new SpaceSystem(mass, radius, x, y, dx, dy);
            list.add(newSystem);
            
            xVelList = new Double[list.size()];
            yVelList = new Double[list.size()];
            xCentList = new Double[list.size()];
            yCentList = new Double[list.size()];
        }
        
        
        public void mouseClicked(MouseEvent e)
        {
            
        }
        
        public void mouseEntered(MouseEvent e)
        {
            
        }
        
        public void mouseExited(MouseEvent e)
        {
            
        }
        
        public class DragListener implements MouseMotionListener
        {            
            public void mouseDragged(MouseEvent e)
            {
                double dx = e.getX() - x;
                double dy = e.getY() - y;
                double velocity = Math.sqrt(dx * dx + dy * dy);
                controls.setVelocityLabel(velocity);
                
                g.drawLine((int) x, (int) y, (int) e.getX(), (int) e.getY());
                
                repaint();
            }
            
            public void mouseMoved(MouseEvent e) {}
        }
    }
    
    
    
    
    
    
    
    
    
    
}
