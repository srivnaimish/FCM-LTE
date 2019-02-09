package com.fcm.notifications.push.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

public class HttpCaller {

        public static Map<String, String> getHeaderMap() {
                HashMap<String, String> map = new HashMap<String, String>();
                // map.put("Content-Type", content_type);
                return map;
        }

        public static HttpURLConnection get(String url) throws IOException {
                URL obj = new URL(url);
                HttpURLConnection con = (HttpURLConnection) obj.openConnection();
                con.setRequestMethod("GET");
                con.setRequestProperty("Content-Type", "application/json");
                int responseCode = con.getResponseCode();

                System.out.println("response code " + responseCode);


                if (responseCode != 200)
                        return null;
                BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
                String inputLine;
                StringBuffer response = new StringBuffer();

                while ((inputLine = in.readLine()) != null) {
                        response.append(inputLine);
                }
                in.close();
                return con;
        }
	
	/*public static HttpURLConnection postRequest(String url,JSONObject body) throws IOException {
		
		
		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		con.setRequestMethod("POST");
		con.setRequestProperty("Content-Type", "application/json; charset=UTF-8");		
		import medsutra.network.HttpCaller;

		int responseCode = con.getResponseCode();
		
		System.out.println("response code "+ responseCode);
		
		
		if (responseCode != 200)
			return null;
		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();
		return con;
	}
	*/

        public static String getCall(String url) throws IOException {
                URL obj = new URL(url);
                HttpURLConnection con = (HttpURLConnection) obj.openConnection();
                con.setRequestMethod("GET");

                // add request header
                con.setRequestProperty("User-Agent", "Mozilla/5.0");

                int responseCode = con.getResponseCode();
                if (responseCode != 200)
                        return null;
                System.out.println("response code:" + responseCode);
                BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
                String inputLine;
                StringBuffer response = new StringBuffer();

                while ((inputLine = in.readLine()) != null) {
                        response.append(inputLine);
                }
                in.close();
                // print result
                return response.toString();
        }

        public static String post(String url, HashMap<String, String> headers, String body) throws IOException {
                CloseableHttpClient httpClient = HttpClientBuilder.create().build();
                HttpPost request = new HttpPost(url);
                StringEntity params = new StringEntity(body);
                for (Entry<String, String> e : headers.entrySet()) {
                        request.addHeader(e.getKey(), e.getValue());
                }
                request.setEntity(params);
                System.out.println("parms = " + request);
                HttpResponse result = httpClient.execute(request);
                String json = EntityUtils.toString(result.getEntity(), "UTF-8");
                System.out.println(json);
                return json;
        }

        public static String post(String url, Map<String, String> headers) throws IOException {
                URL obj = new URL(url);
                HttpURLConnection con = (HttpURLConnection) obj.openConnection();
                con.setRequestMethod("PUT");
                con.setRequestProperty("Authorization", "1bafa5d45e96f6ac7cdca214f5a34fd9");
                for (Entry<String, String> e : headers.entrySet()) {
                        con.setRequestProperty(e.getKey(), e.getValue());
                }
                // add request header
                con.setRequestProperty("User-Agent", "Mozilla/5.0");

                int responseCode = con.getResponseCode();
                if (responseCode != 200)
                        return null;
                BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
                String inputLine;
                StringBuffer response = new StringBuffer();

                while ((inputLine = in.readLine()) != null) {
                        response.append(inputLine);
                }
                in.close();
                // print result
                return response.toString();
        }

}