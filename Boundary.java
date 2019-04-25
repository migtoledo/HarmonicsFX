

/**
 *
 * @author mtg
 * 
 * 
 */

package packHarmonicsFXd5;

import java.awt.*;
import java.awt.geom.*;

/** Class to implement boundary objects */
public class Boundary 
{
    
    double l=8.0;
    double offset=2;
    double x0=offset;
    double y0=offset;
    double v0=200.0;
    double x=x0;
    double y=y0;
    double vx=v0;
    double vy=0.0;
    Color color=GlobalsColors.COLORFOREGROUND;
    
    // Update period in milliseconds
    double dtms=GlobalsTimers.DTF;
    
    // Update period in seconds
    double dt0=dtms/1000.0;
    double dt=dt0*GlobalsTimers.SIG;
    
    // Differential XY movement per-cycle: 
    double dx=vx*dt;
    double dy=vy*dt;
    
    boolean _top=false, _right=false, _bottom=false, _left=false;
    
    protected void setDefaults()
    {
       l=8.0;
       offset=3;
       x0=offset;
       y0=offset;
       v0=200.0; // Pixels / second
       x=x0;
       y=y0;
       vx=v0;
       vy=0.0;
       color=GlobalsColors.COLORFOREGROUND;
       // Update period in milliseconds
       dtms=GlobalsTimers.DTF;
       // Update period in seconds
       dt0=dtms/1000.0;
       dt=dt0*GlobalsTimers.SIG;
       // Differential XY movement per-cycle: 
       dx=vx*dt;
       dy=vy*dt; 
       
       _top=false;
       _right=false;
       _bottom=false;
       _left=false;
    }
    
    void updateXY()
    {
        
        dt=dt0*GlobalsTimers.SIG;
        dx=vx*dt;
        dy=vy*dt;
        x=x+dx;
        y=y+dy;
        
        if(_right) x=GlobalsGridXY.waWidth-l-x0-2;
        if(_bottom) y=GlobalsGridXY.waHeight-l-y0-2;
        if(_left) x=x0;
        if(_top) y=y0;
    }
    
    void right()
    {
        if(x+l+1>GlobalsGridXY.waWidth && dx>0) 
        {
            vy=v0;
            vx=0;
            x=GlobalsGridXY.waWidth-l-x0-2;  
            _right=true;
            _top=false;
            _bottom=false;
            _left=false;
        }
    }
    
    void left()
    {
       if(x<x0 && dx<0)
       {
            vy=-v0;
            vx=0;
            x=x0;
            _bottom=false;
            _left=true;
            _top=false;
            _right=false;
       } 
    }
    
    void bottom()
    {
        if(y+l+1>GlobalsGridXY.waHeight && dy>0) 
        {
            vx=-v0;
            vy=0;
            y=GlobalsGridXY.waHeight-l-y0-2;
            _right=false;
            _bottom=true;
            _left=false;
            _top=false;
        }
    }
    
    void top()
    {
        if(y<y0 && dy<0) 
        {
            vx=v0;
            vy=0;
            y=y0;
            _top=true;
            _left=false;
            _right=false;
            _bottom=false;
        }
    }
    
    void updateVXY()
    { 
        right(); left(); bottom(); top();
    }
    
    void updateColors()
    {
        color=GlobalsColors.COLORFOREGROUND;
    }
    
    void update()
    {
        updateColors();
        updateVXY();
        updateXY();
    }
    
    void draw(Graphics2D g2)
    {
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(color);
        Rectangle2D.Double boundary = new Rectangle2D.Double(x, y, l, l);
        float thickness = (float) 1.1;
        Stroke oldStroke = g2.getStroke();
        g2.setStroke(new BasicStroke(thickness));
        g2.draw(boundary);
        g2.setStroke(oldStroke);
        g2.setColor(GlobalsColors.COLORFOREGROUND);
    }
    
    
    Boundary()
    {
        this.setDefaults();
    }
    
    /***
        This objects moving with velocity vx=100 px/s takes
        13,82 seconds to make a horizontal distance
        of Window-Width=1382 pixels : t(Width)=1382 pixels * 1s/100pixels
        Tested : OK !
    
        *** Basic technology : 
        Java Swing Timers + JPanels + Custom Double-Buffer
    
        *** Conditions : Just under light-weight calculations, single-object.
    
        *** Utility / Functions : This objects serves as basic 
        *   virtual-time calibration tool, because it lets to make
        *   coherent measures between real-time and screen/pixel space,
        *   by testing velocity and actual differential update-period
        *   from timers. 
        *   The observable velocity can be measured against the refresh
        *   video-output frequency, letting to determine maximal, minimal, 
        *   and optimal animation velocity ranges.
        * 
        * Maximal "observable" movement-velocity: 6900 pixels / second,
        * reacting good under user inputs :  start, restart, pause, stop, and 
        * backwards. Tested under Windows 10, Intel Core i3 Dual CPU 1.70 GHz X2.
        * Result : OK
        * 
        * Velocities higher than 7000 pixels / second in screens of ranges 
        * 1400x900
        * are capable but carent of pratical observable facility for normal
        * users.
        * 
        * From my before experience in other systems under C++ / Allegro 4 or
        * 5 Graphics libraries, I must constate that the precision and
        * and accuracy is remarkable better. That is testing for single
        * dynamical objects.
        * 
        * 
    **/
    
}
