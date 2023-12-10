package cf2dxf;

import javax.swing.JOptionPane;

/**
  *
  * Description
  * @version 1.01 from 7/30/2016
  * @author TG
  * Keeping it simple with DXF version 12
  */

public class dxf12objects {
  
  // start attributes
  public double xabs = 0;
  public double yabs = 0;
  public int hndl = 1000;
  // 1,1 = Right hand, pointing up Flap
  public int Xaxis = 1; // Direction of X axis +1 or -1
  public int Yaxis = 1; // Direction of Y axis +1 or -1
   
   
  public String dxf = "";
  public String dxfxmax = "10";
  public String dxfymax = "10";
  public String dimasz = "5.5";
  public String dimtxt = "7.1";
  public String dimexe = "2.2";
  public String lunits = "2";
  public String luprec = "4";
  public String dimdec = "9";
  public String dimzin = "8";
  public String dimexo = "0.1";
  public String dimgap = "0.1";
  // end attributes
  
  // start methods
  protected String dxf_header12() {
    // DXF Version 12 Header - Simplified version
    String cr = "\r\n";
    
    dxf +="  0"+ cr;
    dxf +="SECTION"+ cr;
    dxf +="  2"+ cr;
    dxf +="HEADER"+ cr;
    dxf +="  9"+ cr;
    dxf +="$ACADVER"+ cr;
    dxf +="  1"+ cr;
    dxf +="AC1009"+ cr; // DXF Version 12
    dxf +="  9"+ cr;
    dxf +="$EXTMIN"+ cr; // X, Y, and Z drawing extents 10, 20, 30 lower-left corner (in WCS)
    dxf +=" 10"+ cr;
    dxf +="0"+ cr;
    dxf +=" 20"+ cr;
    dxf +="0"+ cr;
    dxf +="  9"+ cr;
    dxf +="$EXTMAX"+ cr; // X, Y, and Z drawing extents 10, 20, 30 upper-right corner (in WCS)
    dxf +=" 10"+ cr;
    dxf += dxfxmax + cr;
    dxf +=" 20"+ cr;
    dxf += dxfymax + cr;
    dxf +="  0"+ cr;
    dxf +="ENDSEC"+ cr;
    dxf +="  0"+ cr;
    dxf +="SECTION"+ cr;
    dxf +="  2"+ cr;
    dxf +="TABLES"+ cr;
    dxf +="  0"+ cr;
    
dxf +="TABLE"+ cr;
dxf +="  2"+ cr;
dxf +="LAYER"+ cr;
dxf +=" 70"+ cr;
dxf +="    14"+ cr;
dxf +="  0"+ cr;
dxf +="LAYER"+ cr;
dxf +="  2"+ cr;
dxf +="0"+ cr;
dxf +=" 70"+ cr;
dxf +="     0"+ cr;
dxf +=" 62"+ cr;
dxf +="     7"+ cr;
dxf +="  6"+ cr;
dxf +="CONTINUOUS"+ cr;
dxf +="  0"+ cr;
dxf +="LAYER"+ cr;
dxf +="  2"+ cr;
dxf +="DIMS"+ cr;
dxf +=" 70"+ cr;
dxf +="     0"+ cr;
dxf +=" 62"+ cr;
dxf +="     7"+ cr;
dxf +="  6"+ cr;
dxf +="CONTINUOUS"+ cr;
dxf +="  0"+ cr;
dxf +="LAYER"+ cr;
dxf +="  2"+ cr;
dxf +="SAFETYEDGE"+ cr;
dxf +=" 70"+ cr;
dxf +="     0"+ cr;
dxf +=" 62"+ cr;
dxf +="    11"+ cr;
dxf +="  6"+ cr;
dxf +="CONTINUOUS"+ cr;
dxf +="  0"+ cr;
dxf +="LAYER"+ cr;
dxf +="  2"+ cr;
dxf +="CUT"+ cr;
dxf +=" 70"+ cr;
dxf +="     0"+ cr;
dxf +=" 62"+ cr;
dxf +="     1"+ cr;
dxf +="  6"+ cr;
dxf +="CONTINUOUS"+ cr;
dxf +="  0"+ cr;
dxf +="LAYER"+ cr;
dxf +="  2"+ cr;
dxf +="CREASE"+ cr;
dxf +=" 70"+ cr;
dxf +="     0"+ cr;
dxf +=" 62"+ cr;
dxf +="     3"+ cr;
dxf +="  6"+ cr;
dxf +="CONTINUOUS"+ cr;
dxf +="  0"+ cr;
dxf +="LAYER"+ cr;
dxf +="  2"+ cr;
dxf +="PERF"+ cr;
dxf +=" 70"+ cr;
dxf +="     0"+ cr;
dxf +=" 62"+ cr;
dxf +="     2"+ cr;
dxf +="  6"+ cr;
dxf +="CONTINUOUS"+ cr;
dxf +="  0"+ cr;
dxf +="LAYER"+ cr;
dxf +="  2"+ cr;
dxf +="SCORE"+ cr;
dxf +=" 70"+ cr;
dxf +="     0"+ cr;
dxf +=" 62"+ cr;
dxf +="     5"+ cr;
dxf +="  6"+ cr;
dxf +="CONTINUOUS"+ cr;
dxf +="  0"+ cr;
dxf +="LAYER"+ cr;
dxf +="  2"+ cr;
dxf +="MATRIX"+ cr;
dxf +=" 70"+ cr;
dxf +="     0"+ cr;
dxf +=" 62"+ cr;
dxf +="   126"+ cr;
dxf +="  6"+ cr;
dxf +="CONTINUOUS"+ cr;
dxf +="  0"+ cr;
dxf +="LAYER"+ cr;
dxf +="  2"+ cr;
dxf +="ZIPPER"+ cr;
dxf +=" 70"+ cr;
dxf +="     0"+ cr;
dxf +=" 62"+ cr;
dxf +="     8"+ cr;
dxf +="  6"+ cr;
dxf +="CONTINUOUS"+ cr;
dxf +="  0"+ cr;
dxf +="LAYER"+ cr;
dxf +="  2"+ cr;
dxf +="CUTCRE"+ cr;
dxf +=" 70"+ cr;
dxf +="     0"+ cr;
dxf +=" 62"+ cr;
dxf +="     6"+ cr;
dxf +="  6"+ cr;
dxf +="CONTINUOUS"+ cr;
dxf +="  0"+ cr;
dxf +="LAYER"+ cr;
dxf +="  2"+ cr;
dxf +="DRAWNOTBURN"+ cr;
dxf +=" 70"+ cr;
dxf +="     0"+ cr;
dxf +=" 62"+ cr;
dxf +="   254"+ cr;
dxf +="  6"+ cr;
dxf +="CONTINUOUS"+ cr;
dxf +="  0"+ cr;
dxf +="LAYER"+ cr;
dxf +="  2"+ cr;
dxf +="BURNNORULE"+ cr;
dxf +=" 70"+ cr;
dxf +="     0"+ cr;
dxf +=" 62"+ cr;
dxf +="    32"+ cr;
dxf +="  6"+ cr;
dxf +="CONTINUOUS"+ cr;
dxf +="  0"+ cr;
dxf +="LAYER"+ cr;
dxf +="  2"+ cr;
dxf +="PUNCH"+ cr;
dxf +=" 70"+ cr;
dxf +="     0"+ cr;
dxf +=" 62"+ cr;
dxf +="   240"+ cr;
dxf +="  6"+ cr;
dxf +="CONTINUOUS"+ cr;
dxf +="  0"+ cr;
dxf +="LAYER"+ cr;
dxf +="  2"+ cr;
dxf +="OTHER"+ cr;
dxf +=" 70"+ cr;
dxf +="     0"+ cr;
dxf +=" 62"+ cr;
dxf +="     7"+ cr;
dxf +="  6"+ cr;
dxf +="CONTINUOUS"+ cr;
dxf +="  0"+ cr;
dxf +="ENDTAB"+ cr;
  
    dxf +="  0"+ cr;
    dxf +="ENDSEC"+ cr;
    dxf +="  0"+ cr;
    dxf +="SECTION"+ cr;
    dxf +="  2"+ cr;
    dxf +="BLOCKS"+ cr;
    dxf +="  0"+ cr;
    dxf +="ENDSEC"+ cr;
    dxf +="  0"+ cr;
    dxf +="SECTION"+ cr;
    dxf +="  2"+ cr;
    dxf +="ENTITIES"+ cr;
    
    return dxf;
  } // dxf_header12
  
  
  protected String dxf_footer12() {
    /* WONT PRINT */
    //  Final part of the DXF File
    //  DXF version 12
    String cr = "\r\n"; // Chr(10).Chr(13);
    
    dxf +="  0" + cr;
    dxf +="ENDSEC" + cr;
    dxf +="  0" + cr;
    dxf +="EOF" + cr;
    
    return dxf;
  }
  
