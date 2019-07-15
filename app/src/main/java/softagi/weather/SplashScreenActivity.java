package softagi.weather;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import java.util.Timer;
import java.util.TimerTask;

public class SplashScreenActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        TimerTask timerTask = new TimerTask()
        {
            @Override
            public void run()
            {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
            }
        };

        new Timer().schedule(timerTask, 3000);
    }

    @Override
    public void onBackPressed() { }
}
