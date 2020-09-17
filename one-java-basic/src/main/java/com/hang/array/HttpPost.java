package com.hang.array;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLDecoder;

/**
 * @author Hang
 * @date 2020-09-01 16:33
 */

public class HttpPost {
    public static void main(String[] args) {
        sendPost();

    }
    public static void sendPost() {

        try {
            //创建连接

            URL url = new URL(SaveStaticFinalString.URL_OUS);
            HttpURLConnection connection =
                    (HttpURLConnection) url.openConnection();
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setRequestMethod("POST");
            connection.setUseCaches(false);
            connection.setInstanceFollowRedirects(true);
            connection.setRequestProperty("Content-Type", "application/json");
            connection.connect();
            // POST请求
            DataOutputStream out =
                    new DataOutputStream(connection.getOutputStream());
            JSONObject obj = new JSONObject();
            JSONArray jarr = new JSONArray();
            JSONObject job = new JSONObject();
            job.put("id", "20_SEM");
            job.put("ouName", "南大区");
            job.put("parentId", "");
            jarr.add(job);
            obj.put("datas", jarr);
            obj.put("apikey", SaveStaticFinalString.APIKEY);
            obj.put("salt", "123");
            obj.put( "signature", QidaSHA256.SHA256Encrypt(SaveStaticFinalString.SECRETKEY + "123"));
            System.out.println(obj.toString());
            String json = java.net.URLEncoder.encode(obj.toString(), "utf-8");
            out.writeBytes(obj.toString());
            out.flush();
            out.close();
            // 读取响应
            BufferedReader reader =
                    new BufferedReader(
                            new InputStreamReader(connection.getInputStream()));
            String lines;
            StringBuffer sb = new StringBuffer("");
            while ((lines = reader.readLine()) != null) {
                lines = URLDecoder.decode(lines, "utf-8");
                sb.append(lines);
            }
            System.out.println(sb);
            reader.close();
            // 断开连接
            connection.disconnect();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            // TODO 自动生成 catch 块
            e.printStackTrace();
        }

    }

}