  protected boolean Line(double xval, double yval, String layer) {
    // DXF version 12
    // xval    = Incremental X movement
    // yval    = Incremental Y movement
    // layer   = Layer to use
    // eColor    = Colour to use
    // eLinetype = Line type to use
    //echo xval.'*'.yval.'*'.layer.'*'.eColor.'*'.eLinetype.'<br>';
try {
    String cr = "\r\n";
    String layCol[] = this.getLayer(layer);
    
    dxf +="  0" + cr;
    dxf +="LINE" + cr;
    dxf +="  5" + cr; //  Drawing Datadbase Handle
    dxf +=hndl + cr;
    dxf +="  8" + cr; //  Layer name
    dxf += layCol[0] + cr; // layer + cr;
    
    dxf +=" 6" + cr; // Line Type
    dxf +="CONTINUOUS" + cr;
    dxf +=" 62" + cr; // Line Colour
    dxf += layCol[1] + cr; // getLineCol(layer) + cr;
    
    dxf +=" 10" + cr; //  Start X
    dxf += xabs + cr;
    dxf +=" 20" + cr; //  Start Y
    dxf += yabs + cr;
    dxf +=" 30" + cr; //  Start Z
    dxf +="0.0" + cr;
    dxf +=" 11" + cr; //  End X
    dxf += String.valueOf(xabs + xval * Xaxis) + cr;
    dxf +=" 21" + cr; //  End Y
    dxf += String.valueOf(yabs + yval * Yaxis) + cr;
    dxf +=" 31" + cr; //  End Z
    dxf +="0.0" + cr;
    //  Finish
    xabs = xabs + xval * Xaxis;
    yabs = yabs + yval * Yaxis;
    hndl = hndl + 1;
    
} catch (Exception e) {
    JOptionPane.showMessageDialog(null, e.toString(), "Line", JOptionPane.ERROR_MESSAGE);                        
}   

    return false;
  } // Line
  
  
  protected String[] getLayer(String layer) {
    // Return a Layer Name from the number
    /* 

1 Cut
2 Crease
3 Perforation (2 parameters, cut and gap)
4 Score/half cut.
40 Rillma/Matrix designs.
41 Zipper (3 parameters, length, gap, and angle).
42 Cut/Crease (3 parameters, cut, crease, and land length).
43 Draw but don’t burn into the die.
44 Burn but don’t rule.
45 Safety edge (2 parameters, height, and pitch).
46 Dimensions.
99 Punch lines representing a shape that the punch would be expected to cut.
    */
 
    String rtn[] = {"CUT","1"};

try {       
    switch (layer) {
      case "1" : 
        rtn[0] = "CUT";  // Artios understands Colour 7 as CUT   
        rtn[1] = "1";
        break;
      case "2" : 
        rtn[0] = "CREASE";  
        rtn[1] = "3";
        break;
      case "3" : 
        rtn[0] = "PERF";
        rtn[1] = "2";
        break; 
      case "4" : 
        rtn[0] = "SCORE";
        rtn[1] = "5";
        break;       
      case "40" : 
        rtn[0] = "MATRIX";
        rtn[1] = "126";
        break; 
      case "41" : 
        rtn[0] = "ZIPPER";
        rtn[1] = "8";
        break;  
      case "42" : 
        rtn[0] = "CUTCRE";
        rtn[1] = "6";
        break;  
      case "43" : 
        rtn[0] = "DRAWNOTBURN";
        rtn[1] = "32";
        break;  
      case "44" : 
        rtn[0] = "BURNNORULE";
        rtn[1] = "254";
        break;  
      case "45" : 
        rtn[0] = "SAFETYEDGE";
        rtn[1] = "11";
        break;           
      case "46" : 
        rtn[0] = "DIMS";
        rtn[1] = "7";
        break;          
      case "99" : 
        rtn[0] = "PUNCH";
        rtn[1] = "240";
        break;          
      default: 
        rtn[0] = "OTHER";
        rtn[1] = "7";
    } // end of switch
    
} catch (Exception e) {
    JOptionPane.showMessageDialog(null, e.toString(), "getLayer", JOptionPane.ERROR_MESSAGE);                        
} 

    return rtn;  
  } // getLayer
  
