package superscene.com.mybattery;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.SeekBar;

import superscene.com.mybattery.view.BatteryView;

public class MainActivity extends AppCompatActivity implements SeekBar.OnSeekBarChangeListener {

    private SeekBar see;
    private BatteryView bat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        see = (SeekBar) findViewById(R.id.seek);
        bat = (BatteryView) findViewById(R.id.btn);
        int jd = bat.getPower();
        see.setProgress(jd);
        assert see != null;
        see.setOnSeekBarChangeListener(this);
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        bat.setPower(progress);
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {
        seekBar.setBackgroundColor(Color.parseColor("#000000"));

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        seekBar.setBackgroundColor(Color.parseColor("#ffffff"));
    }
}
