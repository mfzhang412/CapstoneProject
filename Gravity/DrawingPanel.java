import javax.swing.JPanel;
import java.util.ArrayList;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseEvent;

/**
 * A panel that displays and allows the user to interact, via pressing, dragging, and releasing the mouse, with the simulation.
 *      The SpaceSystem objects run according to 1 second intervals (calculations, new velocities, new center positions).
 *      Implications of 1 second intervals: the SpaceSystem objects move linearly for 1 second before making adjustments to velocity, and thus position is also affected.
 *      Scaling: 1 pixel (simulation) = 1 meter (real world). 1/10 second --> 1 frame (simulation) = 1 second (real world).
 * 
 * @author Michael Zhang 
 * @version 15 May 2016
 */
public class DrawingPanel extends JPanel
{
    /** Newtonian constant of gravitation*/
    private static final double G = 6.6741 * Math.pow(10, -11);
    
    /** ArrayList that stores the SpaceSytem objects to be drawn */
    private ArrayList<SpaceSystem> list;
    
    /** Arrays that hold the next frame's SpaceSystem objects' aspects that change (velocity and center coordinates)*/
    protected Double[] xVelList;
    protected Double[] yVelList;
    protected Double[] xCentList;
    protected Double[] yCentList;

    /** The ControlPanel object that allows the user to change the velocity label displayed on the control panel */
    protected ControlPanel controls;
    
    /** The mass and radius of the system to be added to the simulation (the values from control panel's "set" Jbutton) */
    protected double mass;
    protected double radius;
    
    /** The listeners that perform an action when the mouse is pressed, dragged, then released */
    protected CreationListener cListener;
    protected DragListener dListener;
    
    /** The temporary circle created when the user presses the mouse */
    protected Ellipse2D.Double temp;
    
    /** Sets the simulation to one of two states: start or pause */
    private boolean starter;
    
    /** Boolean variable that checks whether or not the color to be displayed should be blue or black (depends on whether the user is adding a SpaceSystem object to the simulation or not) */
    protected boolean trueFalse;
    
    /**
     * Default constructor for objects of class DrawingPanel
     */
    public DrawingPanel()
    {   
        // sets the background of the simulation to black
        this.setBackground(Color.BLACK);
        
        // sets the color of the "temp" intance variable - if drawn - to black
        this.trueFalse = false;
        
        // sets the simulation's initial state to pause
        this.starter = false;
        
        // creates the list that the SpaceSystem objects will be stored in and later drawn
        list = new ArrayList<SpaceSystem>();
        //list.add(new SpaceSystem(1076545000876.0, 25, 600, 300, 0, -5));
        //list.add(new SpaceSystem(1000000000000.0, 50, 500, 300, 3, 13));
        //list.add(new SpaceSystem(10000091.0, 15, 200, 400, -7, 3));
        //list.add(new SpaceSystem(3087098471023.0, 34, 400, 100, 0, 0));
        
        // creates the arrays that will hold the next frame's changing components
        xVelList = new Double[list.size()];
        yVelList = new Double[list.size()];
        xCentList = new Double[list.size()];
        yCentList = new Double[list.size()];
        
        // sets the initial systems - if added by the user - to a negligible mass and radius
        this.mass = Math.pow(1, -10);
        this.radius = 1;
        
        // creates the listener objects
        cListener = new CreationListener();
        dListener = new DragListener();
        
        // adds the listener objects to perform actions when pressing, dragging, and releasing the mouse
        this.addMouseListener(cListener);
        this.addMouseMotionListener(dListener);
    }
    
    
    //  A potential extension in which I model our solar system
    //     public void simulateSolarSystem()
    //     {
    //         list = new ArrayList<SpaceSystem>();
    //         list.add(new SpaceSystem(1.9891 * Math.pow(10, 30), 8.789386813, 575, 350, 0, 0));//sun
    //         list.add(new SpaceSystem(3.285 * Math.pow(10, 23), .0308288462, 
    //     }
    
    
    /**
     * Reads in the controls so the user can modify the velocity label on the control panel
     * 
     * @param  c   The ControlPanel object
     */
    public void readControls(ControlPanel c)
    {
        this.controls = c;
    }
    
    /**
     * Sets the state of the simulation to either start or pause
     * 
     * @param  tf   The parameter being passed in from clicking one of the "Start simulation", "Pause simulation", and "Clear systems" JButtons from the control panel
     */
    public void setStarter(boolean tf)
    {
        this.starter = tf;
    }
    
