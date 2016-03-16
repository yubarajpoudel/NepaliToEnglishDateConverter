package com.yuvi.dateconverter;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.yuvi.nepalitoenglishdateconverter.DateConverter;
import com.yuvi.nepalitoenglishdateconverter.DateHolder;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by yubraj on 3/16/16.
 */

public class DateConverterActivity extends AppCompatActivity {
    String selectedItem = "AD";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dateconverter);

        final EditText edt_date = (EditText) findViewById(R.id.edt_date);

        Spinner spnr_bs_ad = (Spinner) findViewById(R.id.spnr_bs_ad);
        final String date_format = (String) spnr_bs_ad.getSelectedItem();

        final Button btn_convert = (Button) findViewById(R.id.btn_convert);

        spnr_bs_ad.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                selectedItem = (String) adapterView.getSelectedItem();
                if (selectedItem.equalsIgnoreCase("AD")) {
                    btn_convert.setText("CONVERT TO " + "BS");
                } else {
                    btn_convert.setText("CONVERT TO " + "AD");
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        btn_convert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String date = edt_date.getText().toString();
                String convertedDate = "";

                if (isvalidateDateFormat(date)) {
                    if (selectedItem.equals("AD")) {
                        DateHolder dateHolder = new DateConverter().convertADToBS(date, "-");
                        convertedDate = dateHolder.year + "-" + dateHolder.month + "-" + dateHolder.dayOfMonth;
                    } else {
                        convertedDate = new DateConverter().convertBSToAD(date, "-");
                    }
                    ((TextView) findViewById(R.id.tv_result)).setText(convertedDate);

                } else {
                    edt_date.setError("invalid date");
                }

            }
        });
    }

    public boolean isvalidateDateFormat(String dateToValdate) {
        boolean isValid = true;
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            Date dt = format.parse(dateToValdate);
        } catch (Exception e) {
            e.printStackTrace();
            isValid = false;
        }
        return isValid;
    }
}
