import java.util.ArrayList;
import java.util.Scanner;

public class TextIO {
  
  private ArrayList<String> goodInputs; // Quit is a keyword
  private String welcome, farewell;
  
  Scanner myScanner;
  
  public TextIO(ArrayList<String> g, String w, String f) {
    int len = g.size();
    goodInputs = new ArrayList<String>(len);
    for(int i = 0; i < len; i++) 
      goodInputs.add(g.get(i));
    welcome = w;
    farewell = f;
    myScanner = new Scanner(System.in);
  }
  
  public void setGoodInputs(ArrayList<String> other){
    this.goodInputs = other;
  }

  public boolean isGoodInput(String str) {
    for(String x: goodInputs){
      if(x.equals(str))
        return true;
    }
    return false;
  }
  
  public static String oxfordComma(ArrayList<String> items) {
    String output = "";
    int len = items.size();
    for(int i = 0; i < len; i++) {
      if(i < len - 1) {
        output += "[" + items.get(i) + "], ";
      }
      else if(i == len - 1) {
        output += "or [" + items.get(i);
      }
    }
    return output + "]";
  }
  
  public void act(String str) {
    int len = goodInputs.size();
    int num = 0; 
    for(int i = 0; i < len; i++) {
      if( goodInputs.get(i).equals(str) ) {
        num = i;
      }
    }
    switch(num) {
      case 0:  /*INSERT CODE HERE*/; break;
      default: /*INSERT CODE HERE*/;
    }
  }
  
  public void run() {
    String input = "";
    System.out.println(welcome);
    while( !input.equals("quit") ) {
      input = "quit";
      while( isGoodInput(input) ) {
        System.out.print("Enter " + oxfordComma(goodInputs) + " as your input: ");
        input = myScanner.nextLine();
        if( !isGoodInput(input) ) {
          System.out.println("That is not an accepted input.\n");
        }
        if( input.equals("quit") ) {
          break;
        }
        else {
          act(input);
        }
      }
    }
    System.out.println(farewell);
  }
  
  public String getFarewell(){
    return this.farewell;
  }
  public static void main(String[] args) {/*
    String start = "Welcome to \"The Text Input Game\". You can go [up], [down], or [quit].";
    String stop = "Thank you for playing \"The Text Input Game\".";
    ArrayList<String> inputs = new ArrayList<String>();
    inputs.add("up");
    inputs.add("down");
    inputs.add("quit");
    TextIO t = new TextIO(inputs, start, stop);
    t.run();
    for(int i = 0; i < 8; i++){
      for(int j = 0; j < 8; j++){
        System.out.println("\"" + i + ", " + j + "\",");
      }
    }*/
  }
}