
import java.util.Scanner;


public class Main {

    public static void main(String[] args) {

        double money;

        double bet;

        Scanner scan= new Scanner (System.in);

        System.out.println("Please, Enter the bet?");
        bet= scan.nextDouble();    //nextInt for the int values

        System.out.println("Please enter the buy-in");

        money=scan.nextDouble();


        playMexico(money, bet);

    }

    public static int diceRoll(){

        int random; //random varibale decleration
        random = (int) (Math.random() % 6); //typecast to int
        random=random+1;
        return random;
    }
    public static int getScore(int a, int b){

        int temp;  //temp variable

        if (a>b){
            int ten = 10* a ;  //digit arithmetic
            temp = ten+b;
            return temp;
        }
        else {
            int ten = 10* b ;  //digit arithmetic
            temp = ten+a;
            return temp;
        }
    }

    public static int playOneRound(String name){

        int dice1= diceRoll();
        int dice2= diceRoll();

        int score = getScore( dice1 , dice2 );

        return score;
    }



    public static String getWinner(int score1, int score2){
        String g = "Giulia";
        String d = "David";
        String tie= "tie";
        if (score1>score2){
            return g;
        }
        else if (score1==score2){
            return tie;
        }
        else {
            return d;
        }
    }


    public static boolean canPlay(double buyin, double bet) {
        if ((buyin % bet) == 0) {
            return true;
        } else {
            return false;
        }

    }

    public static void playMexico(double buyin, double bet){
        if (!canPlay(buyin,bet)){
            System.out.println("The game has ended. Add Funds to Play...");
        }
        else {

            int money1=(int) buyin; //Guilia
            int money2=(int) buyin; //David
            int count= 1;

            while(money1==0 || money2==0 ){
                String winner= getWinner(playOneRound("Guilia"), playOneRound("David"));

                if (winner=="Guilia"){
                    money1+= bet;
                    money2-=bet;
                    System.out.println("Guilia is the winner, Guilia's Budget: "+money1);
                }
                else if( winner=="tie"){
                    System.out.println("TIE!, Please roll the dice again");
                }
                else {
                    money2+= bet;
                    money1-=bet;
                    System.out.println("David is the winner, David's Budget: "+money2);
                }

                System.out.println("Round: "+ count);
                count++;
            }
        }
    }

}
