/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package schoolcf;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import javax.swing.JEditorPane;
import javax.swing.JFileChooser;
import javax.swing.UIManager;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;

/**
 *
 * @author KV
 */
public class SchoolCF extends javax.swing.JFrame {
    
    private static final String HOME_PATH = "E:\\Downloads\\SchoolRelated\\HK2_19-20\\Java\\SchoolCF\\code\\";
    
    private static final int SOURCE_INDEX = 0;
    private static final int INPUT_INDEX = 1;
    private static final int TEST_GEN_INDEX = 2;
    private static final int TEST_SOURCE_INDEX = 3;
    private static final int TEMPLATE_INDEX = 4;
    
    private static final int FORMAT_NONE = 0;
    private static final int FORMAT_SUCCESS = 1;
    private static final int FORMAT_WARNING = 2;
    private static final int FORMAT_ERROR = 3;
    private static final boolean FORMAT_BOLD = true;
    private static final boolean FORMAT_NO_BOLD = false;
    private static final boolean FORMAT_ITALIC = true;
    private static final boolean FORMAT_NO_ITALIC = false;
    
    private static final String[] EDITOR_FILES = {
        "source",
        "inp",
        "test_gen",
        "test_source",
        "template"
    };
    
    private JEditorPane EDITOR_PANES[];
    
    private UserSession user;
    private Process runningProcess = null;
    private String consoleString = "";
    BufferedWriter processWriter = null;

    /**
     * Creates new form SchoolCF
     */
    public SchoolCF() {
        initComponents();
        
        setTitle("Codeforces submit tool");
        
        user = new UserSession();
        
        cfDetailPanel.setVisible(false);
        processRunning(false);
        
        jsyntaxpane.DefaultSyntaxKit.initKit();
        sourceEditorPane.setContentType("text/cpp");
        testGenEditorPane.setContentType("text/cpp");
        testSourceEditorPane.setContentType("text/cpp");
        
        EDITOR_PANES = new JEditorPane[EDITOR_FILES.length];
        EDITOR_PANES[SOURCE_INDEX] = sourceEditorPane;
        EDITOR_PANES[INPUT_INDEX] = inputEditorPane;
        EDITOR_PANES[TEST_GEN_INDEX] = testGenEditorPane;
        EDITOR_PANES[TEST_SOURCE_INDEX] = testSourceEditorPane;
        
        boolean loadOk = true;
        for (int i = EDITOR_FILES.length - 2; i >= 0; i--) {
            mainTabbedPane.setSelectedIndex(i);
            loadOk = load(HOME_PATH + EDITOR_FILES[i] + (EDITOR_FILES[i].equals(EDITOR_FILES[INPUT_INDEX]) ? "" : ".cpp")) && loadOk;
        }
        
        
        if (!loadOk) printToConsole("Problem loading files.", FORMAT_ERROR, FORMAT_BOLD, FORMAT_NO_ITALIC);
    }
    
    /**
     * Print to console and virtual console
     * @param message Message to print
     */
    private void printToConsole(String message) {
//        consoleTextPane.setText(consoleTextPane.getText() + message + "\n");
//        consoleTextPane.setCaretPosition(consoleTextPane.getText().length());
        Document doc = consoleTextPane.getDocument();
        try {
            doc.insertString(doc.getLength(), message + "\n", new SimpleAttributeSet());
        } catch (BadLocationException ex) {
            consoleTextPane.setText("SchoolCF.printToConsole: " + ex.toString());
            System.out.println("SchoolCF.printToConsole: " + ex.toString());
        }
        
        consoleScrollPane.getVerticalScrollBar().setValue(consoleScrollPane.getVerticalScrollBar().getMaximum());
        consoleTextPane.setCaretPosition(doc.getLength());
        this.consoleString = consoleTextPane.getText();
        
        System.out.println(message);
    }
    
