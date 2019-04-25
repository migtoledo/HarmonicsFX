


/**
 *
 * @author mtg
 */

package packHarmonicsFXd5;

import java.awt.*;
import java.awt.event.*;
import java.time.*;
import java.time.format.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

@SuppressWarnings("serial")
public class MainPanel extends JPanel 
{
    TopPanel topPanel=new TopPanel();
    CenterPanel workArea=new CenterPanel();
    BottomPanel bottomPanel = new BottomPanel();
    
    Timer animationTimer;
    
    // Timer Action: workArea.updateVariables
    private void setupWATimer()
    {
        animationTimer = new Timer(GlobalsTimers.DT, (ActionEvent e) -> 
        {
            workArea.animationRunning();
        });
    }
    
    // BUTTON ACTIONS
    
    void startButtonAction()
    {
        // Connecting Button with Work Area Variables
       //workArea.message="Hola de nuevo!";
        
       animationTimer.restart();
       workArea.repaint();
    }
    
    void saveDataXYButtonAction()
    {
        this.workArea.fx.saveDataXY();
        
        String filename;
        String strPattern="yyyy/MM/dd HH:mm:ss";
        strPattern="HH_mm_ss_dd_MM_yyyy";
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern(strPattern);
	LocalDateTime now = LocalDateTime.now();
        filename="DataXY_"+dtf.format(now)+".txt";
        try 
        {
            
            String saveDataXYTextl1="Data XY saved at local program's folder as file:\n";
            String saveDataXYTextl2=filename;
            String saveScreenText=saveDataXYTextl1+saveDataXYTextl2;
            JOptionPane.showMessageDialog(this, saveScreenText, "SAVE DATA XY", 
                JOptionPane.INFORMATION_MESSAGE);
        } 
        catch (Exception ex) 
        {
            Logger.getLogger(MainPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
        
        
    }
    
    void saveScreenButtonAction()
    {
        String filename;
        String strPattern="yyyy/MM/dd HH:mm:ss";
        strPattern="HH_mm_ss_dd_MM_yyyy";
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern(strPattern);
	LocalDateTime now = LocalDateTime.now();
        filename="Screen_"+dtf.format(now)+".png";
        try 
        {
            SnapShot.getSaveSnapShot(this, filename);
            String saveScreenTextl1="Screen saved at local program's folder as file:\n";
            String saveScreenTextl2=filename;
            String saveScreenText=saveScreenTextl1+saveScreenTextl2;
            JOptionPane.showMessageDialog(this, saveScreenText, "SAVE SCREEN", 
                JOptionPane.INFORMATION_MESSAGE);
        } 
        catch (Exception ex) 
        {
            Logger.getLogger(MainPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    void saveGraphicButtonAction()
    {  
        String filename;
        String strPattern="yyyy/MM/dd HH:mm:ss";
        strPattern="HH_mm_ss_dd_MM_yyyy";
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern(strPattern);
	LocalDateTime now = LocalDateTime.now();
        filename="Graphic_"+dtf.format(now)+".png";
        try 
        {
            SnapShot.getSaveSnapShot(this.workArea, filename);
            String saveScreenTextl1="Graphic saved at local program's folder as file:\n";
            String saveScreenTextl2=filename;
            String saveScreenText=saveScreenTextl1+saveScreenTextl2;
            JOptionPane.showMessageDialog(this, saveScreenText, "SAVE GRAPHIC", 
                JOptionPane.INFORMATION_MESSAGE);
        } 
        catch (Exception ex) 
        {
            Logger.getLogger(MainPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    void showAbout()
    {
        String strAbout;
        String n="\n";
        String l1, l2, l3, l4, l5, l6,l7;
        
        l1=          "HARMONICS FX v 1.0";
        l2=l1+n+"-----------------------------";
        l3=l2+n+"Copyright (c) 2019 Miguel Toledo González";
        l4=l3+n+"Java Standard Edition Program";
        l5=l4+n+"Dynamical Visualization of Harmonic Functions";
        l6=l5+n+"License GPL v3 - Free Software Foundation - USA";
         l7=l6+n+"Contact: migtoledo@aol.com";
        strAbout=l7;
        
        JOptionPane.showMessageDialog(this, strAbout, "ABOUT", 
                JOptionPane.INFORMATION_MESSAGE);
    }
    
    private class ShowAboutButtonHandler implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent ae) 
        {     
            showAbout();
            
        }        
    }
    
    void showHelp()
    {
        String strHelp;
        String n="\n";
        String l1, l2, l3, l4, l5, l6, l7, l8, l9, l10, l11;
        
        l1="H E L P";
        l2=l1+n+"This program shows the harmonic waves sin(k1x-w1t) and cos(k2x-w2t)";
        l3=l2+n+"You can change many parameters on the functions and on the coordinate system.";
        l4=l3+n+"Enter any XY scale on the text-fields or use your mouse-wheel.";
        l5=l4+n+"Enter values for the radius of the points R1 and R2.";
        l6=l5+n+"Go forwards or backwards on the time just pressing the corresponding buttons.";
        l7=l6+n+"Make a screen-shot and/or save the data XY at some time t.";
        l8=l7+n+"Well... Just play with it and enjoy.";
        //l9=l8+n+"Line9";
        //l10=l9+n+"Line10";
        //l11=l10+n+"Line11";
        strHelp=l8;
        
        JOptionPane.showMessageDialog(this, strHelp, "HELP", 
                JOptionPane.INFORMATION_MESSAGE);
    }
    
    private class ShowHelpButtonHandler implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent ae) 
        {     
            showHelp();
            
        }        
    }
    
    void backwardsButtonAction()
    {
        GlobalsTimers.SIG=-GlobalsTimers.SIG;
    }
    
    private class backwardsButtonHandler implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent ae) 
        {     
            backwardsButtonAction();
            
        }        
    }
    
    void stopButtonAction()
    {
        animationTimer.stop();
        GlobalsTimers.setInitTime();  
        workArea.animationSetDefaults();
        workArea.repaint();
    }
    
    private class startButtonHandler implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent ae) 
        {     
            if(animationTimer.isRunning()==false)
            {
                startButtonAction();
            }
            else
            {
                stopButtonAction();
                startButtonAction();
            }
            
        }        
    }
    
