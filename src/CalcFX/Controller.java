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
    private double num1 = 0;
    private double num2 = 0;
    private double numTemp = 0; // variable used to store the result of a single operand operation
    private boolean reset = false; // variable set to true if "=" is pressed. Used to reset display for next operation
    private Model model = new Model();

    @FXML
    private void processNumpad(ActionEvent event){
        String value = ((Button)event.getSource()).getText();
        if (reset == true){
            operationOut.setText("");
            output.setText("");
            num1 = 0;
            num2 = 0;
            reset = false;
        }
        output.setText(output.getText() + value);

    }
    @FXML
    private void processOperator(ActionEvent event){
        String value = ((Button)event.getSource()).getText();
        if (reset == true){
            operationOut.setText("");
            output.setText("");
            num1 = 0;
            num2 = 0;
            reset = false;
        }
        if (value.equals("%") || value.equals("sqrt") || value.equals("1/x") || value.equals("x^2")){
            operator = value;
            numTemp = Double.parseDouble(output.getText());
            operationOut.setText(operationOut.getText() + " " + numTemp + " " + operator);
            numTemp = model.singleOperator(numTemp, operator);
            output.setText(String.valueOf(numTemp));
        } else if(!(value.equals("="))){
            operator = value;
            if(output.getText().equals("")){
                num1 = 0;
                operationOut.setText(operationOut.getText() + " " + num1 + " " + operator);
            } else {
                num1 = Double.parseDouble(output.getText());
                output.setText("");
                operationOut.setText(operationOut.getText() + " " + num1 + " " + operator);
            }
        } else {
            if(output.getText().equals("")){
                num2 = 0;
                output.setText(String.valueOf(model.calculate(num1, num2, operator)));
                operationOut.setText(operationOut.getText() + " " + num2);
                reset = true;
            } else {
                num2 = Double.parseDouble(output.getText());
                output.setText(String.valueOf(model.calculate(num1, num2, operator)));
                operationOut.setText(operationOut.getText() + " " + num2);
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
            num1 = 0;
            num2 = 0;
            output.setText("");
            operationOut.setText("");
        }
    }
    @FXML
    private void processElem(ActionEvent event){
        String value = ((Button)event.getSource()).getText();
        if (reset == true){
            operationOut.setText("");
            output.setText("");
            num1 = 0;
            num2 = 0;
            reset = false;
        }
        if (value.equals(".")) {
            output.setText(output.getText() + ".");
        } else {
            output.setText("-" + output.getText());
        }
    }
}
