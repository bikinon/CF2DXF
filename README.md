# CF2DXF
CF2 to DXF CAD file converter

This is a conversion program that translates the CF2 packaging Common File Format into a standard DXF CAD format.

The files were created as a NetBeans Java project, a compiled JAR file is provided along with the code. The JAR file should run just by double clicking on it in most interfaces. To run from the command line use: java -jar "cf2dxf.jar" 

CF2 is typically a file format that is requested by forme makers, many packaging professionals find themselves having to deal with files in the CF2 format. It has been enough of a problem for me over the years that I finally wrote this translation program.

The program doesn't cover every aspect of the format, but most files should translate pretty well and even those that contain less usual features should end up giving you some idea of what is in the file. 

The main parts of the file format not currently dealt with are inserted blocks and scaling. Aspects that the file format was supposedly developed to contain, but in my experience are usually ignored, like knife & crease pointage and bridging are also not dealt with, but from a general design point of view those are rarely relevant. 

If someone would care to add any additional improvements to the code it will be appreciated, perhaps I'll get around to improving it some more at some point.

The usual caveat applies; no warrantee is either given or implied with this software, use it at your own risk.

TG 23/11/2019

Directory converrsion and CF2out.lsp added 10/12/2023.
Obviously CF2out.lsp needs to t=be run from within AutoCAD or a similar CAD program.