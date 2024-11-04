import javax.json.JsonObject;
import javax.json.JsonValue;
import java.util.function.BinaryOperator;


public class Main {
    public static void main(String[] args) {
        WeatherService service = new WeatherService();

        JsonObject weather;
        try {
            weather = service.getWeather(55, 37);

            System.out.println("Все данные:");
            System.out.println(weather);

            System.out.println("\nТемпература:");
            int temp = weather.getJsonObject("fact").getInt("temp");
            System.out.println(temp);

            int limit = 7;
            var weatherWithLimit = service.getWeather(55, 37, limit);
            var forecasts = weatherWithLimit.getJsonArray("forecasts");

            int tempSum = 0;
            for (int i = 0; i < limit; i++) {
                var forecast = forecasts.getJsonObject(i);
                var day = forecast.getJsonObject("parts").getJsonObject("day");
                tempSum += day.getInt("temp_avg");
            }
            int avgTemp = tempSum / limit;

            System.out.println("\nСредняя температура:");
            System.out.println(avgTemp);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
