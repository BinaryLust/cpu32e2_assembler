

package assembler.symboltable;

import java.util.HashMap;
import java.util.LinkedList;


public class SymbolTable
{
    private HashMap<String, Entry>          symbolTable;
    private int                             currentScope;
    private int                             topScope;
    private LinkedList<Integer>             scopeStack;
    private ScopeTreeNode                   scopeTree;
    
    
    // constructor
    public SymbolTable()
    {
        symbolTable  = new HashMap<>();
        currentScope = 0;
        topScope     = 0;
        scopeStack   = new LinkedList<>();
        scopeStack.addFirst(0);                         // start with scope 0 (global scope)
        scopeTree = new ScopeTreeNode("globals", null); // create the first node of the scope tree
        ScopeTreeNode.nodeTable.put(0, scopeTree);      // add this node to the global node table
        System.out.println("Entering scope: " + ScopeTreeNode.nodeTable.get(currentScope).getPath());
    }
    
    
    public void resetScope()
    {
        currentScope = 0;
        topScope     = 0;
    }
    
    
    public void enterScope(String name)
    {
        topScope++;                           // increment the top scope level
        currentScope = topScope;
        scopeStack.addFirst(topScope);        // push the new scope level on to the stack
        scopeTree = scopeTree.addChild(name, topScope);
        System.out.println("Entering scope: " + ScopeTreeNode.nodeTable.get(currentScope).getPath());
    }
    
    
    public void exitScope()
    {
        scopeStack.removeFirst();             // pop the previous scope level off the stack
        currentScope = scopeStack.getFirst(); // set current scope to the top of the stack
        scopeTree = ScopeTreeNode.nodeTable.get(currentScope);
        System.out.println("Entering scope: " + ScopeTreeNode.nodeTable.get(currentScope).getPath());
    }
    
    
    public void dumpTable() // for debugging
    {
        symbolTable.entrySet().stream().forEach((i) -> {
            //System.out.println("Key: " + i.getKey() + "," + i.getValue().toString()); // print out all entrys of the current symbol table
            System.out.println(i.getValue().toString()); // print out all entrys of the current symbol table
        });
    }
    
    
    public void dumpScopeTree() // for debugging
    {
        ScopeTreeNode.nodeTable.entrySet().stream().forEach((i) -> {
            System.out.println(i.getValue().getPath());
        });
        
    }
    
    public boolean insertSymbol(String name, SymbolType type, long value)
    {
        String key = name + "[" + currentScope + "]";
        
        if(symbolTable.containsKey(key)) // check if the current scope contains the symbol name already
        {
            //System.out.println("already contained key!");
            return false;                 // if it does return false, and don't add anything
        }
        else
        {
            System.out.println("Insert: " + key);
            
            Entry entry = new Entry(name, currentScope, type, value);
            symbolTable.put(key, entry); // if it doesn't add the symbol entry and return true
            return true;
        }
    }
    
    
    public Entry localSearch(String name)
    {
        String key = name + "[" + currentScope + "]";
        
        if(symbolTable.containsKey(key))
            return symbolTable.get(key); // if we get a match return the entry
        else
            return null;                       // else return null
    }
    
    
    public Entry globalSearch(String name)
    {
        for(Integer scope: scopeStack)
        {
            String key = name + "[" + scope + "]";
            
            System.out.println("Search: " + key);
            
            if(symbolTable.containsKey(key))
                return symbolTable.get(key); // if we get a match return the entry
        }
        
        return null;                          // else return null
    }
    
}


/*public class SymbolTable
{

    private LinkedList<HashMap<String, BaseEntry>> scope; // each entry in this list is a symbol table for a different scope
    
    
    // constructor
    public SymbolTable()
    {
        scope = new LinkedList<>();      // create the scope array list
        scope.addFirst(new HashMap<>()); // create the first symbol table for global scope
    }
    
    
    public void enterScope()
    {
        scope.addFirst(new HashMap<>()); // add a new symbol table at a new scope level
    }
    
    
    public void exitScope()
    {
        scope.removeFirst();
    }
    
    
    public void dumpCurrentScope() // for debugging
    {
        HashMap<String, BaseEntry> symbolTable = scope.getFirst(); // get current scope
        
        symbolTable.entrySet().stream().forEach((i) -> {
            System.out.println("Name: " + i.getKey() + "," + i.getValue().toString()); // print out all entrys of the current symbol table
        });
    }
    
    
    public boolean insertSymbol(String name, BaseEntry entry)
    {
        HashMap<String, BaseEntry> symbolTable = scope.getFirst(); // get current scope
        
        if(symbolTable.containsKey(name)) // check if the current scope contains the symbol name already
        {
            //System.out.println("already contained key!");
            return false;                 // if it does return false, and don't add anything
        }
        else
        {
            //System.out.println("adding key!");
            symbolTable.put(name, entry); // if it doesn't add the symbol entry and return true
            return true;
        }
    }
    
    
    public BaseEntry localSearch(String name)
    {
        HashMap<String, BaseEntry> symbolTable = scope.getFirst(); // get current scope
        
        if(symbolTable.containsKey(name))
            return symbolTable.get(name); // if we get a match return the entry
        else
            return null;                  // else return null
    }
    
    
    public BaseEntry globalSearch(String name)
    {
        for(HashMap<String, BaseEntry> symbolTable: scope)
        {
            if(symbolTable.containsKey(name))
                return symbolTable.get(name); // if we get a match return the entry
        }
        
        return null;                          // else return null
    }
    
}*/