  protected boolean circle(double XcenterAbs, double YcenterAbs, double radius, String layer, int eColor) {
    /*
    * Make a relative move to the center of the circle and call this function
    */
    String cr = "\r\n";
    String layCol[] = this.getLayer(layer);

try {    
    dxf +="  0"+ cr;
    dxf +="CIRCLE"+ cr;
    
    dxf +="  5"+ cr; //  Drawing Datadbase Handle
    dxf += hndl+ cr;
    dxf +="  8"+ cr; //  Layer name
    dxf += layCol[0] + cr; //layer+ cr;
    
    dxf +=" 6" + cr; // Line Type
    dxf +="CONTINUOUS" + cr;
    dxf +=" 62" + cr; // Line Colour
    dxf += layCol[1] + cr; // getLineCol(layer) + cr;
    
    dxf +=" 10"+ cr; //  center X
    dxf += XcenterAbs + cr;
    dxf +=" 20"+ cr; //  center Y
    dxf += YcenterAbs + cr;
    dxf +=" 30"+ cr; //  center Z
    dxf +="0.0"+ cr;
    dxf +=" 40"+ cr; //  Radius
    dxf +=radius+ cr;
    
    //  Finish
    hndl = hndl + 1;
    
} catch (Exception e) {
    JOptionPane.showMessageDialog(null, e.toString(), "circle", JOptionPane.ERROR_MESSAGE);                        
}

    return true;
  } // circle
  
