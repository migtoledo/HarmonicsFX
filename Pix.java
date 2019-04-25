
/**
 *
 * @author mtg
 */

package packHarmonicsFXd5;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;

@SuppressWarnings("serial")
public class Pix extends Point2D.Double
{  
    // Radius in pixels
    
    double R=1;
    
    // Center color
    
    Color centerColor=Color.BLUE;
    
    // Boundary color
    
    Color boundaryColor=Color.DARK_GRAY;
    
    Pix()
    {
        super();
    }
    
    Pix( double x, double y)
    {
        super(x,y);       
    }
    
    Pix( double x, double y, double R)
    {
        super(x,y);    
        
        this.R=R;
    }
    
    Pix( double x, double y, double R, Color centerColor)
    {
        super(x,y);    
        
        this.R=R;
        
        this.centerColor=centerColor;
    }
    
    Pix( double x, double y, double R, Color centerColor, Color boundaryColor)
    {
        super(x,y);    
        
        this.R=R;
        
        this.centerColor=centerColor;
        
        this.boundaryColor=boundaryColor;
    }
    
    double xx, yy;
    
    void getxx()
    {
        this.xx=GlobalsGridXY.waxx0+this.x*GlobalsGridXY.xscale;
    }
    
    void getyy()
    {
        this.yy=GlobalsGridXY.wayy0-this.y*GlobalsGridXY.yscale;
    }
    
    double xx(double _x)
    {
        return GlobalsGridXY.waxx0+_x*GlobalsGridXY.xscale;
    }
    
    double yy(double _y)
    {
        return GlobalsGridXY.wayy0-_y*GlobalsGridXY.yscale;
    }
    
    void getxxyy()
    {
        getxx();
        getyy();
    }
    
    void draw(Graphics2D g2)
    {

        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        getxxyy();
        
        Ellipse2D ellipsePix = new Ellipse2D.Double();
        
        double xx1=xx(this.x+this.R/GlobalsGridXY.xscale);
        double yy1=yy(this.y+this.R/GlobalsGridXY.yscale);
        
        ellipsePix.setFrameFromCenter(this.xx, this.yy, xx1, yy1);
        
        g2.setColor(this.centerColor);
        
        g2.fill(ellipsePix);
        
        if(GlobalsColors.COLORBACKGROUND==Color.WHITE) this.boundaryColor=Color.BLACK;
        if(GlobalsColors.COLORBACKGROUND==Color.BLACK) this.boundaryColor=Color.WHITE;
        
        g2.setColor(this.boundaryColor);
        
        g2.draw(ellipsePix);
        
        g2.setColor(GlobalsColors.COLORBACKGROUND);
        
    }
}