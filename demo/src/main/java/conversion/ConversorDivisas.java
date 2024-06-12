package conversion;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONObject;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Scanner;

public class ConversorDivisas {

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Divisa a convertir");
        String divisaAconvertir = scanner.nextLine();
        System.out.println("A que moneda deseas convertir");
        String cambioMoneda = scanner.nextLine();
        System.out.println("Escirbe la cantidad a convertir");
        BigDecimal cantidad = scanner.nextBigDecimal();


        String urlString = "https://v6.exchangerate-api.com/v6/dd78fd068c5489b7242463ef/latest/" + divisaAconvertir.toUpperCase();

        OkHttpClient cliente = new OkHttpClient();
        Request respuesta = new Request.Builder()
                .url(urlString)
                .get()
                .build();

        Response response = cliente.newCall(respuesta).execute();
        String stringResponse = response.body().string();
        JSONObject jsonObject = new JSONObject(stringResponse);
        JSONObject ratesObjetc = jsonObject.getJSONObject("conversion_rates");
        BigDecimal conversion_rates = ratesObjetc.getBigDecimal(cambioMoneda.toUpperCase());

        BigDecimal resultado = conversion_rates.multiply(cantidad);
        System.out.println(resultado);


    }
}
