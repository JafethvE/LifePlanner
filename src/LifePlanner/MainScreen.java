/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LifePlanner;

import com.sun.security.auth.module.NTSystem;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import javax.swing.JFrame;
import org.ini4j.Ini;
/**
 *
 * @author Jafeth
 */
public class MainScreen extends javax.swing.JFrame {

    private NTSystem NTSystem;
    
    public static void main(String[] args) throws IOException
    {
        MainScreen mainScreen = new MainScreen();
        mainScreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainScreen.setVisible(true);
    }
    
    /**
     * Creates new form MainScreen
     */
    public MainScreen() {
        NTSystem = new com.sun.security.auth.module.NTSystem();
        initComponents();
        createFilePath();
        findFiles();
        try
        {
            setStrings();
        }
        catch (Exception e)
        {
            System.out.println("Problem while reading in Strings for the main screen." + e);
        }
    }
    /**
     * Finds the settings and language files for the program.
     */
    private void findFiles()
    {
        findSettingsFile();
        findLanguageFile("English");
        findLanguageFile("Nederlands");
    }
    
    /**
     * Finds the settings file for the program.
     */
    private void findSettingsFile()
    {
        Ini ini = null;
        try
        {
            ini = new Ini(new File("C:\\Users\\" + NTSystem.getName() + "\\Documents\\LifePlanner\\Settings\\LifeplannerSettings.ini"));
        }
        catch (Exception e)
        {
            System.out.println("Error while trying to find settings ini file." + e);
        }
        if(ini == null)
        {
            try
            {
                createSettingsFile();
            }
            catch (Exception e)
            {
                System.out.println("Problem while creating settings ini files." + e);
            }
        }
    }
    
    /**
     * Finds a specific language file for the program.
     * @param language The language for which a file is to be found.
     */
    private void findLanguageFile(String language)
    {
        Ini ini = null;
        try
        {
            ini = new Ini(new File("C:\\Users\\" + NTSystem.getName() + "\\Documents\\LifePlanner\\Settings\\Languages\\" + language + ".ini"));
        }
        catch (Exception e)
        {
            System.out.println("Error while trying to find " + language + " ini file." + e);
        }
        if(ini == null)
        {
            try
            {
                createLanguageFile(language);
            }
            catch (Exception e)
            {
                System.out.println("Problem while creating " + language + " ini file." + e);
            }
        }
    }
    
    /**
     * Creates the settings file with the default settings.
     * @throws IOException 
     */
    private void createSettingsFile() throws IOException
    {   
        FileWriter fileWriter = new FileWriter("C:\\Users\\" + NTSystem.getName() + "\\Documents\\LifePlanner\\Settings\\LifeplannerSettings.ini", false);
        PrintWriter printWriter = new PrintWriter(fileWriter);
        printWriter.printf("%s" + "%n", "[header]\n" + "language = English");
        
        printWriter.close();
    }
    
    /**
     * Creates a specific language file.
     * @param language The language for which the file is made.
     * @throws IOException 
     */
    private void createLanguageFile(String language) throws IOException
    {
        FileWriter fileWriter = new FileWriter("C:\\Users\\" + NTSystem.getName() + "\\Documents\\LifePlanner\\Settings\\Languages\\" + language + ".ini", false);
        PrintWriter printWriter = new PrintWriter(fileWriter);
        if(language.equals("English"))
        {
            printWriter.printf("%s" + "%n", "[meta]\n" +
"language = English\n" +
"\n" +
"[common]\n" +
"ok = Ok\n" +
"delete = Delete\n" +
"refresh = Refresh\n" +
"new = New\n" +
"details = Details\n" +
"cancel = Cancel\n" +
"done = Done\n" +
"\n" +
"[main]\n" +
"title = Lifeplanner\n" +
"goals = Goals\n" +
"entertainment = Entertainment\n" +
"toStudy = To Study\n" +
"planning = Planning\n" +
"today = Today\n" +
"sandbox = Sandbox\n" +
"meta = Meta\n" +
"settings = Settings\n" +
"about = About\n" +
"\n" +
"[sandbox]\n" +
"title = Sandbox\n" +
"notes = Notes\n" +
"summaries = Summaries\n" +
"ideas = Ideas\n" +
"suggestions = Suggestions\n" +
"questions = Questions\n" +
"inventions = Inventions\n" +
"\n" +
"[notes]\n" +
"title = Notes\n" +
"\n" +
"[settings]\n" +
"title = Settings\n" +
"language = Language:\n" +
"\n" +
"[error]\n" +
"unselected = Please select a file");
        }
        else if(language.equals("Nederlands"))
        {
            printWriter.printf("%s" + "%n", "[meta]\n" +
"language = Nederlands\n" +
"\n" +
"[common]\n" +
"ok = Ok\n" +
"delete = Verwijder\n" +
"refresh = Ververs\n" +
"new = Nieuw\n" +
"details = Details\n" +
"cancel = Annuleren\n" +
"done = Klaar\n" +
"\n" +
"[main]\n" +
"title = Levensplanner\n" +
"goals = Doelen\n" +
"entertainment = Entertainment\n" +
"toStudy = Te Bestuderen\n" +
"planning = Planning\n" +
"today = Vandaag\n" +
"sandbox = Sandbox\n" +
"meta = Meta\n" +
"settings = Instellingen\n" +
"about = Over\n" +
"\n" +
"[sandbox]\n" +
"title = Sandbox\n" +
"notes = Notities\n" +
"summaries = Samenvattingen\n" +
"ideas = Ideeën\n" +
"suggestions = Suggesties\n" +
"questions = Vragen\n" +
"inventions = Uitvindingen\n" +
"\n" +
"[notes]\n" +
"title = Notities\n" +
"\n" +
"[settings]\n" +
"title = Instellingen\n" +
"language = Taal:\n" +
"\n" +
"[error]\n" +
"unselected = Selecteer een file");
        }
        printWriter.close();
    }
    
