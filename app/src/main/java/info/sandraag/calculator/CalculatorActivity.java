package info.sandraag.calculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class CalculatorActivity extends AppCompatActivity {

    private String curr_num = ""; // "Model" number (to store the input number)
    private String prev_num = ""; // "Model" previous number (to remember the last input number)
    private TextView num_view = null; // Contains a copy of curr_num or prev_num

    private Button operator = null; // Reference to the last operator selected
    private boolean is_equals = false; // To check if the equals button has been clicked

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);

        num_view = findViewById(R.id.curr_num);

        num_view.setText(curr_num);
    }

    public void onClickDigit(View view) {

        Button b = (Button)view;

        // Reset the current number if the equals button has been clicked
        if (is_equals) {

            curr_num = "";
            operator = null;
            is_equals = false;
        }

        curr_num += b.getText().toString().charAt(0);

        num_view.setText(curr_num);
    }

    public void onClickDot(View view) {

        // Reset the current number if the equals button has been clicked
        if (is_equals) {

            curr_num = "";
            operator = null;
            is_equals = false;
        }

        if (!curr_num.contains(".")) {

            Button b = (Button) view;

            curr_num += b.getText().toString().charAt(0);

            num_view.setText(curr_num);
        }
    }

    public void onClickOperator(View view) {

        Button new_operator = (Button)view;

        if (curr_num.equals("."))
            return;

        if (!curr_num.equals("")) {

            if (operator != null && !is_equals) {
                
                // Overwrite previous number with result
                prev_num = Operate(operator.getId());

                // Display the result
                num_view.setText(prev_num);
            } else {

                // Overwrite previous number with current number
                prev_num = curr_num;
            }
        }

        curr_num = "";
        is_equals = false;

        // Save new operator
        operator = new_operator;
    }

    public void onClickEquals(View view) {

        if (curr_num.equals("."))
            return;

        if (operator != null) {

            if (curr_num.equals(""))
                // Overwrite current number with previous number
                curr_num = prev_num;

            // Overwrite both current and previous numbers with result
            curr_num = prev_num = Operate(operator.getId());

            // Display the result
            num_view.setText(prev_num);
        }

        is_equals = true;
    }

    public void onClickClear(View view) {

        prev_num = curr_num = "";

        num_view.setText(curr_num);

        is_equals = false;

        operator = null;
    }

    private String Operate(int operation_id) {

        double curr_num_value = Double.parseDouble(curr_num);
        double prev_num_value = Double.parseDouble(prev_num);
        double result_value = 0;

        switch (operation_id)
        {
            case R.id.btn_plus:
                result_value = prev_num_value + curr_num_value;
                break;

            case R.id.btn_minus:
                result_value = prev_num_value - curr_num_value;
                break;

            case R.id.btn_multiplication:
                result_value = prev_num_value * curr_num_value;
                break;

            case R.id.btn_division:
                result_value = prev_num_value / curr_num_value;
                break;

            default:
                break;
        }

        if (result_value % 1 == 0) { // All integers are modulo of 1

            int simplified_result_value = (int)result_value;
            return Integer.toString(simplified_result_value);
        }

        return Double.toString(result_value);
    }
}