    private void printToConsole(String message, int format, boolean bold, boolean italic) {
        SimpleAttributeSet sas = new SimpleAttributeSet();
        
        StyleConstants.setBold(sas, bold);
        StyleConstants.setItalic(sas, italic);
        
        switch(format) {
            case FORMAT_SUCCESS:
                StyleConstants.setForeground(sas, Color.green);
                break;
            case FORMAT_WARNING:
                StyleConstants.setForeground(sas, Color.magenta);
                break;
            case FORMAT_ERROR:
                StyleConstants.setForeground(sas, Color.red);
                break;
            default:
                break;
        }
        
        Document doc = consoleTextPane.getDocument();
        try {
            doc.insertString(doc.getLength(), message + "\n", sas);
        } catch (BadLocationException ex) {
            consoleTextPane.setText("SchoolCF.printToConsole: " + ex.toString());
            System.out.println("SchoolCF.printToConsole: " + ex.toString());
        }
        
        consoleScrollPane.getVerticalScrollBar().setValue(consoleScrollPane.getVerticalScrollBar().getMaximum()+1);
        consoleTextPane.setCaretPosition(doc.getLength());
        this.consoleString = consoleTextPane.getText();
        
        System.out.println(message);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mainSplitPane = new javax.swing.JSplitPane();
        sideSplitPane = new javax.swing.JSplitPane();
        cfPanel = new javax.swing.JPanel();
        cfDetailPanel = new javax.swing.JPanel();
        submitBtn = new javax.swing.JButton();
        welcomeLabel = new javax.swing.JLabel();
        problemCodeField = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        logoutBtn = new javax.swing.JButton();
        statusBtn = new javax.swing.JButton();
        verdictLabel = new javax.swing.JLabel();
        cfLoginPanel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        handleField = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        loginBtn = new javax.swing.JButton();
        passwordField = new javax.swing.JPasswordField();
        consoleScrollPane = new javax.swing.JScrollPane();
        consoleTextPane = new javax.swing.JTextPane();
        mainTabbedPane = new javax.swing.JTabbedPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        sourceEditorPane = new javax.swing.JEditorPane();
        jScrollPane3 = new javax.swing.JScrollPane();
        inputEditorPane = new javax.swing.JEditorPane();
        jScrollPane2 = new javax.swing.JScrollPane();
        testGenEditorPane = new javax.swing.JEditorPane();
        jScrollPane4 = new javax.swing.JScrollPane();
        testSourceEditorPane = new javax.swing.JEditorPane();
        menuBar = new javax.swing.JMenuBar();
        fileMenu = new javax.swing.JMenu();
        runMenuItem = new javax.swing.JMenuItem();
        runInputMenuItem = new javax.swing.JMenuItem();
        runTestMenuItem = new javax.swing.JMenuItem();
        jSeparator3 = new javax.swing.JPopupMenu.Separator();
        compileMenuItem = new javax.swing.JMenuItem();
        compileInputMenuItem = new javax.swing.JMenuItem();
        compileTestMenuItem = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        saveMenuItem = new javax.swing.JMenuItem();
        saveAsMenuItem = new javax.swing.JMenuItem();
        loadTemplateMenuItem = new javax.swing.JMenuItem();
        loadFileMenuItem = new javax.swing.JMenuItem();
        jSeparator2 = new javax.swing.JPopupMenu.Separator();
        exitMenuItem = new javax.swing.JMenuItem();
        jMenu1 = new javax.swing.JMenu();
        terminateMenuItem = new javax.swing.JMenuItem();
        consolePreserveOutput = new javax.swing.JMenu();
        consoleClearBtn = new javax.swing.JMenuItem();
        preserveOutputMenuItem = new javax.swing.JCheckBoxMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        mainSplitPane.setDividerLocation(850);
        mainSplitPane.setResizeWeight(0.6);

        sideSplitPane.setDividerLocation(340);
        sideSplitPane.setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT);
        sideSplitPane.setResizeWeight(1.0);

        cfPanel.setBackground(new java.awt.Color(255, 255, 255));
        cfPanel.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        cfDetailPanel.setBackground(new java.awt.Color(255, 255, 255));

