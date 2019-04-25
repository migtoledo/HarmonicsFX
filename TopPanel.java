
/**
 * 
 * Class for implementation of the TOP PANEL
 *
 * @author mtg
 * 
 */

package packHarmonicsFXd5;

import java.awt.*;
import java.awt.event.*;
import java.beans.*;
import static java.lang.Math.PI;
import java.text.*;
import java.util.Date;
import javax.swing.*;
import javax.swing.text.*;

@SuppressWarnings("serial")
public class TopPanel extends JPanel implements PropertyChangeListener
{   
    // Number Formats
    
    private NumberFormat formatInt;
    private NumberFormatter formatterInt;
    
    private void setupFormatInt()
    {
        formatInt=NumberFormat.getNumberInstance();
        formatterInt= new NumberFormatter(formatInt);
        formatterInt.setValueClass(Integer.class);
        formatterInt.setMinimum(1);
        formatterInt.setMaximum(Integer.MAX_VALUE);
        formatterInt.setAllowsInvalid(true);
        //format = NumberFormat.getInstance();
        
        //If you want the value to be committed on each keystroke instead of focus lost
        //formatterInt.setCommitsOnValidEdit(true);
       
    }
    
    private NumberFormat formatDec;
    private NumberFormatter formatterDec;
    
    private void setupFormatDec()
    {
        formatDec=NumberFormat.getNumberInstance();     
        formatterDec = new NumberFormatter(formatDec);
        formatterDec.setValueClass(Double.class);
        //formatterDec.setMinimum(Double.MIN_VALUE);
        formatterDec.setMaximum(Double.MAX_VALUE);
        formatterDec.setAllowsInvalid(true);
        //formatterDec.setCommitsOnValidEdit(true); 
        //If you want the value to be committed on each keystroke instead of focus lost
             
    }
    
    // FONTS AND COLORS
    
    // FONTS
    
    String styleDefaultFont="Serif";
    int sizeDefaultFont=10;
    Font defaultFont=new Font(styleDefaultFont, Font.BOLD, sizeDefaultFont);
    
    // COLORS
    
    Color colorDefault=Color.WHITE;
    Color color1=new Color(250, 250, 255);
    Color color2=new Color(250, 255, 255);
    
    // BORDER COLORS
    Color colorBorderDefault=Color.GRAY;
    
    // BACKGROUND COLOR
    Color backgroundColor=Color.WHITE;
    
    // COLOR OF LINE 1
    Color colorPanelTopNorthLine1=color1;
    
    // COLOR OF LINE 2
    Color colorPanelTopCenterLine2=Color.WHITE;
    
    // COLOR OF panelTopSouth
    Color colorPanelTopSouth=Color.WHITE;
    
    // COLOR OF LINE 3
    Color colorPanelTopSouth1Line3=color1;
    
    // COLOR OF LINE 4
    Color colorPanelTopSouth2Line4=Color.WHITE;
    
    // COLOR OF LINE 5
    Color colorPanelTopSouth3Line5=color1;
    
    // SUB-PANELS
    
    // Line 1
    
    private final JPanel panelTopNorthLine1=new JPanel();
    
    void setupPanelTopNorthLine1()
    {
           
        panelTopNorthLine1.setBackground(colorPanelTopNorthLine1);
        //panelTopNorthLine1.setBorder(BorderFactory.createLineBorder(colorBorderDefault));
        //panelTopNorthLine1.setBorder(BorderFactory.createEtchedBorder(colorBorderDefault, colorBorderDefault)); 
    }
    
    
    // Line 2
    
    private final JPanel panelTopCenterLine2=new JPanel();
    
    void setupPanelTopCenterLine2()
    {
        panelTopCenterLine2.setBackground(colorPanelTopCenterLine2);
        //panelTopCenterLine2.setBorder(BorderFactory.createLineBorder(colorBorderDefault));
        //panelTopCenterLine2.setBorder(BorderFactory.createEtchedBorder(colorBorderDefault, colorBorderDefault));  
    }
    
    // Lines 3,4,5
    private final JPanel panelTopSouth=new JPanel(); 
    // Line 3
    private final JPanel panelTopSouthLine3=new JPanel();
    // Line 4
    private final JPanel panelTopSouthLine4=new JPanel();
    // Line 5
    private final JPanel panelTopSouthLine5=new JPanel();
    
    // BUTTONS
    
    private final JButton buttonRestoreDefaults = new JButton("DEFAULTS");
    
    private final JButton buttonRefresh=new JButton("REFRESH");
    
    // TIMER FOR OUTPUT UPDATE
    
    private Timer timerTopPanelOutputs;
    
    // Timer update period in milliseconds
    private final static int UPDATERATE = GlobalsTimers.DT;
    
    private void setupTimerTopPanelOutputs()
    {
        timerTopPanelOutputs = new Timer(UPDATERATE, (ActionEvent e) -> 
        { updateTopPanelOutputs(); });   
    }
    
    
    // ELEMENTS OF THE SUB-PANELS
    
    
    // Elements of panelTopNorthLine1
    
    
    
    // Screen Dimensions
    
    private String strLabelScreenDimensions;
    private final JLabel labelScreenDimensions=new JLabel(strLabelScreenDimensions);
    
    final void updateTextLabelScreenDimensions()
    {
        labelScreenDimensions.setFont(defaultFont);
        strLabelScreenDimensions=" [ WINDOW : "+Integer.toString(GlobalsGridXY.scrw)+" x "+
                Integer.toString(GlobalsGridXY.scrh)+" ] ";
        labelScreenDimensions.setText(strLabelScreenDimensions);
    }
    
    
    
    // Graphic Dimensions
    
    private String strLabelGraphicsDimensions;
    private final JLabel labelGraphicsDimensions=new JLabel(strLabelGraphicsDimensions);

    final void updateTextGraphicDimLabel()
    {
        labelGraphicsDimensions.setFont(defaultFont);
        String val1=String.format("%.0f",GlobalsGridXY.waWidth);
        String val2=String.format("%.0f",GlobalsGridXY.waHeight);
        strLabelGraphicsDimensions=" [ GRAPHIC : " + val1 + " x " + val2 + " ] ";
        labelGraphicsDimensions.setText(strLabelGraphicsDimensions);
    }
    
    
    // Info about equivalence between unified metric pixels and math units
    
    private String strLabelPix;

    private final JLabel labelPix=new JLabel(strLabelPix);
     
