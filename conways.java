
/**
 * 
 * Sasha Lambrechtsen Conways Game of Life 
 * Copyright /Conways comes under the creative commons
 * Version 8
 * 26/06/2024
 * I'm doing it for CSC223 at WHS
 * 
 */
import java.util.Scanner;
public class conways
{
    static int DEAD = 0;
    static int ALIVE = 1; // this is making dead mean 0 and alive be 1 for the whole program, they are numbers because its easier
    //to make it an array of ints (as ints can only be numbers) than doing string parsing
    static int XSIZE = 5;  // make the size 20 wide
    static int YSIZE = 5;  // make the size 20 high
  public static void main(String[] args)
  {
      Scanner keyboard = new Scanner(System.in); 
      int[][] grid = new int[XSIZE][YSIZE];// This is the 2d array for the grid aka the basic layout code
      System.out.print("  ");
      int cell;
      System.out.println(); //i did this so then the x's and the numbers wouldnt be in the same line
      for (int x=0; x<XSIZE; x++){ 
          for (int y=0; y<YSIZE;y++)
              grid[x][y]=DEAD; //this is making all the values start as dead
        }
        //System.out.println("Where do you want your cell to be?");
        //String cell1 = keyboard.nextLine();
        
        //int cellX = ((int) cell1.toUpperCase().charAt(0))-65;
        
        //int cellY = (Integer.parseInt(String.valueOf(cell1.charAt(1))))-1;
        //grid[cellX][cellY] = ALIVE;
        grid[2][1] = ALIVE;
        grid[2][2] = ALIVE;
        grid[2][3] = ALIVE;
        PrintingGrid(grid);
       // System.out.println(grid[cellX][cellY]);
        int[][] newGrid = gridDeadOrAlive(grid);
        PrintingGrid(newGrid);
        
        cell = keyboard.nextInt();
    }
    public static int[][] gridDeadOrAlive(int[][] grid) //making a method for calculating the next generation ( aka
    //if all cells are alive or dead)
    {
        int[][] newGrid = new int[XSIZE][YSIZE]; //making another 2d array for the new generation grid
          for (int x=0; x<XSIZE; x++){ // looking thru each cell of grid
          for (int y=0; y<YSIZE; y++){ 
              int numberOfNeighboursAlive = 0; //defining variable of number of neighbours that are alive
              for (int xNeighbouringCells=-1; xNeighbouringCells<2; xNeighbouringCells++){ 
                  for (int yNeighbouringCells=-1; yNeighbouringCells<2; yNeighbouringCells++){  // loop through all neighbours and seeing whether alive or dead
                      if ((x+xNeighbouringCells >= 0 && y+yNeighbouringCells >= 0) && // if it's above x0 or y0 grid 
                          x+xNeighbouringCells < XSIZE && y+yNeighbouringCells < YSIZE && // if less than x20 or y20 
                          grid[x+xNeighbouringCells][y+yNeighbouringCells] == ALIVE) { // this is checking if alive
                          numberOfNeighboursAlive++; // add neighbours onto cell if conditions are correct 
                        }
                    }
                }
              if (grid[x][y]== ALIVE){ //if the current cell we r looking at is alive
                  numberOfNeighboursAlive--; //subtract the active cell to not count it twice
                  System.out.println("Grid alive X " + String.format("%02d ", x) + " Y " + String.format("%02d ", y) + " count " + String.format("%02d ", numberOfNeighboursAlive )); //this is me getting help from the internet to 
                  if(numberOfNeighboursAlive < 2){ //checking if it has less than two neighbours
                      newGrid[x][y]=DEAD; //if it does then its dead
                  } else if(numberOfNeighboursAlive <= 3){ //checking if it less than 3 or = to 3 neighbours
                      newGrid[x][y]=ALIVE; //if it does it is alive
                  } else{
                      newGrid[x][y]=DEAD; // if it has more than 3 neighbours ( the only other option) it dies
                  } 
              } else { // if the cell is dead
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
        System.out.print("   "); // Pad the first line for characters
         for (int x=0; x<XSIZE; x++){ 
            int letters = 65+x;// ascii character A is 66, so printing charcters
            System.out.print(" "+(char)letters+" "); // printing characters
          }
         System.out.println(); //print another line so it would make a new row
          for (int y=0; y<YSIZE; y++){ 
            System.out.print(String.format("%02d ", y)); //this is me getting help from the internet to 
            //find out how to turn an int into a string and make sure its always the same length (thats why i put
            //the 0 and it's getting padded by 2 so thats why i did 2) the string.format is a function that you
            //use to convert and pad and format the int into a string11
              for (int x=0; x<XSIZE;x++){  // making sure its 20 wide and 20 high
                  if (grid[x][y]== ALIVE){ //if the cell is alive
                      System.out.print(" x "); //if it is alive then make it x
                  } else {
                      System.out.print(" . "); // if it is dead make it .
                  }
            } //got rid of old code because i wanted to print x and . instead of 0 and 1 for aesthetic purposes
              System.out.println(); //print another line so it would make a new row
         }
    }
}
