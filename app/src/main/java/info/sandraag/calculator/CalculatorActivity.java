package info.sandraag.calculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class CalculatorActivity extends AppCompatActivity {

    private String num = ""; // "model" number
    private TextView num_view; // contains a copy of 'num'

    private String result = ""; // "model" last number

    private int operation_id = 0; // operation selected id
    private boolean operator_clicked = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);

        num_view = findViewById(R.id.curr_num);
        num_view.setText(num);
    }

    public void onClickDigit(View view) {

        Button b = (Button)view;

        num += b.getText().toString().charAt(0);
        num_view.setText(num);
    }

    public void onClickDot(View view) {

        //if (num_view.getText() != "") {
            //if (num_view.getText().charAt(num_view.length() - 1) != '.') {
                Button b = (Button) view;
                num += b.getText().toString().charAt(0);
                num_view.setText(num);
            //}
        //}
    }

    public void onClickOperator(View view) {

        Button b = (Button)view;

        if (operator_clicked) {
            num = Operate(operation_id);

            // Update text view
            num_view.setText(num);
        }
        else {
            // Clean text view
            num_view.setText("");

            operator_clicked = true;
            operation_id = b.getId();

            result = num;
            num = "";
        }
    }

    public void onClickEquals(View view) {

        Button b = (Button) view;
        num = Operate(operation_id);

        // Update text view
        num_view.setText(num);

        operator_clicked = false;
    }

    private String Operate(int operation_id)
    {
        double num_value = Double.parseDouble(num);
        double result_value = Double.parseDouble(result);

        switch (operation_id)
        {
            case R.id.btn_plus:
                result_value += num_value;
                break;

            case R.id.btn_minus:
                result_value -= num_value;
                break;

            case R.id.btn_multiplication:
                result_value *= num_value;
                break;

            case R.id.btn_division:
                result_value /= num_value;
                break;
        }

        result = Double.toString(result_value);
        return result;
    }
}
