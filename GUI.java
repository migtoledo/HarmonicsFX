

/**
 *
 * @author mtg
 */

package packHarmonicsFXd5;

import javax.swing.*;

public class GUI 
{
    private static final Runnable DORUNGUI=new RunnableImpl();
    
    protected static MainWindow mainWindow;
  
    GUI()
    {
        SwingUtilities.invokeLater(DORUNGUI);
    }

    private static class RunnableImpl implements Runnable 
    {
        @Override
        public void run(){ mainWindow=new MainWindow(); }
    }
}
