


package packHarmonicsFXd5;

import static java.lang.Math.*;

/**
 *
 * @author mtg
 * 
 * 
 * Global parameters for functions f=f(x)
 * 
 * This class works together with class FX
 * 
 */
public class GlobalsFX 
{   
    // DX1 in pixels
	// Real computing resolution for x-differential : dx=x(n+1)-x(n)
	// DX is transformed automatically from pixels to math units.
    static double factorDXF1=3;
    static double factorDXF2=3;
    
    // Point radius of the function in pixels
    static double PXR1=0.5;
    static double PXR2=0.5;
    
    // Angular frequency in radians/s : T=2PI/W
    static double W1=1;
    static double W2=1;
    
    
    // Range X Min
    static double F1XMin= -2*PI;
    static double F2XMin= -2*PI;
    
    // Range X Max
    static double F1XMax=-F1XMin;
    static double F2XMax=-F2XMin;
    
    // Vertical translation Y0 for function y(x) -> y0+y(x)
    static double Y01=0;
    static double Y02=0;
    
    // Wave amplitude
    static double A1=1;
    static double A2=1;
    
    // k=2Pi/Lambda -> Lambda=2Pi/k
    
    // Wave number
    static double K1=1;
    static double K2=1;
    
    // Wave length
    static double Lambda1=2*PI/K1;
    static double Lambda2=2*PI/K2;
    
    // Horizontal translation X0 for function y(x) -> y(x-x0)
    static double X01=0;
    static double X02=0;
    
    // Initial angular-phase J (radians/degrees) for function y(x) = y(Kx+J)
    static double J1=0;
    static double J2=0;
    
    /// FORMAL ANALYTIC HARMONIC FUNCTIONS
    
    /// f1(x) = Y01 + A1 Sin ( K1 ( x - X01 ) + J1 )
    /// f2(x) = Y02 + A2 Cos ( K2 ( x - X02 ) + J2 )
    
    // Get the wave number from the wave length
    static double getWaveNumber(double Lambda)
    {
    	double k;
    	k=2*PI/Lambda;
    	return k;
    	
    }
    
    // Get the wave length from the wave number
    static double getWaveLength(double K)
    {
    	double Lambda;
    	Lambda=2*PI/K;
    	return Lambda;
    }
    
    static double getPeriod(double W)
    {
        double T;
        T=2*PI/W;
        return T;
    }
    
    static double getFreq(double T)
    {
        return 1/T;
    }
    
    
    
    // Unified Math unit XY "length" expressed in pixels, 
    // as function of the actual XY scale.
    // Pixels / unit
    
    static double pixelsOf1u()
    {
        double retval;       
        double ex=GlobalsGridXY.xscale;
        double ey=GlobalsGridXY.yscale;
        // Just applying Pitagoras theorem on the XY scales
        double exy=sqrt(pow(ex,2)+pow(ey,2));
        retval=exy;
        return retval;
        
    }
    
    // Unified metric pixel "length" expressed 
    // as function of the actual XY scale in Math units.
    // units / pixel
    
    static double unitsOf1px()
    {
        return (1/pixelsOf1u());
    }
    
    // exy : Pixels / Math unit
    // Example exy=20 pixels / u -> 1 Pixel = (1/20) u =0.05 u
    // If we want to have a "point" per pixel, just make dx=0.05
    // for that scale.
    // Because the "points" are PX objects with radius 1 pixel, we
    // multiply x3 the "ideal" dx.
    
    static double dxF1()
    { 
        double retval;
                        
        double dxdefault=unitsOf1px();
        
        double dx=dxdefault*factorDXF1;
        
        retval=dx; 
                      
        return retval;
    }
    
    static double dxF2()
    { 
        double retval;
                        
        double dxdefault=unitsOf1px();
        
        double dx=dxdefault*factorDXF2;
        
        retval=dx; 
                      
        return retval;
    }
    
}
