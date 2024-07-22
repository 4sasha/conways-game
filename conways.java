/**
 * 
 * Sasha Lambrechtsen Conways Game of Life 
 * Copyright /Conways comes under the creative commons
 * Version 19
 * 26/06/2024
 * I'm doing this project for CSC223 at WHS
 * 
 */
import java.util.Scanner;
import java.io.IOException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
public class conways
{
    static int DEAD = 0;
    static int ALIVE = 1; // this is making dead mean 0 and alive be 1 for the whole program, they are numbers because its easier
    //to make it an array of ints (as ints can only be numbers) than doing string parsing
    static int XSIZE = 20;  // make the size 20 wide
    static int YSIZE = 20;  // make the size 20 high
  public static void main(String[] args)
  {
      Scanner keyboard = new Scanner(System.in); 
      int[][] grid = new int[XSIZE][YSIZE];// This is the 2d array for the grid aka the basic layout code
      System.out.print("  "); 
      System.out.println(); //i did this so then the x's and the numbers wouldnt be in the same line
      for (int x=0; x<XSIZE; x++){ 
          for (int y=0; y<YSIZE;y++)
              grid[x][y]=DEAD; //this is making all the values start as dead
        }
        System.out.println('\u000c'); //clear screen
        System.out.println("Hello and welcome to my (Sasha's) rendition of Conways game of life!");
        System.out.println("The rules are basic yet may take a few tries to ");
        while (true) {
            System.out.println("L to load saved game or else press enter");
            String load_answer = keyboard.nextLine(); // get user answer
            if (load_answer.length() > 0 && load_answer.toUpperCase().charAt(0) == "L".charAt(0)) { // if we load the file
                try {
                    // i found code online showing how to save an array to a file https://stackoverflow.com/questions/11924843/saving-an-array-of-objects-in-java-for-later-use-in-another-program
                    ObjectInputStream file_in = new ObjectInputStream(new FileInputStream("conway.backupppp")); // load file into input stream
                    grid = (int[][]) file_in.readObject(); // read input stream into grid
                    file_in.close(); //close file once done
                    PrintingGrid(grid); //then go back to printing that grid from backed up game
                    break;
                } catch(IOException ex){ //if the file doesnt exist this causes an exception 
                    System.out.println(ex.toString()); //print exception message out
                    System.out.println("Could not find file");
                } catch (ClassNotFoundException ex) { //taken from code online example
                    System.out.println(ex.toString()); 
                    System.out.println("Class not found error");
                }
            } if (load_answer.length() == 0) {
                break;
            }else {
                System.out.println("invalid that is not either load or enter...");
            }
        }
        System.out.println('\u000c'); //clear screen

        int generations = 1; //default generation is 1
        while (true) {
            System.out.println("How many generations do you want to play or enter for 1 generation?");
            String generations_answer = keyboard.nextLine(); 
            if (generations_answer.length() > 0) {//if you don't enter anything then it's just 1 generation
                if (generations_answer.matches("[0-9]+")) {// check to make sure it's a number
                    generations = Integer.valueOf(generations_answer);//setting the variable generations to whatever the user input was
                    break;
                } else{
                    System.out.println("invalid that is not a number buddy");
                }
            } else{
                break;
            }
        }
        PrintingGrid(grid); 
        while (true) {
            System.out.println("where do you want your cell to be (alive or dead), or use enter key to stop ?");
            String cell1 = keyboard.nextLine(); //this is turning the user input into a string called cell1
            if (cell1.length() == 0) { // if we have no value aka enter button was pressed we play the game
                break;
            } else {
                if (Character.toString(cell1.toUpperCase().charAt(0)).matches("[A-Z]")){
                    if (cell1.substring(1).matches("[0-9]+")) { 
                        int cellX = ((int) cell1.toUpperCase().charAt(0))-65; //this is turning the first character (with charAt0, since the first value is
                        //always 0 and not 1, then minusing 65 from that due to using ascii keyboard cuz its java. also making sure it's always
                        //counted as an uppercase
                        int cellY = Integer.valueOf(cell1.substring(1))-1; //making a substring so the string goes through each number (so if value
                        //is above 9 it doesn't break randomly and counts all numbers)
                        if (grid[cellX][cellY] == ALIVE) { // check the cell is alive
                            grid[cellX][cellY] = DEAD; // and make it dead
                        } else {
                        grid[cellX][cellY] = ALIVE; // or cell is dead and make it alive
                        }
                    }  
                    else{
                        System.out.println("Invalid answer, second value is not a number");
                    }
                }
                 else {
                    System.out.println("Invalid answer, first character is not a letter");
                }
            }
            PrintingGrid(grid); 
           
        }
        //System.out.println("Finished"); - debugging

        for (int generation = 0; generation < generations; generation++) {
                grid = gridDeadOrAlive(grid);
                PrintingGrid(grid);
                System.out.println("generation: " +String.format("%d ", generation + 1));
        }
             
        // Save current game
        System.out.println("S to save the current game?");
        String save_answer = keyboard.nextLine(); // get answer
        if (save_answer.length() > 0 && save_answer.toUpperCase().charAt(0) == "S".charAt(0)) { // to save the file
            try {
                ObjectOutputStream file_out = new ObjectOutputStream(new FileOutputStream("conway.backup")); //save file to a new file called conway backup
                file_out.writeObject(grid); //writing grid to file
                file_out.flush(); // flushing the file buffer to write the grid to the disk
                file_out.close(); //then close file
            }
            catch(IOException ex){ //if file fails to save/an error happens with file
                System.out.println (ex.toString()); //printing the exception
                System.out.println("Could not find file"); 
            }
        }
    }
    public static int[][] gridDeadOrAlive(int[][] grid) //making a method for calculating the next generation ( aka
    //if all cells are alive or dead)
    {
        int[][] newGrid = new int[XSIZE][YSIZE]; //making another 2d array for the new generation grid
          for (int x=0; x<XSIZE; x++){ // looking through each cell of grid
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
                  // System.out.println("Grid alive X " + String.format("%02d ", x) + " Y " + String.format("%02d ", y) + " count " + String.format("%02d ", numberOfNeighboursAlive )); debugging (no longer required)
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
         System.out.println('\u000c');
        System.out.print("   "); // Pad the first line for characters
         for (int x=0; x<XSIZE; x++){ 
            int letters = 65+x;// ascii character A is 66, so printing charcters
            System.out.print(" "+(char)letters+" "); // printing characters
          }
         System.out.println(); //print another line so it would make a new row
          for (int y=0; y<YSIZE; y++){ 
            System.out.print(String.format("%02d ", y+1)); //this is me getting help from the internet to 
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
         try 
         {
             Thread.sleep(300); //doesnt do anything for 300 ms so if there are multiple generations, you can see 
             //how the code changes
         }
         catch (InterruptedException ie) 
         {
             ie.printStackTrace(); //if you wanted to stop the program mid computing then it will show up with an error
         }
    }
}