package rahandaz.exilios.com.rahandaz.network;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by NaviD on 8/14/2017.
 */

public class NetworkManager {
//    172.20.4.19
    public static final String baseUrl = "http://192.168.43.51:8585";
    public static boolean isConnected (Context context){

        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        if (netInfo != null && netInfo.isConnectedOrConnecting() == true)
            return true;
        return false;
    }

    public static String getData(RequestPackage p){

        BufferedReader reader = null;
        String uri = p.getUri();

//        JSONObject json = new JSONObject(p.getParams());

        if (p.getMethod().equals("GET")) {
            uri += p.getEncodedParams();
        }

        try {
            URL url = new URL(uri);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod(p.getMethod());
            JSONObject json = new JSONObject(p.getParams());
            String params = "params="+json.toString();

            if (p.getMethod().equals("POST")){
                con.setDoOutput(true);
                OutputStreamWriter writer = new OutputStreamWriter(con.getOutputStream());
                writer.write(params);
                writer.flush();
            }

//            Log.d("**********************", url.toString());

            StringBuilder sb = new StringBuilder();
            reader = new BufferedReader(new InputStreamReader(con.getInputStream()));

            String line;
            while ((line = reader.readLine()) != null) {
                if (sb.length() > 0)
                    sb.append("\n");
                sb.append(line);
            }

            return sb.toString();

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                    return null;
                }
            }
        }
    }


}
