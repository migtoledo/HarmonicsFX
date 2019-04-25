

/**
 *
 * HARMONICS FX v1.0
 * 
 * Copyright (c) 2019 Miguel Toledo González
 * 
 * License GPL v3
 * 
 * 
 * @author mtg
 * 
 * 
 */

package packHarmonicsFXd5;

import javax.swing.*;

public class Main 
{
    protected static GUI app;
    
    private static final Runnable DORUN=() -> { app = new GUI(); };
    
    public static void main(String[] args) throws Exception
    {
        SwingUtilities.invokeLater(DORUN);
        
        // app = new GUI();
    }
}
