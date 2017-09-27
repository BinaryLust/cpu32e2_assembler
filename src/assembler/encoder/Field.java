

package assembler.encoder;


import java.util.Arrays;


public class Field
{
   // an array of bit positions
   public  short[]  bits;
   public  boolean  signExtend;
   //public  int      min;
   //public  int      max;
   
   
   // default constructor
   public Field()
   {
    
   }
   
   
   // main constructor
   public Field(short[] bits, boolean signExtend)
   {
      this.bits = bits;
      this.signExtend = signExtend;
   }
   
   
   public int getValue(int source)
   {
      // we set all bits to zero to begin with
      int temp = 0;
      
      for(int i = 0; i < bits.length; i++)
         if((source & (1 << bits[i])) != 0) // test the bit at the bit position bitPosition[i] from the source value
            temp |= (1 << i);                       // if it is 1 then we set the bit at bit position i in the return value
      
      // check if we want to sign extend the result
      if(signExtend)
      {
         int highestBit = bits.length - 1;
         
         if((temp & (1 << highestBit)) != 0) // check the highest bit of the result
         {
            // generate a bit mask
            int mask = (bits.length == 32) ? -1 : (1 << bits.length) - 1;
            temp |= ~mask; // invert the mask and set those bits in the result to finish the extension
         }
      }
      
      return temp; // return the final value
   }
   
   
   public int setValue(int source, int value)
   {
      for(int i = 0; i < bits.length; i++)
         if((value & (1 << i)) != 0)       // test the bit at bit position i from the value to be set
            source |= (1 << bits[i]);      // if it is 1 then we set the bit in the source at the bit position bits[i]
         else
            source &= ~(1 << bits[i]);     // otherwise we clear the bit in the source at bit position bits[i]
   
      return source;
   }
   
   
   @Override
   public String toString()
   {
      return " field bits: " + Arrays.toString(bits) + ", sign extend: " + signExtend;
   }
}

