


/**
 * Class for dynamical functions f=f(x)
 *
 * @author mtg
 * 
 */

package packHarmonicsFXd5;


import static java.lang.Math.*;

import java.awt.*;
import java.awt.geom.*;
import java.util.*;
import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/** Class for the wave functions f1(x,t) and f2(x,t) */
public class FX 
{
    
    private ArrayList<PX> mPXF1;
    private ArrayList<Point2D.Double> mxyF1;
    Color colorCenterF1=Color.BLUE;
    
    private ArrayList<PX> mPXF2;
    private ArrayList<Point2D.Double> mxyF2;
    Color colorCenterF2=Color.GREEN;
    
    void saveDataXY()
    {
        // Saving Data XY
        
        /*
           Range 1 | F1
        
           | x | f1(x) |  
        
           Range 2 | F2
        
           | x | f2(x) |
        
        */
        
        String filename;
        String strPattern="yyyy/MM/dd HH:mm:ss";
        strPattern="HH_mm_ss_dd_MM_yyyy";
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern(strPattern);
	LocalDateTime now = LocalDateTime.now();
        filename="DataXY_"+dtf.format(now)+".txt";
        
        File datafile=new File(filename);
        
        BufferedWriter databuffer;
        
        String strx;
        String stry;
        String strdataxy = null;
        
        String strHeader;
        
        String strf1x;
        
        strf1x="#  F1(x)="+GlobalsFX.Y01+"+"+GlobalsFX.A1+"Sin("+GlobalsFX.K1+
                "("+"x-"+GlobalsFX.X01+")"+"-"+GlobalsFX.W1+"t+"+GlobalsFX.J1+")";
        
        
        strHeader="#  DATA XY for t= "+GlobalsTimers.t+" s \n\n";
        
        strHeader=strHeader+strf1x+"\n\n";
        
        String str1=null;
        String str2=null;
        
        str1=" [  F1(x)="+GlobalsFX.Y01+"+"+GlobalsFX.A1+"Sin("+GlobalsFX.K1+
                "("+"x-"+GlobalsFX.X01+")"+"-"+GlobalsFX.W1+"t+"+GlobalsFX.J1+")  ]  ";
        
        str2=" [  F2(x)="+GlobalsFX.Y02+"+"+GlobalsFX.A2+"Sin("+GlobalsFX.K2+
                "("+"x-"+GlobalsFX.X02+")"+"-"+GlobalsFX.W2+"t+"+GlobalsFX.J2+")  ]  ";
        
        try
        {
           databuffer=new BufferedWriter(new FileWriter(datafile)); 
           
           double x,y;
         databuffer.write(strHeader);
         
        databuffer.write("\n\n#  |  X  |   | " + str1 + "  | \n\n\n");
        
        for(x=GlobalsFX.F1XMin; x<GlobalsFX.F1XMax; x+=GlobalsFX.dxF1())
        {
            y=f1(x);
            
            strx=x+" ";
            stry=y+" ";
            
            strdataxy=strx+" "+stry+"\n";
            
            strdataxy=strdataxy+"\n";
            
            databuffer.write(strdataxy);
        }
           
         databuffer.write("\n\n\n#  |  X  |   | " + str2 + "  | \n\n\n");
        
           for(x=GlobalsFX.F2XMin; x<GlobalsFX.F2XMax; x+=GlobalsFX.dxF2())
        {
            y=f2(x);
            
            strx=x+" ";
            stry=y+" ";
            
            strdataxy=strx+" "+stry+"\n";
            
            strdataxy=strdataxy+"\n";
            
            databuffer.write(strdataxy);
        }
           
           databuffer.write(strdataxy);
           databuffer.close();
        }
        catch(IOException e)
        {
            Throwable cause = e.getCause();
            cause.getMessage();
        }
        
        
    }
    
    private double f1(double x)
    {
        double y;
        
        y=GlobalsFX.Y01+GlobalsFX.A1*
        		sin(GlobalsFX.K1*(x-GlobalsFX.X01)-GlobalsFX.W1*GlobalsTimers.t+GlobalsFX.J1);
        
        return y;
    }
    
    
    private double f2(double x)
    {
        double y;
        
        y=GlobalsFX.Y02+GlobalsFX.A2*
        		cos(GlobalsFX.K2*(x-GlobalsFX.X02)-GlobalsFX.W2*GlobalsTimers.t+GlobalsFX.J2);
        
        return y;
    }
    
    
    private void getmxyF1()
    {
        double x,y;
        
        mxyF1=new ArrayList<>(1);
        mPXF1=new ArrayList<>(1);
        
        for(x=GlobalsFX.F1XMin; x<GlobalsFX.F1XMax; x+=GlobalsFX.dxF1())
        {
            y=f1(x);
            Point2D.Double tmp=new Point2D.Double(x, y);
            mxyF1.add(tmp);
            PX _tmp=new PX(x, y, GlobalsFX.PXR1, this.colorCenterF1);
            mPXF1.add(_tmp);
        }
    }
    
    private void getmxyF2()
    {
        double x,y;
        
        mxyF2=new ArrayList<>(1);
        mPXF2=new ArrayList<>(1);
        
        for(x=GlobalsFX.F2XMin; x<GlobalsFX.F2XMax; x+=GlobalsFX.dxF2())
        {
            y=f2(x);
            Point2D.Double tmp=new Point2D.Double(x, y);
            mxyF2.add(tmp);
            PX _tmp=new PX(x, y, GlobalsFX.PXR2, this.colorCenterF2);
            mPXF2.add(_tmp);
        }
    }
    
    
    
    void draw(Graphics2D g2)
    {
        /*
        Make the animation very slowly when antialiasing is on.

        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

         */

        //g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
          //      RenderingHints.VALUE_ANTIALIAS_ON);

        getmxyF1();
        getmxyF2();
        
        int i;
        for(i=0; i<mPXF1.size(); i++)
        {
            PX tmp=mPXF1.get(i);
            tmp.draw(g2);
        }
        
        for(i=0; i<mPXF2.size(); i++)
        {
            PX tmp=mPXF2.get(i);
            tmp.draw(g2);
        }
        
    }
    
    void compute()
    {
        getmxyF1();
        getmxyF2();
    }
    
    
    FX()
    {
        getmxyF1();
        getmxyF2();
    }
    
    
}
