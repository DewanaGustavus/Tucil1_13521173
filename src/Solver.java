package src;
import java.util.*;

public class Solver {
    private static ArrayList<int[]> permutation = new ArrayList<int[]>();
    private static ArrayList<char[]> operations = new ArrayList<char[]>();
    private static char[] validOperator = {'+', '-', '*', '/'};

    private static ArrayList<String> answerList = new ArrayList<String>();

    public static int[] copyArrayInt(int[] array){
        int[] copy = new int[array.length];
        for(int i=0;i<array.length;i++){
            copy[i] = array[i];
        }
        return copy;
    }

    public static char[] copyArrayChar(char[] array){
        char[] copy = new char[array.length];
        for(int i=0;i<array.length;i++){
            copy[i] = array[i];
        }
        return copy;
    }

    public static void countingSort(int[] array){
        int count[] = new int[14];
        for(int i=0;i<array.length;i++){
            count[array[i]]++;
        }

        int i = 0;
        for(int j=0;j<count.length;j++){
            for(int k=0;k<count[j];k++){
                array[i++] = j;
            }
        }
    }

    public static void arraySwapElement(int[] array, int i, int j){
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    public static void generatePermutation(int[] array, int idx){
        if(idx == (array.length - 1)){
            permutation.add(copyArrayInt(array));
        }

        int prev = -1;
        for(int j=idx;j<array.length;j++){
            int temp[] = copyArrayInt(array);
            if(j > idx && temp[idx] == temp[j])continue;
            if(prev != -1 && prev == array[j])continue;
            arraySwapElement(temp, idx, j);
            prev = array[j];
            generatePermutation(temp, idx + 1);
        }
    }

    public static void generateOperatorCombination(char[] operator, int idx){
        if(idx == 3){
            operations.add(copyArrayChar(operator));
        }else{
            for(int i=0;i<validOperator.length;i++){
                char tmp = operator[idx];
                operator[idx] = validOperator[i];
                generateOperatorCombination(operator, idx + 1);
                operator[idx] = tmp;
            }
        }
    }

    public static void printArrayInt(int[] array){
        for(int i=0;i<array.length;i++){
            System.out.print(array[i] + " ");
        }
        System.out.println();
    }

    public static void printArrayChar(char[] array){
        for(int i=0;i<array.length;i++){
            System.out.print(array[i] + " ");
        }
        System.out.println();
    }

    public static void printArrayListIntArray(ArrayList<int[]> arrlist){
        int amount = arrlist.size();
        for(int i=0;i<amount;i++){
            printArrayInt(arrlist.get(i));
        }
    }

    public static void printArrayListCharArray(ArrayList<char[]> arrlist){
        int amount = arrlist.size();
        for(int i=0;i<amount;i++){
            printArrayChar(arrlist.get(i));
        }
    }

    private static Fraction operate(Fraction a, Fraction b, char op){
        try{
            if(op == '+')return Fraction.plus(a,b);
            else if(op == '-')return Fraction.minus(a, b);
            else if(op == '*')return Fraction.multiply(a, b);
            else if(op == '/')return Fraction.divide(a, b);
            return new Fraction(0, 1); // Invalid operator
        }catch(Exception e){ // handle division by zero
            return new Fraction(9999,1);
        }
    }

    private static boolean checkAnswer(Fraction a){
        Fraction.simplify(a);
        return (a.numerator == 24) && (a.denominator == 1);
    }

    public static String convertNumber(int num){
        String name;
        if(num == 1)name = "A";
        else if(num == 11)name = "J";
        else if(num == 12)name = "Q";
        else if(num == 13)name = "K";
        else name = Integer.toString(num);
        return name;
    }

    public static void getAnswer(int[] cardValue, char[] operator){

        Fraction a,b,c,d,e,f,res;
        a = new Fraction(cardValue[0], 1);
        b = new Fraction(cardValue[1], 1);
        c = new Fraction(cardValue[2], 1);
        d = new Fraction(cardValue[3], 1);

        // assume operation is a op1 b op2 c op3 d
        // all bracket combination
        // ((a op1 b) op2 c) op3 d
        e = operate(a, b, operator[0]);
        f = operate(e, c, operator[1]);
        res = operate(f, d, operator[2]);
        if(checkAnswer(res)){
            String answer = new String();
            answer += "((" + convertNumber(cardValue[0]) + " " + operator[0] + " " + convertNumber(cardValue[1]) + ") ";
            answer += operator[1] + " " +  convertNumber(cardValue[2]) + ") " + operator[2] + " " + convertNumber(cardValue[3]);
            answerList.add(answer);
        }
        // ! duplicate from above a op1 (b op2 (c op3 d))
        // (a op1 b) op2 (c op3 d)
        e = operate(a, b, operator[0]);
        f = operate(c, d, operator[2]);
        res = operate(e, f, operator[1]);
        if(checkAnswer(res)){
            String answer = new String();
            answer += "(" + convertNumber(cardValue[0]) + " " + operator[0] + " " + convertNumber(cardValue[1]) + ") ";
            answer += operator[1] + " (" +  convertNumber(cardValue[2]) + " " + operator[2] + " " + convertNumber(cardValue[3]) + ")";
            answerList.add(answer);
        }
        
        // a op1 ((b op2 c) op3 d)
        e = operate(b, c, operator[1]);
        f = operate(e, d, operator[2]);
        res = operate(a, f, operator[0]);
        if(checkAnswer(res)){
            String answer = new String();
            answer += convertNumber(cardValue[0]) + " " + operator[0] + " ((" + convertNumber(cardValue[1]) + " ";
            answer += operator[1] + " " +  convertNumber(cardValue[2]) + ") "+ operator[2] + " " + convertNumber(cardValue[3]) + ")";
            answerList.add(answer);
        }
        
        // (a op1 (b op2 c)) op3 d
        e = operate(b, c, operator[1]);
        f = operate(a, e, operator[0]);
        res = operate(f, d, operator[2]);
        if(checkAnswer(res)){
            String answer = new String();
            answer += "(" + convertNumber(cardValue[0]) + " " + operator[0] + " (" + convertNumber(cardValue[1]) + " ";
            answer += operator[1] + " " + convertNumber(cardValue[2]) + ")) " + operator[2] + " " + convertNumber(cardValue[3]);
            answerList.add(answer);
        }
    }

    public static ArrayList<String> solve(int[] cardValue){
        int copyCard[] = copyArrayInt(cardValue);
        countingSort(copyCard);
        generatePermutation(copyCard, 0);
        generateOperatorCombination(new char[3], 0);

        for(int i=0;i<permutation.size();i++){
            for(int j=0;j<operations.size();j++){
                getAnswer(permutation.get(i), operations.get(j));
            }
        }
        
        ArrayList<String> answerCopy = new ArrayList<String>(answerList);
        answerList.clear();
        permutation.clear();
        operations.clear();
        return answerCopy;
    }

    public static void main(String args[]){
        int arr[] = {1,8,9,12};
        solve(arr);
        System.out.println(answerList.size());
    }
}
