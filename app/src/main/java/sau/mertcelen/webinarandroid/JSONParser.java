package sau.mertcelen.webinarandroid;

/**
 * Created by mertcelen on 13/06/16.
 */

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

public class JSONParser {

    static InputStream is = null;
    static JSONObject jObj = null;
    static String json = "";

    // constructor
    public JSONParser() {

    }

    public String getJSONFromUrl(String url,boolean isPostReq,String email,String name,String surname) {

        // Making HTTP request
        try {
            // defaultHttpClient
            DefaultHttpClient httpClient = new DefaultHttpClient();
            HttpUriRequest httpRes = null;
            if(isPostReq){
                ArrayList<NameValuePair> postParameters;
                httpRes = new HttpPost(url);
                HttpPost a = new HttpPost(url);
                postParameters = new ArrayList<NameValuePair>();
                postParameters.add(new BasicNameValuePair("WebinarId", String.valueOf(MainActivity._eventID)));
                postParameters.add(new BasicNameValuePair("Email", email));
                postParameters.add(new BasicNameValuePair("Name", name));
                postParameters.add(new BasicNameValuePair("Surname", surname));
//              dirty fix but works. (basically setEntity can't use of HttpUriRequest' inheritance for some reason?
                a.setEntity(new UrlEncodedFormEntity(postParameters));
                httpRes = a;
                a= null;
            }else{
                httpRes = new HttpGet(url);
            }
            HttpResponse httpResponse = httpClient.execute(httpRes);
            HttpEntity httpEntity = httpResponse.getEntity();
            is = httpEntity.getContent();
            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    is, "iso-8859-1"), 8);
            StringBuilder sb = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null) {
                sb.append(line + "n");
            }
            is.close();
            json = sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        // return JSON String
        return json;
    }
}