    private void updateTextLabelPix()
    {
        // EQUIVALENCE PIXELS <-> MATH UNITS
        
        labelPix.setFont(defaultFont);
    	
        double val1=GlobalsFX.unitsOf1px();
        String format="%.3f";
        String strval1=String.format(format, val1);
        
        double val2=GlobalsFX.pixelsOf1u();
        String strval2=String.format("%.0f", val2);
        
        strLabelPix=" [ 1 px = "+strval1+" u ]  "
        		+ "[ 1 u = "+strval2+" px ] ";
        
        labelPix.setText(strLabelPix);
    }
    
    
    
    
    
    
    // F1(x)
    
    private final String strLabelF1xForm=" [ F1(x) = Y01 + A1 Sin(  K1( x - X01 ) - W1t + J1  ) ] ";
    private final JLabel labelF1xForm = new JLabel(strLabelF1xForm);
    
    void setupLabelF1xForm()
    {
        labelF1xForm.setFont(defaultFont);
    }
    
    
    // F2(x)
    
    private final String strLabelF2xForm=" [ F2(x) = Y02 + A2 Cos(  K2( x - X02 ) - W2t + J2  ) ] ";
    private final JLabel labelF2xForm = new JLabel(strLabelF2xForm);
    
    void setupLabelF2xForm()
    {
        labelF2xForm.setFont(defaultFont);
    }
    
    
    // Virtual Time 
    
    private String strLabelVirtualTime;
    private final JLabel labelVirtualTime =new JLabel(strLabelVirtualTime);
    
    private void updateTextLabelVirtualTime()
    {
        labelVirtualTime.setFont(defaultFont);
        strLabelVirtualTime=" [ t : "+GlobalsTimers.str_t + " s ] ";
        labelVirtualTime.setText(strLabelVirtualTime);
    }
    
    
    // Clock
    
    private String strLabelClock;
    private final JLabel labelClock = new JLabel(new Date().toString());
    
    void updateTextLabelClock()
    {
        labelClock.setFont(defaultFont);
        strLabelClock=new Date().toString();
        labelClock.setText(strLabelClock);
    }
    
    // Elements of panelTopCenterLine2
    
    // Surface XY - Range
    
    String strLabelXYMinMax=null;
    private final JLabel labelXYMinMax=new JLabel(strLabelXYMinMax);
    
    private void updateTextXYMinMaxLabel()
    {  
        labelXYMinMax.setFont(defaultFont);
        
        String format="%.2f";
        String strXmin, strXmax, strYmin, strYmax;
        strXmin=String.format(format, GlobalsGridXY.Xmin);
        strYmin=String.format(format, GlobalsGridXY.Ymin);
        
        strXmax=String.format(format, GlobalsGridXY.Xmax);
        strYmax=String.format(format, GlobalsGridXY.Ymax);
        
        strLabelXYMinMax="  [ SURF. X MIN : " + strXmin + 
                " u ]   [ SURF. X MAX : " + strXmax + " u ]   [ SURF. Y MIN : " + 
                strYmin + " u ]   [ SURF. Y MAX : " + strYmax + " u ]  ";
        
        labelXYMinMax.setText(strLabelXYMinMax);
        
    }
    
    // Relative Origin (OX, OY) of the coordinate system
    
    private String strLabelOX;
    private final JLabel labelOX=new JLabel(strLabelOX);
    
    private void updateTextOXLabel()
    {
        String format="%.2f";
        labelOX.setFont(defaultFont);
        String val=String.format(format,GlobalsGridXY.OX); 
        strLabelOX=" [ SURF. OX =  " + val + " u ] ";
        labelOX.setText(strLabelOX);
    }
    
    private final JFormattedTextField textFieldOX;
    
   
    
    
    private String strLabelOY;
    private final JLabel labelOY=new JLabel(strLabelOY);
    
    private final JFormattedTextField textFieldOY;
    
    private void updateTextOYLabel()
    {
        String format="%.2f";
        labelOY.setFont(defaultFont);
        String val=String.format(format,GlobalsGridXY.OY); 
        strLabelOY=" [ SURF. OY =  " + val + " u ] ";
        labelOY.setText(strLabelOY);
    }
    
    private void updateTextOYField()
    {
        String format="%.2f";
        String text=String.format(format, GlobalsGridXY.OY);
        textFieldOY.setText(text);
    }
    
    
    // XY Scales
    
    private String strLabelXscale;
    private JLabel labelXscale=new JLabel(strLabelXscale);
    private void updateTextLabelXscale()
    {
        labelXscale.setFont(defaultFont);
        String val=String.format("%.0f",GlobalsGridXY.xscale); 
        strLabelXscale=" [ X-SCALE : " + val + " px / u ] ";
        labelXscale.setText(strLabelXscale);
    }
    
    
    private JFormattedTextField textFieldXScale;
    //private double valueXscale;
    
    
    
    private String strLabelYscale;
    private JLabel labelYScale=new JLabel(strLabelYscale);
    private void updateTextLabelYscale()
    {
        labelYScale.setFont(defaultFont);
        String val=String.format("%.0f",GlobalsGridXY.yscale);
        strLabelYscale=" [ Y-SCALE : " + val + " px / u ] ";
        labelYScale.setText(strLabelYscale);
    }
    
    
    
    private JFormattedTextField textFieldYScale;
    //private double valueYscale;
    
    
    
    
    
    
    
    
    
    
    
    // Elements of panelTopSouth = Line3+Line4+Line5
    
    

    
    
    // Elements of panelTopSouth1Line3
    
    
    
    
    
    // Elements of panelTopSouth2Line4
    
    
    
    
    // Elements of panelTopSouth3Line5
    
    
    
    
    private String strTextDX1Label;

    private final JLabel labelDX1Label=new JLabel(strTextDX1Label);
     
    private void updateTextDX1Label()
    {
        
        labelDX1Label.setFont(defaultFont);
        // EQUIVALENCE PIXELS <-> MATH UNITS
        double val=GlobalsFX.dxF1();
        String strval=String.format("%.4f", val);
        
        double val2=GlobalsFX.dxF1()*GlobalsFX.pixelsOf1u();
        String strval2=String.format("%.0f", val2);

        strTextDX1Label=" [ DX1= "+strval+" u  = "+strval2+" px ] ";
        
        labelDX1Label.setText(strTextDX1Label);
    }
    
    
    
    private String strTextDX2Label;

    private final JLabel labelDX2Label=new JLabel(strTextDX2Label);
     
