

/**
 *
 * @author mtg
 * 
 * 
 */

package packHarmonicsFXd5;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import javax.swing.*;

@SuppressWarnings("serial")
public class CenterPanel extends JPanel implements MouseWheelListener 
{   
    @SuppressWarnings("unused")
	private Timer timerCenterPanelOutputs;
    private void setupTimerCenterPanelOutputs()
    {
        timerCenterPanelOutputs = new Timer((int) GlobalsTimers.UPDATECENTERPANEL,
                (ActionEvent e) ->
        { updateDoubleBuffer(); });   
    }
    
    protected void updateDoubleBuffer()
    {   
        Dimension d = getSize();
        if (GlobalsBuffers.bufferImage == null)
        {
            GlobalsBuffers.bufferImage = (BufferedImage) createImage(d.width, 
                    d.height);
        }  
        // Double-buffer: clear the off-screen image.
        GlobalsBuffers.bufferGraphics = GlobalsBuffers.bufferImage.getGraphics();      
        GlobalsBuffers.bufferGraphics.setColor(GlobalsColors.COLORBACKGROUND);
        GlobalsBuffers.bufferGraphics.fillRect(0, 0, d.width, d.height) ;
        /////////////////////
        //Paint Off-screen //
        ///////////////////// 
        doDrawing(GlobalsBuffers.bufferGraphics);
        repaint();
    }
    
    // Animation elements
    
    private PX p3=new PX(-2,-2);
    FX fx;
    private Boundary armadillo=new Boundary();
    
    @SuppressWarnings("unused") void animationSetDefaults()
    {
        armadillo.setDefaults();
        p3.setLocation(-2, -2);
    }
    
    @SuppressWarnings("unused")
	protected void animationRunning()
    {
        GlobalsTimers.updateAnimTime();
        // Update XY for PX elements
        armadillo.update();
        p3.setLocation(GlobalsTimers.t, GlobalsTimers.t);
        updateDoubleBuffer();
    } 
    
    // --------------------------------------------
    
    private void drawMouseXYLabel(Graphics2D g2, boolean showXY)
    {
        if(GlobalsGridXY.mouseX>getWidth()-100) 
        {
            GlobalsGridXY.offsetMouseCursorX0=-100;
        }
        else
        {
            GlobalsGridXY.offsetMouseCursorX0=20;
        }
        if(GlobalsGridXY.mouseY>getHeight()-40) 
        {
            GlobalsGridXY.offsetMouseCursorY0=-10;
        }
        else
        {
            GlobalsGridXY.offsetMouseCursorY0=20;
        }
        if(GlobalsGridXY.showXY)
        {
            g2.setFont(GlobalsBuffers.stdfont);
            if(GlobalsGridXY.strMouseXY!=null)
            {
                g2.drawString(GlobalsGridXY.strMouseXY, GlobalsGridXY.mouseX+
                        GlobalsGridXY.offsetMouseCursorX0, GlobalsGridXY.mouseY+
                                GlobalsGridXY.offsetMouseCursorY0);
            }   
        }
    }
    
    private void drawGrid(Graphics2D g2)
    {
        GraphicsGrid waGrid=new GraphicsGrid(GlobalsGridXY.waWidth, 
                GlobalsGridXY.waHeight, GlobalsColors.COLORGRID);
        
        waGrid.draw(g2, GlobalsGridXY.drawGrid);
        
        GraphicsAxis waAxis=new GraphicsAxis(GlobalsGridXY.waWidth, 
                GlobalsGridXY.waHeight, GlobalsColors.COLORAXIS1);
         
        waAxis.draw(g2, GlobalsGridXY.drawAxis);
    }
    
    private void setupDrawing(Graphics2D g2)
    { 
        g2.setFont(GlobalsBuffers.stdfont);
        
        g2.setColor(GlobalsColors.COLORBACKGROUND);
        
        g2.fillRect(0, 0, getWidth(), getHeight());
        
        g2.setColor(GlobalsColors.COLORFOREGROUND);
        
        drawGrid(g2);
        
        drawMouseXYLabel(g2, GlobalsGridXY.showXY);
    }
    
    private void doDrawing(Graphics g)
    {
        Graphics2D g2 = (Graphics2D) g;

        //g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
            //RenderingHints.VALUE_ANTIALIAS_ON);
        
        setupDrawing(g2);
        
        // Uncomment to see a "particle" in movement
        //p3.draw(g2);
        
        // Boundary
        armadillo.draw(g2);
        
        // The harmonic functions
        fx.draw(g2);
    }
    
    @Override
    public void paintComponent(Graphics g)
    {
    	Graphics2D g2 = (Graphics2D) g;
        
        if ( GlobalsBuffers.bufferImage == null || 
                GlobalsBuffers.bufferImage.getWidth(this) != getWidth() 
                || GlobalsBuffers.bufferImage.getHeight(this) != getHeight() )
        {   
            GlobalsBuffers.bufferImage = new BufferedImage(getWidth(), 
                    getHeight(), BufferedImage.TYPE_INT_ARGB);
            doDrawing(GlobalsBuffers.bufferImage.getGraphics());
        }
        
        g2.drawImage(GlobalsBuffers.bufferImage, 0, 0, this);
    }
    
    // CONSTRUCTOR
    
    CenterPanel()
    {
        
    	setDoubleBuffered(true);
        GlobalsGridXY.getXYMinMax();
        addMouseWheelListener(this);
        addMouseMotionListener(new MouseAdapter() 
        { 
          @Override
          public void mouseMoved(MouseEvent e) 
          { 
            System.out.println("Moving "+e.getX()+ "  "+e.getY()); 
            GlobalsGridXY.mouseX=e.getX();
            GlobalsGridXY.mouseY=e.getY();
            System.out.println("Globals:  "+GlobalsGridXY.mouseX+ "  "+
                    GlobalsGridXY.mouseY); 
            GlobalsGridXY.updatewaxy();
            GlobalsGridXY.updateStrMouseXY();
            GlobalsGridXY.getXYMinMax();
          } 
        }); 
        addMouseListener(new MouseAdapter() 
        { 
          @Override
          public void mousePressed(MouseEvent e) 
          { 
            GlobalsGridXY.getXYMinMax();
            System.out.println("Moving "+e.getX()+ "  "+e.getY()); 
            GlobalsGridXY.mouseX=e.getX();
            GlobalsGridXY.mouseY=e.getY();
            System.out.println("Globals:  "+GlobalsGridXY.mouseX+ "  "+
                    GlobalsGridXY.mouseY); 
            GlobalsGridXY.showXY=!GlobalsGridXY.showXY;
            GlobalsGridXY.updatewaxy();
            GlobalsGridXY.updateStrMouseXY();
           } 
        });
        setBackground(GlobalsColors.COLORBACKGROUND); 
        GlobalsTimers.setInitTime(); 
        
        fx=new FX();
        
        setBorder(BorderFactory.createLineBorder(Color.GRAY));
        setBorder(BorderFactory.createEtchedBorder(Color.YELLOW, 
                Color.GRAY));
        
        setupTimerCenterPanelOutputs();
        
        //timerCenterPanelOutputs.start();
    }
    
    @Override
    public void mouseWheelMoved(MouseWheelEvent e) 
    {
        int notches = e.getWheelRotation();
        GlobalsGridXY.xscale=GlobalsGridXY.xscale+notches;
        GlobalsGridXY.yscale=GlobalsGridXY.yscale+notches;
    }
     
}
