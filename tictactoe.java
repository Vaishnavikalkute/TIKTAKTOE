import java.util.ArrayList;  
import java.util.Arrays; 
import java.util.List;
import java.util.Random;
import java.util.Scanner;


public class tictactoe {
    static ArrayList<Integer> PlayerPositions=new ArrayList<Integer>();
    static ArrayList<Integer> CPUPositions=new ArrayList<Integer>();/*these are the array lists where 
    the positions enetered by the player will be stored.*/
    public static void main(String[] args) {
	//2D array 
        char [][] playboard = {{' ','|',' ','|',' '},
                              {'-','+', '-', '+', '-'},
                              {' ','|',' ','|',' '},
                              {'-','+','-','+','-'},
                              {' ','|',' ','|',' '}};
         //This is 2D array of character type to diaplay our playboard (design of playboard) 
         printboard(playboard);  
    while(true){       //loop will be executed continue until it satisfy the any condition form checkwin method.
         Scanner scan=new Scanner(System.in); // scanner will scan the values from user
         System.out.println("Enter your place (1-9)");//I have given numbers (1-9)to those empty positions in array.
         int playerpos = scan.nextInt(); //values enter by player will be stored here
         while(PlayerPositions.contains(playerpos)||CPUPositions.contains(playerpos))
             /*This  loop will check wheather the the position selected by user is already taken or not.*/
         {
             System.out.println("Position is already taken, Enter the new valid position");
             playerpos= scan.nextInt();
         }
         
         placevalue(playboard, playerpos ,"Player");//here we have called plcaevalue methode as for player 
         printboard(playboard);// Playboard will be printed with the value X & O value as per given by user
         String result =checkwin();// check wheather the playboard contents stisfy the condition or not
         if(result.length()>0){
            System.out.println(result); 
            break;
         }
         
         Random rand =new Random();//I have created rand (i.e random variable) for computer to choose any random position.
         int cpupos=rand.nextInt(9)+1;
         while(PlayerPositions.contains(cpupos)||CPUPositions.contains(cpupos))
//This  loop will check wheather the the position selected by user is already taken or not.
         {
             System.out.println("Position is already taken, cpu will select new valid position");
             cpupos= rand.nextInt(9)+1;
         }
         placevalue(playboard, cpupos ,"CPU");//here we have called plcaevalue methode for cpu
         printboard(playboard);// Playboard will be printed with the value X & O value as per given by user
         
         result =checkwin();
         if(result.length()>0){
            System.out.println(result);
            break;
         }
          
        }

    } 
    public static void printboard(char[][] playboard) //Method to print the Game board.
    {
        for (char[] row: playboard)//loop for rows
        {
            for(char c: row)//loop for columns of current rows
            {
                System.out.print(c);
            }
            System.out.println();
            
        }
    }
    
    public static void placevalue(char[][]playboard,int pos,String user)//Methode to print X and Q according to the user 
            //Here pos is used as number for the empty positions on the board
            //user we have to define when we will call this function.
    {
        char symbol=' ';
        if(user.equals("Player")) //to check whether computer have to print X or O at provided location 
        {
            symbol = 'X';
            PlayerPositions.add(pos);
        }else if(user.equals("CPU"))
        {
            symbol = 'O';
            CPUPositions.add(pos);
        }
        switch(pos) //Here i am using switch case to decide the place where the X and O will be printed 
        {
            case 1:
                playboard [0][0] =symbol;
                break;
            case 2:
                playboard [0][2] =symbol;
                break;
            case 3:
                playboard [0][4] =symbol;
                break;  
            case 4:
                playboard [2][0] =symbol;
                break;
            case 5:
                playboard [2][2] =symbol;
                break;
            case 6:
                playboard [2][4] =symbol;
                break;
            case 7:
                playboard [4][0] =symbol;
                break;
            case 8:
                playboard [4][2] =symbol;
                break;
            case 9:
                playboard [4][4] =symbol;
                break;
            default:
                break;            
            
        }
    }
    public static String checkwin()//Method to check the winner .
  /*here i have created lists to hold the list of positions
    i.e if the players position matches with this list the particular player will win.*/
    {
        List topRow=Arrays.asList(1,2,3);
        List midRow=Arrays.asList(4,5,6);
        List bottomRow=Arrays.asList(7,8,9);
        List rightcol=Arrays.asList(1,4,7);
        List midcol=Arrays.asList(2,5,8);
        List leftcol=Arrays.asList(3,6,9);
        List cross1=Arrays.asList(1,5,9);
        List cross2=Arrays.asList(3,5,7);
        //I have created arraylist to hold all these created list so that system will check all the positions.
        List<List>win=new ArrayList<List>();
        win.add(topRow);
        win.add(midRow);
        win.add(bottomRow);
        win.add(rightcol);
        win.add(midcol);
        win.add(leftcol);
        win.add(cross1);
        win.add(cross2);
        
        for(List l : win)//so now content of win will be copied in l which will check the conditions
        {
            if(PlayerPositions.containsAll(l))//so if the positions given by player matches any of list(i.e) content of l 
            {
                return "Congratulations, You WIN :)";
                    }
            else if(CPUPositions.containsAll(l))//so if the random positions given by cpu matches any of list(i.e) content of l
            {
                    return"OOPS Better luck next time :(";
                    } 
           else if(PlayerPositions.size()+ CPUPositions.size()==9)
               /*If none of the above condition get satisfied the system will check if the positions occupied player and cpu is equals 
               to 9 if not then the loop will be continue*/
            {       
               return"You tied with cpu";
            }  
        }
        return"";
    }
  
}