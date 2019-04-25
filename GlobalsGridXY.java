

/**
 *
 * @author mtg
 * 
 */

package packHarmonicsFXd5;

public class GlobalsGridXY 
{
    static boolean showXY=true;
    static boolean drawAxis=true;
    static boolean drawGrid=true;
    static boolean drawXYLabels=true;
    static boolean drawXLabels=true;
    static boolean drawYLabels=true;
    
    // SCREEN DIMENSIONS
    static int scrw, scrh;
    
    // WORK AREA
    
    // Relative XY Origin - centered  
    static double OX=0, OY=0;
    static double waWidth, waHeight;
    static double waxx0, wayy0;
    
    // Maxima and Minima for XY coordinates
    static double Xmin, Xmax, Ymin, Ymax;
    
    static int mouseX, mouseY;
    static double waX, waY;
    
    // XY SCALES
    static double xscale=60, yscale=60;
    
    static void setupScales(CenterPanel panel)
    {     
        GlobalsGridXY.waWidth=panel.getWidth(); 
        GlobalsGridXY.waHeight=panel.getHeight();      
        
        GlobalsGridXY.waxx0=panel.getWidth()/2; 
        GlobalsGridXY.wayy0=panel.getHeight()/2;
        
        GlobalsGridXY.getXYMinMax();
        
    }
    
    static void getXmin()
    {
        GlobalsGridXY.Xmin=GlobalsGridXY.OX-GlobalsGridXY.waWidth/(2*GlobalsGridXY.xscale);
        // Update X-Origin for screen
        GlobalsGridXY.waxx0=GlobalsGridXY.waWidth/2-GlobalsGridXY.OX*GlobalsGridXY.xscale;
    }
    
    static void getXmax()
    {
        GlobalsGridXY.Xmax=GlobalsGridXY.OX+GlobalsGridXY.waWidth/(2*GlobalsGridXY.xscale);
        // Update X-Origin for screen
        GlobalsGridXY.waxx0=GlobalsGridXY.waWidth/2-GlobalsGridXY.OX*GlobalsGridXY.xscale;
    }
    
    static void getYmin()
    {
        GlobalsGridXY.Ymin=GlobalsGridXY.OY-GlobalsGridXY.waHeight/(2*GlobalsGridXY.yscale);
        // Update Y-Origin for screen
        GlobalsGridXY.wayy0=GlobalsGridXY.waHeight/2+GlobalsGridXY.OY*GlobalsGridXY.yscale;
    }
    
    static void getYmax()
    {
        GlobalsGridXY.Ymax=GlobalsGridXY.OY+GlobalsGridXY.waHeight/(2*GlobalsGridXY.yscale);
        // Update Y-Origin for screen
        GlobalsGridXY.wayy0=GlobalsGridXY.waHeight/2+GlobalsGridXY.OY*GlobalsGridXY.yscale;
    }
    
    static void getXYMinMax()
    {
        getXmin(); 
        getXmax();
        getYmin(); 
        getYmax();
    }
    
    static void updateXYScreenOrigin()
    {
        // Update X-Origin for screen
        GlobalsGridXY.waxx0=GlobalsGridXY.waWidth/2-GlobalsGridXY.OX*GlobalsGridXY.xscale;
        // Update Y-Origin for screen
        GlobalsGridXY.wayy0=GlobalsGridXY.waHeight/2+GlobalsGridXY.OY*GlobalsGridXY.yscale;
    }
    
    
    // XY Math coordinates of the Mouse
    
    static int offsetMouseCursorX0=20;
    static int offsetMouseCursorY0=20;
    
    static double waMouseX, waMouseY;
    
    static void updatewax()
    {
        //GlobalsGridXY.waX=GlobalsGridXY.OX+(1/GlobalsGridXY.xscale)*(GlobalsGridXY.mouseX-GlobalsGridXY.waxx0);
        GlobalsGridXY.waX=(1/GlobalsGridXY.xscale)*(GlobalsGridXY.mouseX-GlobalsGridXY.waxx0);
    }
    
    static void updateway()
    {
        //GlobalsGridXY.waY=GlobalsGridXY.OY+(1/GlobalsGridXY.yscale)*(-GlobalsGridXY.mouseY+GlobalsGridXY.wayy0);
        GlobalsGridXY.waY=(1/GlobalsGridXY.yscale)*(-GlobalsGridXY.mouseY+GlobalsGridXY.wayy0);
    }
    
    static void updatewaxy()
    {
        updatewax();
        updateway();
    }
    
    
    // From Math Space -> Screen Space
    
    static double getxx(double x)
    {
        double xx=GlobalsGridXY.waxx0+x*GlobalsGridXY.xscale;
        return xx;
    }
    
    static double getyy(double y)
    {
        double yy=GlobalsGridXY.wayy0-y*GlobalsGridXY.yscale;
        return yy;
    }
    
    static String strMouseXY;
    
    static void updateStrMouseXY()
    {
        updatewaxy();
        String strx, stry;
        strx=String.format("%.3f", GlobalsGridXY.waX);
        stry=String.format("%.3f", GlobalsGridXY.waY);
        strMouseXY="(" + strx + " | " + stry + ")";
    }
}
