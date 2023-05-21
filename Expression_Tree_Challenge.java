ENode.java:
----------
public class ENode {
    char data;
    int root;
   ENode right = null;
   ENode left = null;
  // int depth;
   public ENode(char d){
      // depth = dep;
       data = d;
   }

ExpressionTree.java:
-------------------
public class ExpressionTree {
   public ENode start;
   public ExpressionTree(char d){
       start = new ENode(d);
       
   }
   public void add(char d){
      
       start = recursiveAdd(start,d);
   }
   public ENode recursiveAdd(ENode current, char d){
     
       if(current == null){
           
           current = new ENode(d);
           if(d != '+' || d != '-' || d != '*'){
               current.root = Character.getNumericValue(d);
           
           }
           return current;
       }
       if(d == '+' || d == '-' || d == '*'){
            if(current.left == null){
                current.left = recursiveAdd(current.left,d);
            }else if(current.left.data == '+' || current.left.data == '-' || current.left.data == '*'){
                if(checkEmpty(current.left) == true){
                    current.left = recursiveAdd(current.left,d);
                }else{
                    current.right = recursiveAdd(current.right,d);
                }
            }else if(current.right == null){
                current.right = recursiveAdd(current.right,d);
            }else if(current.right.data == '+' || current.right.data == '-' || current.right.data == '*'){
                if(checkEmpty(current.right) == true){
                    current.right = recursiveAdd(current.right,d);
                }else{
                    current.right = recursiveAdd(current.right,d);
                }
            }
       
       }else{
             if(current.left == null){
                   current.left = recursiveAdd(current.left,d);
               }else if(current.left.data == '+' || current.left.data == '-' || current.left.data == '*'){
                  
                   if(checkEmpty(current.left) == true){
                       current.left = recursiveAdd(current.left,d);
                       
                   }else{
                       current.right = recursiveAdd(current.right,d);
                   }
                   
               }else if(current.right == null || (current.right.data == '+' || current.right.data == '-' || current.right.data == '*')){
                   current.right = recursiveAdd(current.right,d);
               }
           
           
           
       }
   
       return current;
   }
   public boolean checkEmpty(ENode current){
       if(current.left == null || current.right == null){
        return true;  
       }else if(current.left.data == '+' || current.left.data == '-' || current.left.data == '*' ){
            return checkEmpty(current.left);
       }else if( current.right.data == '+' || current.right.data == '-' || current.right.data == '*'){
           
           return checkEmpty(current.right);
       }else{
           return false;
       }
       
   }
   
   public void calc(){
       calculate(start);
       
   }
   
   public boolean calculate(ENode current){
      
       
       if(current.data == '+' || current.data == '-' || current.data == '*'){
           if((current.left.data == '+' || current.left.data == '-' || current.left.data == '*') && (current.right.data == '+' || current.right.data == '-' || current.right.data == '*')){
           calculate(current.left);
           calculate(current.right);
           compute(current);
           return true;
           }else if(current.left.data == '+' || current.left.data == '-' || current.left.data == '*'){
            calculate(current.left); 
            
            //System.out.println(current.root);
            compute(current);   
            return true;
           }else if(current.right.data == '+' || current.right.data == '-' || current.right.data == '*'){
                
            calculate(current.right);   
          // System.out.println(current.root);
             compute(current);  
             return true;
               
           }else{
             
           compute(current);
           return true;
           }
        
       }
     
       return true;
   }
   
   public boolean compute(ENode current){
                
               if(current.data == '+'){
                   current.root = current.left.root + current.right.root;
               }else if(current.data == '-'){
                   current.root = current.left.root - current.right.root;
               }else if(current.data == '*'){
                   current.root = current.left.root * current.right.root;
               }
       
       return true;
   }
   
   public void ans(){
       System.out.println("The answer is: "+start.root);
   }
   
