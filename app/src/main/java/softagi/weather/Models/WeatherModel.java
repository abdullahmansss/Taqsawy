package softagi.weather.Models;

public class WeatherModel
{
    String city,status,icon,last_update,winddir;
    double temp,wind;
    int humidity;

    public WeatherModel() {
    }

    public WeatherModel(String city, String status, String icon, String last_update, String winddir, double temp, double wind, int humidity) {
        this.city = city;
        this.status = status;
        this.icon = icon;
        this.last_update = last_update;
        this.winddir = winddir;
        this.temp = temp;
        this.wind = wind;
        this.humidity = humidity;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getLast_update() {
        return last_update;
    }

    public void setLast_update(String last_update) {
        this.last_update = last_update;
    }

    public String getWinddir() {
        return winddir;
    }

    public void setWinddir(String winddir) {
        this.winddir = winddir;
    }

    public double getTemp() {
        return temp;
    }

    public void setTemp(double temp) {
        this.temp = temp;
    }

    public double getWind() {
        return wind;
    }

    public void setWind(double wind) {
        this.wind = wind;
    }

    public int getHumidity() {
        return humidity;
    }

    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }
}
