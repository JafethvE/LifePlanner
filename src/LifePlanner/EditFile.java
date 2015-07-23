/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LifePlanner;

import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.PrintWriter;

/**
 * A file reading class.
 * @author Jafeth
 */
public class EditFile
{
    
    private final String path;
    private boolean appendToFile;
    
    /**
     * Reads the given file.
     * @param path The path of the file to be read.
     */
    public EditFile (String path)
    {
        this.appendToFile = false;
        this.path = path;
    }
    
    public EditFile(String path, boolean appendToFile)
    {
        this.appendToFile = appendToFile;
        this.path = path;
    }
    
    public String[] openFile() throws IOException
    {
        FileReader fr = new FileReader(path);
        String[] textData;
        int numberOfLines = readLines();
        textData = new String[numberOfLines];
        try (BufferedReader textReader = new BufferedReader(fr))
        {
            for(int i = 0; i < numberOfLines;i++)
            {
                textData[i] = textReader.readLine();
            }
        }
        catch (Exception e)
        {
            System.out.println("Problem while opening file." + e);
        }
        
        return textData;
    }
    
    /**
     * Gives the amount of lines in the file to be read.
     * @return the amount of lines.
     * @throws IOException
     */
    private int readLines() throws IOException
    {
        FileReader fileToRead = new FileReader(path);
        int numberOfLines;
        numberOfLines = 0;
        try (BufferedReader bf = new BufferedReader(fileToRead))
        {
            while(bf.readLine() != null)
            {
                numberOfLines++;
            }
        }
        catch (Exception e)
        {
            System.out.println("Problem while reading the amount of lines." + e);
        }
        
        return numberOfLines;
    }
    
    public void writeToFile(String textLine, boolean appendToFile) throws IOException
    {
        FileWriter fileWriter = new FileWriter(path, appendToFile);
        PrintWriter printWriter = new PrintWriter(fileWriter);
        
        printWriter.printf("%s" + "%n", textLine);
        
        printWriter.close();
    }
}