        submitBtn.setText("Submit");
        submitBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                submitBtnActionPerformed(evt);
            }
        });

        welcomeLabel.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        welcomeLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        welcomeLabel.setText("Welcome");

        jLabel4.setText("Problem Code");

        logoutBtn.setText("Log out");
        logoutBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logoutBtnActionPerformed(evt);
            }
        });

        statusBtn.setText("Status");
        statusBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                statusBtnActionPerformed(evt);
            }
        });

        verdictLabel.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        verdictLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        verdictLabel.setText("Verdict status");

        javax.swing.GroupLayout cfDetailPanelLayout = new javax.swing.GroupLayout(cfDetailPanel);
        cfDetailPanel.setLayout(cfDetailPanelLayout);
        cfDetailPanelLayout.setHorizontalGroup(
            cfDetailPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, cfDetailPanelLayout.createSequentialGroup()
                .addGap(60, 60, 60)
                .addComponent(welcomeLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(48, 48, 48))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, cfDetailPanelLayout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(cfDetailPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(problemCodeField)
                    .addComponent(verdictLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(statusBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(submitBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(logoutBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(27, 27, 27))
        );
        cfDetailPanelLayout.setVerticalGroup(
            cfDetailPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, cfDetailPanelLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(welcomeLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 18, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 17, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(problemCodeField, javax.swing.GroupLayout.DEFAULT_SIZE, 23, Short.MAX_VALUE)
                .addGap(8, 8, 8)
                .addComponent(verdictLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(statusBtn)
                .addGap(6, 6, 6)
                .addComponent(submitBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(logoutBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        cfLoginPanel.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setText("Handle or Email");

        jLabel2.setText("Password");

        loginBtn.setText("Log in");
        loginBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loginBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout cfLoginPanelLayout = new javax.swing.GroupLayout(cfLoginPanel);
        cfLoginPanel.setLayout(cfLoginPanelLayout);
        cfLoginPanelLayout.setHorizontalGroup(
            cfLoginPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cfLoginPanelLayout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addGroup(cfLoginPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(passwordField)
                    .addComponent(handleField, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(loginBtn, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 164, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, cfLoginPanelLayout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(49, 49, 49))
        );
        cfLoginPanelLayout.setVerticalGroup(
            cfLoginPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cfLoginPanelLayout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(handleField)
                .addGap(18, 18, 18)
                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(passwordField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(loginBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout cfPanelLayout = new javax.swing.GroupLayout(cfPanel);
        cfPanel.setLayout(cfPanelLayout);
        cfPanelLayout.setHorizontalGroup(
            cfPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(cfDetailPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(cfPanelLayout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addComponent(cfLoginPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        cfPanelLayout.setVerticalGroup(
            cfPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cfPanelLayout.createSequentialGroup()
                .addComponent(cfDetailPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cfLoginPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        sideSplitPane.setRightComponent(cfPanel);

        consoleScrollPane.setBackground(new java.awt.Color(255, 255, 255));

        consoleTextPane.setEditable(false);
        consoleTextPane.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                consoleTextPaneKeyPressed(evt);
            }
        });
        consoleScrollPane.setViewportView(consoleTextPane);

        sideSplitPane.setLeftComponent(consoleScrollPane);

        mainSplitPane.setRightComponent(sideSplitPane);

        mainTabbedPane.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jScrollPane1.setViewportView(sourceEditorPane);

        mainTabbedPane.addTab("Source", jScrollPane1);

        jScrollPane3.setViewportView(inputEditorPane);

        mainTabbedPane.addTab("Input", jScrollPane3);

        jScrollPane2.setViewportView(testGenEditorPane);

        mainTabbedPane.addTab("Test gen", jScrollPane2);

        jScrollPane4.setViewportView(testSourceEditorPane);

        mainTabbedPane.addTab("Test source", jScrollPane4);

        mainSplitPane.setLeftComponent(mainTabbedPane);

        fileMenu.setText("File");

        runMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F6, 0));
        runMenuItem.setText("Run");
        runMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                runMenuItemActionPerformed(evt);
            }
        });
        fileMenu.add(runMenuItem);

        runInputMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F7, 0));
        runInputMenuItem.setText("Run (I)");
        runInputMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                runInputMenuItemActionPerformed(evt);
            }
        });
        fileMenu.add(runInputMenuItem);

        runTestMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F8, 0));
        runTestMenuItem.setText("Run (T)");
        runTestMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                runTestMenuItemActionPerformed(evt);
            }
        });
        fileMenu.add(runTestMenuItem);
        fileMenu.add(jSeparator3);

        compileMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F6, java.awt.event.InputEvent.SHIFT_MASK));
        compileMenuItem.setText("Compile & Run");
        compileMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                compileMenuItemActionPerformed(evt);
            }
        });
        fileMenu.add(compileMenuItem);

        compileInputMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F7, java.awt.event.InputEvent.SHIFT_MASK));
        compileInputMenuItem.setText("Compile & Run (I)");
        compileInputMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                compileInputMenuItemActionPerformed(evt);
            }
        });
        fileMenu.add(compileInputMenuItem);

        compileTestMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F8, java.awt.event.InputEvent.SHIFT_MASK));
        compileTestMenuItem.setText("Compile & Run (T)");
        compileTestMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                compileTestMenuItemActionPerformed(evt);
            }
        });
        fileMenu.add(compileTestMenuItem);
        fileMenu.add(jSeparator1);

        saveMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_MASK));
        saveMenuItem.setText("Save");
        saveMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveMenuItemActionPerformed(evt);
            }
        });
        fileMenu.add(saveMenuItem);

        saveAsMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.SHIFT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        saveAsMenuItem.setText("Save to file...");
        saveAsMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveAsMenuItemActionPerformed(evt);
            }
        });
        fileMenu.add(saveAsMenuItem);

        loadTemplateMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N, java.awt.event.InputEvent.CTRL_MASK));
        loadTemplateMenuItem.setText("Load template");
        loadTemplateMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loadTemplateMenuItemActionPerformed(evt);
            }
        });
        fileMenu.add(loadTemplateMenuItem);

        loadFileMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.CTRL_MASK));
        loadFileMenuItem.setText("Load from file...");
        loadFileMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loadFileMenuItemActionPerformed(evt);
            }
        });
        fileMenu.add(loadFileMenuItem);
        fileMenu.add(jSeparator2);

        exitMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F4, 0));
        exitMenuItem.setText("Exit");
        exitMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitMenuItemActionPerformed(evt);
            }
        });
        fileMenu.add(exitMenuItem);

        menuBar.add(fileMenu);

        jMenu1.setText("Process");

        terminateMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_C, java.awt.event.InputEvent.CTRL_MASK));
        terminateMenuItem.setText("Terminate process");
        terminateMenuItem.setEnabled(false);
        terminateMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                terminateMenuItemActionPerformed(evt);
            }
        });
        jMenu1.add(terminateMenuItem);

        menuBar.add(jMenu1);

        consolePreserveOutput.setText("Console");

        consoleClearBtn.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_C, java.awt.event.InputEvent.SHIFT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        consoleClearBtn.setText("Clear");
        consoleClearBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                consoleClearBtnActionPerformed(evt);
            }
        });
        consolePreserveOutput.add(consoleClearBtn);

        preserveOutputMenuItem.setText("Preserve output");
        consolePreserveOutput.add(preserveOutputMenuItem);

        menuBar.add(consolePreserveOutput);

        setJMenuBar(menuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(mainSplitPane, javax.swing.GroupLayout.DEFAULT_SIZE, 1119, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(mainSplitPane, javax.swing.GroupLayout.DEFAULT_SIZE, 572, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Save file to a default location (can't change location)
     * @param fileName File to save: source, test_gen, test_source
     * @return 
     */
    private boolean save(String filePath) {
        if (mainTabbedPane.getSelectedIndex() != INPUT_INDEX) filePath += ".cpp";
        
        try (FileWriter fileWriter = new FileWriter(filePath)) {
            if (filePath.equals(EDITOR_FILES[INPUT_INDEX])) {
                fileWriter.write(EDITOR_PANES[INPUT_INDEX].getText());
            } else if (filePath.equals(EDITOR_FILES[SOURCE_INDEX])) {
                fileWriter.write(EDITOR_PANES[SOURCE_INDEX].getText());
            } else if (filePath.equals(EDITOR_FILES[TEST_GEN_INDEX])) {
                fileWriter.write(EDITOR_PANES[TEST_GEN_INDEX].getText());
            } else if (filePath.equals(EDITOR_FILES[TEST_SOURCE_INDEX])) {
                fileWriter.write(EDITOR_PANES[TEST_SOURCE_INDEX].getText());
            } else {
                fileWriter.write(EDITOR_PANES[mainTabbedPane.getSelectedIndex()].getText());
            }
        } catch (IOException ex) {
            printToConsole("Problem saving file " + filePath, FORMAT_ERROR, FORMAT_NO_BOLD, FORMAT_ITALIC);
            printToConsole(ex.toString(), FORMAT_ERROR, FORMAT_NO_BOLD, FORMAT_ITALIC);
            return false;
        }
        
        return true;
    }
    
    /**
     * Load file to current active tab in TabbedPanel
     * @param filePath File to load
     * @return 
     */
    private boolean load(String filePath) {
        try {
            File file = new File(filePath);
            FileInputStream fis = new FileInputStream(file);
            byte[] data = new byte[(int)file.length()];
            fis.read(data);
            fis.close();
            
            EDITOR_PANES[mainTabbedPane.getSelectedIndex()].setText(new String(data));
        } catch (IOException ex) {
            printToConsole("Problem loading file " + filePath + ": " + ex.getMessage(), FORMAT_ERROR, FORMAT_NO_BOLD, FORMAT_ITALIC);
            return false;
        }
        
        return true;
    }
    
    /**
     * Enable/Disable menu items and console based on process's state
     * The following will happen if the process is running (on == true)
     *  - Run and Compile menu items will be disabled
     *  - Terminate menu item and virtual console input will be enabled
     * 
     * If the process is not running (on == false)
     *  - Run and Compile menu items will be enable
     *  - Terminate menu item and virtual console input will be disabled
     *  - Process input stream will be closed if it was established
     * 
     * @param on Process state
     */
    private void processRunning(boolean on) {
        runMenuItem.setEnabled(!on);
        runInputMenuItem.setEnabled(!on);
        runTestMenuItem.setEnabled(!on);
        
        compileMenuItem.setEnabled(!on);
        compileInputMenuItem.setEnabled(!on);
        compileTestMenuItem.setEnabled(!on);
        
        terminateMenuItem.setEnabled(on);
        consoleTextPane.setEditable(on);
        
        if (!on) {
            
            if (this.processWriter != null) {
                try {
                    processWriter.close();
                } catch(IOException ex) {
                    printToConsole("processRunning: Problem closing processWriter: " + ex.toString(), FORMAT_ERROR, FORMAT_NO_BOLD, FORMAT_ITALIC);
                }
            }
            this.processWriter = null;
            
            this.runningProcess = null;
        }
    }
    
    /**
     * Compile the file with bash using g++ compiler, the output file name will the same as sourceFile
     * sourceFile MUST NOT HAVE FILE EXTENSION
     * 
     * @param sourceFile Name of source file (no extension)
     * @return Boolean value: true if the compilation succeeded
     */
    private boolean compile(String sourceFile, boolean verbose) {
        
        if (!preserveOutputMenuItem.isSelected() && verbose) {
            consoleTextPane.setText("");
            this.consoleString = "";
        }
        
        if (verbose) printToConsole("Compiling...", FORMAT_WARNING, FORMAT_BOLD, FORMAT_ITALIC);
        
        boolean ok = true;
        
        if (save(HOME_PATH + EDITOR_FILES[mainTabbedPane.getSelectedIndex()])) {
            try{
                ProcessBuilder pBuilder = new ProcessBuilder(
                        "C:\\MinGW\\bin\\g++",
                        "-o" + HOME_PATH + sourceFile + ".exe",
                        "" + HOME_PATH + sourceFile + ".cpp"
                );
                
//                ProcessBuilder pBuilder = new ProcessBuilder(
//                        "bash",
//                        "-c",
//                        "\"cd /mnt/e/Downloads/SchoolRelated/HK2_19-20/Java/SchoolCF/code && g++ " + sourceFile + ".cpp -o " + sourceFile + "\"");
                
                pBuilder.redirectErrorStream(true);
                
                this.runningProcess = pBuilder.start();
//                terminateMenuItem.setEnabled(true);
                processRunning(true);

                BufferedReader reader = new BufferedReader(new InputStreamReader(this.runningProcess.getInputStream()));

                String line;
                while (true) {
                    line = reader.readLine();
                    if (line == null) break;
                    printToConsole(line, FORMAT_ERROR, FORMAT_NO_BOLD, FORMAT_NO_ITALIC);
                    ok = false;
                }
            } catch (IOException ex) {
    //            System.out.println(ex.toString());
                if (verbose) printToConsole("Problem compiling: " + ex.toString(), FORMAT_ERROR, FORMAT_NO_BOLD, FORMAT_NO_ITALIC);
                ok = false;
            }
        }
        
        
//        terminateMenuItem.setEnabled(false);
        
        if (ok && this.runningProcess != null)
            if (verbose) printToConsole("\nCompiled successfully\n======================\n",FORMAT_SUCCESS, FORMAT_BOLD, FORMAT_ITALIC);
        else if (verbose) printToConsole("=== COMPILATION FAILED! ===", FORMAT_ERROR, FORMAT_BOLD, FORMAT_ITALIC);
        
        ok = ok && this.runningProcess != null;
        
        processRunning(false);
        
        return ok;
    }
    
    /**
     * Run the compiled binary file named sourceFile with corresponding input
     * from inputFile redirect output to outputFile
     * 
     * If inputFile is null, the process wait for input from virtual console (if any input needed)
     * If outputFile is null, the process output will be printed to console and virtual console
     * 
     * @param sourceFile The binary file to run
     * @param inputFile Input file to get input from
     * @param outputFile Output file to redirect process output
     */
    private void runCode(String sourceFile, String inputFile, String outputFile, boolean verbose) {
        if (verbose) printToConsole("Running...\nOutput:", FORMAT_NONE, FORMAT_NO_BOLD, FORMAT_ITALIC);;
      
        try{
//            ProcessBuilder pBuilder = new ProcessBuilder(
//                    "E:\\Downloads\\SchoolRelated\\HK2_19-20\\Java\\SchoolCF\\source.exe"
//            );
            
//            String command = "\"/mnt/e/Downloads/SchoolRelated/HK2_19-20/Java/SchoolCF/code/" + sourceFile;
            String command = "\"" + HOME_PATH + sourceFile + ".exe";
            
            if (inputFile != null) command += " < " + inputFile;
            if (outputFile != null) command += " > " + outputFile;

            command += "\"";
            
            ProcessBuilder pBuilder = new ProcessBuilder(
//                    "bash",
//                    "-c",
                    "cmd.exe",
                    "/c",
                    command
            );
            
            pBuilder.redirectErrorStream(true);
            
            this.runningProcess = pBuilder.start();
//            terminateMenuItem.setEnabled(true);
//            consoleTextArea.setEditable(true);
            
            processRunning(true);
            
            // Process Outputstream (our point of view) is stdin from process point of view
//            OutputStream processInput = this.runningProcess.getOutputStream();
            this.processWriter = new BufferedWriter(new OutputStreamWriter(this.runningProcess.getOutputStream()));
            BufferedReader reader = new BufferedReader(new InputStreamReader(this.runningProcess.getInputStream()));
            
            
            String line;
            while (true) {
                line = reader.readLine();
                if (line == null) break;
                if (verbose) printToConsole(line);
            }
            
            if (verbose) printToConsole("---- Program ended ----\n", FORMAT_NONE, FORMAT_BOLD, FORMAT_NO_ITALIC);
        } catch (IOException ex) {
//            System.out.println(ex.toString());
            if (verbose) printToConsole(ex.toString(), FORMAT_ERROR, FORMAT_NO_BOLD, FORMAT_ITALIC);
        }
        
//        this.runningProcess = null;
//        this.processWriter = null;
//        terminateMenuItem.setEnabled(false);
//        consoleTextArea.setEditable(false);

        processRunning(false);
    }
    
    /**
     * Compile test generator, run source code and test code with different test cases generated by test generator
     */
    private void runTest(boolean compiling) {
        
        if (!preserveOutputMenuItem.isSelected()) {
            consoleTextPane.setText("");
            this.consoleString = "";
        }
        
        int numberOfTest = 30;
        printToConsole("Preparing test cases...", FORMAT_NONE, FORMAT_BOLD, FORMAT_NO_ITALIC);
        printToConsole("Number of test cases: " + numberOfTest, FORMAT_NONE, FORMAT_NO_BOLD, FORMAT_NO_ITALIC);
        printToConsole("");
        
        
        processRunning(true);
        
        if (compiling) {
            printToConsole("Compiling test case generator...", FORMAT_WARNING, FORMAT_BOLD, FORMAT_ITALIC);
            if (!compile(EDITOR_FILES[TEST_GEN_INDEX], false)) {
                printToConsole("\nProblem compiling test case generator.", FORMAT_ERROR, FORMAT_NO_BOLD, FORMAT_ITALIC);
                printToConsole("--- ABORTED!!! ---", FORMAT_ERROR, FORMAT_BOLD, FORMAT_NO_ITALIC);
                return;
            }
            
            printToConsole("Compiling source code...", FORMAT_WARNING, FORMAT_BOLD, FORMAT_ITALIC);
            if (!compile(EDITOR_FILES[SOURCE_INDEX], false)) {
                printToConsole("\nProblem compiling source file.", FORMAT_ERROR, FORMAT_NO_BOLD, FORMAT_ITALIC);
                printToConsole("--- ABORTED!!! ---", FORMAT_ERROR, FORMAT_BOLD, FORMAT_NO_ITALIC);
                return;
            }
            
            printToConsole("Compiling test source code...", FORMAT_WARNING, FORMAT_BOLD, FORMAT_ITALIC);
            if (!compile(EDITOR_FILES[TEST_SOURCE_INDEX], false)) {
                printToConsole("\nProblem compiling test source file.", FORMAT_ERROR, FORMAT_NO_BOLD, FORMAT_ITALIC);
                printToConsole("--- ABORTED!!! ---", FORMAT_ERROR, FORMAT_BOLD, FORMAT_NO_ITALIC);
                return;
            }
            
            printToConsole("");
        }

        boolean ok = true;
        
        String testInPath = HOME_PATH + "test_inp";
        String testOutPath = HOME_PATH + "test_out";
        String outPath = HOME_PATH + "out";
        
        for (int i = 1; i <= numberOfTest; i++) {
            runCode(EDITOR_FILES[TEST_GEN_INDEX], null, testInPath, false);
            runCode(EDITOR_FILES[SOURCE_INDEX], testInPath, outPath, false);
            runCode(EDITOR_FILES[TEST_SOURCE_INDEX], testInPath, testOutPath, false);
            
            printToConsole("Running test case " + i + "...", FORMAT_NONE, FORMAT_NO_BOLD, FORMAT_NO_ITALIC);
            
            try{
//                ProcessBuilder pBuilder = new ProcessBuilder(
//                        "bash",
//                        "-c",
//                        "\"cd /mnt/e/Downloads/SchoolRelated/HK2_19-20/Java/SchoolCF/code &&  diff out test_out\"");

                ProcessBuilder pBuilder = new ProcessBuilder(
                        "cmd.exe",
                        "/c",
                        "fc " + testOutPath + " " + outPath + " > " + HOME_PATH + "fc.out" + " || echo err");
                
                pBuilder.redirectErrorStream(true);
                
                this.runningProcess = pBuilder.start();

                BufferedReader reader = new BufferedReader(new InputStreamReader(this.runningProcess.getInputStream()));

                String line;
                while (true) {
                    line = reader.readLine();
                    if (line == null) break;
//                    printToConsole(line);
                    ok = false;
                }
            } catch (IOException ex) {
    //            System.out.println(ex.toString());
                ok = false;
            }
            
            if (!ok) {
                printToConsole("WRONG ANSWER on test " + i + "! (Check input for the wrong test case)", FORMAT_ERROR, FORMAT_NO_BOLD, FORMAT_ITALIC);
                mainTabbedPane.setSelectedIndex(INPUT_INDEX);
                load(testInPath);
                break;
            }
        }
        
        printToConsole("");
        if (ok) printToConsole("ALL TEST CASES PASSED", FORMAT_SUCCESS, FORMAT_BOLD, FORMAT_NO_ITALIC);
        else printToConsole("--- WRONG ANSWER ---", FORMAT_ERROR, FORMAT_BOLD, FORMAT_NO_ITALIC);
        
        
        processRunning(false);
    }
    
    /**
     * Create a thread to compile and run code
     * 
     * @param evt 
     */
    private void compileMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_compileMenuItemActionPerformed
        // TODO add your handling code here:
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                if (compile()) {
//                   runCode();
//                }
//            }
//            
//        }).start();

        new Thread(() -> {
            if (compile(EDITOR_FILES[mainTabbedPane.getSelectedIndex()], true)) {
                runCode(EDITOR_FILES[mainTabbedPane.getSelectedIndex()], null, null, true);
            } else {
                printToConsole("\n--- ABORTED ----\n", FORMAT_ERROR, FORMAT_BOLD, FORMAT_NO_ITALIC);
            }
        }).start();

    }//GEN-LAST:event_compileMenuItemActionPerformed

    private void saveMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveMenuItemActionPerformed
        // TODO add your handling code here:
        save(HOME_PATH + this.EDITOR_FILES[mainTabbedPane.getSelectedIndex()]);
    }//GEN-LAST:event_saveMenuItemActionPerformed

    private void loginBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loginBtnActionPerformed
        // TODO add your handling code here:
        
        new Thread(() -> {
            loginBtn.setText("Logging in...");
            loginBtn.setEnabled(false);
            
            if (user.login(handleField.getText(), new String(passwordField.getPassword()))) {
                welcomeLabel.setText("Welcome " + user.getHandle() + "\n");
                cfDetailPanel.setVisible(true);
                cfLoginPanel.setVisible(false);
            } else {
                printToConsole("Login failed!", FORMAT_ERROR, FORMAT_BOLD, FORMAT_NO_ITALIC);
                printToConsole("Wrong handle/email or password?\n", FORMAT_ERROR, FORMAT_NO_BOLD, FORMAT_ITALIC);
            }
            
            loginBtn.setText("Log in");
            loginBtn.setEnabled(true);
        }).start();
    }//GEN-LAST:event_loginBtnActionPerformed

    private void logoutBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logoutBtnActionPerformed
        // TODO add your handling code here:
        user = new UserSession();
        cfDetailPanel.setVisible(false);
        cfLoginPanel.setVisible(true);
        passwordField.setText("");
    }//GEN-LAST:event_logoutBtnActionPerformed

    private void statusBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_statusBtnActionPerformed
        // TODO add your handling code here:
        new Thread(new Runnable() {
            @Override
            public void run() {
                submitBtn.setText("Waiting...");
                submitBtn.setEnabled(false);
                statusBtn.setEnabled(false);
                
                verdictLabel.setText("FETCHING...");
                verdictLabel.setText(user.getLastestSubmissionVerdict());
                
                submitBtn.setEnabled(true);
                submitBtn.setText("Submit");
                statusBtn.setEnabled(true);
            }
            
        }).start();
    }//GEN-LAST:event_statusBtnActionPerformed

    private void submitBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_submitBtnActionPerformed
        // TODO add your handling code here:
        
        new Thread(new Runnable() {
            @Override
            public void run() {
                verdictLabel.setText("FETCHING...");
                submitBtn.setText("Waiting...");
                submitBtn.setEnabled(false);
                statusBtn.setEnabled(false);
                
                if (user.submit(problemCodeField.getText(), sourceEditorPane.getText())) {
                    printToConsole("Solution for problem " + problemCodeField.getText() + " was submitted", FORMAT_SUCCESS, FORMAT_NO_BOLD, FORMAT_ITALIC);
                    for (int i = 0; i < 5; i++) {
                        try {
                            Thread.sleep(2000);
                        } catch (InterruptedException ex) {
                            System.out.println(ex.toString());
                        }
                        verdictLabel.setText(user.getLastestSubmissionVerdict());
                    }
                } else {
                    printToConsole("Submission failed!", FORMAT_ERROR, FORMAT_NO_BOLD, FORMAT_ITALIC);
                    verdictLabel.setText("CANNOT SUBMIT");
                }
                
                submitBtn.setEnabled(true);
                submitBtn.setText("Submit");
                statusBtn.setEnabled(true);
            }
            
        }).start();
    }//GEN-LAST:event_submitBtnActionPerformed

    private void terminateMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_terminateMenuItemActionPerformed
        // TODO add your handling code here:
        this.runningProcess.destroy();
        processRunning(false);

        printToConsole("\n---- PROCESS TERMINATED ----\n", FORMAT_NONE, FORMAT_BOLD, FORMAT_NO_ITALIC);
    }//GEN-LAST:event_terminateMenuItemActionPerformed

    private void saveAsMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveAsMenuItemActionPerformed
        // TODO add your handling code here:
        JFileChooser fileChooser = new JFileChooser("C:\\Users\\KV\\Desktop");
        int choice = fileChooser.showSaveDialog(null);
        if (choice == JFileChooser.APPROVE_OPTION) {
            save(fileChooser.getSelectedFile().getAbsolutePath());
        }
    }//GEN-LAST:event_saveAsMenuItemActionPerformed

    private void loadFileMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loadFileMenuItemActionPerformed
        // TODO add your handling code here:
        JFileChooser fileChooser = new JFileChooser("C:\\Users\\KV\\Desktop");
        int choice = fileChooser.showOpenDialog(null);
        if (choice == JFileChooser.APPROVE_OPTION) {
            load(fileChooser.getSelectedFile().getAbsolutePath());
        }
    }//GEN-LAST:event_loadFileMenuItemActionPerformed

    private void consoleTextPaneKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_consoleTextPaneKeyPressed
        // TODO add your handling code here:
        switch(evt.getKeyCode()) {
            case KeyEvent.VK_ENTER:
                try {
                    if (this.runningProcess != null) {
                        this.processWriter.write(consoleTextPane.getText().substring(this.consoleString.length()));
                        this.processWriter.newLine();
                        this.processWriter.flush();
                    }
                } catch (IOException ex) {
                    printToConsole("Problem sending input to console: \n" + ex.toString(), FORMAT_WARNING, FORMAT_NO_BOLD, FORMAT_ITALIC);
                }

                this.consoleString = consoleTextPane.getText();
                break;
            case KeyEvent.VK_BACK_SPACE:
                if (this.consoleString.length() >= consoleTextPane.getText().length()) {
                    consoleTextPane.setText(consoleTextPane.getText() + 'a');
                }
                break;
            case KeyEvent.VK_DELETE:
                // NOT YET IMPLEMENTED
                if (consoleTextPane.getCaretPosition() < this.consoleString.length());
                break;
            default:
                break;
        }
    }//GEN-LAST:event_consoleTextPaneKeyPressed

    private void consoleClearBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_consoleClearBtnActionPerformed
        // TODO add your handling code here:
        consoleTextPane.setText("");
        this.consoleString = "";
    }//GEN-LAST:event_consoleClearBtnActionPerformed

    private void compileInputMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_compileInputMenuItemActionPerformed
        // TODO add your handling code here:
        new Thread(() -> {
            if (compile(EDITOR_FILES[mainTabbedPane.getSelectedIndex()], true)) {
                runCode(EDITOR_FILES[mainTabbedPane.getSelectedIndex()], EDITOR_FILES[INPUT_INDEX], null, true);
            } else {
                printToConsole("\n--- ABORTED ----\n", FORMAT_ERROR, FORMAT_BOLD, FORMAT_NO_ITALIC);
            }
        }).start();
    }//GEN-LAST:event_compileInputMenuItemActionPerformed

    private void exitMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitMenuItemActionPerformed
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_exitMenuItemActionPerformed

    private void runTestMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_runTestMenuItemActionPerformed
        // TODO add your handling code here:
        new Thread(()->{
            runTest(false);
        }).start();
    }//GEN-LAST:event_runTestMenuItemActionPerformed

    private void runMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_runMenuItemActionPerformed
        // TODO add your handling code here:
        new Thread(() -> {
            runCode(EDITOR_FILES[mainTabbedPane.getSelectedIndex()], null, null, true);
        }).start();
    }//GEN-LAST:event_runMenuItemActionPerformed

    private void compileTestMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_compileTestMenuItemActionPerformed
        // TODO add your handling code here:
        new Thread(()->{
            runTest(true);
        }).start();
    }//GEN-LAST:event_compileTestMenuItemActionPerformed

    private void runInputMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_runInputMenuItemActionPerformed
        // TODO add your handling code here:
        new Thread(() -> {
            runCode(EDITOR_FILES[mainTabbedPane.getSelectedIndex()], EDITOR_FILES[INPUT_INDEX], null, true);
        }).start();
    }//GEN-LAST:event_runInputMenuItemActionPerformed

    private void loadTemplateMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loadTemplateMenuItemActionPerformed
        // TODO add your handling code here:
        load(HOME_PATH + EDITOR_FILES[TEMPLATE_INDEX] + ".cpp");
    }//GEN-LAST:event_loadTemplateMenuItemActionPerformed

    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }

            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(SchoolCF.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SchoolCF.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SchoolCF.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SchoolCF.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SchoolCF().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel cfDetailPanel;
    private javax.swing.JPanel cfLoginPanel;
    private javax.swing.JPanel cfPanel;
    private javax.swing.JMenuItem compileInputMenuItem;
    private javax.swing.JMenuItem compileMenuItem;
    private javax.swing.JMenuItem compileTestMenuItem;
    private javax.swing.JMenuItem consoleClearBtn;
    private javax.swing.JMenu consolePreserveOutput;
    private javax.swing.JScrollPane consoleScrollPane;
    private javax.swing.JTextPane consoleTextPane;
    private javax.swing.JMenuItem exitMenuItem;
    private javax.swing.JMenu fileMenu;
    private javax.swing.JTextField handleField;
    private javax.swing.JEditorPane inputEditorPane;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JPopupMenu.Separator jSeparator2;
    private javax.swing.JPopupMenu.Separator jSeparator3;
    private javax.swing.JMenuItem loadFileMenuItem;
    private javax.swing.JMenuItem loadTemplateMenuItem;
    private javax.swing.JButton loginBtn;
    private javax.swing.JButton logoutBtn;
    private javax.swing.JSplitPane mainSplitPane;
    private javax.swing.JTabbedPane mainTabbedPane;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JPasswordField passwordField;
    private javax.swing.JCheckBoxMenuItem preserveOutputMenuItem;
    private javax.swing.JTextField problemCodeField;
    private javax.swing.JMenuItem runInputMenuItem;
    private javax.swing.JMenuItem runMenuItem;
    private javax.swing.JMenuItem runTestMenuItem;
    private javax.swing.JMenuItem saveAsMenuItem;
    private javax.swing.JMenuItem saveMenuItem;
    private javax.swing.JSplitPane sideSplitPane;
    private javax.swing.JEditorPane sourceEditorPane;
    private javax.swing.JButton statusBtn;
    private javax.swing.JButton submitBtn;
    private javax.swing.JMenuItem terminateMenuItem;
    private javax.swing.JEditorPane testGenEditorPane;
    private javax.swing.JEditorPane testSourceEditorPane;
    private javax.swing.JLabel verdictLabel;
    private javax.swing.JLabel welcomeLabel;
    // End of variables declaration//GEN-END:variables
}