    /**
     * Clears the SpaceSystem objects from the ArrayList<SpaceSystem> so that the simulation no longer have anything drawn
     */
    public void clearSystems()
    {
        list = new ArrayList<SpaceSystem>();
    }

    /**
     * Reads the mass and radius passed in from clicking the "Set parameters" JButton in the control panel.
     * 
     * @param  m   The mass that will determine the new SpaceSystem object's mass when added to the simulation
     * @param  r   The radius that will determine the new SpaceSystem objet's radius when added to the simulation
     */
    public void addSystem(double m, double r)
    {
        this.mass = m;
        this.radius = r;
    }
    
    /**
     * Returns the ArrayList of SpaceSystem objects
     * 
     * @return     returns the ArrayList of SpaceSystem objects
     */
    public ArrayList<SpaceSystem> getSystems()
    {
        return list;
    }
    
    /**
     * The paint component that will draw the shapes 
     * 
     * @param  g   A Graphics object
     */
    public void paintComponent(Graphics g)
    {
        // calls super's paintComponent method
        super.paintComponent(g);
        
        // checks whether to set the color to blue or black (for drawing the temporary circle when the user is adding a SpaceSystem object to the simulation)
        if (trueFalse)
        {
            ((Graphics2D) g).setColor(Color.BLUE);
        }
        else
        {
            ((Graphics2D) g).setColor(Color.BLACK);
        }
        
        // draws the temporary circle if the user has clicked on the simulation
        if (temp != null)
        {
            try
            {
                ((Graphics2D) g).fill(temp);
                ((Graphics2D) g).draw(temp);
            }
            catch (NullPointerException e) {}
        }
        
        // draws the magnitude of the velocity as a line that the user has dragged
        ((Graphics2D) g).drawLine((int) cListener.getiX(), (int) cListener.getiY(), (int) dListener.getfX(), (int) dListener.getfY());
        
        // checks whether to draw the simulation in a state of 'pause' or 'start'
        if (!starter)  // to be activated when the simulation is in a state of 'pause'
        {
            // draws the static circles in the state of 'pause'
            for (SpaceSystem sys: list)
            {
                Ellipse2D.Double simTemp = new Ellipse2D.Double(sys.getXVal() - sys.getRadius(), sys.getYVal() - sys.getRadius(), sys.getRadius() * 2, sys.getRadius() * 2);
                ((Graphics2D) g).setColor(Color.WHITE);
                ((Graphics2D) g).fill(simTemp);
                ((Graphics2D) g).draw(simTemp);
            }
        }
        else  // to be activated when the simulation is in a state of 'start'
        {
            // draws the SpaceSystem objects in the state of 'start'
            for (SpaceSystem sys: list)
            {
                sys.draw((Graphics2D) g);
            }
            
            // sets a gap between the next redrawing so the animation looks smooth
            try
            {
                Thread.sleep(100);
            }
            catch (InterruptedException e) {}
            
            // function that is called to calculate the SpaceSystem objects' new centers and new velocities
            this.calculateNextFrame();
        }
        
        
        repaint();
    }
    
    /**
     * The method that calculates the new centers and new velocities of the SpaceSystem objects' for the next frame
     */
    private void calculateNextFrame()
    {   
        // calculates the new center and velocity for the respective element in the ArrayList 'list'
        for (int i = 0; i < list.size(); i++)
        {
            // the force components of the SpaceSystem object in the x and y direction
            double xComp = 0.0;
            double yComp = 0.0;
            
            // calculates the x and y components for the SpaceSystem object with correct magnitude (1 unit = 1 newton)
            for (SpaceSystem sys: list)
            {
                if ((list.get(i) != sys) && ((sys.getXVal() != list.get(i).getXVal()) && (sys.getYVal() != list.get(i).getYVal())))
                {
                    xComp += ((sys.getXVal() - list.get(i).getXVal()) * (G * sys.getMass() * list.get(i).getMass())) / Math.sqrt((Math.pow(sys.getXVal() - list.get(i).getXVal(), 2)) + (Math.pow(sys.getYVal() - list.get(i).getYVal(), 2)));
                    yComp += ((sys.getYVal() - list.get(i).getYVal()) * (G * sys.getMass() * list.get(i).getMass())) / Math.sqrt((Math.pow(sys.getXVal() - list.get(i).getXVal(), 2)) + (Math.pow(sys.getYVal() - list.get(i).getYVal(), 2)));
                }
            }
            
            // calculates the new center of the SpaceSystem object after a 1 second interval
            double cenX = list.get(i).getXVal() + list.get(i).getXVelocity() * 1 + .5 * (xComp / list.get(i).getMass()) * Math.pow(1, 2);
            double cenY = list.get(i).getYVal() + list.get(i).getYVelocity() * 1 + .5 * (yComp / list.get(i).getMass()) * Math.pow(1, 2);
            
            // calculates the velocity components of the SpaceSystem object after a 1 second interval
            double new_velocity_X = (xComp / list.get(i).getMass()) * 1 + list.get(i).getXVelocity();
            double new_velocity_Y = (yComp / list.get(i).getMass()) * 1 + list.get(i).getYVelocity();
            
            // adds the components to arrays so as to keep the integrity of the calculations due to instantaneity
            xCentList[i] = cenX;
            yCentList[i] = cenY;
            xVelList[i] = new_velocity_X;
            yVelList[i] = new_velocity_Y;
        }
        
        // function that is called to set the SpaceSystem objects' center, x-component velocity, and y-component velocity
        this.updateSystems();
    }
    
