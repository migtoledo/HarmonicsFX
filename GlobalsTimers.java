

/**
 *
 * @author mtg
 */

package packHarmonicsFXd5;

public class GlobalsTimers 
{
    
    static double UPDATECENTERPANEL=20;
    
    // ANIMATION VARIABLES
    
    static int ticks;
    
    static void updateAnimTime()
    {
        GlobalsTimers.ticks+=GlobalsTimers.SIG;
        GlobalsTimers.getts(GlobalsTimers.ticks);
    }
    
    static void setInitTime()
    {
        GlobalsTimers.ticks=0;
        GlobalsTimers.getts(GlobalsTimers.ticks);       
    }
    
    
    
    // Animation update period in milliseconds
    protected static final int DT=20;
    
    protected static final double DTF=20;
    
    // Auxiliary update timer period in milliseconds
    protected static final int AUXDT=10;
    
    // Time in milliseconds
    static double tms;
    
    static int SIG=1;
    
    // Time in seconds
    static double t;
    
    // Formatted to String
    static String str_t;
    
    // Animation ellapsed time in milliseconds
    static void gettms(int _ticks)
    {
        tms=_ticks*DT;
    }
    
    // Animation ellapsed time in seconds
    static void getts(int _tms)
    {
        gettms(_tms);
        t=tms/1000.0;
        str_t=String.format("%.2f", t);
    }
}
