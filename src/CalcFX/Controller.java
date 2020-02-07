package CalcFX;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.text.Text;

public class Controller {
    @FXML
    private Text output;

    private String operator = "";
    private long num1 = 0;
    private long num2 = 0;
    private Model model = new Model();

    @FXML
    private void processNumpad(ActionEvent event){
        String value = ((Button)event.getSource()).getText();
        output.setText(output.getText() + value);
    }
    @FXML
    private void processOperator(ActionEvent event){
        String value = ((Button)event.getSource()).getText();
        System.out.println(value);
        if(!(value.equals("="))){
            operator = value;
            num1 = Long.parseLong(output.getText());
            output.setText("");
        } else {
            num2 = Long.parseLong(output.getText());
            output.setText(String.valueOf(model.calculate(num1, num2, operator)));
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
            output.setText("");
        }



    }
}
