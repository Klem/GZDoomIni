package klem.gzdoom.app;

import klem.gzdoom.entities.IniDOM;
import klem.gzdoom.entities.IniNode;
import klem.gzdoom.utils.Config;
import klem.gzdoom.utils.Constants;
import klem.gzdoom.utils.Tools;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.JTextComponent;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.Iterator;
import java.util.LinkedHashMap;

/**
 GZDoomIni
 <p/>
 Author : Klem Date   : 22/03/14 Time   : 11:02 */
public class GZDoomIni extends javax.swing.JFrame {

    /**
     * Variable de classes, accessible par toutes les m�thodes (fonctions) Le
     * private indique que seule les methodes ce cette classe peuvent y avoir
     * acc�s
     */
    private JMenuBar menuBar;
    private JMenu jMenu;
    private JMenu saveFrequencyMenu;
    private JMenuItem preferencesMenu;
    private JMenuItem exitMenuItem;
    private JMenuItem saveMenuItem;

    private JPanel rawPanel;
    private JPanel guiFlatPanel;
    private JPanel guiTreePanel;
    private static JTextArea textContainer;
    private static JTextArea console;
    private boolean firstRun = true;
    private JMenu aboutMenu;
    private JMenuItem releaseNotesMenuItem;
    private JMenuItem licenseNotesMenuItem;
    private String DOOM_INI_CONTENT;
    private IniDOM INI_DOM;

    public static Config config;

    public static URL url;

    /**
     * Point d'entr�e du programme
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                GZDoomIni app = new GZDoomIni();
                app.setLocationRelativeTo(null);
                app.setTitle("Klem's GZDoom Ini Frontend");
                app.setVisible(true);
            }
        });
    }

    /**
     * Constructeur
     */
    public GZDoomIni() {
        super();
        System.out.println("Launching " + this.getClass().getName() + "...");
        // MAKE DYNAMIC
        DOOM_INI_CONTENT = Tools.getContentAsString(Constants.FILENAME_DOOM_INI);
        INI_DOM = IniEngine.parse(Constants.FILENAME_DOOM_INI);

       // initProperties();
        initGUI();

        System.out.println("Application initialized...");
    }

