
/**
 *
 * @author mtg
 */

package packHarmonicsFXd5;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

@SuppressWarnings("serial")
public class BottomPanel extends JPanel 
{
    JOptionPane showHelpDialog=new JOptionPane();
    
    JOptionPane showAboutDialog=new JOptionPane();
    
    JPanel panelBottomNorth=new JPanel();
    
    JPanel panelBottomCenter=new JPanel();
    
    JPanel panelBottomSouth=new JPanel();
    
    JButton buttonStart=new JButton("START");
    
    JButton buttonPause=new JButton("PAUSE");
    
    JButton buttonContinue=new JButton("CONTINUE");
    
    JButton buttonStop=new JButton("STOP");
    
    JButton buttonShowAxis=new JButton("SHOW / HIDE AXIS");
    
    JButton buttonShowGrid=new JButton("SHOW / HIDE GRID");
    
    JButton buttonShowXLabels=new JButton("SHOW / HIDE X LABELS");
    
    JButton buttonShowYLabels=new JButton("SHOW / HIDE Y LABELS");
    
    JButton buttonShowMouseXY=new JButton("SHOW / HIDE MOUSE XY");
    
    JButton buttonBackgroundColor=new JButton("BLACK / WHITE");
     
    JButton buttonSaveScreen=new JButton("SAVE SCREEN");
    
    JButton buttonSaveGraphic=new JButton("SAVE GRAPHIC");
     
    JButton buttonBackwards=new JButton("BACKWARDS (-)");
    
    JButton buttonHelp=new JButton("HELP");
    
    JButton buttonAbout=new JButton("ABOUT");
    
    JButton buttonQuit=new JButton("QUIT");
    
    JButton buttonSaveDataXY=new JButton("SAVE DATA XY");
    
    void exitprogram()
    {
        System.exit(0);
    }
    
    BottomPanel()
    {
        setBackground(GlobalsColors.COLORBOTTOMPANEL);
        
        setLayout(new BorderLayout());
        
        // NORTH SUBPANEL
        
        panelBottomNorth.setLayout(new GridLayout(1, 8, 2, 2));
        
        panelBottomNorth.add(buttonStart);
        
        panelBottomNorth.add(buttonPause);
       
        panelBottomNorth.add(buttonContinue);
       
        panelBottomNorth.add(buttonBackwards);
        
        panelBottomNorth.add(buttonStop);
        
        panelBottomNorth.add(buttonSaveScreen);
        
        panelBottomNorth.add(buttonSaveGraphic);
        
        panelBottomNorth.add(buttonHelp);
        
        panelBottomNorth.add(buttonAbout);
        
        panelBottomNorth.add(buttonQuit);
        
        panelBottomNorth.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        panelBottomNorth.setBorder(BorderFactory.createEtchedBorder(Color.BLUE, Color.GRAY));
        
        // CENTER SUBPANEL
        
        panelBottomCenter.setLayout(new GridLayout(1, 8, 2,2));
        
        panelBottomCenter.add(buttonBackgroundColor);
        
        panelBottomCenter.add(buttonShowMouseXY);
        
        panelBottomCenter.add(buttonShowAxis);
        
        panelBottomCenter.add(buttonShowGrid);
        
        panelBottomCenter.add(buttonShowXLabels);
        
        panelBottomCenter.add(buttonShowYLabels);
        
        panelBottomCenter.add(buttonSaveDataXY);
        
        panelBottomCenter.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        panelBottomCenter.setBorder(BorderFactory.createEtchedBorder(Color.BLUE, 
                Color.GRAY));
        
        // SOUTH SUBPANEL
        
        panelBottomSouth.setLayout(new GridLayout(1, 10,40,10));
        
        // Add Buttons subpanel South
        
        panelBottomSouth.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        panelBottomSouth.setBorder(BorderFactory.createEtchedBorder(Color.BLUE, 
                Color.GRAY));
        
        this.add(this.panelBottomNorth, BorderLayout.NORTH);
        
        this.add(this.panelBottomSouth, BorderLayout.SOUTH);
        
        this.add(this.panelBottomCenter, BorderLayout.CENTER);
        
        // BUTTON LISTENERS
        
        buttonQuit.addActionListener((ActionEvent e) -> 
        {
            exitprogram();
        });
        
        
        buttonShowAxis.addActionListener((ActionEvent e) -> 
        {
            GlobalsGridXY.drawAxis=!GlobalsGridXY.drawAxis;
        });
         
         
        buttonShowGrid.addActionListener((ActionEvent e) -> 
        {
            GlobalsGridXY.drawGrid=!GlobalsGridXY.drawGrid;
        });
         
        
        buttonShowXLabels.addActionListener((ActionEvent e) -> 
        {
            GlobalsGridXY.drawXLabels=!GlobalsGridXY.drawXLabels;
            
            if(GlobalsGridXY.xscale<20  ) { GlobalsGridXY.drawXLabels=false; }
            
        });
         
        buttonShowYLabels.addActionListener((ActionEvent e) -> 
        {
            GlobalsGridXY.drawYLabels=!GlobalsGridXY.drawYLabels;
            
            if(GlobalsGridXY.yscale<20  ) { GlobalsGridXY.drawYLabels=false; }
            
        });
         
         
        buttonShowMouseXY.addActionListener((ActionEvent e) -> 
        {
            GlobalsGridXY.showXY=!GlobalsGridXY.showXY;
            
        });
          
          
        
        
        
        setBorder(BorderFactory.createLineBorder(Color.GRAY));
        setBorder(BorderFactory.createEtchedBorder(Color.BLUE, Color.GRAY));
        
        
    }
    
}
