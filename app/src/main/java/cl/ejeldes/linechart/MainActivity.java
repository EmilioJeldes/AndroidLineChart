package cl.ejeldes.linechart;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btn_linechart).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent lineChartIntent = new Intent(MainActivity.this, LineChartActivity.class);
                startActivity(lineChartIntent);
            }
        });

        findViewById(R.id.btn_barchart).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent barChartActivity = new Intent(MainActivity.this, BarChartActivity.class);
                startActivity(barChartActivity);
            }
        });
    }

}
