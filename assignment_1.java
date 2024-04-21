// Import scanner class
import java.util.Scanner;

public class Assignment1 {

  public static void main(String[] args){
    //Define questions, options, and answers for each questions
String[][] qna = {
  {"What is the formula for calculating the acceleration of an object?", 
   "A. v = a.t",
   "B. a = (v-u)/t",
   "C. F = m.a",
   "D. s = v.t + 1/2 at^2",
   "B"
  },
  {"An object moves with a constant velocity. What is the net force acting on it?", 
   "A. Equal to its weight",
   "B. Dependent on its speed",
   "C. Zero",
   "D. Dependent on its mass",
   "C"
  },
  {"What type of simple machine is a seesaw?", 
   "A. Lever",
   "B. Pulley",
   "C. Wheel and axle",
   "D. Inclined plane",
   "A"
  },
  {"What does Newton's third law of motion state?", 
   "A. For every action, there is an equal and opposite reaction.",
   "B. The force exerted on an object is equal to its mass times its acceleration",
   "C. Every object in a state of uniform motion will remain in that state unless an external force is applied",
   "D. The acceleration of an object depends on the net force action on the object and its mass.",
   "A"
  },
  {"What is the unit of force in the International System of Units(SI)?", 
   "A. Newton",
   "B. Joule",
   "C. Pascal",
   "D. Watt",
   "A"
  },
};

Scanner scanner = new Scanner(System.in);
int counter = 0;


for (int i = 0; i < qna.length; i++){
  System.out.println(qna[i][0]);
  for (int j = 1; j<=4; j++){
        System.out.println(qna[i][j]);
      }
  
  boolean validInput;
  do{
      System.out.print("Enter Your Answer: ");
      String answerInput = scanner.next().toUpperCase();

      switch(answerInput){
      case "A":
      case "B":
      case "C":
      case "D":
            validInput = true;
            if(answerInput.equalsIgnoreCase(qna[i][5])){
                counter++;
            }
            break;
      default:
        System.out.println("Invalid input. Please enter A, B, C or D");
        validInput = false;
        break;
      }
  } while (!validInput);

}

    double score = (counter/ 5.0) * 100;
    System.out.println("Your score is: " + score + '%');

    scanner.close();
  }
}
