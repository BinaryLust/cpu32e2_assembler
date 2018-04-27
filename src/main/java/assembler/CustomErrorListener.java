

package assembler;

import org.antlr.v4.runtime.BaseErrorListener;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;


public class CustomErrorListener extends BaseErrorListener
{
    private int            errorCount;
    
    
    public CustomErrorListener()
    {
        this.errorCount     = 0;
    }
    
    
    @Override
    public void syntaxError(Recognizer<?,?> recognizer, Object offendingSymbol,
               int line, int charPositionInLine, String msg, RecognitionException e)
    {
        String str = String.format("At Line %d Character %d -> %s", line, charPositionInLine, msg);
        System.err.println(str);
        errorCount++;
    }
    
    
    public boolean hadErrors()
    {
        return (errorCount != 0);
    }
}

