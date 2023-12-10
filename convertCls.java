package cf2dxf;

import java.lang.*;
import java.io.*;
import java.util.Arrays;

import javax.swing.JOptionPane;

public class convertCls extends dxf12objects{

	public String cf2File;
	public String dxfFile;
	public int layer;
	public double ang;
        public double radius;
        public boolean batch = false;
// LINE TYPES
//0 OTHER ???        
//1 Cut
//2 Crease
//3 Perforation (2 parameters, cut and gap) < Never seen them used
//4 Score/half cut.
//40 Rillma/Matrix designs.
//41 Zipper (3 parameters, length, gap, and angle).
//42 Cut/Crease (3 parameters, cut, crease, and land length) < Never seen them used or an Interpreter than can read them
//43 Draw but don’t burn into the die.
//44 Burn but don’t rule.
//45 Safety edge (2 parameters, height, and pitch) < As above
//46 Dimensions.
//99 Punch lines representing a shape that the punch would be expected to cut.
        
	public void convertDxf() {
        // ***** TG - 28/10/97 ********  THIS VERSION IN JAVA 6/12/2016... mean while 20 years on! :-)
        // Completed 21/03/2019 TG - currently doesn't separate out and name cut/crease & perf
	// More tweaks & directory conversion added 2023
        // I still say CF2 is a Poltter file format, it has more in common with HPGL than DXF/DWG
        
                double tmpAng;
		int dirc;
                String txt = "";

		int  s1,s2,index, auxLT;
		double  sang=0,eang=0;
		String cmd="";
		String[] thevals = new String[15];
		double v1=0,v2=0,v3=0,v4=0,v5=0,v6=0;
		
                dxf = ""; // reset DXF string
		dxf += dxf_header12();
				
		try (BufferedReader br = new BufferedReader(new FileReader(cf2File))) {
		    String line;
		    while ((line = br.readLine()) != null) {

		    if (line.length() > 3) {
                        cmd = line.substring(0, 2);
                        if (line.contains(cmd)) {
                            thevals = line.split(",", -1);
                        }
                    }
                    
//System.out.println(cmd + "*" + line);
// L, pointage, LineType, AuxLineType, sx, sy, ex, ey, NoBridges, WidthBridge
		      if (cmd.equals("L,")) { // LINE
		            v1=Double.parseDouble(thevals[4]); // start X
		            v2=Double.parseDouble(thevals[5]); // start Y
		            v3=Double.parseDouble(thevals[6]) - v1; // End X
		            v4=Double.parseDouble(thevals[7]) - v2; // End Y
		            layer = Integer.parseInt(thevals[2]);  // Layer / pen
		            auxLT=Integer.parseInt(thevals[3]); // Auxillery line type

		        	this.xabs = v1;
		        	this.yabs = v2;
		            Line(v3, v4, Integer.toString(this.layer));
		      }// if cmd='L,'
		      
		      if (cmd.equals("A,")) { // ARC
                            //0 =                                                           - is the pointage in points (1/72 inches).
		            v1=Double.parseDouble(thevals[4]); // start X                   - the start coordinate of the arc. X
		            v2=Double.parseDouble(thevals[5]); // start Y                   - the start coordinate of the arc. Y
		            v3=Double.parseDouble(thevals[6]); // End X                     - the end coordinate of the arc X
		            v4=Double.parseDouble(thevals[7]); // End Y                     - the end coordinate of the arc Y
		            v5=Double.parseDouble(thevals[8]); // Centre X
		            v6=Double.parseDouble(thevals[9]); // Centre Y
                                if (thevals[10].equals("1.0000")) { // Quick and DIRTY Fix do better later MUST be INT
                                    thevals[10] = "1";
                                }
		            dirc =  Integer.parseInt(thevals[10]); // Direction of rotation
		            layer = Integer.parseInt(thevals[2]);  // Layer / pen           - is the common file linetype
		            auxLT = Integer.parseInt(thevals[3]); // Auxillery line type    - the common file auxiliary linetype

		            if ((v1==v3) && (v2==v4)) { // Circle
		                // HPGLcircle(v1, v2, v5, v6, dirc);
                                 find_Ang_Rad(v1,v2,v5,v6,dirc);
                                circle(v5, v6, radius, Integer.toString(layer), layer);
		            }
		            else {// Arc
		            	sang = find_Ang_Rad(v1,v2,v5,v6,dirc);
		                eang = find_Ang_Rad(v3,v4,v5,v6,dirc);
		                ang = sang - eang;

		                if (dirc == -1) { // eang > sang && 
                                //    ang = 360  - ang;
                                    tmpAng = sang;
                                    sang = eang;
                                    eang = tmpAng;
                                }
                                arc2(v5, v6, radius, sang, eang, 0, 0, String.valueOf(layer));
		        } // (v1=v3) and (v2=v4)
		    } // if cmd='A,'


			  if (cmd.equals("T,")) {
                              //0,1,2,3,4          ,5          ,6        ,7        ,8
                              //T,0,8,0,116.1061660,405.4294330,0.0000000,8.0000000,5.9946713
		            v1=Double.parseDouble(thevals[4]); // start X                   - the bottom left position of the text design X
		            v2=Double.parseDouble(thevals[5]); // start Y                   - the bottom left position of the text design Y
		            v3=Double.parseDouble(thevals[6]); // Text angle                - the rotation about x, y of the text design.
		            v4=Double.parseDouble(thevals[7]); // Size of Text              - the height of the characters in mm.
		            v5=Double.parseDouble(thevals[8]); // No idea???                - the width of the characters including the inter-character gap in mm.
		            layer = Integer.parseInt(thevals[2]);  // Layer / pen           - the common file linetype
		            auxLT = Integer.parseInt(thevals[3]); // Auxillery line type    - the common file auxiliary linetype
                            
                            txt = br.readLine();
//System.out.println(cmd + "*" + line + ">>>>> ANGLE=" + v3 + "**" + String.valueOf(v3));
                            TextInsert(v1, v2, txt, String.valueOf(v4), String.valueOf(layer), String.valueOf(v3));
			  }
                          
                    cmd = "";      
					  
		  }  // while

		} catch (IOException e) {
                    // e.printStackTrace(); // auto close of file
                    JOptionPane.showMessageDialog(null, Arrays.toString(e.getStackTrace()), "convertDxf.MainLoop", JOptionPane.ERROR_MESSAGE);
                } catch (Exception e) {  
                    JOptionPane.showMessageDialog(null, e.toString(), "convertDxf.MainLoop", JOptionPane.ERROR_MESSAGE);                    
		}
		
	    // dxf += dxf_footer12(); WONT PRINT!
	    dxf +="  0\r\n";
	    dxf +="ENDSEC\r\n";
	    dxf +="  0\r\n";
	    dxf +="EOF\r\n";
	    
		
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(dxfFile))) {
			// String content = "This is the content to write into file\n";
			bw.write(dxf);
			//bw.close(); // no need to close it.
		} catch (IOException e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(null, Arrays.toString(e.getStackTrace()), "convertDxf.BufferedWriter", JOptionPane.ERROR_MESSAGE);                        
		}
		
                if (batch = false) {
                    String msg = "CONVERTED:\n" + cf2File + "\n\nConverted to DXF\n";
                    msg += "This program performs a standard Layer conversion from the CF2 spec.\n";
                    msg += "It doesn't follow that whoever created your file did the same.\n\n";
                    msg += "Note - Rarer commands such as Scale\nare not dealt with in this program.\n";
                    msg += "Cut/Crease & Perf are put onto generic layers.";
                    JOptionPane.showMessageDialog(null, msg, "CF2 to DXF by Tim Gathercole 1997-2019      timgathercole@zoho.com", JOptionPane.INFORMATION_MESSAGE ); //  28/10/1997 - 21/03/2019
                }
		
	}

	

