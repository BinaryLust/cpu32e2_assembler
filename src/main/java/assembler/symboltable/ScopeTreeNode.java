

package assembler.symboltable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class ScopeTreeNode
{
    public  static HashMap<Integer, ScopeTreeNode> nodeTable = new HashMap<>(); // contains links to all scope tree nodes
    
    private String              name;
    private ScopeTreeNode       parent;
    private List<ScopeTreeNode> children;
    
    
    public ScopeTreeNode(String name, ScopeTreeNode parent)
    {
        this.name     = name;
        this.parent   = parent;
        children      = new ArrayList<>();
    }
    
    
    public ScopeTreeNode addChild(String name, Integer tag)
    {
        if(nodeTable.containsKey(tag))                             // if the global node table already has this entry
        {
            ScopeTreeNode oldNode = nodeTable.get(tag);            // return the old node
            return oldNode;
        }
        else                                                       // else
        {
            ScopeTreeNode newNode = new ScopeTreeNode(name, this); // create a new node
            nodeTable.put(tag, newNode);                           // add it to the global node table
            children.add(newNode);                                 // add as child node to this node
            return newNode;
        }
    }
    
    
    public String getPath()
    {
        if(parent != null)
            return parent.getPath() + "." + name;    // recursively walk the tree backwards from the current node
        else
            return name;                             // if we are at the root node then stop
    }
}