    private void updateTextDX2Label()
    {
        
        labelDX2Label.setFont(defaultFont);
        // EQUIVALENCE PIXELS <-> MATH UNITS
        double val=GlobalsFX.dxF2();
        String strval=String.format("%.4f", val);
        
        double val2=GlobalsFX.dxF2()*GlobalsFX.pixelsOf1u();
        String strval2=String.format("%.0f", val2);

        strTextDX2Label=" [ DX2= "+strval+" u  = "+strval2+" px ] ";
        
        labelDX2Label.setText(strTextDX2Label);
    }
    
    
    
    
    
    /*
     * NEW LABELS + TEXT FIELDS :
     * 	Y01
     * 	A1
     * 	K1
     * 	X01
     * 	J1
     */
    
    // Y01 Label und Text Field
    
    String strTextY01Label;
    JLabel labelY01=new JLabel(strTextY01Label);
    void updateTextY01Label()
    {
        labelY01.setFont(defaultFont);
        double val=GlobalsFX.Y01;
        String strval=String.format("%.2f", val);
        
        strTextY01Label="[ Y01= "+strval+" u ]";
        
        labelY01.setText(strTextY01Label);
        
    }
    
    String strTextY02Label;
    JLabel labelY02=new JLabel(strTextY02Label);
    void updateTextY02Label()
    {
        labelY02.setFont(defaultFont);
        double val=GlobalsFX.Y02;
        String strval=String.format("%.2f", val);
        
        strTextY02Label="[ Y02= "+strval+" u ]";
        
        labelY02.setText(strTextY02Label);
        
    }
    
    
    
    private final JFormattedTextField textFieldY01;
    
    private final JFormattedTextField textFieldY02;
    
    // A1 Label und Text Field
    
    String strTextA1Label;
    JLabel labelA1=new JLabel(strTextA1Label);
    void updateTextA1Label()
    {
        labelA1.setFont(defaultFont);
        double val=GlobalsFX.A1;
        String strval=String.format("%.2f", val);
        
        strTextA1Label="[ A1= "+strval+" u ]";
        
        //labelY01.setText("Hola");
        
        labelA1.setText(strTextA1Label);
        
    }
    
    String strTextA2Label;
    JLabel labelA2=new JLabel(strTextA2Label);
    void updateTextA2Label()
    {
        labelA2.setFont(defaultFont);
        
        String strval=String.format("%.2f", GlobalsFX.A2);
        
        strTextA2Label="[ A2= "+strval+" u ]";
        
        //labelY01.setText("Hola");
        
        labelA2.setText(strTextA2Label);
        
    }
    
    
    
    private final JFormattedTextField textFieldA1;
    
    private final JFormattedTextField textFieldA2;
    
    
     // K1 Label und Text Field
    
    String strTextK1Label;
    JLabel labelK1=new JLabel(strTextK1Label);
    void updateTextK1Label()
    {
        labelK1.setFont(defaultFont);
        double val=GlobalsFX.K1;
        String strval=String.format("%.2f", val);
        
        strTextK1Label="[ K1= "+strval+" /u ]";
        
        //labelY01.setText("Hola");
        
        labelK1.setText(strTextK1Label);
        
    }
    
    private final JFormattedTextField textFieldK1;
    
    
    
    String strTextK2Label;
    JLabel labelK2=new JLabel(strTextK2Label);
    void updateTextK2Label()
    {
        labelK2.setFont(defaultFont);
        double val=GlobalsFX.K2;
        String strval=String.format("%.2f", val);
        
        strTextK2Label="[ K2= "+strval+" /u ]";
        
        //labelY01.setText("Hola");
        
        labelK2.setText(strTextK2Label);
        
    }
    
    private final JFormattedTextField textFieldK2;
    
    
    
    
    
    
    // W1 Label und Text Field
    
    String strLabelW1;
    JLabel labelW1=new JLabel(strLabelW1);
    void updateTextLabelW1()
    {
        labelW1.setFont(defaultFont);
        double val=GlobalsFX.W1;
        String strval=String.format("%.2f", val);
        
        strLabelW1="[ W1= "+strval+" Hz ]";
        
        //labelY01.setText("Hola");
        
        labelW1.setText(strLabelW1);
        
    }
    
    private final JFormattedTextField textFieldW1;
    
    
    
    String strLabelW2;
    JLabel labelW2=new JLabel(strLabelW2);
    void updateTextLabelW2()
    {
        labelW2.setFont(defaultFont);
        double val=GlobalsFX.W2;
        String strval=String.format("%.2f", val);
        
        strLabelW2="[ W2= "+strval+" Hz ]";
        
        //labelY01.setText("Hola");
        
        labelW2.setText(strLabelW2);
        
    }
    
    private final JFormattedTextField textFieldW2;
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    // X01 Label und Text Field
    
    String strTextX01Label;
    JLabel labelX01=new JLabel(strTextX01Label);
    void updateTextX01Label()
    {
        labelX01.setFont(defaultFont);
        double val=GlobalsFX.X01;
        String strval=String.format("%.2f", val);
        
        strTextX01Label="[ X01= "+strval+" u ]";
        
        //labelY01.setText("Hola");
        
        labelX01.setText(strTextX01Label);
        
    }
    
    private final JFormattedTextField textFieldX02;
   
    
    String strTextX02Label;
    JLabel labelX02=new JLabel(strTextX02Label);
    void updateTextX02Label()
    {
        labelX02.setFont(defaultFont);
        double val=GlobalsFX.X02;
        String strval=String.format("%.2f", val);
        
        strTextX02Label="[ X02= "+strval+" u ]";
        
        //labelY01.setText("Hola");
        
        labelX02.setText(strTextX02Label);
        
    }
    
    private final JFormattedTextField textFieldX01;
    
    
    
    
    
    
    // J1 Label und Text Field
    
    String strTextJ1Label;
    JLabel labelJ1=new JLabel(strTextJ1Label);
    void updateTextJ1Label()
    {
        labelJ1.setFont(defaultFont);
        double val=GlobalsFX.J1;
        String strval=String.format("%.2f", val);
        
        strTextJ1Label="[ J1= "+strval+" ]";
        
        //labelY01.setText("Hola");
        
        labelJ1.setText(strTextJ1Label);
        
    }
    
    private final JFormattedTextField textFieldJ1;
    
    
    
    String strTextJ2Label;
    JLabel labelJ2=new JLabel(strTextJ2Label);
    void updateTextJ2Label()
    {
        labelJ2.setFont(defaultFont);
        double val=GlobalsFX.J2;
        String strval=String.format("%.2f", val);
        
        strTextJ2Label="[ J2= "+strval+" ]";
        
        //labelY01.setText("Hola");
        
        labelJ2.setText(strTextJ2Label);
        
    }
    
