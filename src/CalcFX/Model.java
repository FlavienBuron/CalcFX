package CalcFX;

public class Model {

    public long calculate(long num1, long num2, String operator){
        switch (operator){
            case "+":
                return num1 + num2;
            case "-":
                return num1 - num2;
            case "*":
                return num1 * num2;
            case "/":
                if(num2 != 0){
                    return num1 / num2;
                } else {
                    System.out.println("Cannot divide by zero !");
                }
                break;
        }
        System.out.println("Unknown operator : " + operator);
        return 0;
    }
}
