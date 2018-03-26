package com.sy.Tset;



import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.AuthCache;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.ClientContext;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.auth.BasicScheme;
import org.apache.http.impl.client.BasicAuthCache;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.util.EntityUtils;


/**
* @author kobe
*
*/
public class Testhttps  {

   public static final String username = "";
   public static final String password = ""; 
   public static final String ip = ""; 
    public static final int port = 443;
     
   /**
    * 
    * @param requestUrl 
    * @param xmlData
    * @param contentType
    * @param charset
    */
   public void postRequest(String requestUrl, String xmlData, String contentType, String charset)
   {
      
       int returncode = 0;
       String msg = "";
       // 1. ����HttpClient����
       DefaultHttpClient httpClient = new DefaultHttpClient();
       //  2.�������󷽷���ʵ������ָ������URL�������Ҫ����GET���󣬴���HttpGet���������Ҫ����POST���󣬴���HttpPost����
       HttpPost post = new HttpPost(requestUrl);       
       try
           {   
           // 3. �����Ҫ�������������
           StringEntity entity = new StringEntity(xmlData, charset);
           entity.setContentType(contentType);
           post.setEntity(entity);
            //3.1����https����վ����ssl
            enableSSL(httpClient);
            //3.2���ó�ʱ
            httpClient.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 30 * 1000);
            httpClient.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, 60 * 1000);
            //3.3����basic������֤
            BasicHttpContext basicHttpContext = enableBasic(httpClient, username, password, ip, port);
           //4. ����HttpClient�����execute
            HttpResponse response = httpClient.execute(post, basicHttpContext);
            // 5. ����HttpResponse��getAllHeaders()��getHeaders(String
            // name)�ȷ����ɻ�ȡ����������Ӧͷ������HttpResponse��getEntity()�����ɻ�ȡHttpEntity���󣬸ö����װ�˷���������Ӧ���ݡ������ͨ���ö����ȡ����������Ӧ���ݡ�
            returncode = response.getStatusLine().getStatusCode();
            System.out.println("postCode= " + returncode);
            // ��״ֵ̬Ϊ2�࣬��ok
            if (200<=returncode&&returncode<300)
            {
                System.out.println("���ݷ��ͳɹ���");
            }
            else{
                HttpEntity entityRep = response.getEntity();    
                if (entityRep != null)
                {
                  msg = EntityUtils.toString(response.getEntity(),"UTF-8");
                  System.out.println("������Ϣ"+msg);

                }
               
            }
            
           }
           catch (Exception e)
           {
               e.printStackTrace();
           }
           finally
           {
               // �ر������ͷ���Դ
               if (null != post)
               {
                   post.releaseConnection();

               }
               if (null != httpClient)
               {
                   httpClient.getConnectionManager().shutdown();
               }
           
           }      
       
   }

  


   public  BasicHttpContext enableBasic(DefaultHttpClient httpClient,String username,String password,String ip, int port)
   {
       AuthScope authScope = new AuthScope(AuthScope.ANY_HOST, AuthScope.ANY_PORT);
        UsernamePasswordCredentials credentials = new UsernamePasswordCredentials(username, password);
        httpClient.getCredentialsProvider().setCredentials(authScope, credentials);    
        // Create AuthCache instance
        AuthCache authCache = new BasicAuthCache();
        // Generate BASIC scheme object and add it to the local auth cache
        BasicScheme basicAuth = new BasicScheme();
        HttpHost targetHost = new HttpHost(ip, port, "https");
        authCache.put(targetHost, basicAuth);
        // Add AuthCache to the execution context
        BasicHttpContext localcontext = new BasicHttpContext();
        localcontext.setAttribute(ClientContext.AUTH_CACHE, authCache);
        
        return localcontext;
   }

   /**
    * ����https����վ

    * 
    * @param httpclient
    */
   public  void enableSSL(DefaultHttpClient httpclient)
   {
       // ����ssl 
       try
       {
           SSLContext sslcontext = SSLContext.getInstance("TLS");
           sslcontext.init(null, new TrustManager[] { truseAllManager }, null);
           SSLSocketFactory sf = new SSLSocketFactory(sslcontext);
           sf.setHostnameVerifier(SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
           Scheme https = new Scheme("https", sf, 443);
           httpclient.getConnectionManager().getSchemeRegistry().register(https);
       }
       catch (Exception e)
       {
           e.printStackTrace();
       }
   }

   /**
    * ��д��֤������ȡ�����ssl
    */
   public  TrustManager truseAllManager = new X509TrustManager() {

       public java.security.cert.X509Certificate[] getAcceptedIssuers()
       {
           return null;
       }

       @Override
       public void checkClientTrusted(java.security.cert.X509Certificate[] chain, String authType)
               throws java.security.cert.CertificateException
       {
       }

       @Override
       public void checkServerTrusted(java.security.cert.X509Certificate[] chain, String authType)
               throws java.security.cert.CertificateException
       {
       }

   };
 
}
