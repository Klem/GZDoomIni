package klem.gzdoom.app;

import klem.gzdoom.entities.*;
import klem.gzdoom.utils.Constants;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 GZDoomIni
 <p/>
 Author : Klem Date   : 22/03/14 Time   : 12:44 */
public class IniEngine {

    public static String PARAM_PATH = "Path";
    public static String PARAM_GL = "gl";
    public static String COMMENT_CHAR = "# ";
    public static String NODE_CHAR = "[";

    public static IniDOM parse(String iniFilePath) {
        String fullPath = Constants.CWD + iniFilePath;
        System.out.println("Reading ini... "+fullPath);
        StringBuilder output = new StringBuilder();
        IniNode root = new IniNode("root", "", 0, "root");
        IniDOM dom = new IniDOM(root);
        IniNode currentNode = null;

        try {
            // open the stream and put it into BufferedReader
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(fullPath)));

            String line;
            int lineNumber = 1;
            // PARSING LOOP
            while ((line = br.readLine()) != null) {
                   // System.out.println("Current Node " + currentNode);
                    if(line.startsWith(NODE_CHAR)) {

                        currentNode = new IniNode(IniDOM.NODE_ENTRY, line, lineNumber, IniDOM.NODE_ENTRY);
                        root.addChild(currentNode);
                    }

                    else if(line.startsWith(COMMENT_CHAR)) {
                    //    System.out.println("  Found comment " + line);
                       //SAVE THE LOCATION SO WE DO NOT LOOSE THEM WHEN SAVING
                        currentNode = new IniNode (IniDOM.NODE_COMMENT, line, lineNumber, IniDOM.NODE_COMMENT);
                        root.addChild(currentNode);
                    }
                    else if(line.contains(PARAM_PATH)) {
                    //    System.out.println("  Found path " + line);
                        String param = extractParamFromLine(line);
                        String value = extractValueFromLine(line);
                        IniNode added = currentNode.addChild( new IniNode(param, value, lineNumber, IniDOM.NODE_PATH));
                       // System.out.println("   Added " + added);
                    }
                    else if(line.startsWith(PARAM_GL)) {
                    //    System.out.println("  Found GL " + line);
                        String param = extractParamFromLine(line);
                        String value = extractValueFromLine(line);
                        currentNode.addChild(new IniNode(param, value, lineNumber, IniDOM.NODE_PARAMGL));

                    }
                    else if(line.isEmpty()) {
                        // NOTHING TO DO, LAEVE THIS FOR
                        // BETTER UNDERSTANDING OF ALL
                        // POSSIBLE PARSING SITUATION
                        root.addChild(new IniNode(IniDOM.NODE_EMPTY, line, lineNumber, IniDOM.NODE_EMPTY));
                        // EVERY OTHER LINE
                    } else {
//                        System.out.println("Wierd line "+line);
                        String param = extractParamFromLine(line);
                        String value = extractValueFromLine(line);
                        currentNode.addChild(new IniNode(param, value, lineNumber, IniDOM.NODE_UKNOWN));
                    }

                  lineNumber++;

                }
            br.close();


        } catch (IOException e) {
            e.printStackTrace();
        }

        return dom;
    }


    /**
     Extract the value after "="
     @param line
     @return
     */
    private static String extractValueFromLine(String line) {
        // VALUE MAY BE EMPTY
        try {
            return line.split("=")[1];
        } catch(ArrayIndexOutOfBoundsException aioobe) {
            return "";
        }

    }

    /**
     Extract the param before "="
     @param line
     @return
     */
    private static String extractParamFromLine(String line) {
        return line.split("=")[0];
    }

}
