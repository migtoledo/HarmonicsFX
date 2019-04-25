
/**
 *
 * @author mtg
 * 
 */

package packHarmonicsFXd5;

import java.awt.*;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;

/**
 * 
 * This class draws the absolute and the relative Axes of the coordinate system.
 */
public class GraphicsAxis 
{
    double w, h;
    Color color1;
    Color color2;
    double xx0, yy0;
    double relativeXX0, relativeYY0;
    
    void draw(Graphics2D g2, boolean drawAxis)
    {
        if(drawAxis==true)
        {
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON);

            // Relative Axes
            
            this.relativeXX0=xx0-GlobalsGridXY.OX*GlobalsGridXY.xscale;
            this.relativeYY0=yy0+GlobalsGridXY.OY*GlobalsGridXY.yscale;
            
            this.color2=GlobalsColors.COLORAXIS2;
            g2.setColor(this.color2);
        
            Line2D xaxis2=new Line2D.Double(0,relativeYY0, w, relativeYY0);   
            g2.draw(xaxis2);
        
            Line2D yaxis2=new Line2D.Double(relativeXX0,0, relativeXX0, h);   
            g2.draw(yaxis2);
        
            g2.setColor(GlobalsColors.COLORFOREGROUND);
            
            // Absolute Axes
            
            g2.setColor(this.color1);
        
            Line2D xaxis=new Line2D.Double(0,yy0, w, yy0);   
            g2.draw(xaxis);
        
            Line2D yaxis=new Line2D.Double(xx0,0, xx0, h);   
            g2.draw(yaxis);
        
            g2.setColor(GlobalsColors.COLORFOREGROUND);
            
            
        
        }
    }
    
    GraphicsAxis()
    {
        
    }
    
    GraphicsAxis(double w, double h, Color color)
    {
        this.w=w;
        this.h=h;
        this.color1=color;
        
        this.xx0=w/2;
        this.yy0=h/2;
    }
    
}
