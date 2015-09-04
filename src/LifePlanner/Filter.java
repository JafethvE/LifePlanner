package LifePlanner;

import java.io.File;
import java.io.FilenameFilter;

/**
 * A filter for file types.
 * @author Jafeth
 */
public class Filter {
    
    String fileType;
    
    public Filter(String fileType)
    {
        this.fileType = fileType;
    }
    
    /**
     * Finds all files of a specific type in a directory.
     * @param dirName The directory to be searched.
     * @return The list of files of a certain type this directory contains.
     */
    public File[] finder( String dirName)
    {
    	File dir = new File(dirName);

    	return dir.listFiles(new FilenameFilter()
        { 
            public boolean accept(File dir, String filename)
    	    {
                return filename.endsWith(fileType);
            }
    	}
        );

   }
}
