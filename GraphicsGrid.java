

/**
 *
 * @author mtg
 */

package packHarmonicsFXd5;

import java.awt.*;
import java.awt.geom.Line2D;

public class GraphicsGrid 
{ 
    double w, h;
    Color color;
    double xx0, yy0;
    float _xx0, _yy0;
    
    void drawXLabels(Graphics2D g2)
    {
       
        _xx0=(float) xx0; 
        
        _yy0=(float) yy0;
        
        g2.setColor(GlobalsColors.COLORFOREGROUND); 
        g2.drawString("Hola", _xx0, _yy0);
        
        
        // Drawing the XY Labels : Math Units -> Screen Units
        
                   
            float dx=(float) GlobalsGridXY.xscale;
        
            float n;
        
            float nmax=_xx0/dx;
            
            float offsetY=14;
            
           
        
            for(n=1; n<=nmax; n++)
            {
                
                // EAST X
                
                float x1=_xx0+(n)*dx;
                
                String str_x1=String.format("%.1f", GlobalsGridXY.OX+n);
                
                g2.drawString(str_x1, x1,_yy0+offsetY);
                
                // WEST X
            
                float x2=_xx0-(n)*dx;
            
                String str_x2=String.format("%.1f", (GlobalsGridXY.OX-n));
                
                g2.drawString(str_x2, x2, _yy0+offsetY);
            
            }
            
        
    }
    
    
    
     void drawYLabels(Graphics2D g2)
    {
       
        _xx0=(float) xx0; _yy0=(float) yy0;
        
        g2.setColor(GlobalsColors.COLORFOREGROUND); 
       
         float offsetX=19;
         
        // Drawing the XY Labels : Math Units -> Screen Units
        
            float dy=(float) GlobalsGridXY.yscale;      
            float m;       
            float mmax=_yy0/dy;
            
                // SOUTH
             for(m=1; m<=mmax; m++)
             {
                 
                float y1=_yy0+(m)*dy;
                String str_y1=String.format("%.1f", (GlobalsGridXY.OY-m));
                g2.drawString(str_y1, _xx0-offsetX, y1);
            
               // NORTH
                
                float y2=_yy0-(m)*dy;
                String str_y2=String.format("%.1f", (GlobalsGridXY.OY+m));
                g2.drawString(str_y2, _xx0-offsetX, y2);
                
            }
            
            
        
    }
    
    void draw(Graphics2D g2, boolean drawGrid)
    {
        if(drawGrid==true)
        {
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON);
            
            // Drawing the Grid : Math Units -> Screen Units
        
            g2.setColor(this.color);
        
            double dx=GlobalsGridXY.xscale;
        
            double n;
        
            double nmax=xx0/dx;
        
            for(n=0; n<=nmax; n++)
            {
                double x1=xx0+(n)*dx;
            
                Line2D lineTempRight=new Line2D.Double(x1, 0, x1, h);
                g2.draw(lineTempRight);
            
                double x2=xx0-(n)*dx;
            
                Line2D lineTempLeft=new Line2D.Double(x2, 0, x2, h);
                g2.draw(lineTempLeft);
            
            }
        
        
            double dy=GlobalsGridXY.yscale;
        
            double m;
        
            double mmax=yy0/dy;
        
        
             for(m=0; m<=mmax; m++)
             {
                double y1=yy0+(m)*dy;
            
                 Line2D lineTempSouth=new Line2D.Double(0, y1, w, y1);
                g2.draw(lineTempSouth);
            
                double y2=yy0-(m)*dy;
            
                Line2D lineTempNorth=new Line2D.Double(0, y2, w, y2);
                g2.draw(lineTempNorth);
            
            }
             
            g2.setColor(GlobalsColors.COLORFOREGROUND); 
            
            if(GlobalsGridXY.drawXLabels==true)
            {
                if(GlobalsGridXY.xscale>20) this.drawXLabels(g2);
            }
            
             if(GlobalsGridXY.drawYLabels==true)
            {
                if(GlobalsGridXY.yscale>20) this.drawYLabels(g2);
            }
            
        }
        
        g2.setColor(GlobalsColors.COLORFOREGROUND); 
        
    }
    
    GraphicsGrid()
    {
        
    }
    
    GraphicsGrid(double w, double h, Color color)
    {
        this.w=w;
        this.h=h;
        this.color=color;
        
        this.xx0=w/2;
        this.yy0=h/2;
    }
    
    
    
}
