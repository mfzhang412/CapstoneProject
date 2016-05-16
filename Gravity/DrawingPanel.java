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

import java.awt.geom.Line2D;

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
    
    protected CreationListener cListener;
    protected DragListener dListener;
    
    protected Ellipse2D.Double temp;
    
    protected boolean trueFalse;
    private boolean starter;
    
    /**
     * Default constructor for objects of class DrawingPanel
     */
    public DrawingPanel()
    {   
        this.setBackground(Color.BLACK);
        
        this.trueFalse = false;
        this.starter = false;
        
        list = new ArrayList<SpaceSystem>();
        //list.add(new SpaceSystem(1076545000876.0, 25, 600, 300, 0, -5));
        //list.add(new SpaceSystem(1000000000000.0, 50, 500, 300, 3, 13));
        //list.add(new SpaceSystem(10000091.0, 15, 200, 400, -7, 3));
        //list.add(new SpaceSystem(3087098471023.0, 34, 400, 100, 0, 0));
        
        xVelList = new Double[list.size()];
        yVelList = new Double[list.size()];
        xCentList = new Double[list.size()];
        yCentList = new Double[list.size()];
        
        this.mass = Math.pow(1, -10);
        this.radius = 1;
        
        cListener = new CreationListener();
        dListener = new DragListener();
        this.addMouseListener(cListener);
        this.addMouseMotionListener(dListener);
    }
    
    public void simulateSolarSystem()
    {
        //list = new ArrayList<SpaceSystem>();
        //list.add(new SpaceSystem(1.9891 * Math.pow(10, 30), 8.789386813, 575, 350, 0, 0));//sun
        //list.add(new SpaceSystem(3.285 * Math.pow(10, 23), .0308288462, 
    }
    
    public void readControls(ControlPanel c)
    {
        this.controls = c;
    }
    
    public void setStarter(boolean tf)
    {
        this.starter = tf;
    }
    
    public void clearSystems()
    {
        list = new ArrayList<SpaceSystem>();
    }

    public void addSystem(double m, double r)
    {
        this.mass = m;
        this.radius = r;
    }
    
    public ArrayList<SpaceSystem> getSystems()
    {
        return list;
    }
    
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        
        this.g = g;
        
        if (trueFalse)
        {
            ((Graphics2D) g).setColor(Color.BLUE);
        }
        else
        {
            ((Graphics2D) g).setColor(Color.BLACK);
        }
        
        if (temp != null)
        {
            try
            {
                ((Graphics2D) g).fill(temp);
                ((Graphics2D) g).draw(temp);
            }
            catch (NullPointerException e) {}
        }
        
        ((Graphics2D) g).drawLine((int) cListener.getiX(), (int) cListener.getiY(), (int) dListener.getfX(), (int) dListener.getfY());
        
        if (!starter)
        {
            for (SpaceSystem sys: list)
            {
                Ellipse2D.Double simTemp = new Ellipse2D.Double(sys.getXVal() - sys.getRadius(), sys.getYVal() - sys.getRadius(), sys.getRadius() * 2, sys.getRadius() * 2);
                ((Graphics2D) g).setColor(Color.WHITE);
                ((Graphics2D) g).fill(simTemp);
                ((Graphics2D) g).draw(simTemp);
            }
        }
        else
        {
            for (SpaceSystem sys: list)
            {
                sys.draw((Graphics2D) g);
            }
            
            try
            {
                Thread.sleep(100);
            }
            catch (InterruptedException e) {}
            
            this.calculateNewCenters();
        }
        
        repaint();
    }
    
    private void calculateNewCenters()
    {   
        for (int i = 0; i < list.size(); i++)
        {
            double xComp = 0.0;
            double yComp = 0.0;
            
            for (SpaceSystem sys: list)
            {
                if ((list.get(i) != sys) && ((sys.getXVal() != list.get(i).getXVal()) && (sys.getYVal() != list.get(i).getYVal())))
                {
                    xComp += ((sys.getXVal() - list.get(i).getXVal()) * (G * sys.getMass() * list.get(i).getMass())) / ((Math.pow(sys.getXVal() - list.get(i).getXVal(), 2)) + (Math.pow(sys.getYVal() - list.get(i).getYVal(), 2)));
                    yComp += ((sys.getYVal() - list.get(i).getYVal()) * (G * sys.getMass() * list.get(i).getMass())) / ((Math.pow(sys.getXVal() - list.get(i).getXVal(), 2)) + (Math.pow(sys.getYVal() - list.get(i).getYVal(), 2)));
                }
            }
            
            double cenX = list.get(i).getXVal() + list.get(i).getXVelocity() * 1 + .5 * (xComp / list.get(i).getMass()) * Math.pow(1, 2);
            double cenY = list.get(i).getYVal() + list.get(i).getYVelocity() * 1 + .5 * (yComp / list.get(i).getMass()) * Math.pow(1, 2);
            
            double new_velocity_X = (xComp / list.get(i).getMass()) * 1 + list.get(i).getXVelocity();
            double new_velocity_Y = (yComp / list.get(i).getMass()) * 1 + list.get(i).getYVelocity();
            
            xVelList[i] = new_velocity_X;
            yVelList[i] = new_velocity_Y;
            xCentList[i] = cenX;
            yCentList[i] = cenY;
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
        private double x;
        private double y;
        
        public void mousePressed(MouseEvent e)
        {
            this.x = e.getX();
            this.y = e.getY();
            
            temp = new Ellipse2D.Double(x - radius, y - radius, radius * 2, radius * 2);
            
            trueFalse = true;
            
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
            
            trueFalse = false;
        }
        
        public double getiX()
        {
            return this.x;
        }
        
        public double getiY()
        {
            return this.y;
        }
        
        public void mouseClicked(MouseEvent e) {}
        
        public void mouseEntered(MouseEvent e) {}
        
        public void mouseExited(MouseEvent e) {}
    }
    
    public class DragListener implements MouseMotionListener
    {
        private double x;
        private double y;
        
        public void mouseDragged(MouseEvent e)
        {
            this.x = e.getX();
            this.y = e.getY();
            
            double dx = e.getX() - cListener.getiX();
            double dy = e.getY() - cListener.getiY();
            double velocity = Math.sqrt(dx * dx + dy * dy);
            controls.setVelocityLabel(velocity);
            
            repaint();
        }
        
        public double getfX()
        {
            return this.x;
        }
        
        public double getfY()
        {
            return this.y;
        }
        
        public void mouseMoved(MouseEvent e) {}
    }
}
