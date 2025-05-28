import java.util.Scanner;
import java.util.Random;
public class Main{
    public static void main(String[]args){
        Scanner ohio=new Scanner(System.in);
        Scanner choiceNum=new Scanner(System.in);
        Random num=new Random();
        int targetNumber=num.nextInt(10)+1;
        int guess=0;
        int counter=1;
        while (true){
            System.out.print(counter+" ENTER YOUR GUESS: ");
            guess=ohio.nextInt();
            counter++;
            if(guess>targetNumber){
                System.out.println("TOO MUCH HIGH! TRY AGAIN.");
                if(counter>5){
                    System.out.println("LIMIT EXCEEDED.");
                    String choice;
                    System.out.println("IF YOU WANT TO PLAY AGAIN, TYPE YES AND IF NOT, TYPE NO");
                    choice=choiceNum.next();
                    String small=choice.toLowerCase();
                    String first;
                    first="yes";
                    String second;
                    second="no";
                    if(small.equals(first)){
                        continue;
                    }
                    else if(small.equals(second)){
                        System.out.println("THANKS FOR PLAYING.");
                        break;
                    }
                    else{
                        System.out.println("INCORRECT INPUT");
                        break;
                    }
                }
            }
            else if(guess<targetNumber){
                System.out.println("TOO MUCH LOW! TRY AGAIN");
                if(counter>5){
                    System.out.println("LIMIT EXCEEDED.");
                    String choice;
                    System.out.println("IF YOU WANT TO PLAY AGAIN, TYPE YES AND IF NOT, TYPE NO");
                    choice=choiceNum.next();
                    String small=choice.toLowerCase();
                    String first;
                    first="yes";
                    String second;
                    second="no";
                    if(small.equals(first)){
                        continue;
                    }
                    else if(small.equals(second)){
                        System.out.println("THANKS FOR PLAYING.");
                        break;
                    }
                    else{
                        System.out.println("INCORRECT INPUT");
                        break;
                    }
                }
            }
            else {
                System.out.println("CORRECTLY GUESSED.");
                String choice;
                System.out.println("IF YOU WANT TO PLAY AGAIN, TYPE YES AND IF NOT, TYPE NO");
                choice=choiceNum.next();
                String small=choice.toLowerCase();
                String first;
                first="yes";
                String second;
                second="no";
                if(small.equals(first)){
                    continue;
                }
                else if(small.equals(second)){
                    System.out.println("THANKS FOR PLAYING.");
                    break;
                }
                else{
                    System.out.println("INCORRECT INPUT");
                    break;
                }
            }

        }
        ohio.close();
    }
}