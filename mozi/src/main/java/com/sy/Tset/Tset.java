package com.sy.Tset;


import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import javax.security.cert.CertificateException;

import net.sf.json.JSONObject;

import com.sun.net.ssl.TrustManagerFactory;

public class Tset {
//	 * "channel_id":"jftester"
//"channel_secret":"Xi9G3w7Qr5yO"
//"name":"15522518863"
//"secret":"6336202"
//"device_id":"6A6668010001010101010000FFFFFFFF" 
	public static void main(String[] args) throws java.security.cert.CertificateException {
		JSONObject js = new JSONObject();
		js.put("service", "HRV");
		js.put("channel_id", "jftester");
		js.put("channel_secret", "Xi9G3w7Qr5yO");
		js.put("name", "15522518863");
		js.put("secret", "6336202");
		js.put("client_id", 0);
		js.put("device_id", "6A6668010001010101010000FFFFFFFF");
		js.put("start_time", "2017-04-05 10:12:00");
		js.put("period", "1d");
		 //String s=httpsRequest("https://api.jingfantech.com/V1.02/physical_exam/manufacturer","POST",js.toString());  
		 String s= requestHTTPS("https://api.jingfantech.com/V1.02/physical_exam/manufacturer","POST", null);
		 System.out.println(s);
		
	}
    /* 
     * ����https GET/POST���� 
     * �����ַ�����󷽷������� 
     * */  
    public static String httpsRequest(String requestUrl,String requestMethod,String outputStr){  
        StringBuffer buffer=null;  
        try{  
        //����SSLContext  
        SSLContext sslContext=SSLContext.getInstance("SSL");  
        TrustManager[] tm={new MyX509TrustManager()};  
        //��ʼ��  
        sslContext.init(null, tm, new java.security.SecureRandom());;  
        //��ȡSSLSocketFactory����  
        SSLSocketFactory ssf=sslContext.getSocketFactory();  
        URL url=new URL(requestUrl);  
        HttpsURLConnection conn=(HttpsURLConnection)url.openConnection();  
        conn.setDoOutput(true);  
        conn.setDoInput(true);  
        conn.setUseCaches(false);  
        conn.setRequestMethod(requestMethod);  
        conn.setSSLSocketFactory(ssf);  
        conn.connect();  
        //����������д����  
        if(null!=outputStr){  
            OutputStream os=conn.getOutputStream();  
            os.write(outputStr.getBytes("utf-8"));  
            os.close();  
        }  
          
        //��ȡ�������˷��ص�����  
        InputStream is=conn.getInputStream();  
        InputStreamReader isr=new InputStreamReader(is,"utf-8");  
        BufferedReader br=new BufferedReader(isr);  
        buffer=new StringBuffer();  
        String line=null;  
        while((line=br.readLine())!=null){  
            buffer.append(line);  
        }  
        }catch(Exception e){  
            e.printStackTrace();  
        }  
        return buffer.toString();  
    }  
    public static String requestHTTPS(String targetUrl, String method, Map<String,String> params) throws java.security.cert.CertificateException {  
        try {  
            System.out.println("requestHTTPS targetUrl:"+targetUrl+",method:"+method);  
            System.out.println(targetUrl);  
            URL url = new URL(targetUrl);  
            HttpsURLConnection connection = (javax.net.ssl.HttpsURLConnection) url  
                    .openConnection();  
            /* Load the keyStore that includes self-signed cert as a "trusted" entry. */  
            //http://stackoverflow.com/questions/859111/how-do-i-accept-a-self-signed-certificate-with-a-java-httpsurlconnection  
            //javax.net.ssl.SSLSocketFactory  
            KeyStore trustStore = KeyStore.getInstance(KeyStore.getDefaultType());  
            FileInputStream instream = new FileInputStream(new File("C:/Program Files/Java/jdk1.8.0_151/lib/security/jingfantech.keystore"));  
            try {  
                // 加载keyStore     
                trustStore.load(instream, "D#s@a1".toCharArray());  
            } catch (NoSuchAlgorithmException e) {  
                e.printStackTrace();  
            } finally {  
                try {  
                    instream.close();  
                } catch (Exception ignore) {  
                }  
            }  
            TrustManagerFactory tmf =   
                      TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());  
            tmf.init(trustStore);  
              
            //  
            X509TrustManager defaultTrustManager = (X509TrustManager)tmf.getTrustManagers()[0];  
  
            SSLContext ctx = SSLContext.getInstance("TLS");  
            ctx.init(null, new TrustManager[] {defaultTrustManager}, null);  
              
            SSLSocketFactory sslFactory = ctx.getSocketFactory();  
              
            connection.setSSLSocketFactory(sslFactory);  
  
            connection.setDoOutput(true);  
            connection.setDoInput(true);  
            connection.setRequestMethod(method);  
            connection.setUseCaches(false);  
            connection.setInstanceFollowRedirects(true);  
            connection.setRequestProperty("Content-Type", "application/json");  
            connection.setRequestProperty("Accept", "application/json");  
              
            connection.connect();  
            if(params!=null){  
                //POST请求  
                DataOutputStream out = new DataOutputStream(  
                        connection.getOutputStream());  
                out.writeBytes(buildRequestParams(params,"UTF-8"));  
                out.flush();  
                out.close();  
            }  
              
            BufferedReader reader = new BufferedReader(new InputStreamReader(  
                    connection.getInputStream()));  
            String lines;  
            StringBuffer sb = new StringBuffer("");  
            while ((lines = reader.readLine()) != null) {  
                lines = new String(lines.getBytes(), "utf-8");  
                sb.append(lines);  
            }  
            reader.close();  
            connection.disconnect();  
            System.out.println("response:"+sb.toString());  
            return sb.toString();  
        } catch (MalformedURLException e) {  
            e.printStackTrace();  
        } catch (UnsupportedEncodingException e) {  
            e.printStackTrace();  
        } catch (IOException e) {  
            e.printStackTrace();  
        } catch (KeyStoreException e1) {  
            e1.printStackTrace();  
        } catch (NoSuchAlgorithmException e) {  
            e.printStackTrace();  
        } catch (KeyManagementException e) {  
            e.printStackTrace();  
        }  
        return null;  
    }  
    public static String buildRequestParams(Map<String, String> params,  
            String charset) throws UnsupportedEncodingException {  
        if (params == null || params.isEmpty()) {  
            return null;  
        }  
           
        // 对参数进行排序  
        List<Map.Entry<String, String>> newParams = new ArrayList<Map.Entry<String, String>>(  
                params.entrySet());  
        Collections.sort(newParams,  
                new Comparator<Map.Entry<String, String>>() {  
                    public int compare(Map.Entry<String, String> o1,  
                            Map.Entry<String, String> o2) {  
                        return (o1.getKey()).toString().compareTo(o2.getKey());  
                    }  
                });  
   
        StringBuilder query = new StringBuilder();  
        for (Map.Entry<String, String> entry : newParams) {  
            String name = entry.getKey();  
            String value = entry.getValue();  
            query.append("&");  
            query.append(name).append("=").append(URLEncoder.encode(value, charset));  
        }  
        if(!"".equalsIgnoreCase(query.toString()))  
            return query.toString().substring(1, query.toString().length());  
        return query.toString();  
    }
}
