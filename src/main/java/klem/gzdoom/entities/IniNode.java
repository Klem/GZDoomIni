package klem.gzdoom.entities;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;

/**
 GZDoomIni
 <p/>
 Author : Klem Date   : 23/03/14 Time   : 11:36 */
public class IniNode {
    private String name;
    private String value;
    private int lineNumber;
    private String type;
    private IniNode parent = null;
    public static final String SEPARATOR ="__";
    private LinkedHashMap<String, IniNode> children = new LinkedHashMap<String, IniNode>();


    public IniNode(String name, String value, int lineNumber, String type) {
        this.name = name + SEPARATOR + lineNumber;
        this.value = value;
        this.lineNumber = lineNumber;
        this.type = type;
    }

    /**
     Return a keyset iterator of the children
     @return Iterator<String>
     */
     public Iterator<String> iterator() {
         return children.keySet().iterator();
     }

    public IniNode addChild(IniNode child) {
       child.setParent(this);
       children.put(child.getName(), child);
       return child;
    }


    public IniNode getChild(String name) {
        return children.get(name);
    }

    /**
     Return the technical name
     @return
     */
    public String getName() {
        return name;
    }

    /**
     Return the name as it, is in the file
     @return
     */
    public String getFunctionalName() {
        return name.split(SEPARATOR)[0];
    }

    /**
     Return the name as it, is in the file
     @return
     */
    public String getValue() {
        return value;
    }

    public void setName(String name) {
        this.name = name;
    }



    public void setValue(String value) {
        this.value = value;
    }

    public int getLineNumber() {
        return lineNumber;
    }

    public void setLineNumber(int lineNumber) {
        this.lineNumber = lineNumber;
    }

    public HashMap<String, IniNode> getChildren() {
        return children;
    }

    public IniNode getParent() {
        return parent;
    }

    public void setParent(IniNode parent) {
        this.parent = parent;
    }

    public boolean hasChildren() {
        return children.size() != 0;
    }

    public boolean hasParent() {
        return parent != null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        IniNode iniNode = (IniNode) o;

        if (!name.equals(iniNode.name)) {
            return false;
        }

        return true;
    }

    public boolean isCommented() {
        return name.startsWith("#");
    }

    public boolean isOfType(String type) {
        return this.type.equals(type);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    @Override
    public String toString() {

       String  p = this.hasParent() ? "{"+ parent.getName()+", "+ parent.getValue() +"}" : "{}" ;
       String spacer =  this.hasParent() ? "     " : "";
       String s= ""+name+"{     " +
                "\n      value=" + value +
                "\n      lineNumber=" + lineNumber +
                "\n      parent=" + p +
                "\n  }\n";
        return s;
    }
}
