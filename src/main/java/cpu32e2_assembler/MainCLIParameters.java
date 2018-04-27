
package cpu32e2_assembler;

import com.beust.jcommander.Parameter;
import java.util.ArrayList;
import java.util.List;

//@Parameters(separators = "=") // and space by default
public class MainCLIParameters
{
    @Parameter(names         = {"-h", "--help"},
               help          = true,
               description   = "Displays help information")
    private boolean help = false;
    
    @Parameter(names         = {"-id"},
               //validateWith  = DirectoryParameterValidator.class,
               description   = "The directory the input files are located in")
    public String inputDirectory;
    
    @Parameter(names         = {"-i"},
               required      = true,
               variableArity = true,
               //validateWith  = FileParameterValidator.class,
               description   = "A list of files to assemble")
    public List<String> inputFiles = new ArrayList<>();
    
    @Parameter(names = {"-od"},
               //validateWith  = DirectoryParameterValidator.class,
               description   = "The directory the output file is located in")
    public String outputDirectory;
    
    @Parameter(names = {"-o"},
               required      = true,
               //validateWith  = FileParameterValidator.class,
               description   = "The file to save the assembled code in")
    public String outputFile;

    @Parameter(names = {"-bin"},
               description   = "Enables the generation of an output .bin file")
    public boolean generateBin = false;
    
    @Parameter(names = {"-mif"},
               description   = "Enables the generation of an output .mif file")
    public boolean generateMif = false;
    
    @Parameter(names = {"-hex"},
               description   = "Enables the generation of an output .hex file")
    public boolean generateHex = false;
    
    @Parameter(names = {"-debug"},
               description   = "Enables the the display of detailed debug info")
    public boolean showDebugInfo = false;
    
    
    public boolean isHelp()
    {
        return help;
    }
    
    
    @Override
    public String toString()
    {
        return "\nHelp              = " + help +
               "\nInput Directory   = " + inputDirectory +
               "\nInput Files       = " + inputFiles +
               "\nOutput Directory  = " + outputDirectory +
               "\nOutput File       = " + outputFile +
               "\nGenerate Bin File = " + generateBin +
               "\nGenerate Mif File = " + generateMif +
               "\nGenerate Hex File = " + generateHex +
               "\nShow Debug Info   = " + showDebugInfo;
    }
}