    private final JFormattedTextField textFieldJ2;
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
   
    
    // Elements for F1(x)=A1sin(K1x+x01) 
    
    private String strTextF1Label;
    
    private final JLabel labelF1Label=new JLabel("F1(x)");
    
    
    
    private void updateTextF1Label()
    {
        labelF1Label.setFont(defaultFont);
        strTextF1Label="[ F1(x)="+GlobalsFX.Y01+"+"+GlobalsFX.A1+
                "Sin("+GlobalsFX.K1+"(x-"+GlobalsFX.X01+")-"+GlobalsFX.W1+"t +"+GlobalsFX.J1+") ]";
        labelF1Label.setText(strTextF1Label);
    }
    
    private final JLabel labelPX1RadiusLabel=new JLabel("R1= ");
    private String strTextPX1RadiusLabel;
    
    private final JFormattedTextField textFieldPX1Radius;
   
    
    private final JLabel labelPX2RadiusLabel=new JLabel("R2= ");
    private String strTextPX2RadiusLabel;
    
    private final JFormattedTextField textFieldPX2Radius;
    
    
    
    
    private void updateTextPX1RadiusLabel()
    {
        labelPX1RadiusLabel.setFont(defaultFont);
        String strval=String.format("%.1f", GlobalsFX.PXR1);
        strTextPX1RadiusLabel=" [ R1 : " + strval + " px ] ";
        labelPX1RadiusLabel.setText(strTextPX1RadiusLabel);
    }
    
    private void updateTextPX2RadiusLabel()
    {
        labelPX2RadiusLabel.setFont(defaultFont);
        String strval=String.format("%.1f", GlobalsFX.PXR2);
        strTextPX2RadiusLabel=" [ R2= " + strval + " px ] ";
        labelPX2RadiusLabel.setText(strTextPX2RadiusLabel);
    }
    
    
    
    
    
     // Elements for F2(x)=A2cos(K2x+x02) 
    
    private String strTextF2Label="F2(x)=cos(x)";
    private final JLabel labelF2Label=new JLabel(strTextF2Label);
    
    @SuppressWarnings("unused")
	private void updateTextF2Label()
    {
        strTextF2Label="F2(x)=cos(x)";
    }
    
    private final JFormattedTextField textFieldfactorDX1;
    
    
    private final JFormattedTextField textFieldfactorDX2;
    
    
    
    
    
    
    
    
    //private double waX, waY;
    //private String strwaX, strwaY;
    
    private String strLabelXY;
    private final JLabel labelXY=new JLabel(strLabelXY);
    
    
    
    
    
    
    
    
    
    // Adding X-Range for F1(x) and F2(x)
    
    private String strTextXMinF1Label="[ X Min: ]";
    private JLabel labelXMinF1Label = new JLabel(strTextXMinF1Label);
    
    private String strTextXMaxF1Label=" [ X Max: ] ";
    private JLabel labelXMaxF1Label = new JLabel(strTextXMaxF1Label);
        
    
    private void updateTextXMinF1Label()
    {
        labelXMinF1Label.setFont(defaultFont);
        String strVal=String.format("%.4f", GlobalsFX.F1XMin);
        String text="[ X Min = " + strVal + " u ]";
        labelXMinF1Label.setText(text);
    }
     
    
     private String strTextXMinF2Label="[ X Min: ]";
    private JLabel labelXMinF2Label = new JLabel(strTextXMinF2Label);
    
    private String strTextXMaxF2Label=" [ X Max: ] ";
    private JLabel labelXMaxF2Label = new JLabel(strTextXMaxF2Label);
        
    
    private void updateTextXMinF2Label()
    {
        labelXMinF2Label.setFont(defaultFont);
        String strVal=String.format("%.4f", GlobalsFX.F2XMin);
        String text="[ X Min = " + strVal + " u ]";
        labelXMinF2Label.setText(text);
    }
    
    
    
    
    
    
    
    
    
    
    private void updateTextXMaxF1Label()
    {
        labelXMaxF1Label.setFont(defaultFont);
        String strVal=String.format("%.4f", GlobalsFX.F1XMax);
        String text="[ X Max = " + strVal + " u ]";
        labelXMaxF1Label.setText(text);
    }
     
    
    private void updateTextXMaxF2Label()
    {
        labelXMaxF2Label.setFont(defaultFont);
        String strVal=String.format("%.4f", GlobalsFX.F2XMax);
        String text="[ X Max = " + strVal + " u ]";
        labelXMaxF2Label.setText(text);
    }
     
    
    
    
    
     
     // XMinF1 Field
     
    private final JFormattedTextField textFieldXMinF1;
    
    
    private final JFormattedTextField textFieldXMinF2;
     
    // XMaxF1 Field
     
    private final JFormattedTextField textFieldXMaxF1;
    
    
    
     private final JFormattedTextField textFieldXMaxF2;
    
    
    
    
    
    
    private void updateTextXYLabel()
    {
        labelXY.setFont(defaultFont);
        String strwaX=String.format("%.4f", GlobalsGridXY.waX);
        String strwaY=String.format("%.4f", GlobalsGridXY.waY);
        strLabelXY=" [ XY = ( " + strwaX + " u | " + strwaY + " u ) ] " ;
        labelXY.setText(strLabelXY);
    }
    
    
    
   
    
    
    
    
    
    
    
    /*
    private void updateTextFactorDX1Field()
    {
        String text=String.format("%.0f", GlobalsFX.factorDXF1);
        textFieldfactorDX1.setText(text);
    }
    */
    
    
    
    
    
    
    
    private void updateTopPanelOutputs()
    {
        updateTextLabelClock();
        updateTextLabelVirtualTime();
        updateTextLabelXscale();
        updateTextLabelYscale();
        updateTextXYLabel();
        updateTextOXLabel();
        updateTextOYLabel();
        updateTextXYMinMaxLabel();
        updateTextPX1RadiusLabel();
        updateTextPX2RadiusLabel();
        
        
        updateTextDX1Label();
        updateTextDX2Label();
        
        updateTextF1Label();
        updateTextLabelPix();
        updateTextXMinF1Label();
        updateTextXMinF2Label();
        
        
        updateTextXMaxF1Label();
        updateTextXMaxF2Label();
        
        updateTextY01Label();
        updateTextY02Label();
        
        updateTextA1Label();
        updateTextA2Label();
        
        updateTextK1Label();
        updateTextK2Label();
        
        updateTextLabelW1();
        updateTextLabelW2();
        
        updateTextX01Label();
        updateTextX02Label();
        
        updateTextJ1Label();
        updateTextJ2Label();
        
        updateTextLabelF2();
        
        
        
    }
    
    
    
