package cf2dxf;

/**
 *
 * @author tim_g
 */
import java.io.File;
import java.io.FilenameFilter;

public class GetCf2FilesInDir {


    public File[] getCf2FileList( String dirName){
        File dir = new File(dirName);

        return dir.listFiles(new FilenameFilter() { 
            public boolean accept(File dir, String filename)
                { return filename.toLowerCase().endsWith(".cf2"); }
        } );

    }
}

    
