package org.ike.pms.pmsfilesystem.service;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * @author ike
 * @since 2019-03-06 17:50
 */
public class ConnectionTest {
    public static void main(String[] args) {
        String url = "";
        String body = "";
        try {
            System.out.println(connection(url, body));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String connection (String rquestUrl,String  body) {
        OutputStream outputStream = null;
        OutputStreamWriter outputStreamWriter = null;
        InputStream inputStream = null;
        InputStreamReader inputStreamReader = null;
        BufferedReader reader = null;
        StringBuffer resultBuffer = new StringBuffer();
        String tempLine;
        boolean isDoInput = false;
        if (body != null && body.length() > 0) {
            isDoInput = true;
        }
        try {
            URL url = new URL(rquestUrl);//Http请求地址
            // 将url 以 open方法返回的urlConnection  连接强转为HttpURLConnection连接  (标识一个url所引用的远程对象连接)
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();// 此时cnnection只是为一个连接对象,待连接中
            if (isDoInput) {
                connection.setDoOutput(true);
                connection.setRequestProperty("Content-Length", String.valueOf(body.length()));
            }
            connection.setDoInput(true);
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);
            connection.setUseCaches(false);
            connection.setRequestProperty("Accept-Charset", "UTF-8");
            connection.setRequestProperty("Content-Type", "application/xml");
            connection.setRequestMethod("POST");
//          connection.setInstanceFollowRedirects(true);
            connection.connect();

            if (isDoInput) {
                outputStream = connection.getOutputStream();
                outputStreamWriter = new OutputStreamWriter(outputStream, "UTF-8");
                outputStreamWriter.write(body);
                outputStreamWriter.flush();// 刷新
            }

            if (connection.getResponseCode() >= 300) {
                throw new Exception(
                        "HTTP Request is not success, Response code is " + connection.getResponseCode());
            }

            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                inputStream = connection.getInputStream();
                inputStreamReader = new InputStreamReader(inputStream,"UTF-8");
                reader = new BufferedReader(inputStreamReader);

                while ((tempLine = reader.readLine()) != null) {
                    resultBuffer.append(tempLine);
                    resultBuffer.append("\n");
                }
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {// 关闭流

            try {
                if (outputStreamWriter != null) {
                    outputStreamWriter.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                if (outputStream != null) {
                    outputStream.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                if (inputStreamReader != null) {
                    inputStreamReader.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return resultBuffer.toString();
    }
}
