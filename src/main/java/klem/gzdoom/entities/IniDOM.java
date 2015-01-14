package klem.gzdoom.entities;

import java.util.Iterator;
import java.util.LinkedHashMap;

/**
 GZDoomIni
 <p/>
 Author : Klem Date   : 23/03/14 Time   : 11:35 */
public class IniDOM {

    private IniNode root;
    public static final String NODE_ENTRY = "entry";
    public static final String NODE_COMMENT = "comment";
    public static final String NODE_EMPTY = "empty";
    public static final String NODE_PATH = "Path";
    public static final String NODE_PARAMGL = "gl";
    public static final String NODE_UKNOWN ="unkonwn";


    public IniDOM(IniNode root) {
        this.root = root;
    }

    public LinkedHashMap<String, IniNode> getPathNodes() {
        LinkedHashMap<String, IniNode> result = new LinkedHashMap<String, IniNode>();

        findNodesOfType(root, NODE_PATH, result);

        return result;
    }

    public Iterator<String> getPathNodesIterator() {
        LinkedHashMap<String, IniNode> result = new LinkedHashMap<String, IniNode>();

       findNodesOfType(root, NODE_PATH, result);

        return result.keySet().iterator();
    }

    public LinkedHashMap<String, IniNode> getEntryNodes() {
        LinkedHashMap<String, IniNode> result = new LinkedHashMap<String, IniNode>();

        findNodesOfType(root, NODE_ENTRY, result);

        return result;
    }
    int i = 0;
    public IniNode getNode(String name) {
        i = 0;
        System.out.print("Looking for "+name);
        IniNode result = findNodeWithKey(root, name);
        System.out.print("Found "+result);
        return result;
    }

    public Iterator<String> getEntryNodesIterator() {
        LinkedHashMap<String, IniNode> result = new LinkedHashMap<String, IniNode>();

        findNodesOfType(root, NODE_ENTRY, result);

        return result.keySet().iterator();
    }

    public LinkedHashMap<String, IniNode> geCommentNodes() {
        LinkedHashMap<String, IniNode> result = new LinkedHashMap<String, IniNode>();

        findNodesOfType(root, NODE_COMMENT, result);

        return result;
    }

    public LinkedHashMap<String, IniNode> geEmptyNodes() {
        LinkedHashMap<String, IniNode> result = new LinkedHashMap<String, IniNode>();

        findNodesOfType(root, NODE_EMPTY, result);

        return result;
    }

    @Override
    public String toString() {

        StringBuffer sb = new StringBuffer("IniDom {+\n");

        readChildren(sb, root);

        sb.append("}\n");

        return sb.toString();
    }

    private LinkedHashMap<String, IniNode> findNodesOfType(IniNode root, String nodeType, LinkedHashMap<String, IniNode> result) {
        Iterator<String> it = root.iterator();
        while (it.hasNext()) {
            String key = it.next();
            IniNode n = root.getChild(key);

            if (n.isOfType(nodeType)) {
                result.put(n.getName(), n);
               // System.out.println("Found Path" + n);
            }

            if (n.hasChildren()) {
                findNodesOfType(n, nodeType, result);
            }
        }

        return result;
    }

    private IniNode findNodeWithKey(IniNode root, String key) {
        Iterator<String> it = root.iterator();
        IniNode result = null;
        int i=0;
        i++ ;
        while (it.hasNext()) {
            String s = it.next();
            IniNode n = root.getChild(s);

            System.out.println("=============in "+n.getValue()+"============");

            if (n.getName().equals(key)) {
                result = n;
                System.out.println("    found "+n+" exiting...");
                return result;
            }
            System.out.println("---------------");
            if (n.hasChildren()) {
                System.out.println("   has children");
                findNodeWithKey(n, key);
            } else {
                System.out.println("   no children for node "+n.getName());
            }
        }
        return result;
    }

    private void readChildren(StringBuffer sb, IniNode root) {

        Iterator<String> it = root.iterator();

        while (it.hasNext()) {
            String key = it.next();
            IniNode n = root.getChild(key);
            sb.append(n.toString());


            if (n.hasChildren()) {
                readChildren(sb, n);
            }
        }
    }

}