    /**
     Create the GUI
     */
    private void initGUI() {
        try {
            // PARENT FRAME PROPERTIES
            setSize(800, 600);
            setResizable(true);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            // INI CONTENT PANEL
            rawPanel = new JPanel();
            initRawPanel();

            //FLAT FORM CONTENT PANEL
            guiFlatPanel = new JPanel();
            initGuiFlatPanel();

            //FLAT FORM CONTENT PANEL
            guiTreePanel = new JPanel();
            initGuiTreePanel();

            // TABS
            JTabbedPane jtp = new JTabbedPane();
            // AFFECT EACH PANEL TO A TAB
            jtp.addTab("Raw", rawPanel);
            jtp.addTab("Flat", guiFlatPanel);
            jtp.addTab("Tree", guiTreePanel);

            setContentPane(jtp);

            System.out.println("Initializing GUI components...");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     Initialize the raw view panel, from file loading to formatting...
     */
    private void initRawPanel() {
        int   cols = 27;

        textContainer = new JTextArea(cols, 95);
        textContainer.setForeground(Color.GREEN);
        textContainer.setBackground(Color.BLACK);
        textContainer.setLineWrap(true);
        textContainer.setWrapStyleWord(true);
        textContainer.setEditable(false);
        textContainer.setFont(new Font("Monospaced", Font.PLAIN, 14));
        textContainer.setSize(760, 560);
        textContainer.setText(DOOM_INI_CONTENT);

        // Ajout de la textarea au scrolling
        JScrollPane textScroll = new JScrollPane(textContainer);
        textScroll.setSize(780, 580);
        textScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        // ajout du scrolling aun panel
        rawPanel.add(textScroll);
        rawPanel.setLayout(new FlowLayout());
        rawPanel.setBackground(Color.BLACK);
    }

    /**
     Generates the flat form from the iniFile
     */
    private void initGuiFlatPanel() {
        guiFlatPanel.setLayout(new GridLayout(0,1));
        guiFlatPanel.setBackground(Color.GRAY);


        LinkedHashMap<String,IniNode> entryNodes = INI_DOM.getPathNodes();
        Iterator<String> itPath = entryNodes.keySet().iterator();
        JCheckBox box = null;
        while(itPath.hasNext()) {
            String next = itPath.next();
            System.out.println("CURRENT GUI KEY "+next);
            IniNode node = entryNodes.get(next);
            box = new JCheckBox(node.getValue());
            box.setSelected(true);
           guiFlatPanel.add(box);

        }
    }

    /**
     Generates the flat form from the iniFile
     */
    private void initGuiTreePanel() {
        guiTreePanel.setLayout(new GridLayout());
        guiTreePanel.setBackground(Color.GRAY);
    }



    private void initProperties() {
        config = new Config();
        config.printProperties();
    }

    /**
     * LE menu d'action
     */
    private void createMenus() {
        // Barre de menu
        menuBar = new JMenuBar();
        setJMenuBar(menuBar);
        {


            {
                preferencesMenu = new JMenu("Preferences");
                JMenuItem blacklistUser = new JMenuItem("Utilisateur");

            }
            {
                // saveFrequencyMenu = new JMenu("AutoSave");
                // JCheckBoxMenuItem realtimeMenuItem = new JCheckBoxMenuItem(
                // "Temps r�el");
                // JCheckBoxMenuItem messagesMenuItem50 = new JCheckBoxMenuItem(
                // "50");
                // JCheckBoxMenuItem messagesMenuItem100 = new
                // JCheckBoxMenuItem(
                // "100");
                // JCheckBoxMenuItem messagesMenuItem250 = new
                // JCheckBoxMenuItem(
                // "250");
                // JCheckBoxMenuItem messagesMenuItem500 = new
                // JCheckBoxMenuItem(
                // "500");
                // JCheckBoxMenuItem messagesMenuItem1000 = new
                // JCheckBoxMenuItem(
                // "1000");
                // JCheckBoxMenuItem messagesMenuItem2500 = new
                // JCheckBoxMenuItem(
                // "2500");
                // saveFrequencyMenu.add(realtimeMenuItem);
                // saveFrequencyMenu.add(messagesMenuItem50);
                // saveFrequencyMenu.add(messagesMenuItem100);
                // saveFrequencyMenu.add(messagesMenuItem250);
                // saveFrequencyMenu.add(messagesMenuItem500);
                // saveFrequencyMenu.add(messagesMenuItem1000);
                // saveFrequencyMenu.add(messagesMenuItem2500);
                //
                // preferencesMenu.add(saveFrequencyMenu);
                //
                // menuBar.add(preferencesMenu);

            }
            {
                saveMenuItem = new JMenuItem();
                saveMenuItem.addActionListener(new Save());
                saveMenuItem.setText("Enregister");
                menuBar.add(saveMenuItem);
            }

            {
                aboutMenu = new JMenu("About");
                licenseNotesMenuItem = new JMenuItem("License");
                licenseNotesMenuItem.addActionListener(new TextfilePopup("license.txt"));
                releaseNotesMenuItem = new JMenuItem("Release notes");
                releaseNotesMenuItem.addActionListener(new TextfilePopup("release-notes.txt"));
                aboutMenu.add(licenseNotesMenuItem);
                aboutMenu.add(releaseNotesMenuItem);
                menuBar.add(aboutMenu);
            }

            {
                exitMenuItem = new JMenuItem("Quitter");
                exitMenuItem.addActionListener(new ExitApp());
                menuBar.add(exitMenuItem);
            }
        }
    }




    /**
     * Save to file
     *
     * @author Big Enregistrement vers un fichier Action invoqu�e lors du clic
     *         sur "enregistrer"
     */
    class Save implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            final JFileChooser fileChooser = new JFileChooser();
            // Affiche la boite de s�l�ction
            // et v�rifie que c'est bien le bouton "ok" qui � �t� cliqu�
            if (fileChooser.showSaveDialog(GZDoomIni.this) == JFileChooser.APPROVE_OPTION) {
                // Thread pour �crire dans le fichier
                // Sers � ce que l'interface graphique ne soit pas fig�e
                // le temps que le fichier soit acc�d� + �crit durant des
                // op�ration longues
                // PAr ex enregistrer 2500 messages sur un lecteur r�sau
                Thread writeToFile = new Thread() {
                    public void run() {
                        try {
                            // OUvre le fichier
                            File file = fileChooser.getSelectedFile();
                            FileWriter fstream = new FileWriter(file);
                            BufferedWriter out = new BufferedWriter(fstream);
                            // Ecrit ce qui a été intercept�
                            // Pas de controle de validit�. Si rien n'as �t�
                            // intercept�
                            // cr�era un fichier vide
                            // TODO manage empty files
                            out.write(textContainer.getText(0, textContainer.getDocument().getLength()));
                            out.close();
                        } catch (IOException e1) {
                            // TODO Auto-generated catch block
                            e1.printStackTrace();
                        } catch (BadLocationException e2) {
                            // TODO Auto-generated catch block
                            e2.printStackTrace();
                        }
                    }
                };
                // Lancement du Thread
                writeToFile.run();
            }
        }
    }

    /**
     * Action invoqués lors du clic sur "a propos" Prend en paramètre le nom du
     * fichier a afficher
     *
     * @author e402685
     *
     */
    class TextfilePopup implements ActionListener {

        private String fileToDisplay;

        public TextfilePopup(String fileToDisplay) {
            this.fileToDisplay = fileToDisplay;
        }

        private TextfilePopup() {
        }

        @Override
        public void actionPerformed(ActionEvent e) {

            JOptionPane.showMessageDialog(null, Tools.getFileContentAsString(fileToDisplay), fileToDisplay,
                    JOptionPane.INFORMATION_MESSAGE);
        }

    }

    /**
     * Action invoqués lors du clic sur "quitter"
     *
     * @author e402685
     *
     */
    class ExitApp implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("Exiting.....");

            // c'est cracra de faire ca (ca �quivaut a eteindre ton PC direct �
            // l'alim)
            // Il faudrai lib�rer les resources, fermer les connection etc etc.
            // ici on se l'autorise ca c'est une appli monoutilisateur mono
            // poste doc pas de risque
            // que des resources soient v�rouill�es par d'autres personnes
            // (fichier de sauvgarde)
            // ou que le nombre de connection monte en fleche,
            // saturant le serveur
            System.exit(0);
        }
    }

}