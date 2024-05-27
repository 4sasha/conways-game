
/**
 * 
 * 
 * 
 */
import java.util.Scanner;
public class conways
{
  public static void main(String[] args)
  {
      Scanner keyboard = new Scanner(System.in); 
      String [][] grid = new String[20][20]; // This is the 2d array for the grid aka the basic layout code
      System.out.print("  ");
      for (int x=1; x<21; x++){ 
        System.out.print(String.format("%02d ", x)); //this is me getting help from the internet to 
        //find out how to turn an int into a string and make sure its always the same length (thats why i put
        //the 0 and it's getting padded by 2 so thats why i did 2) the string.format is a function that you
        //use to convert and pad and format the int into a string
      }
      System.out.println(); //i did this so then the x's and the numbers wouldnt be in the same line
      for (int x=0; x<20; x++){ 
          for (int y=0; y<20;y++)
              grid[x][y]=" . "; //this is setting a value in every part of the array
        }
        for (int x=0; x<20; x++){ 
            int letters = 65+x;
            System.out.print((char)letters);
          for (int y=0; y<20;y++) 
              System.out.print(grid[x][y]); //this is printing the value in every part of the array
              System.out.println(); 
        }
        
    }
}