    /** Called when a field's "value" property changes.
     * @param e */
    @Override
    public void propertyChange(PropertyChangeEvent e) 
    {
        Object source = e.getSource();
        
        if (source == textFieldXScale) 
        {
            GlobalsGridXY.xscale = ( (Number) textFieldXScale.getValue() ).intValue();           
            
        } 
        
        if (source == textFieldYScale) 
        { 
            GlobalsGridXY.yscale = ( (Number) textFieldYScale.getValue() ).intValue();           
            
        } 
        
        if (source == textFieldOX ) 
        {
            GlobalsGridXY.OX = ((Number) textFieldOX.getValue()).doubleValue();           
            GlobalsGridXY.updateXYScreenOrigin();   
            //updateTextOXYFields();
        } 
         
        if (source == textFieldOY ) 
        { 
            GlobalsGridXY.OY = ((Number)textFieldOY.getValue()).doubleValue();
            GlobalsGridXY.updateXYScreenOrigin();
        } 
        
        if (source == textFieldPX1Radius ) 
        { 
            GlobalsFX.PXR1 = ((Number)textFieldPX1Radius.getValue()).doubleValue();           
            
            updateTextPX1RadiusField();
        } 
        
        if (source == textFieldPX2Radius ) 
        { 
            GlobalsFX.PXR2 = ((Number)textFieldPX2Radius.getValue()).doubleValue();           
            
            this.updateTextPX2RadiusLabel();
            //updateTextPX2RadiusField();
        } 
        
        
        if (source == textFieldfactorDX1) 
        { 
            GlobalsFX.factorDXF1 = ((Number)textFieldfactorDX1.getValue()).doubleValue();           
            
            this.updateTextDX1Label();
            
            //updateTextFactorDX1Field();
        } 
        
        if (source == textFieldfactorDX2) 
        { 
            GlobalsFX.factorDXF2 = ((Number)textFieldfactorDX2.getValue()).doubleValue();           
            
            this.updateTextDX2Label();
            //updateTextFactorDX1Field();
        } 
        
        if (source == textFieldXMinF1) 
        { 
            GlobalsFX.F1XMin = ((Number)textFieldXMinF1.getValue()).doubleValue();           
            
            updateTextXMinF1Label();
        } 
        
        if (source == textFieldXMinF2) 
        { 
            GlobalsFX.F2XMin = ((Number)textFieldXMinF2.getValue()).doubleValue();           
            
            updateTextXMinF2Label();
        } 
        
        
        
         
        if (source == textFieldXMaxF1) 
        { 
            GlobalsFX.F1XMax = ((Number)textFieldXMaxF1.getValue()).doubleValue();           
            
            updateTextXMaxF1Label();
        } 
        
        if (source == textFieldXMaxF2) 
        { 
            GlobalsFX.F2XMax = ((Number)textFieldXMaxF2.getValue()).doubleValue();           
            
            updateTextXMaxF2Label();
        } 
        
        
        if (source == textFieldY01) 
        { 
            GlobalsFX.Y01 = ((Number)textFieldY01.getValue()).doubleValue();           
            
            updateTextY01Label();
        } 
        
        if (source == textFieldY02) 
        { 
            GlobalsFX.Y02 = ((Number)textFieldY02.getValue()).doubleValue();           
            
            updateTextY02Label();
        } 
        
        
        if (source == textFieldA1) 
        { 
            GlobalsFX.A1 = ((Number)textFieldA1.getValue()).doubleValue();           
            
            updateTextA1Label();
        } 
        
        
        if (source == textFieldA2) 
        { 
            GlobalsFX.A2 = ((Number)textFieldA2.getValue()).doubleValue();           
            
            updateTextA2Label();
        } 
        
        
        
        if (source == textFieldK1) 
        { 
            GlobalsFX.K1 = ((Number)textFieldK1.getValue()).doubleValue();           
            
            updateTextK1Label();
        } 
        
        
        if (source == textFieldK2) 
        { 
            GlobalsFX.K2 = ((Number)textFieldK2.getValue()).doubleValue();           
            
            updateTextK2Label();
        } 
        
        
        
        if (source == textFieldW1) 
        { 
            GlobalsFX.W1 = ((Number)textFieldW1.getValue()).doubleValue();           
            
            updateTextLabelW1();
        } 
        
        if (source == textFieldW2) 
        { 
            GlobalsFX.W2 = ((Number)textFieldW2.getValue()).doubleValue();           
            
            updateTextLabelW2();
        } 
        
        
        
        
        if (source == textFieldX01) 
        { 
            GlobalsFX.X01 = ((Number)textFieldX01.getValue()).doubleValue();           
            
            updateTextX01Label();
        } 
        
        if (source == textFieldX02) 
        { 
            GlobalsFX.X02 = ((Number)textFieldX02.getValue()).doubleValue();           
            
            updateTextX02Label();
        } 
        
        
        if (source == textFieldJ1) 
        { 
            GlobalsFX.J1 = ((Number)textFieldJ1.getValue()).doubleValue();           
            
            updateTextJ1Label();
        } 
        
        
        if (source == textFieldJ2) 
        { 
            GlobalsFX.J2 = ((Number)textFieldJ2.getValue()).doubleValue();           
            
            updateTextJ2Label();
        } 
        
        
        
        
        
        
        
        cleanTextFields();
         
    }
    
    private void updateTextPX1RadiusField()
    {
       String textPX1RadiusField=String.format("%.1f", GlobalsFX.PXR1);
       textFieldPX1Radius.setText(textPX1RadiusField);
    }
    
    void updateTextY01Field()
    {
        String textY01Field=String.format("%.2f", GlobalsFX.Y01);
        this.textFieldY01.setText(textY01Field);
    }
    