    private class pauseButtonHandler implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent ae) 
        {     
            if(animationTimer.isRunning())
            {
               animationTimer.stop();
            }
            else
            {
                animationTimer.restart();
            }           
        }        
    }
    
    private class continueButtonHandler implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent ae) 
        {            
            animationTimer.restart();
        }        
    }
    
    private class stopButtonHandler implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent ae) 
        {            
            stopButtonAction();
        }        
    }
    
    private class saveScreenButtonHandler implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent ae) 
        {            
            saveScreenButtonAction();
        }        
    }
    
    private class saveGraphicButtonHandler implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent ae) 
        {            
            saveGraphicButtonAction();
        }        
    }
    
    private class saveDataXYButtonHandler implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent ae) 
        {            
            saveDataXYButtonAction();
        }        
    }
    
    
    
    MainPanel()
    {
        setDoubleBuffered(true);
        setLayout(new BorderLayout());
        
        add(topPanel, BorderLayout.NORTH);
        add(workArea, BorderLayout.CENTER); 
        add(bottomPanel, BorderLayout.SOUTH);
        
        bottomPanel.buttonStart.addActionListener(new startButtonHandler());
        bottomPanel.buttonPause.addActionListener(new pauseButtonHandler());
        bottomPanel.buttonContinue.addActionListener(new continueButtonHandler());
        bottomPanel.buttonStop.addActionListener(new stopButtonHandler());
        bottomPanel.buttonSaveScreen.addActionListener(new saveScreenButtonHandler());
        bottomPanel.buttonSaveGraphic.addActionListener(new saveGraphicButtonHandler());
        bottomPanel.buttonSaveDataXY.addActionListener(new saveDataXYButtonHandler());
        bottomPanel.buttonHelp.addActionListener(new ShowHelpButtonHandler());
        bottomPanel.buttonAbout.addActionListener(new ShowAboutButtonHandler());
        bottomPanel.buttonBackwards.addActionListener(new backwardsButtonHandler());
        
        this.setupWATimer();
        this.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        this.setBorder(BorderFactory.createEtchedBorder(Color.YELLOW, Color.GRAY));
    }
}
