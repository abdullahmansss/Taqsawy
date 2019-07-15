package softagi.weather;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.squareup.picasso.Picasso;

import softagi.weather.Models.WeatherModel;

public class MainActivity extends AppCompatActivity
{
    EditText search;

    ImageView icon;
    TextView temp,status,location,lastupdate,wind,winddir,humidity;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        search = findViewById(R.id.search_city);
        icon = findViewById(R.id.icon);
        temp = findViewById(R.id.temp);
        status = findViewById(R.id.status);
        location = findViewById(R.id.location);
        lastupdate = findViewById(R.id.last_update);
        wind = findViewById(R.id.wind);
        winddir = findViewById(R.id.wind_dir);
        humidity = findViewById(R.id.humidity);
    }

    public void search(View view)
    {
        String city = search.getText().toString();

        if (TextUtils.isEmpty(city))
        {
            Toast.makeText(getApplicationContext(), "enter city name", Toast.LENGTH_SHORT).show();
            return;
        }

        new weatherBackground().execute("http://api.apixu.com/v1/current.json?key=12064d954629488f832185500190407&q=" + city);
    }

    class weatherBackground extends AsyncTask<String, Void, WeatherModel>
    {
        @Override
        protected WeatherModel doInBackground(String... strings)
        {
            WeatherModel weatherModel = null;

            try
            {
                weatherModel = Utils.utils(strings[0]);
            } catch (Exception e)
            {
                e.printStackTrace();
            }
            return weatherModel;
        }

        @Override
        protected void onPostExecute(WeatherModel weatherModel)
        {
            if (weatherModel == null)
            {
                Toast.makeText(getApplicationContext(), "empty", Toast.LENGTH_SHORT).show();
            } else
                {
                    updateUI(weatherModel);
                }
        }
    }

    public void updateUI (WeatherModel weatherModel)
    {
        location.setText(weatherModel.getCity());
        temp.setText(String.valueOf(weatherModel.getTemp()));
        status.setText(weatherModel.getStatus());
        lastupdate.setText(weatherModel.getLast_update());
        wind.setText(String.valueOf(weatherModel.getWind()));
        winddir.setText(weatherModel.getWinddir());
        humidity.setText(String.valueOf(weatherModel.getHumidity()));

        Picasso.get()
                .load(weatherModel.getIcon())
                .error(R.drawable.ic_cloudy)
                .placeholder(R.drawable.ic_cloudy)
                .into(icon);
    }

    @Override
    public void onBackPressed()
    {
        finishAffinity();
    }
}
