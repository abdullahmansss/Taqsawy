package softagi.weather;

import android.text.TextUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;

import javax.net.ssl.HttpsURLConnection;

import softagi.weather.Models.WeatherModel;

public class Utils
{
    static String city,status,icon,last_update,winddir;
    static double temp,wind;
    static int humidity;

    private static URL createURL (String api) throws MalformedURLException
    {
        URL url = new URL(api);
        return url;
    }

    private static String makeHTTPrequest (URL url) throws IOException
    {
        String response = "";

        if (url == null)
        {
            return response;
        }

        HttpURLConnection httpURLConnection = null;
        InputStream inputStream = null;

        try
        {
            httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.connect();

            if (httpURLConnection.getResponseCode() == 200)
            {
                inputStream = httpURLConnection.getInputStream();
                response = readFromstream(inputStream);
            }
        } catch (IOException e)
        {
            e.printStackTrace();
        }

        finally
        {
            if (httpURLConnection != null)
            {
                httpURLConnection.disconnect();
            }

            if (inputStream != null)
            {
                inputStream.close();
            }
        }
        return response;
    }

    private static String readFromstream (InputStream inputStream) throws IOException
    {
        StringBuilder stringBuilder = new StringBuilder();

        if (inputStream != null)
        {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("utf-8"));
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            String line = bufferedReader.readLine();

            while (line != null)
            {
                stringBuilder.append(line);
                line = bufferedReader.readLine();
            }
        }
        return stringBuilder.toString();
    }

    private static WeatherModel extractDatafromJSON (String json) throws JSONException
    {
        if (TextUtils.isEmpty(json))
        {
            return null;
        }

        JSONObject root = new JSONObject(json);
        city = root.getJSONObject("location").getString("name");
        JSONObject current = root.getJSONObject("current");
        last_update = current.getString("last_updated");
        temp = current.getDouble("temp_c");
        wind = current.getDouble("wind_kph");
        winddir = current.getString("wind_dir");
        humidity = current.getInt("humidity");
        status = current.getJSONObject("condition").getString("text");
        icon = current.getJSONObject("condition").getString("icon");

        WeatherModel weatherModel = new WeatherModel(city,status,icon,last_update,winddir,temp,wind,humidity);

        return weatherModel;
    }

    public static WeatherModel utils (String api) throws Exception
    {
        URL url = createURL(api);
        String json = makeHTTPrequest(url);
        WeatherModel weatherModel = extractDatafromJSON(json);

        return weatherModel;
    }
}
