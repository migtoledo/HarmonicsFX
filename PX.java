
/**
 *
 * @author mtg
 * 
 */
package packHarmonicsFXd5;

import java.awt.Color;

@SuppressWarnings("serial")
public class PX extends Pix
{  
    PX()
    {
        super();
    }
    
    PX(double x, double y)
    {
        super(x,y);
    }
    
    PX(double x, double y, double r)
    {
        super(x, y, r);
    }
    
    PX( double x, double y, double r, Color centerColor)
    {
        super(x,y,r,centerColor);
    }
    
    
    PX( double x, double y, double r, Color centerColor, Color boundaryColor)
    {
        super(x,y,r,centerColor,boundaryColor);
    }
    
    
}
