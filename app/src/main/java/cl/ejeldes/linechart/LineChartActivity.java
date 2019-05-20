package cl.ejeldes.linechart;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.LegendEntry;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.formatter.IValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.utils.ViewPortHandler;

import java.util.ArrayList;
import java.util.List;

public class LineChartActivity extends AppCompatActivity {

    private LineChart lineChart;
    private int[] colorArray = {
            Color.MAGENTA,
            Color.BLUE,
            Color.GREEN,
            Color.RED
    };
    private String[] legendNames = {"Dog", "Cow", "Fish", "Duck"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_line_chart);

        lineChart = findViewById(R.id.lineChart);

        // Obtain Axis
        XAxis xAxis = lineChart.getXAxis();
        YAxis yAxisLeft = lineChart.getAxisLeft();
        YAxis yAxisRight = lineChart.getAxisRight();

        xAxis.setValueFormatter(new XAxisFormatter());
        yAxisLeft.setValueFormatter(new YLeftAxisFormatter());
        yAxisRight.setValueFormatter(new YRightAxisFormatter());

        LineDataSet lineDataSet1 = new LineDataSet(dataValues1(), "Data Set 1");
        LineDataSet lineDataSet2 = new LineDataSet(dataValues2(), "Data Set 2");

        List<ILineDataSet> lineDataSets = new ArrayList<>();
        lineDataSets.add(lineDataSet1);
        lineDataSets.add(lineDataSet2);

        LineData lineData = new LineData(lineDataSets);

        // Get colors from resources as int
        // ResourcesCompat.getColor(ctx, R.color.someColor, theme(can be null))

        // Change Background color
        lineChart.setBackgroundColor(Color.WHITE);

        // Set No data Text and Color
        lineChart.setNoDataText("No Data Longi qliao");
        lineChart.setNoDataTextColor(Color.BLUE);

        // Set chart background (only the data portion)
        lineChart.setDrawGridBackground(false);
        lineChart.setGridBackgroundColor(Color.YELLOW);

        // Set borders of the Data portion
        lineChart.setDrawBorders(false);
        lineChart.setBorderColor(Color.RED);
        lineChart.setBorderWidth(5f);

        // Set Description
        Description description = new Description();
        description.setText("Zoo");
        description.setTextColor(Color.BLUE);
        description.setTextSize(25f);

        // Legend
        Legend legend = lineChart.getLegend();
        legend.setEnabled(true);
        legend.setTextColor(Color.RED);
        legend.setTextSize(15);
        // Form (shape)
        legend.setForm(Legend.LegendForm.SQUARE);
        legend.setFormSize(20);
        // space between shape and text
        legend.setXEntrySpace(20);
        legend.setFormToTextSpace(20);

        // Legend Entries
        LegendEntry[] legendEntries = new LegendEntry[legendNames.length];

        for (int i = 0; i < legendEntries.length; i++) {
            LegendEntry entry = new LegendEntry();
            entry.formColor = colorArray[i];
            entry.label = legendNames[i];
            legendEntries[i] = entry;
        }

        // set LegendEntries
        legend.setCustom(legendEntries);

        // LineDataSets Colors and width
        lineDataSet1.setColor(ResourcesCompat.getColor(this.getResources(), R.color.colorAccent, null));
        lineDataSet1.setLineWidth(5);

        int[] colors = {R.color.color1, R.color.color2, R.color.color3, R.color.color4};
        // LineDataSet Colors in array
        lineDataSet2.setColors(colors, this);

        // LineDataSet Circles
        lineDataSet1.setDrawCircles(true);
        lineDataSet1.setDrawCircleHole(true);
        lineDataSet1.setCircleColor(ResourcesCompat.getColor(this.getResources(), R.color.colorPrimary, null));
        lineDataSet1.setCircleHoleRadius(5);
        lineDataSet1.setCircleRadius(6);

        // LinaDataSet Values
        lineDataSet1.setValueTextSize(15);
        lineDataSet1.setValueTextColor(Color.BLUE);
        lineDataSet1.enableDashedLine(10, 10, 50);


        // Pass Value Formatter
        lineData.setValueFormatter(new MyValueFormatter());

        lineChart.setDescription(description);
        lineChart.setData(lineData);
        lineChart.invalidate();
    }

    private ArrayList<Entry> dataValues1() {
        ArrayList<Entry> dataVals = new ArrayList<>();
        dataVals.add(new Entry(0, 20));
        dataVals.add(new Entry(1, 4));
        dataVals.add(new Entry(2, 24));
        dataVals.add(new Entry(2, 2));
        dataVals.add(new Entry(7, 10));
        return dataVals;
    }

    private ArrayList<Entry> dataValues2() {
        ArrayList<Entry> dataVals = new ArrayList<>();
        dataVals.add(new Entry(0, 12));
        dataVals.add(new Entry(2, 16));
        dataVals.add(new Entry(3, 23));
        dataVals.add(new Entry(5, 1));
        dataVals.add(new Entry(7, 18));
        return dataVals;
    }

    /**
     * You can format points showed on the chart
     * You need to pass this class to the LineData Object OR LineDataSet
     */
    private class MyValueFormatter implements IValueFormatter {
        @Override
        public String getFormattedValue(float value, Entry entry, int dataSetIndex, ViewPortHandler viewPortHandler) {
            return "$ " + value;
        }
    }

    /**
     * You can format Axis of the char using this
     * You need to get the axis from LineChart object
     * <p>
     * There are 3 types of axis:
     * 1. XAxis
     * 2. YAxis:
     * . Left
     * . Right
     */
    private class XAxisFormatter implements IAxisValueFormatter {

        @Override
        public String getFormattedValue(float value, AxisBase axis) {
            axis.setAxisMaximum(10);
            axis.setAxisMinimum(0);
            axis.setLabelCount(3, true);
            axis.setTextSize(10);
            return "" + value;
        }
    }

    private class YLeftAxisFormatter implements IAxisValueFormatter {

        @Override
        public String getFormattedValue(float value, AxisBase axis) {
            axis.setLabelCount(10, true);
            axis.setAxisMinimum(0);
            axis.setAxisMaximum(40);
            axis.setTextSize(12);
            return "" + (int) value;
        }
    }

    private class YRightAxisFormatter implements IAxisValueFormatter {

        @Override
        public String getFormattedValue(float value, AxisBase axis) {
            axis.setDrawAxisLine(false);
            return "";
        }
    }
}
