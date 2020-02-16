package CalcFX;

import java.util.Stack;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Model {

    private String res = "";

    public String calculate(double num1, double num2, String operator){
        switch (operator){
            case "+":
                res = String.valueOf(num1 + num2);
                if (isInt(res)){
                    return res.substring(0, res.length() - 2);
                } else {
                    return res;
                }
            case "-":
                res = String.valueOf(num1 - num2);
                if (isInt(res)){
                    return res.substring(0, res.length() - 2);
                } else {
                    return res;
                }
            case "*":
                res = String.valueOf(num1 * num2);
                if (isInt(res)){
                    return res.substring(0, res.length() - 2);
                } else {
                    return res;
                }
            case "/":
                if(num2 != 0){
                    res = String.valueOf(num1 / num2);
                    if (isInt(res)){
                        return res.substring(0, res.length() - 2);
                    } else {
                        return res;
                    }
                } else {
                    System.out.println("Cannot divide by zero !");
                }
                break;
        }
        System.out.println("Unknown operator : " + operator);
        return "";
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

    public String process (String operations){
        String opPost = toPostfix(operations);
        StringBuilder result = new StringBuilder();
        StringTokenizer st = new StringTokenizer(opPost);
        Stack number = new Stack();
        while (st.hasMoreTokens()){
            String token = st.nextToken();
            if (isNumeric(token)){
                double num = Double.parseDouble(token);
                number.push(token);
            } else {
                double num2 = Double.parseDouble((String)number.pop());
                double num1 = Double.parseDouble((String)number.pop());
                number.push(calculate(num1, num2, token));
            }
        }
        result.append(number.pop());
        Pattern pattern = Pattern.compile("\\.0$");
        Matcher m = pattern.matcher(result);
        if (m.find()){
            return result.delete(m.start(), m.end()).toString();
        }
        return result.toString();
    }

    private static String toPostfix(String expression) {
        StringTokenizer st = new StringTokenizer(expression);
        StringBuilder buffer = new StringBuilder();
        StringBuilder result = new StringBuilder();
        Stack operator = new Stack();
        boolean isOp = false;
        while (st.hasMoreTokens()) {
            buffer.replace(0, buffer.length(), st.nextToken());
            if (isNumeric(buffer.toString())){
                result.append(buffer);
                if (!isOp){
                    result.append(" ");
                } else {
                    result.append(" ").append(operator.pop()).append(" ");
                    isOp = false;
                }
            } else {
                operator.push(buffer.toString());
                isOp = true;
            }
        }
        return result.toString();
    }

    private static boolean isNumeric (String str){
        return str.matches("-?\\d+(\\.\\d+)?");
    }
    private static boolean isInt (String str){
        Pattern pattern = Pattern.compile("\\.0$");
        Matcher m = pattern.matcher(str);
        return m.find();
    }

}