    /**
     * The method that sets the SpaceSystem objects' centers and x-component velocity, and y-component velocity for the next frame
     */
    public void updateSystems()
    {
        // sets each SpaceSystem object's center, x-component velocity, and y-component velocity
        for (int i = 0; i < list.size(); i++)
        {
            list.get(i).move(xCentList[i], yCentList[i]);
            list.get(i).setXVelocity(xVelList[i]);
            list.get(i).setYVelocity(yVelList[i]);
        }
    }
    
    
    public class CreationListener implements MouseListener
    {
        // the x and y value of the initial mouse press
        private double x;
        private double y;
        
        /**
         * Performs an action when the mouse is pressed
         * 
         * @param  e   A MouseEvent object
         */
        public void mousePressed(MouseEvent e)
        {
            // sets the x and y instance variables to the initial mouse press
            this.x = e.getX();
            this.y = e.getY();
            
            // sets the temporary circle object to be drawn at the where the user clicked
            temp = new Ellipse2D.Double(x - radius, y - radius, radius * 2, radius * 2);
            
            // sets the color of the temporary circle and velocity line to blue
            trueFalse = true;
            
            
            repaint();
        }
        
        /**
         * Adds a SpaceSystem object to the simulation when the mouse is released
         * 
         * @param  e   A MouseEvent object
         */
        public void mouseReleased(MouseEvent e)
        {
            // calculates the change in x and y so as to supply the means to calculate the velocity of the added SpaceSystem object
            double dx = e.getX() - x;
            double dy = e.getY() - y;
            
            // creates and adds the new SpaceSystem object to the simulation
            SpaceSystem newSystem = new SpaceSystem(mass, radius, x, y, dx, dy);
            list.add(newSystem);
            
            // increased the size of the arrays for velocity and center
            xVelList = new Double[list.size()];
            yVelList = new Double[list.size()];
            xCentList = new Double[list.size()];
            yCentList = new Double[list.size()];
            
            // sets the color to be drawn to black so the temporary circle 'disappears' by blending into the black background of the simulation
            trueFalse = false;
        }
        
        /**
         * Returns the initial x value of where the user clicked
         * 
         * @return     returns the initial x value of where the user clicked
         */
        public double getiX()
        {
            return this.x;
        }
        
        /**
         * Returns the initial y value of where the user clicked
         * 
         * @return     returns the initial y value of where the user clicked
         */
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
        // the x and y value of the current location of the mouse as it is being draggined (used for calculating the velocity to be displayed on the control panel)
        private double x;
        private double y;
        
        /**
         * Changes the velocity label on the control panel as the user drags the mouse
         */
        public void mouseDragged(MouseEvent e)
        {
            // sets the x and y instance variables to the x and y coordinates of the user's mouse drag
            this.x = e.getX();
            this.y = e.getY();
            
            // calculates the change in x and y to calculate the velocity to be displayed on the control panel
            double dx = e.getX() - cListener.getiX();
            double dy = e.getY() - cListener.getiY();
            double velocity = Math.sqrt(dx * dx + dy * dy);
            
            // changes the velocity label on the control panel
            controls.setVelocityLabel(velocity);
            
            
            repaint();
        }
        
        /**
         * Returns the final x coordinate of the user's drag
         * 
         * @return     returns the final x coordinate of the user's drag
         */
        public double getfX()
        {
            return this.x;
        }
        
        /**
         * Returns the final y coordinate of the user's drag
         * 
         * @return     returns the final y coordinate of the user's drag
         */
        public double getfY()
        {
            return this.y;
        }
        
        public void mouseMoved(MouseEvent e) {}
    }
}
