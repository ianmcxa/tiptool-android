package org.mcxa.tiptool;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnTextChanged;

public class MainActivity extends AppCompatActivity {
    static final double DEFAULT_TIP_FACTOR = .15;

    @BindView(R.id.tipAmount)
    TextView tipAmount;
    @BindView(R.id.totalAmount)
    TextView totalAmount;

    @BindView(R.id.billInput)
    EditText billInput;

    @BindView(R.id.tipFactor)
    EditText tipFactorInput;
    double tipFactor = DEFAULT_TIP_FACTOR;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        tipFactorInput.setText(String.format(Locale.getDefault(), "%.2f", DEFAULT_TIP_FACTOR));
    }

    @OnTextChanged(R.id.tipFactor)
    public void onTipFactorInputChanged() {
        String tipFactorString = tipFactorInput.getText().toString();
        try {
            tipFactor = Double.parseDouble(tipFactorInput.getText().toString());
            //we need to update the tip here too
            onBillInputChanged();
        } catch(NumberFormatException e) {
            Log.d("MAIN", "Invalid number" + tipFactorString);
        }
    }

    @OnTextChanged(R.id.billInput)
    public void onBillInputChanged() {
        String billString = billInput.getText().toString();

        if (billString.length() > 0) {
            Double billNumber = ((double) Math.round(Double.parseDouble(billString) * 100)) / 100;
            Double tipNumber = ((double) Math.round(billNumber * tipFactor * 100)) / 100;
            Double totalNumber = tipNumber + billNumber;

            tipAmount.setText(String.format(getResources().getString(R.string.tip_format), tipNumber));
            totalAmount.setText(String.format(getResources().getString(R.string.total_format), totalNumber));
        } else {
            tipAmount.setText(getResources().getString(R.string.tip));
            totalAmount.setText(getResources().getString(R.string.total));
        }
    }

}
