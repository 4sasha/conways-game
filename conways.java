
/**
 * 
 * 
 * 
 */
import java.util.Scanner;
public class conways
{
    static int DEAD = 0;
    static int ALIVE = 1;
  public static void main(String[] args)
  {
      Scanner keyboard = new Scanner(System.in); 
      int[][] grid = new int[20][20];// This is the 2d array for the grid aka the basic layout code
      System.out.print("  ");
      int cell;
      System.out.println(); //i did this so then the x's and the numbers wouldnt be in the same line
      for (int x=0; x<20; x++){ 
          for (int y=0; y<20;y++)
              grid[x][y]=DEAD; //this is making all the values start as dead
        }
        System.out.print("Where do you want your first sprite to be?");
        cell = keyboard.nextInt();
    }
    public static int[][] gridDeadOrAlive(int[][] grid) //making a method for calculating the next generation ( aka
    //if all cells are alive or dead)
    {
        int[][] newGrid = new int[20][20]; //making another 2d array for the new generation grid
          for (int x=0; x<20; x++){ 
          for (int y=0; y<20;y++){ // looking thru each cell of grid
              int numberOfNeighboursAlive = 0; //defining variable of number of neighbours that are alive(starts as 0)
              if (grid[x][y]==ALIVE){ //if the current cell we r looking at is alive
              if(numberOfNeighboursAlive < 2){ //checking if it has less than two neighbours
                  newGrid[x][y]=DEAD; //if it does then its dead
              } else if(numberOfNeighboursAlive <= 3){ //checking if it less than 3 or = to 3 neighbours
                  newGrid[x][y]=ALIVE; //if it does it is alive
              } else{
                  newGrid[x][y]=DEAD; // if it has more than 3 neighbours ( the only other option) it dies
              } 
             } else{ // if the cell is dead
                 if(numberOfNeighboursAlive == 3){ //if the number of neighbours alive is 3
                     newGrid[x][y]=ALIVE; //make it alive
                 } else{
                     newGrid[x][y]=DEAD; //stay dead
                 }
             }
            }
        }
        return newGrid; // this is returning the new now changed grid
    }
     public static void PrintingGrid(int[][] grid){ //printing the grid to be pretty
            for (int x=1; x<21; x++){ 
            System.out.print(String.format("%02d ", x)); //this is me getting help from the internet to 
            //find out how to turn an int into a string and make sure its always the same length (thats why i put
            //the 0 and it's getting padded by 2 so thats why i did 2) the string.format is a function that you
            //use to convert and pad and format the int into a string11
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
