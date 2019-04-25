

/**
 *
 * @author mtg
 * 
 */

package packHarmonicsFXd5;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

@SuppressWarnings("serial")
public class MainWindow extends JFrame 
{
    private final MainPanel mainPanel;
    
    Timer auxMainPanelTimer; 
    
    void setBlackColor()
    {
        GlobalsColors.COLORBACKGROUND=Color.BLACK;
        GlobalsColors.COLORFOREGROUND=Color.WHITE;
    }
    
    void setWhiteColor()
    {
        GlobalsColors.COLORBACKGROUND=Color.WHITE;
        GlobalsColors.COLORFOREGROUND=Color.BLACK;
    }
    
    void updateColors()
    {
        if(GlobalsColors.whiteBackground==true)
        {
            this.setWhiteColor();
        }
        else
        {
            this.setBlackColor();
        }
        
        this.mainPanel.workArea.setBackground(GlobalsColors.COLORBACKGROUND);
    }
    
    // Timer Action: Repaint Work Area / Auxiliary
    private void setupMainPanelTimer()
    {
        auxMainPanelTimer = new Timer(GlobalsTimers.AUXDT, (ActionEvent e) -> 
        {
            updateGlobals();
            updateColors();
            GlobalsGridXY.setupScales(mainPanel.workArea);
            GlobalsGridXY.getXYMinMax();
            mainPanel.workArea.updateDoubleBuffer();
            mainPanel.repaint();
        });
        
         this.auxMainPanelTimer.start();
    }
    
    private void updateGlobals()
    {
        GlobalsGridXY.scrw=getWidth();
        GlobalsGridXY.scrh=getHeight();
        GlobalsGridXY.getXYMinMax();  
        GlobalsGridXY.updateXYScreenOrigin();
        GlobalsGridXY.updatewaxy();
        mainPanel.topPanel.updateTextLabelScreenDimensions();
        mainPanel.topPanel.updateTextGraphicDimLabel();
        mainPanel.workArea.updateDoubleBuffer();
        mainPanel.repaint();
    }
    
    private void setupResizeListener()
    {
        getRootPane().addComponentListener(new ComponentAdapter()
        {
            @Override
            public void componentResized(ComponentEvent e)
            { updateGlobals(); }
        });
    }
    
    MainWindow()
    {
        setSize(Toolkit.getDefaultToolkit().getScreenSize());
        
        isDoubleBuffered();
        
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setExtendedState(MAXIMIZED_BOTH);       
        setAlwaysOnTop(rootPaneCheckingEnabled);
        setLocationRelativeTo(null); 
        mainPanel=new MainPanel();
        setContentPane(mainPanel);
        setupResizeListener();
        mainPanel.bottomPanel.buttonBackgroundColor.addActionListener((ActionEvent e) -> 
        {
            GlobalsColors.whiteBackground=!GlobalsColors.whiteBackground;
        });
        setupMainPanelTimer();
        String str="  H A R M O N I C S       FX       v1.0  ";
        this.setTitle(str);
        setVisible(rootPaneCheckingEnabled);
    }
    
     

}