    private void actionButtonRestoreDefaults()
    {
        GlobalsGridXY.xscale=60;
        GlobalsGridXY.yscale=60;
        GlobalsGridXY.OX=0;
        GlobalsGridXY.OY=0;
        GlobalsFX.PXR1=0.5;
        GlobalsFX.PXR2=0.5;
        GlobalsFX.factorDXF1=3;
        GlobalsFX.factorDXF2=3;
        GlobalsFX.Y01=0;
        GlobalsFX.A1=1;
         GlobalsFX.Y02=0;
        GlobalsFX.A2=1;
        
        GlobalsFX.W1=1;
        GlobalsFX.W2=1;
        
        GlobalsFX.F1XMax=2*PI;
        GlobalsFX.F2XMax=2*PI;
        
        GlobalsFX.F1XMin=-2*PI;
        GlobalsFX.F2XMin=-2*PI;
        
        
        updateTopPanelOutputs();
        cleanTextFields();
    }
    
    
    void cleanTextFields()
    {
        this.textFieldOX.setText("");
        this.textFieldOY.setText("");
        this.textFieldPX1Radius.setText("");
        this.textFieldPX2Radius.setText("");
        
        this.textFieldXMinF1.setText("");
        this.textFieldXMaxF1.setText("");
        
        this.textFieldXMinF2.setText("");
        this.textFieldXMaxF2.setText("");
        
        
        this.textFieldXScale.setText("");
        this.textFieldYScale.setText("");
        this.textFieldfactorDX1.setText("");
        this.textFieldfactorDX2.setText("");
        
        this.textFieldY01.setText("");
        this.textFieldY02.setText("");
        
        
        this.textFieldA1.setText("");
        this.textFieldA2.setText("");
        
        this.textFieldK1.setText("");
        this.textFieldK2.setText("");
        
        this.textFieldW1.setText("");
        this.textFieldW2.setText("");
        
        this.textFieldX01.setText("");
        this.textFieldX02.setText("");
        
        this.textFieldJ1.setText("");
        this.textFieldJ2.setText("");
        
        
        
        
        
        
    }
    
    void actionButtonRefresh()
    {
        // Just "do nothing"
        updateTopPanelOutputs();
        cleanTextFields();
        
    }
    
    
    void setupTopPanel()
    {
        setBackground(GlobalsColors.COLORTOPPANEL);
        setLayout(new BorderLayout());  
        setupTimerTopPanelOutputs();
        updateTopPanelOutputs();
    }
    
    
    
    
    // Elements for F2(x)=A2cos(K2x+x02) 
    
    private String strLabelF2;
    
    private final JLabel labelF2=new JLabel("F2(x)");
    
    
    
