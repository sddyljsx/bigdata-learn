package neal.spark.utils;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class HttpUtil {

    public static HttpResponse Post(String url, Map<String, String> params) throws Exception {

        HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
        CloseableHttpClient closeableHttpClient = httpClientBuilder.build();

        HttpPost httpPost = new HttpPost(url);


        httpPost.setEntity(getEntity(params));
        HttpResponse httpResponse = closeableHttpClient.execute(httpPost);
        // HttpEntity entity = httpResponse.getEntity();
        // StatusLine statusLine = httpResponse.getStatusLine();

        //return statusLine.getStatusCode();
        return httpResponse;
    }

    private static String getUrl(String url, Map<String, String> params) {

        if (params == null || params.size() == 0) {
            return url;
        }
        StringBuilder builder = new StringBuilder(url);
        boolean isFirst = true;
        for (String key : params.keySet()) {
            if (key != null && params.get(key) != null) {
                if (isFirst) {
                    isFirst = false;
                    builder.append("?");
                } else {
                    builder.append("&");
                }
                builder.append(key)
                        .append("=")
                        .append(params.get(key));
            }
        }
        return builder.toString();
    }

    private static UrlEncodedFormEntity getEntity(Map<String, String> params) throws UnsupportedEncodingException {
        List<NameValuePair> list = new ArrayList<>();
        for (String key : params.keySet()) {
            if (key != null && params.get(key) != null) {
                list.add(new BasicNameValuePair(key, params.get(key)));
            }
        }
        UrlEncodedFormEntity entity = new UrlEncodedFormEntity(list, "utf-8");
        return entity;
    }

    public static String entityToString(HttpEntity httpEntity) throws Exception {
        if (httpEntity == null) {
            return "";
        }
        InputStream is = httpEntity.getContent();
        BufferedReader in = new BufferedReader(new InputStreamReader(is));
        StringBuffer buffer = new StringBuffer();
        String line = "";
        while ((line = in.readLine()) != null) {
            buffer.append(line);
        }
        return buffer.toString();
    }
}