protected double find_Ang_Rad(double absX, double absY, double xc, double yc, int dirc) { //Var absX,absY,xc,Yc,ang : Single; Var dirc : Integer);
/*  absX    - start X
    absY    - start Y
    xc      - Centre X
    yc      - Centre Y
    dirc    - Direction of rotation
    */
// rtnAng - Var used to RETURN Angle of movement
// radius - Global used to return RADIUS
    double rtnAng = 0;
  double rx=0,ry=0;
  double rad=0; 
  double aplus=0, wrx=0, wry=0;

try {    
  
rx = absX - xc;
ry = absY - yc;
if (rx == 0) {
    rad = Math.abs(ry);
    if (ry > 0) {
    	rtnAng = 90;
    } else {
    	rtnAng = 270;
    }
    radius = rad;
    return rtnAng;
} // (rx = 0)

if (ry == 0) {
    rad = Math.abs(rx);
    if (rx > 0) {
    	rtnAng = 0;
    } else  {
    	rtnAng = 180;
    }
    radius = rad;
    return rtnAng;
} // (ry = 0)

rad = (Math.abs(rx) * Math.abs(rx)) + (Math.abs(ry) * Math.abs(ry));
rad = Math.sqrt(rad);
radius = rad;

    if ((rx > 0) && (ry > 0)) {
        aplus = 0; 
    	wrx = rx; 
    	wry = ry;
    } // (rx > 0 And ry > 0)
    if ((rx < 0) && (ry > 0)) {
        aplus = 90; 
        wrx = ry; 
        wry = rx;
    } // (rx < 0 And ry > 0)
    if ((rx < 0) && (ry < 0)) { 
        aplus = 180; 
        wrx = rx; 
        wry = ry;
    } // (rx < 0 And ry < 0)
    if ((rx > 0) && (ry < 0)) {
        aplus = 270; 
        wrx = ry; 
        wry = rx;
    } // (rx > 0 And ry < 0)

   rtnAng = Math.abs(wry) / Math.abs(wrx); // angle if not 0/90/180/270
   rtnAng = Math.atan(rtnAng);
   rtnAng = (rtnAng / 3.141592654) * 180;  // rad2deg
   rtnAng = rtnAng+aplus;
   if (rtnAng > 360) { rtnAng = rtnAng-360;}

} catch (Exception e) {
    JOptionPane.showMessageDialog(null, e.toString(), "find_Ang_Rad", JOptionPane.ERROR_MESSAGE);                        
}
   return rtnAng;
} // find_Ang_Rad

	
	
} // convertCls