   public void print(){
       prefixPrint(start);
   }
   public boolean prefixPrint(ENode current){
       if(current == null){
           return true;
       }
        System.out.print(current.data + " ");
        prefixPrint(current.left);
        prefixPrint(current.right);
        
        return true;
       
       
   }
}

MyProgram.java:
--------------
import java.util.Scanner;

public class MyProgram
{
    public static void main(String[] args)
    {
        
        System.out.println("Enter input");
        Scanner in = new Scanner (System.in);
        String input = in.nextLine();
        ExpressionTree tree = new ExpressionTree(input.charAt(0));
        for(int x = 1; x < input.length(); x++){
        
       tree.add(input.charAt(x));
        }
     tree.calc();
        
     //tree.print();
        
    tree.ans();
        //+*23+45 --> 15
        //+7-*528 --> 9
        //-*+4325 --> 9
        //+3*52 --> 13
        //-+95*23 --> 8
        //+*3-82-9++32*54 -->2
    }
}

/*public class ExpressionTree {
   public ENode start;
   public ExpressionTree(char d){
       start = new ENode(d);
       
   }
   public void add(char d){
      
       start = recursiveAdd(start,d);
   }
   public ENode recursiveAdd(ENode current, char d){
     
       if(current == null){
           
           current = new ENode(d);
           if(d != '+' || d != '-' || d != '*' || d != '%' || d != 'h'){
              
               current.root = Character.getNumericValue(d);
              
           
           }
           return current;
       }
       if(d == '+' || d == '-' || d == '*' || d == '%' || d == 'h'){
            if(current.left == null){
                current.left = recursiveAdd(current.left,d);
            }else if(current.left.data == '+' || current.left.data == '-' || current.left.data == '*' || current.left.data == '%' || current.left.data == 'h'){
                if(checkEmpty(current.left) == true){
                    current.left = recursiveAdd(current.left,d);
                }else{
                    current.right = recursiveAdd(current.right,d);
                }
            }else if(current.right == null){
                current.right = recursiveAdd(current.right,d);
            }else if(current.right.data == '+' || current.right.data == '-' || current.right.data == '*' || current.right.data == '%' || current.right.data == 'h'){
                if(checkEmpty(current.right) == true){
                    current.right = recursiveAdd(current.right,d);
                }else{
                    current.right = recursiveAdd(current.right,d);
                }
            }
       
       }else{
             if(current.left == null){
                   current.left = recursiveAdd(current.left,d);
               }else if(current.left.data == '+' || current.left.data == '-' || current.left.data == '*'|| current.left.data == '%' || current.left.data == 'h'){
                  
                   if(checkEmpty(current.left) == true){
                       current.left = recursiveAdd(current.left,d);
                       
                   }else{
                       current.right = recursiveAdd(current.right,d);
                   }
                   
               }else if(current.right == null || (current.right.data == '+' || current.right.data == '-' || current.right.data == '*'|| current.right.data == '%' || current.right.data == 'h')){
                   current.right = recursiveAdd(current.right,d);
               }
           
           
           
       }
   
       return current;
   }
   public boolean checkEmpty(ENode current){
       if(current.left == null || current.right == null){
        return true;  
       }else if(current.left.data == '+' || current.left.data == '-' || current.left.data == '*'|| current.left.data == '%' || current.left.data == 'h' ){
            return checkEmpty(current.left);
       }else if( current.right.data == '+' || current.right.data == '-' || current.right.data == '*' || current.right.data == '%' || current.right.data == 'h'){
           
           return checkEmpty(current.right);
       }else{
           return false;
       }
       
   }
   
   public void calc(){
       calculate(start);
       
   }
   
   public boolean calculate(ENode current){
      
       
       if(current.data == '+' || current.data == '-' || current.data == '*' || current.data == '%' || current.data == 'h'){
           if((current.left.data == '+' || current.left.data == '-' || current.left.data == '*' || current.left.data == '%' || current.left.data == 'h') && (current.right.data == '+' || current.right.data == '-' || current.right.data == '*' || current.right.data == 'h' || current.right.data == '%')){
           calculate(current.left);
           calculate(current.right);
           compute(current);
           return true;
           }else if(current.left.data == '+' || current.left.data == '-' || current.left.data == '*'|| current.left.data == '*' || current.left.data == '%' || current.left.data == 'h' ){
            calculate(current.left); 
            
            //System.out.println(current.root);
            compute(current);   
            return true;
           }else if(current.right.data == '+' || current.right.data == '-' || current.right.data == '*' || current.right.data == 'h' || current.right.data == '%'){
                
            calculate(current.right);   
          // System.out.println(current.root);
             compute(current);  
             return true;
               
           }else{
             
           compute(current);
           return true;
           }
        
       }
     
       return true;
   }
   
   public boolean compute(ENode current){
                
               if(current.data == '+'){
                   current.root = current.left.root + current.right.root;
               }else if(current.data == '-'){
                   current.root = current.left.root - current.right.root;
               }else if(current.data == '*'){
                   current.root = current.left.root * current.right.root;
               }else if(current.data == '%'){
                   current.root = current.left.root % current.right.root;
               }else if(current.data == 'h'){
                   current.root = (int)(Math.sqrt((current.left.root * current.left.root)+(current.right.root * current.right.root)));
               }
       
       return true;
   }
   
   public void ans(){
       System.out.println("The answer is: "+start.root);
   }
   
   public void print(){
       prefixPrint(start);
   }
   public boolean prefixPrint(ENode current){
       if(current == null){
           return true;
       }
        System.out.print(current.data + " ");
        prefixPrint(current.left);
        prefixPrint(current.right);
        
        return true;
       
       
   }
}*/

