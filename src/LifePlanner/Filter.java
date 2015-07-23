/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LifePlanner;

import java.io.File;
import java.io.FilenameFilter;

/**
 *
 * @author Jafeth
 */
public class Filter {
    
    String fileType;
    
    public Filter(String fileType)
    {
        this.fileType = fileType;
    }
    
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
