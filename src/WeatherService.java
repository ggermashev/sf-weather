import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import java.io.IOException;
import java.io.StringReader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;


public class WeatherService {

    WeatherService() {

    }

    public JsonObject getWeather(double lat, double lon) throws IOException, InterruptedException {
        var client = HttpClient.newHttpClient();

        var request = HttpRequest
                .newBuilder(URI.create("https://api.weather.yandex.ru/v2/forecast?lat=".concat(Double.toString(lat)).concat("&lon=").concat(Double.toString(lon))))
                .header("X-Yandex-Weather-Key", "0fa2a6be-a1fd-493a-bb29-e29840a15020")
                .header("accept", "application/json")
                .build();

        var weather =  client.send(request, HttpResponse.BodyHandlers.ofString()).body();

        JsonReader reader = Json.createReader(new StringReader(weather));
        JsonObject json = reader.readObject();

        return json;
    }

    public JsonObject getWeather(double lat, double lon, int limit) throws IOException, InterruptedException {
        var client = HttpClient.newHttpClient();

        var request = HttpRequest
                .newBuilder(URI.create("https://api.weather.yandex.ru/v2/forecast?lat=".concat(Double.toString(lat)).concat("&lon=").concat(Double.toString(lon)).concat("&limit=").concat(Integer.toString(limit))))
                .header("X-Yandex-Weather-Key", "0fa2a6be-a1fd-493a-bb29-e29840a15020")
                .header("accept", "application/json")
                .build();

        var weather =  client.send(request, HttpResponse.BodyHandlers.ofString()).body();

        JsonReader reader = Json.createReader(new StringReader(weather));
        JsonObject json = reader.readObject();

        return json;
    }




}