    private void updateTextLabelF2()
    {
        labelF2.setFont(defaultFont);
        strLabelF2="[ F2(x)="+GlobalsFX.Y02+"+"+GlobalsFX.A2+
                "Cos("+GlobalsFX.K2+"(x-"+GlobalsFX.X02+")-"+GlobalsFX.W2+"t +"+GlobalsFX.J2+") ]";
        labelF2.setText(strLabelF2);
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    TopPanel()
    {    
        setupTopPanel();
        
        int colFields2=2;
        int colFields3=3;
        
        setupFormatInt();
        setupFormatDec();
        
        this.setupLabelF1xForm();
        this.setupLabelF2xForm();
        
        this.setupPanelTopNorthLine1();
        this.setupPanelTopCenterLine2();
        
        panelTopSouth.setLayout(new BorderLayout());
        
        panelTopSouthLine3.setLayout(new FlowLayout());
        panelTopSouthLine4.setLayout(new FlowLayout());
        panelTopSouthLine5.setLayout(new FlowLayout());
        
        //Create the text fields and set them up.
        
        textFieldOX = new JFormattedTextField(formatterDec);
        textFieldOX.setFont(defaultFont);
        textFieldOX.setValue(GlobalsGridXY.OX);
        textFieldOX.setColumns(colFields2);
        textFieldOX.addPropertyChangeListener("value", this);
        textFieldOX.setBorder(BorderFactory.createEtchedBorder(Color.BLUE, Color.GRAY));
        
        textFieldOY = new JFormattedTextField(formatterDec);
        textFieldOY.setFont(defaultFont);
        textFieldOY.setValue(GlobalsGridXY.OY);
        textFieldOY.setColumns(colFields2);
        textFieldOY.addPropertyChangeListener("value", this);
        textFieldOY.setBorder(BorderFactory.createEtchedBorder(Color.BLUE, Color.GRAY));
        
        textFieldXScale = new JFormattedTextField(formatterInt);
        textFieldXScale.setFont(defaultFont);
        textFieldXScale.setValue(GlobalsGridXY.xscale);
        textFieldXScale.setColumns(colFields2);
        textFieldXScale.addPropertyChangeListener("value", this);
        textFieldXScale.setBorder(BorderFactory.createEtchedBorder(Color.BLUE, Color.GRAY));
        
        textFieldYScale = new JFormattedTextField(formatterInt);
        textFieldYScale.setFont(defaultFont);
        textFieldYScale.setValue(GlobalsGridXY.yscale);
        textFieldYScale.setColumns(colFields2);
        textFieldYScale.addPropertyChangeListener("value", this);
        textFieldYScale.setBorder(BorderFactory.createEtchedBorder(Color.BLUE, Color.GRAY));
        
        textFieldPX1Radius=new JFormattedTextField(formatterDec);
        textFieldPX1Radius.setFont(defaultFont);
        textFieldPX1Radius.setValue(GlobalsFX.PXR1);
        textFieldPX1Radius.setColumns(colFields2);
        textFieldPX1Radius.addPropertyChangeListener("value", this);
        textFieldPX1Radius.setBorder(BorderFactory.createEtchedBorder(Color.BLUE, Color.GRAY));
        
        textFieldPX2Radius=new JFormattedTextField(formatterDec);
        textFieldPX2Radius.setFont(defaultFont);
        textFieldPX2Radius.setValue(GlobalsFX.PXR2);
        textFieldPX2Radius.setColumns(colFields2);
        textFieldPX2Radius.addPropertyChangeListener("value", this);
        textFieldPX2Radius.setBorder(BorderFactory.createEtchedBorder(Color.BLUE, Color.GRAY));
        
        
        
        textFieldfactorDX1=new JFormattedTextField(formatterInt);
        textFieldfactorDX1.setFont(defaultFont);
        textFieldfactorDX1.setValue(GlobalsFX.factorDXF1);
        textFieldfactorDX1.setColumns(colFields2);
        textFieldfactorDX1.addPropertyChangeListener("value", this);
        textFieldfactorDX1.setBorder(BorderFactory.createEtchedBorder(Color.BLUE, Color.GRAY));
        
        
        
        textFieldfactorDX2=new JFormattedTextField(formatterInt);
        textFieldfactorDX2.setFont(defaultFont);
        textFieldfactorDX2.setValue(GlobalsFX.factorDXF2);
        textFieldfactorDX2.setColumns(colFields2);
        textFieldfactorDX2.addPropertyChangeListener("value", this);
        textFieldfactorDX2.setBorder(BorderFactory.createEtchedBorder(Color.BLUE, Color.GRAY));
        
        
        
        textFieldXMinF1=new JFormattedTextField(formatterDec);
        textFieldXMinF1.setFont(defaultFont);
        textFieldXMinF1.setValue(GlobalsFX.F1XMin);
        textFieldXMinF1.setColumns(colFields2+1);
        textFieldXMinF1.addPropertyChangeListener("value", this);
        textFieldXMinF1.setBorder(BorderFactory.createEtchedBorder(Color.BLUE, Color.GRAY));
        
        textFieldXMinF2=new JFormattedTextField(formatterDec);
        textFieldXMinF2.setFont(defaultFont);
        textFieldXMinF2.setValue(GlobalsFX.F2XMin);
        textFieldXMinF2.setColumns(colFields2+1);
        textFieldXMinF2.addPropertyChangeListener("value", this);
        textFieldXMinF2.setBorder(BorderFactory.createEtchedBorder(Color.BLUE, Color.GRAY));
        
        
        textFieldXMaxF1=new JFormattedTextField(formatterDec);
        textFieldXMaxF1.setFont(defaultFont);
        textFieldXMaxF1.setValue(GlobalsFX.F1XMax);
        textFieldXMaxF1.setColumns(colFields2+1);
        textFieldXMaxF1.addPropertyChangeListener("value", this);
        textFieldXMaxF1.setBorder(BorderFactory.createEtchedBorder(Color.BLUE, Color.GRAY));
        
        
        textFieldXMaxF2=new JFormattedTextField(formatterDec);
        textFieldXMaxF2.setFont(defaultFont);
        textFieldXMaxF2.setValue(GlobalsFX.F2XMax);
        textFieldXMaxF2.setColumns(colFields2+1);
        textFieldXMaxF2.addPropertyChangeListener("value", this);
        textFieldXMaxF2.setBorder(BorderFactory.createEtchedBorder(Color.BLUE, Color.GRAY));
        
        
        textFieldY01=new JFormattedTextField(formatterDec);
        textFieldY01.setFont(defaultFont);
        textFieldY01.setValue(GlobalsFX.Y01);
        textFieldY01.setColumns(colFields2);
        textFieldY01.addPropertyChangeListener("value", this);
        textFieldY01.setBorder(BorderFactory.createEtchedBorder(Color.BLUE, Color.GRAY));
        
        textFieldY02=new JFormattedTextField(formatterDec);
        textFieldY02.setFont(defaultFont);
        textFieldY02.setValue(GlobalsFX.Y02);
        textFieldY02.setColumns(colFields2);
        textFieldY02.addPropertyChangeListener("value", this);
        textFieldY02.setBorder(BorderFactory.createEtchedBorder(Color.BLUE, Color.GRAY));
        
        
        textFieldA1=new JFormattedTextField(formatterDec);
        textFieldA1.setFont(defaultFont);
        textFieldA1.setValue(GlobalsFX.A1);
        textFieldA1.setColumns(colFields2);
        textFieldA1.addPropertyChangeListener("value", this);
        textFieldA1.setBorder(BorderFactory.createEtchedBorder(Color.BLUE, Color.GRAY));
        
        
        textFieldA2=new JFormattedTextField(formatterDec);
        textFieldA2.setFont(defaultFont);
        textFieldA2.setValue(GlobalsFX.A2);
        textFieldA2.setColumns(colFields2);
        textFieldA2.addPropertyChangeListener("value", this);
        textFieldA2.setBorder(BorderFactory.createEtchedBorder(Color.BLUE, Color.GRAY));
        
        
         
        textFieldK1=new JFormattedTextField(formatterDec);
        textFieldK1.setFont(defaultFont);
        textFieldK1.setValue(GlobalsFX.K1);
        textFieldK1.setColumns(colFields2);
        textFieldK1.addPropertyChangeListener("value", this);
        textFieldK1.setBorder(BorderFactory.createEtchedBorder(Color.BLUE, Color.GRAY));
        
        
        textFieldK2=new JFormattedTextField(formatterDec);
        textFieldK2.setFont(defaultFont);
        textFieldK2.setValue(GlobalsFX.K2);
        textFieldK2.setColumns(colFields2);
        textFieldK2.addPropertyChangeListener("value", this);
        textFieldK2.setBorder(BorderFactory.createEtchedBorder(Color.BLUE, Color.GRAY));
        
        
        
        textFieldW1=new JFormattedTextField(formatterDec);
        textFieldW1.setFont(defaultFont);
        textFieldW1.setValue(GlobalsFX.W1);
        textFieldW1.setColumns(colFields2);
        textFieldW1.addPropertyChangeListener("value", this);
        textFieldW1.setBorder(BorderFactory.createEtchedBorder(Color.BLUE, Color.GRAY));
        
        textFieldW2=new JFormattedTextField(formatterDec);
        textFieldW2.setFont(defaultFont);
        textFieldW2.setValue(GlobalsFX.W2);
        textFieldW2.setColumns(colFields2);
        textFieldW2.addPropertyChangeListener("value", this);
        textFieldW2.setBorder(BorderFactory.createEtchedBorder(Color.BLUE, Color.GRAY));
        
         
        textFieldX01=new JFormattedTextField(formatterDec);
        textFieldX01.setFont(defaultFont);
        textFieldX01.setValue(GlobalsFX.X01);
        textFieldX01.setColumns(colFields2);
        textFieldX01.addPropertyChangeListener("value", this);
        textFieldX01.setBorder(BorderFactory.createEtchedBorder(Color.BLUE, Color.GRAY));
        
        
        textFieldX02=new JFormattedTextField(formatterDec);
        textFieldX02.setFont(defaultFont);
        textFieldX02.setValue(GlobalsFX.X02);
        textFieldX02.setColumns(colFields2);
        textFieldX02.addPropertyChangeListener("value", this);
        textFieldX02.setBorder(BorderFactory.createEtchedBorder(Color.BLUE, Color.GRAY));
        
        
        
         
        textFieldJ1=new JFormattedTextField(formatterDec);
        textFieldJ1.setFont(defaultFont);
        textFieldJ1.setValue(GlobalsFX.J1);
        textFieldJ1.setColumns(colFields2);
        textFieldJ1.addPropertyChangeListener("value", this);
        textFieldJ1.setBorder(BorderFactory.createEtchedBorder(Color.BLUE, Color.GRAY));
        
        
        
        textFieldJ2=new JFormattedTextField(formatterDec);
        textFieldJ2.setFont(defaultFont);
        textFieldJ2.setValue(GlobalsFX.J2);
        textFieldJ2.setColumns(colFields2);
        textFieldJ2.addPropertyChangeListener("value", this);
        textFieldJ2.setBorder(BorderFactory.createEtchedBorder(Color.BLUE, Color.GRAY));
        
        
        
        // Adding elements TOP PANEL North : Line 1
        
        panelTopNorthLine1.add(labelScreenDimensions);
        panelTopNorthLine1.add(labelGraphicsDimensions);
        panelTopNorthLine1.add(labelPix);
        panelTopNorthLine1.add(labelXY);
        panelTopNorthLine1.add(labelF1xForm);
        panelTopNorthLine1.add(labelF2xForm);
        panelTopNorthLine1.add(labelVirtualTime);
        panelTopNorthLine1.add(labelClock);
        
        // Adding elements TOP PANEL Center : Line 2
        
        panelTopCenterLine2.add(labelXYMinMax);
        panelTopCenterLine2.add(labelOX);
        panelTopCenterLine2.add(textFieldOX);
        panelTopCenterLine2.add(labelOY);
        panelTopCenterLine2.add(textFieldOY);
        panelTopCenterLine2.add(labelXscale);
        panelTopCenterLine2.add(textFieldXScale);
        panelTopCenterLine2.add(labelYScale);
        panelTopCenterLine2.add(textFieldYScale);
        panelTopCenterLine2.add(buttonRestoreDefaults);
        panelTopCenterLine2.add(buttonRefresh);
        // Adding elements TOP PANEL South = South1 + South2 + South3
        
        // Setting Panel Colors
        
        panelTopSouth.setBackground(this.colorPanelTopSouth);
        panelTopSouthLine3.setBackground(this.colorPanelTopSouth1Line3);
        panelTopSouthLine4.setBackground(this.colorPanelTopSouth2Line4);
        panelTopSouthLine5.setBackground(this.colorPanelTopSouth3Line5);
        
        
        
        // Line 3 : F1(x)
        
        panelTopSouthLine3.add(labelF1Label);
          
        panelTopSouthLine3.add(labelXMinF1Label);
        
        panelTopSouthLine3.add(textFieldXMinF1);
        
        panelTopSouthLine3.add(labelXMaxF1Label);
        
        panelTopSouthLine3.add(textFieldXMaxF1);
        
        
        panelTopSouthLine3.add(this.labelY01);
        
        panelTopSouthLine3.add(this.textFieldY01);
        
        
        panelTopSouthLine3.add(this.labelA1);
        
        panelTopSouthLine3.add(this.textFieldA1);
        
        
         panelTopSouthLine3.add(labelPX1RadiusLabel);
        
        panelTopSouthLine3.add(textFieldPX1Radius);
        
        
        
        panelTopSouthLine3.add(labelDX1Label);
        
        panelTopSouthLine3.add(textFieldfactorDX1);
        
        
         panelTopSouthLine3.add(labelK1);
        
        panelTopSouthLine3.add(textFieldK1);
        
        panelTopSouthLine3.add(labelW1);
        
        panelTopSouthLine3.add(textFieldW1);
        
        
        panelTopSouthLine3.add(labelX01);
        
        panelTopSouthLine3.add(textFieldX01);
        
        
        panelTopSouthLine3.add(labelJ1);
        
        panelTopSouthLine3.add(textFieldJ1);
        
        
        
        
        // Line 4 : F2(x)
        
        
        
       
        panelTopSouthLine4.add(this.labelF2);
        
        panelTopSouthLine4.add(labelXMinF2Label);
        
        panelTopSouthLine4.add(textFieldXMinF2);
        
        panelTopSouthLine4.add(labelXMaxF2Label);
        
        panelTopSouthLine4.add(textFieldXMaxF2);
        
        panelTopSouthLine4.add(this.labelY02);
        
         panelTopSouthLine4.add(this.textFieldY02);
        
        panelTopSouthLine4.add(this.labelA2);
        
        panelTopSouthLine4.add(this.textFieldA2);
        
        panelTopSouthLine4.add(labelPX2RadiusLabel);
        
        panelTopSouthLine4.add(textFieldPX2Radius);
        
        panelTopSouthLine4.add(labelDX2Label);
        
        panelTopSouthLine4.add(textFieldfactorDX2);
        
        
        panelTopSouthLine4.add(labelK2);
        
        panelTopSouthLine4.add(textFieldK2);
        
        
        
        panelTopSouthLine4.add(labelW2);
        
        panelTopSouthLine4.add(textFieldW2);
        
        
        panelTopSouthLine4.add(labelX02);
        
        panelTopSouthLine4.add(textFieldX02);
        
        
        panelTopSouthLine4.add(labelJ2);
        
        panelTopSouthLine4.add(textFieldJ2);
        
        
        
        //panelTopSouthLine5.add(new JLabel("Hola"));
        
        
        
        
        
        
        
        
        // GROUPING SUB-PANELS OF TOP PANEL SOUTH : South1+South2+South3
        
        panelTopSouth.add(panelTopSouthLine3, BorderLayout.NORTH);
        panelTopSouth.add(panelTopSouthLine4, BorderLayout.CENTER);
        panelTopSouth.add(panelTopSouthLine5, BorderLayout.SOUTH);
        
       
        
        buttonRestoreDefaults.addActionListener((ActionEvent e) -> 
        {
            actionButtonRestoreDefaults();
            
        });
        
        buttonRefresh.addActionListener((ActionEvent e) -> 
        {
            actionButtonRefresh();
            
        });
        
        
                
        
        // Adding sub-panels : TOP PANEL = Top Panel North + Center + South
          
        add(panelTopNorthLine1, BorderLayout.NORTH);       
        add(panelTopCenterLine2, BorderLayout.CENTER);
        add(panelTopSouth, BorderLayout.SOUTH);
       
        
       
        timerTopPanelOutputs.start();
    }

    
}


/*
    Stuff
    -----

    panelTopSouthLine3.setBorder(BorderFactory.createEtchedBorder(this.colorBorderDefault, colorBorderDefault)); 
    panelTopSouthLine4.setBorder(BorderFactory.createEtchedBorder(colorBorderDefault, colorBorderDefault)); 
    panelTopSouthLine5.setBorder(BorderFactory.createEtchedBorder(colorBorderDefault, colorBorderDefault)); 
    setBorder(BorderFactory.createLineBorder(colorBorderDefault));
    setBorder(BorderFactory.createEtchedBorder(colorBorderDefault, colorBorderDefault));
    panelTopSouth.setBorder(BorderFactory.createLineBorder(colorBorderDefault));
    panelTopSouth.setBorder(BorderFactory.createEtchedBorder(colorBorderDefault, colorBorderDefault)); 



*/