  /**
  * 
  * @param XcenterAbs
  * @param YcenterAbs
  * @param radius
  * @param startAngle (0-90-180-270) Mid(3) not Top Str
  * @param endAngle
  * @param endPosX - Incremental movement to update AbsX
  * @param endPosY - to update AbsY
  * @param layer
  * @return DXF Arc
  */ 
  protected boolean arc2(double XcenterAbs, double YcenterAbs, double radius, double startAngle, double endAngle, double endPosX, double endPosY, String layer) {
    String cr = "\r\n";
    String layCol[] = this.getLayer(layer);
 
try {    
    dxf +="  0" + cr;
    dxf +="ARC" + cr;
    
    dxf +="  5" + cr; //  Drawing Datadbase Handle
    dxf += Integer.toString(hndl) + cr;
    dxf +="  8" + cr; //  Layer name
    dxf += layCol[0] + cr; //layer + cr;
    
    dxf +=" 6" + cr; // Line Type
    dxf +="CONTINUOUS" + cr;
    dxf +=" 62" + cr; // Line Colour
    dxf += layCol[1] + cr; // getLineCol(layer) + cr;
    
    dxf +=" 10" + cr; //  center X
    dxf += XcenterAbs + cr;
    dxf +=" 20" + cr; //  center Y
    dxf += YcenterAbs + cr;
    
    dxf +=" 30" + cr; //  center Z
    dxf +="0.0" + cr;
    
    dxf +=" 40" + cr; //  Radius
    dxf +=radius + cr;
    
    dxf +=" 50" + cr; //  Start Angle of Arc
    dxf +=startAngle + cr;
    dxf +=" 51" + cr; //  End Angle of Arc
    dxf +=endAngle + cr;
    
    //  Finish
    xabs = xabs + endPosX;
    yabs = yabs + endPosY;
    hndl += 1;

} catch (Exception e) {
    JOptionPane.showMessageDialog(null, e.toString(), "arc2", JOptionPane.ERROR_MESSAGE);                        
}

    return false;
  }
  
  
  protected double[] getArcAngles(double IncMoveX, double IncMoveY, double radius, int cw) {
    /* Partial conversion from HPGL2DXF - Largely a Special case protected boolean for 0422 slots
    * Find
    * Xcenter, Ycenter
    * startAngle, endAngle
    */
    // array('Xcenter' => 0, 'Ycenter' => 0, 'startAngle' => 0, 'endAngle' => 0) ** Java doesn't support associative arrays **
    double rtnArray[] = {0, 0, 0, 0};
    
    double rx =  (IncMoveX / 2) * Xaxis ; // absX - Xc
    double ry =  (IncMoveY / 2) * Yaxis; // absY - Yc
    double ang = -1; // set-up
    double aplus = 0; // set-up
    double angle = 0; // not required?
    double wrx = 0;
    double wry = 0;
    double tmp = 0;
    double strang = 0;
    double endang = 0;

try {    
    if (rx == 0) {
      angle = 180; // simple 180 arc
      //  rad = Abs(ry);
      if (ry > 0) {
        ang = 270; //90;
      } else {
        ang = 90; //270;
      }
      //GoTo arnxt
    } else if (ry == 0) {
      angle = 180; // simple 180 arc
      // rad = Abs(rx)
      if (rx > 0) {
        ang = 180; //0;
      } else {
        ang = 0; //180;
      }
      // GoTo arnxt
      //  rad = (Abs(rx) * Abs(rx)) + (Abs(ry) * Abs(ry)) // *** Radius is Known in this instance ***
      //  rad = Sqr(rad)
      
    } else if (rx > 0 && ry > 0) {
      aplus = 0;
      wrx = rx;
      wry = ry;
    } else if (rx < 0 && ry > 0) {
      aplus = 90;
      wrx = ry;
      wry = rx;
    } else if (rx < 0 && ry < 0) {
      aplus = 180;
      wrx = rx;
      wry = ry;
    } else if (rx > 0 && ry < 0) {
      aplus = 270;
      wrx = ry;
      wry = rx;
    }
    
    if (ang == -1) { // not touched by the first 2 conditions
      ang = Math.abs(wry) / Math.abs(wrx);  /* angle if not 0/90/180/270 */
      ang = Math.atan(ang);
      ang = (ang / 3.141592654) * 180;  /* rad2deg */
    }
    //arnxt:
    //  radius = rad
    
    strang = ang + aplus;
    endang = strang + angle;
    if (endang > 360) {
      endang = endang - 360;
    }
    
    /* start_angle = (strang * 3.141592654) /180  deg2rad */
    /* end_angle = (endang * 3.141592654) /180  deg2rad */
    if (Yaxis == -1) { // flipped slot
      tmp = strang;
      strang = endang;
      endang = tmp;
    }
    
    rtnArray[0] = rx; // Xcenter
    rtnArray[1] = ry; // Ycenter
    rtnArray[2] = strang; // startAngle
    rtnArray[3] = endang; // endAngle

} catch (Exception e) {
    JOptionPane.showMessageDialog(null, e.toString(), "getArcAngles", JOptionPane.ERROR_MESSAGE);                        
}

    return rtnArray;
  } // getArcAngles


/**
 * 
 * @param xval
 * @param yval
 * @param textStr
 * @param textSz
 * @param layer
 * @param textAngle 
 */
  protected void TextInsert(double xval, double yval, String textStr, String textSz, String layer, String textAngle) {
    // DXF version 12
    // xval    = Incremental X movement
    // yval    = Incremental Y movement
    // layer   = Layer to use
    // eColor    = Colour to use
    // eLinetype = Line type to use
    String cr = "\r\n";
    String layCol[] = this.getLayer(layer);

try {    
    dxf +="  0" + cr;
    dxf +="TEXT" + cr;
    dxf +="  5" + cr; //  Drawing Datadbase Handle
    dxf += hndl + cr;
    dxf +="  8" + cr; //  Layer name
    dxf += layCol[0] + cr; // layer + cr;
    
    dxf +=" 6" + cr; // Line Type
    dxf +="CONTINUOUS" + cr;
    dxf +=" 62" + cr; // Line Colour
    dxf += layCol[1] + cr;
    
    dxf +=" 10" + cr; //  Start X
    dxf += String.valueOf(xval) + cr;
    dxf +=" 20" + cr; //  Start Y
    dxf += String.valueOf(yval) + cr;
//    dxf +=" 30" + cr; //  Start Z
//    dxf +="0.0" + cr;
    dxf +=" 40" + cr; //  End X
    dxf += String.valueOf(textSz) + cr;
    
    dxf +=" 41" + cr; // Line Type
    dxf +="0" + cr;
    dxf +=" 50" + cr; // Line Angle?
    dxf += textAngle + cr;    
    
    dxf +=" 1" + cr; //  
    dxf += textStr + cr;
    dxf +=" 72" + cr;
    dxf += "0" + cr;    
    //  Finish
//    xabs = xabs + xval * Xaxis; Don
//    yabs = yabs + yval * Yaxis;
    hndl = hndl + 1;

} catch (Exception e) {
    JOptionPane.showMessageDialog(null, e.toString(), "TextInsert", JOptionPane.ERROR_MESSAGE);                        
}

  } // TextInsert  

  
  
  
  
  
  
  // end methods
} // end of dxf12objects
