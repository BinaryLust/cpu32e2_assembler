

package assembler.symboltable;


public class Entry {
    
    public String     name;
    public int        scope;
    public SymbolType type;
    public long       value;
    
    
    // constructor
    public Entry(String name, int scope, SymbolType type, long value)
    {
        this.name  = name;
        this.scope = scope;
        this.type  = type;
        this.value = value;
    }
    
    
    @Override
    public String toString()
    {
        String str = "";
        String Scope = ScopeTreeNode.nodeTable.get(scope).getPath();
        
        switch(type)
        {
            case CODELABEL:
            case DATALABEL:
            case FUNCTION:  str = String.format("Name: %-32s  Type: %-16s  Address: %-16d  Scope: %s", name, type, value, Scope); break;
            case CONSTANT:  str = String.format("Name: %-32s  Type: %-16s  Value:   %-16d  Scope: %s", name, type, value, Scope); break;
        }
        
        return str;
    }
    
    
    /*@Override
    public int hashCode()
    {
        return (name + "[" + scope + "]").hashCode();
    }

    
    @Override
    public boolean equals(Object obj)
    {
        if(this == obj)
            return true;
        
        if(obj == null)
            return false;
        
        if(getClass() != obj.getClass())
            return false;
        
        Entry entry = (Entry) obj;
        if(!(name + "[" + scope + "]").equals(entry.name + "[" + entry.scope + "]"))
            return false;
        
        return true;
    }*/
}

