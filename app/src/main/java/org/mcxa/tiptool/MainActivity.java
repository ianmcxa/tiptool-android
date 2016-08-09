package org.mcxa.tiptool;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnTextChanged;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.tipAmount)
    TextView tipAmount;
    @BindView(R.id.totalAmount)
    TextView totalAmount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnTextChanged(R.id.billInput)
    public void onBillInputChanged( CharSequence billInput) {
        String billString = billInput.toString();

        if (billString.length() > 0) {
            Long billNumber = Math.round(Double.parseDouble(billString) * 100) / 100;
            Double tipNumber = billNumber * .15;
            Double totalNumber = tipNumber + billNumber;

            tipAmount.setText(String.format(getResources().getString(R.string.tip_format), tipNumber.toString()));
            totalAmount.setText(String.format(getResources().getString(R.string.total_format), totalNumber.toString()));
        } else {
            tipAmount.setText(getResources().getString(R.string.tip));
            totalAmount.setText(getResources().getString(R.string.total));
        }
    }

}
