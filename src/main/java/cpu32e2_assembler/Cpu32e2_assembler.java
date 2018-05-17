

package cpu32e2_assembler;

import assembler.Assembler;
import com.beust.jcommander.JCommander;
import com.beust.jcommander.ParameterException;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;


public class Cpu32e2_assembler {

    MainCLIParameters mainArgs        = new MainCLIParameters();
    String            bulkStr         = "";
    String            inputDirectory  = null;
    String            outputDirectory = null;
    Assembler         assembler;
    
    
    public static void main(String[] args) {
        
        Cpu32e2_assembler main = new Cpu32e2_assembler();
        
        main.handleParameters(args);
        main.run();
    }

    
    private void handleParameters(String[] args)
    {
        JCommander jCommander = new JCommander(mainArgs);
        jCommander.setProgramName("Cpu32e2_assembler");
        
        try {
            jCommander.parse(args);
        } catch (ParameterException e) {
            System.err.println(e.getMessage());
            showUsage(jCommander);
        }
        
        if(mainArgs.isHelp())
            showUsage(jCommander);
    }
    
    
    private void run()
    {
        System.out.println("Running with");
        System.out.println(mainArgs);
        System.out.println();
        
        // check if the input directory was defined
        if(mainArgs.inputDirectory != null)
            inputDirectory = mainArgs.inputDirectory;
        else
            inputDirectory = System.getProperty("user.dir") + "\\"; // use default if it wasn't
        
        // check if the output directory was defined
        if(mainArgs.outputDirectory != null)
            outputDirectory = mainArgs.outputDirectory;
        else
            outputDirectory = System.getProperty("user.dir") + "\\"; // use default if it wasn't
    
        
        // cycle through all input files
        for(int i = 0; i < mainArgs.inputFiles.size(); i++)
        {
            File selectedFile = new File(inputDirectory + mainArgs.inputFiles.get(i));
        
            if(selectedFile != null)
            {
                try
                {                
                    BufferedReader fin = new BufferedReader(new FileReader(selectedFile));
                    StringBuilder  str = new StringBuilder(512);
                
                    int c;
                    while((c = fin.read()) != -1)
                    {
                        str.append((char) c);
                    }
                
                    fin.close();
                    bulkStr += str.toString(); // append this string to the bulk string of all files
                }
                catch(FileNotFoundException ex)
                {
                    System.err.println("Unable to open file '" + selectedFile + "'");
                    System.exit(-1);
                }
                catch(IOException ex)
                {
                    System.err.println("Error reading file '" + selectedFile + "'");
                    System.exit(-1);
                }
            }
            else
            {
                System.err.println("No File Opened");
                System.exit(-1);
            }
        }
        
        
        // assemble the program
        if(bulkStr != null)
        {
            // make an assembler object
            assembler = new Assembler();

            if(assembler.assemble(bulkStr, mainArgs.showDebugInfo))
            {
                System.out.println();
                
                // save the result
                if(mainArgs.generateBin)
                    generateBin();
            
                if(mainArgs.generateMif)
                    generateMif(32, 4096);
            
                if(mainArgs.generateHex)
                    generateHex();
            }
        }
    }
    
    
    private void showUsage(JCommander jCommander)
    {
        jCommander.usage();
        System.exit(0);
    }
    
    
    private void generateBin()
    {
        File selectedFile = new File(outputDirectory + mainArgs.outputFile + ".bin");
        
        if(selectedFile != null)
        {
            try {                
                FileOutputStream outputStream = new FileOutputStream(selectedFile);
                DataOutputStream dataOutput   = new DataOutputStream(outputStream);

                for(int i = 0; i < assembler.data.size(); i++)
                    dataOutput.writeInt(assembler.data.get(i));
            
                dataOutput.flush();
                outputStream.flush();

                outputStream.close();
                dataOutput.close();
            } catch(FileNotFoundException ex) {
                System.out.println("Unable to open file '" + selectedFile + "'");                
            } catch(IOException ex) {
                System.out.println("Error writing file '" + selectedFile + "'");                  
                // ex.printStackTrace();
            }
            
            System.out.println("File Saved At " + selectedFile.getAbsolutePath());
        }
    }
    
    
    private void generateMif(int width, int depth)
    {
        File selectedFile = new File(outputDirectory + mainArgs.outputFile + ".mif");
        
        if(selectedFile != null)
        {
            try
            {
                FileOutputStream outputStream = new FileOutputStream(selectedFile);
                BufferedWriter   dataOutput   = new BufferedWriter(new OutputStreamWriter(outputStream));
                
                /*switch(disassemblerWordSize.getSelectionModel().getSelectedItem().toString())
                {
                    case "8 Bits":  width = 8;  break;
                    case "16 Bits": width = 16; break;
                    case "32 Bits": width = 32; break;
                    case "64 Bits": width = 64; break;
                    default:        width = 32; break;
                }*/
                
                dataOutput.write("\n");
                dataOutput.write(String.format("WIDTH=%d;\n", width));
                dataOutput.write(String.format("DEPTH=%d;\n", depth));
                dataOutput.write("\n");
                dataOutput.write("ADDRESS_RADIX=HEX;\n");
                dataOutput.write("DATA_RADIX=HEX;\n");
                dataOutput.write("\n");
                dataOutput.write("CONTENT BEGIN\n");
                
                switch(width)
                {
                    case 8:  for(int i = 0; i < assembler.data.size(); i++)
                             {
                                 int value = assembler.data.get(i);
                                 dataOutput.write(String.format("    %04X : %02X;\n", (i*4),   ((value >> 24) & 0xff)));
                                 dataOutput.write(String.format("    %04X : %02X;\n", (i*4)+1, ((value >> 16) & 0xff)));
                                 dataOutput.write(String.format("    %04X : %02X;\n", (i*4)+2, ((value >> 8)  & 0xff)));
                                 dataOutput.write(String.format("    %04X : %02X;\n", (i*4)+3, ((value >> 0)  & 0xff)));
                             }
                             dataOutput.write(String.format("    [%04X..%04X] : 00;\n", assembler.data.size()*4, depth-1));
                             break;
                    
                    case 16: for(int i = 0; i < assembler.data.size(); i++)
                             {
                                 int value = assembler.data.get(i);
                                 dataOutput.write(String.format("    %04X : %04X;\n", (i*2),   ((value >> 16) & 0xffff)));
                                 dataOutput.write(String.format("    %04X : %04X;\n", (i*2)+1, ((value >> 0)  & 0xffff)));
                             }
                            
                             dataOutput.write(String.format("    [%04X..%04X] : 0000;\n", assembler.data.size()*2, depth-1));
                             break;
                    
                    case 32: for(int i = 0; i < assembler.data.size(); i++)
                             {
                                 int value = assembler.data.get(i);
                                 dataOutput.write(String.format("    %04X : %08X;\n", i, value));
                             }
                             dataOutput.write(String.format("    [%04X..%04X] : 00000000;\n", assembler.data.size(), depth-1));
                             break;
                    
                    case 64: break;
                    default: break;
                }
                
                dataOutput.write("END;\n");
                    
                dataOutput.close();
                outputStream.close();
            } catch(FileNotFoundException ex) {
                System.out.println("Unable to open file '" + selectedFile + "'");                
            } catch(IOException ex) {
                System.out.println("Error writing file '" + selectedFile + "'");                  
                // ex.printStackTrace();
            }
            
            System.out.println("File Saved At " + selectedFile.getAbsolutePath());
        }
    }
    
    
    private void generateHex()
    {
        File selectedFile = new File(outputDirectory + mainArgs.outputFile + ".hex");

        if(selectedFile != null)
        {
            try
            {
                FileOutputStream outputStream = new FileOutputStream(selectedFile);
                BufferedWriter   dataOutput   = new BufferedWriter(new OutputStreamWriter(outputStream));

                int[]         lineBytes = new int[21];
                int           address   = 0;  // default address 0 start
                int           value;

                for(int i = 0; i < assembler.data.size(); i += 4)
                {
                    StringBuilder data = new StringBuilder(); // we should not create a new stringbuilder each loop

                    lineBytes[0] = 16;                      // byte count
                    lineBytes[1] = ((address >> 8) & 0xff); // address
                    lineBytes[2] = ((address >> 0) & 0xff);
                    lineBytes[3] = 0;                       // data record type

                    if(i < assembler.data.size())
                    {
                        value = assembler.data.get(i);
                        lineBytes[4] = (value >> 24) & 0xff;
                        lineBytes[5] = (value >> 16) & 0xff;
                        lineBytes[6] = (value >> 8)  & 0xff;
                        lineBytes[7] = (value >> 0)  & 0xff;
                    }
                    else
                    {
                        lineBytes[4] = 0;
                        lineBytes[5] = 0;
                        lineBytes[6] = 0;
                        lineBytes[7] = 0;
                    }

                    if((i+1) < assembler.data.size())
                    {
                        value = assembler.data.get(i+1);
                        lineBytes[8]  = (value >> 24) & 0xff;
                        lineBytes[9]  = (value >> 16) & 0xff;
                        lineBytes[10] = (value >> 8)  & 0xff;
                        lineBytes[11] = (value >> 0)  & 0xff;
                    }
                    else
                    {
                        lineBytes[8]  = 0;
                        lineBytes[9]  = 0;
                        lineBytes[10] = 0;
                        lineBytes[11] = 0;
                    }

                    if((i+2) < assembler.data.size())
                    {
                        value = assembler.data.get(i+2);
                        lineBytes[12] = (value >> 24) & 0xff;
                        lineBytes[13] = (value >> 16) & 0xff;
                        lineBytes[14] = (value >> 8)  & 0xff;
                        lineBytes[15] = (value >> 0)  & 0xff;
                    }
                    else
                    {
                        lineBytes[12] = 0;
                        lineBytes[13] = 0;
                        lineBytes[14] = 0;
                        lineBytes[15] = 0;
                    }

                    if((i+3) < assembler.data.size())
                    {
                        value = assembler.data.get(i+3);
                        lineBytes[16] = (value >> 24) & 0xff;
                        lineBytes[17] = (value >> 16) & 0xff;
                        lineBytes[18] = (value >> 8)  & 0xff;
                        lineBytes[19] = (value >> 0)  & 0xff;
                    }
                    else
                    {
                        lineBytes[16] = 0;
                        lineBytes[17] = 0;
                        lineBytes[18] = 0;
                        lineBytes[19] = 0;
                    }

                    int checkSum = 0;
                    for(int n = 0; n < 20; n++)
                        checkSum += lineBytes[n];

                    checkSum = checkSum & 0xff; // get the lsb only
                    checkSum = -checkSum;       // and negate it

                    lineBytes[20] = checkSum & 0xff; // checksum

                    data.append(":");

                    for(int n = 0; n < 21; n++)
                        data.append(String.format("%02X", lineBytes[n]));

                    data.append("\n");

                    // output string version of data to file
                    dataOutput.write(data.toString());

                    address += 16;
                }

                // write end of file line
                dataOutput.write(String.format(":%02X%04X00%S%02X", 0, 0, "", 0xff));

                dataOutput.close();
                outputStream.close();
            } catch(FileNotFoundException ex) {
                System.out.println("Unable to open file '" + selectedFile + "'");
            } catch(IOException ex) {
                System.out.println("Error writing file '" + selectedFile + "'");
                // ex.printStackTrace();
            }

            System.out.println("File Saved At " + selectedFile.getAbsolutePath());
        }
    }
}