    /**
     * Sets the texts in the labels and on the buttons from the language settings file.
     * @throws IOException 
     */
    public void setStrings() throws IOException
    {
        Ini ini = new Ini(new File("C:\\Users\\" + NTSystem.getName() + "\\Documents\\LifePlanner\\Settings\\LifeplannerSettings.ini"));
        Ini language = new Ini(new File("C:\\Users\\" + NTSystem.getName() + "\\Documents\\LifePlanner\\Settings\\Languages\\" + ini.get("header", "language") + ".ini"));
        titleLabel.setText(language.get("main", "title"));
        goalsButton.setText(language.get("main", "goals"));
        planningButton.setText(language.get("main", "planning"));
        toStudyButton.setText(language.get("main", "toStudy"));
        todayButton.setText(language.get("main", "today"));
        sandboxButton.setText(language.get("main", "sandbox"));
        settingsButton.setText(language.get("main", "settings"));
        metaButton.setText(language.get("main", "meta"));
        aboutButton.setText(language.get("main", "about"));
        entertainmentButton.setText(language.get("main", "entertainment"));
    }
    
    /**
     * Creates the file system for the program if it doesn't already.
     */
    private void createFilePath()
    {
        String error = "Directory creation failed at: ";
        if(!new File("C:\\Users\\" + NTSystem.getName() + "\\Documents\\LifePlanner\\Sandbox\\Notes").mkdirs())
        {
            System.out.println(error + "C:\\Users\\" + NTSystem.getName() + "\\Documents\\LifePlanner\\Sandbox\\Notes");
        }
        if(!new File("C:\\Users\\" + NTSystem.getName() + "\\Documents\\LifePlanner\\Sandbox\\Summaries").mkdirs())
        {
            System.out.println(error + "C:\\Users\\" + NTSystem.getName() + "\\Documents\\LifePlanner\\Sandbox\\Summaries");
        }
        if(!new File("C:\\Users\\" + NTSystem.getName() + "\\Documents\\LifePlanner\\Sandbox\\Ideas").mkdirs())
        {
            System.out.println(error + "C:\\Users\\" + NTSystem.getName() + "\\Documents\\LifePlanner\\Sandbox\\Ideas");
        }
        if(!new File("C:\\Users\\" + NTSystem.getName() + "\\Documents\\LifePlanner\\Sandbox\\Suggestions").mkdirs())
        {
            System.out.println(error + "C:\\Users\\" + NTSystem.getName() + "\\Documents\\LifePlanner\\Sandbox\\Suggestions");
        }
        if(!new File("C:\\Users\\" + NTSystem.getName() + "\\Documents\\LifePlanner\\Sandbox\\Questions").mkdirs())
        {
            System.out.println(error + "C:\\Users\\" + NTSystem.getName() + "\\Documents\\LifePlanner\\Sandbox\\Questions");
        }
        if(!new File("C:\\Users\\" + NTSystem.getName() + "\\Documents\\LifePlanner\\Sandbox\\Inventions").mkdirs())
        {
            System.out.println(error + "C:\\Users\\" + NTSystem.getName() + "\\Documents\\LifePlanner\\Sandbox\\Inventions");
        }
        if(!new File("C:\\Users\\" + NTSystem.getName() + "\\Documents\\LifePlanner\\Settings").mkdirs())
        {
            System.out.println(error + "C:\\Users\\" + NTSystem.getName() + "\\Documents\\LifePlanner\\Settings");
        }
        if(!new File("C:\\Users\\" + NTSystem.getName() + "\\Documents\\LifePlanner\\Goals\\").mkdirs())
        {
            System.out.println(error + "C:\\Users\\" + NTSystem.getName() + "\\Documents\\LifePlanner\\Goals");
        }
        if(!new File("C:\\Users\\" + NTSystem.getName() + "\\Documents\\LifePlanner\\Entertainment\\Books").mkdirs())
        {
            System.out.println(error + "C:\\Users\\" + NTSystem.getName() + "\\Documents\\LifePlanner\\Entertainment\\Books");
        }
        if(!new File("C:\\Users\\" + NTSystem.getName() + "\\Documents\\LifePlanner\\Entertainment\\Entertainment\\Films").mkdirs())
        {
            System.out.println(error + "C:\\Users\\" + NTSystem.getName() + "\\Documents\\LifePlanner\\Entertainment\\Films");
        }
        if(!new File("C:\\Users\\" + NTSystem.getName() + "\\Documents\\LifePlanner\\Entertainment\\Music").mkdirs())
        {
            System.out.println(error + "C:\\Users\\" + NTSystem.getName() + "\\Documents\\LifePlanner\\Entertainment\\Music");
        }
        if(!new File("C:\\Users\\" + NTSystem.getName() + "\\Documents\\LifePlanner\\Summaries").mkdirs())
        {
            System.out.println(error + "C:\\Users\\" + NTSystem.getName() + "\\Documents\\LifePlanner\\Summaries");
        }
        if(!new File("C:\\Users\\" + NTSystem.getName() + "\\Documents\\LifePlanner\\To Study").mkdirs())
        {
            System.out.println(error + "C:\\Users\\" + NTSystem.getName() + "\\Documents\\LifePlanner\\To Study");
        }
        if(!new File("C:\\Users\\" + NTSystem.getName() + "\\Documents\\LifePlanner\\Settings\\Languages").mkdirs())
        {
            System.out.println(error + "C:\\Users\\" + NTSystem.getName() + "\\Documents\\LifePlanner\\Settings\\Languages");
        }
    }

