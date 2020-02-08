package CalcFX;

public class Model {

    public double calculate(double num1, double num2, String operator){
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

    public double singleOperator(double num, String operator){
        switch (operator){
            case "%":
                return num / 100;
            case "sqrt":
                if (num >= 0){
                    return Math.sqrt(num);
                } else {
                    System.out.println("Cannot do the root of a negative number !");
                }
            case "x^2":
                return Math.pow(num, 2);
            case "1/x":
                if (num != 0){
                    return 1/num;
                } else {
                    System.out.println("Cannot divide by zero !");
                }
                break;
        }
        System.out.println("Unknown operator : " + operator);
        return 0;
    }

}
