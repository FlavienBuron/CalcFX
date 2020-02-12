package CalcFX;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.text.Text;

public class Controller {
    @FXML
    private Text output;
    @FXML
    private Text operationOut;

    private String operator = "";
    private String prevOperator = "";
    private String[] OPERAND = {"0","0"} ;
    private int count = 0;
    private int nbOp = 0; //number of operators being used. Used for control
    private double numTemp = 0; // variable used to store the result of a single operand operation
    private boolean reset = false; // variable set to true if "=" is pressed. Used to reset calculator for next operation
    private boolean isOpUsed = false;
    private Model model = new Model();

    @FXML
    private void processNumpad(ActionEvent event){
        String value = ((Button)event.getSource()).getText();
        if (reset == true){
            operationOut.setText("");
            output.setText("");
            nbOp = 0;
            String[] OPERAND = {"0","0"};
            count = 0;
            isOpUsed = false;
            reset = false;
        } else if (isOpUsed){
            output.setText("");
            isOpUsed = false;
        }
        output.setText(output.getText() + value);

    }
    @FXML
    private void processOperator(ActionEvent event){
        if (!(operator.equals("") || operator.equals("="))){
            prevOperator = operator;
        }
        operator = ((Button)event.getSource()).getText();
        if (operator.equals("%") || operator.equals("sqrt") || operator.equals("1/x") || operator.equals("x^2")){
            if (output.getText().equals("0")){
                numTemp = 0;
            } else {
                numTemp = Double.parseDouble(output.getText());
            }
            numTemp = model.singleOperator(numTemp, operator);
            output.setText(String.valueOf(numTemp));
        } else if(!(operator.equals("="))){
            if (nbOp % 2 == 1){
                if(output.getText().equals("")){
                    OPERAND[count++] = "0";
                    operationOut.setText(operationOut.getText() + " " + OPERAND[count - 1] + " " + operator);
                    double res = model.calculate(Double.parseDouble(OPERAND[count - 2]), Double.parseDouble(OPERAND[count - 1]), prevOperator);
                    OPERAND[count--] = String.valueOf(res);
                    output.setText(String.valueOf(res));
                    nbOp++;
                    isOpUsed = true;
                } else {
                    System.out.println(count);
                    OPERAND[count++] = output.getText();
                    operationOut.setText(operationOut.getText() + " " + OPERAND[count - 1] + " " + operator);
                    double res = model.calculate(Double.parseDouble(OPERAND[count - 2]), Double.parseDouble(OPERAND[count - 1]), prevOperator);
                    OPERAND[count - 1] = String.valueOf(res);
                    count = 0;
                    output.setText(String.valueOf(res));
                    nbOp++;
                    isOpUsed = true;
                }
            } else if (nbOp == 0) {
                if (output.getText().equals("")) {
                    OPERAND[count++] = "0";
                    operationOut.setText(operationOut.getText() + " " + OPERAND[count - 1] + " " + operator);
                    nbOp++;
                    isOpUsed = true;
                } else {
                    OPERAND[count++] = output.getText();
                    operationOut.setText(operationOut.getText() + " " + OPERAND[count - 1] + " " + operator);
                    if (operator.equals("*") || operator.equals("/")){
                        OPERAND[count] = "1";
                    }
                    double res = model.calculate(Double.parseDouble(OPERAND[count - 1]), Double.parseDouble(OPERAND[count]), operator);
                    OPERAND[count - 1] = String.valueOf(res);
                    output.setText(String.valueOf(res));
                    nbOp++;
                    isOpUsed = true;
                }
            } else {
                if (output.getText().equals("")) {
                    OPERAND[count++] = "0";
                    operationOut.setText(operationOut.getText() + " " + OPERAND[count - 1] + " " + prevOperator);
                    nbOp++;
                    isOpUsed = true;
                } else {
                    OPERAND[count++] = output.getText();
                    operationOut.setText(operationOut.getText() + " " + OPERAND[count - 1] + " " + operator);
                    double res = model.calculate(Double.parseDouble(OPERAND[count]), Double.parseDouble(OPERAND[count - 1]), prevOperator);
                    OPERAND[count - 1] = String.valueOf(res);
                    output.setText(String.valueOf(res));
                    nbOp++;
                    isOpUsed = true;
                }
            }
        } else {
            if(output.getText().equals("")){
                operationOut.setText(operationOut.getText() + " " + "0" + operator);
                String operation = operationOut.getText();
                operationOut.setText(operation + " =");
                output.setText(model.process(operation));
                reset = true;
            } else {
                OPERAND[count++] = output.getText();
                operationOut.setText(operationOut.getText() + " " + OPERAND[count - 1]);
                String operation = operationOut.getText();
                operationOut.setText(operation + " =");
                output.setText(model.process(operation));
                reset = true;
            }
        }
    }
    @FXML
    private void processDel(ActionEvent event){
        String num = output.getText();
        String value = ((Button)event.getSource()).getText();

        if (value.equals("DEL")){
            if (num.length() > 0){
                output.setText(num.substring(0, num.length()-1));
            } else {
                output.setText("");
            }
        } else if (value.equals("C")){
            output.setText("");
        } else if (value.equals("CE")){
            OPERAND[0] = "0";
            OPERAND[1] = "0";
            count = 0;
            output.setText("");
            operationOut.setText("");
            nbOp = 0;
            isOpUsed = false;
        }
    }
    @FXML
    private void processElem(ActionEvent event){
        String value = ((Button)event.getSource()).getText();
        if (reset == true){
            operationOut.setText("");
            output.setText("");
            reset = false;
        }
        if (value.equals(".")) {
            output.setText(output.getText() + ".");
        } else {
            output.setText("-" + output.getText());
        }
    }
}