    /**
     * This method is called from within the constructor to initialise the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        goalsButton = new javax.swing.JButton();
        planningButton = new javax.swing.JButton();
        entertainmentButton = new javax.swing.JButton();
        toStudyButton = new javax.swing.JButton();
        todayButton = new javax.swing.JButton();
        metaButton = new javax.swing.JButton();
        sandboxButton = new javax.swing.JButton();
        settingsButton = new javax.swing.JButton();
        aboutButton = new javax.swing.JButton();
        titleLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        goalsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                goalsButtonActionPerformed(evt);
            }
        });

        toStudyButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                toStudyButtonActionPerformed(evt);
            }
        });

        sandboxButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sandboxButtonActionPerformed(evt);
            }
        });

        settingsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                settingsButtonActionPerformed(evt);
            }
        });

        titleLabel.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        titleLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(titleLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(goalsButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(todayButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(metaButton, javax.swing.GroupLayout.DEFAULT_SIZE, 101, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(planningButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(sandboxButton, javax.swing.GroupLayout.DEFAULT_SIZE, 101, Short.MAX_VALUE)
                            .addComponent(entertainmentButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(toStudyButton, javax.swing.GroupLayout.DEFAULT_SIZE, 101, Short.MAX_VALUE)
                            .addComponent(settingsButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(aboutButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(titleLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(goalsButton, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(planningButton, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(toStudyButton, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(sandboxButton, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(entertainmentButton, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(todayButton, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(metaButton, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(settingsButton, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(aboutButton, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void goalsButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_goalsButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_goalsButtonActionPerformed

    private void toStudyButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_toStudyButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_toStudyButtonActionPerformed

    /**
     * Creates a sandboxScreen
     * @param evt The event that has to be passed through but isn't used.
     */
    private void sandboxButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sandboxButtonActionPerformed
        SandboxScreen sandboxScreen = new SandboxScreen();
        sandboxScreen.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        sandboxScreen.setVisible(true);
    }//GEN-LAST:event_sandboxButtonActionPerformed

    /**
     * Creates a settingsScreen.
     * @param evt The event that has to be passed through but isn't used.
     */
    private void settingsButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_settingsButtonActionPerformed
        SettingsScreen settingsScreen = new SettingsScreen(this);
        settingsScreen.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        settingsScreen.setVisible(true);
    }//GEN-LAST:event_settingsButtonActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton aboutButton;
    private javax.swing.JButton entertainmentButton;
    private javax.swing.JButton goalsButton;
    private javax.swing.JButton metaButton;
    private javax.swing.JButton planningButton;
    private javax.swing.JButton sandboxButton;
    private javax.swing.JButton settingsButton;
    private javax.swing.JLabel titleLabel;
    private javax.swing.JButton toStudyButton;
    private javax.swing.JButton todayButton;
    // End of variables declaration//GEN-END:variables
}
