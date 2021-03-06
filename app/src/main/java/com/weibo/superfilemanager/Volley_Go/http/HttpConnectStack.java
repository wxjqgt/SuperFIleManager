/*
 * Copyright (C) 2011 The Android Open Source Project, 张涛
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.weibo.superfilemanager.Volley_Go.http;

import com.weibo.superfilemanager.Volley_Go.constant.Method;
import com.weibo.superfilemanager.Volley_Go.interf.IHttpStack;
import com.weibo.superfilemanager.Volley_Go.toolbox.HTTPSTrustManager;
import com.weibo.superfilemanager.Volley_Go.toolbox.HttpParamsEntry;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSocketFactory;

/**
 * HttpUrlConnection方式实现,添加的默认认可全部https证书
 */
public class HttpConnectStack implements IHttpStack {

  private static final String HEADER_CONTENT_TYPE = "Content-Type";

  private final UrlRewriter mUrlRewriter;
  private final SSLSocketFactory mSslSocketFactory;

  public interface UrlRewriter {
    /**
     * 重写用于请求的URL
     */
    String rewriteUrl(String originalUrl);
  }

  public HttpConnectStack() {
    this(null);
  }

  public HttpConnectStack(UrlRewriter urlRewriter) {
    this(urlRewriter, null);
  }

  public HttpConnectStack(UrlRewriter urlRewriter, SSLSocketFactory sslSocketFactory) {
    mUrlRewriter = urlRewriter;
    mSslSocketFactory = sslSocketFactory;
  }

  @Override public URLHttpResponse performRequest(Request<?> request,
      ArrayList<HttpParamsEntry> additionalHeaders) throws IOException {
    String url = request.getUrl();
    ArrayList<HttpParamsEntry> header = new ArrayList<>();
    header.addAll(request.getHeaders());
    header.addAll(additionalHeaders);

    if (mUrlRewriter != null) {
      String rewritten = mUrlRewriter.rewriteUrl(url);
      if (rewritten == null) {
        throw new IOException("URL blocked by rewriter: " + url);
      }
      url = rewritten;
    }
    URL parsedUrl = new URL(url);
    HttpURLConnection connection = openConnection(parsedUrl, request);
    for (HttpParamsEntry entry : header) {
      connection.addRequestProperty(entry.k, entry.v);
    }
    setConnectionParametersForRequest(connection, request);
    return responseFromConnection(connection);
  }

  private URLHttpResponse responseFromConnection(HttpURLConnection connection) throws IOException {
    URLHttpResponse response = new URLHttpResponse();
    //contentStream
    InputStream inputStream;
    try {
      inputStream = connection.getInputStream();
    } catch (IOException ioe) {
      inputStream = connection.getErrorStream();
    }
    int responseCode = connection.getResponseCode();
    if (responseCode == -1) {
      throw new IOException("Could not retrieve response code from HttpUrlConnection.");
    }
    response.setResponseCode(responseCode);
    response.setResponseMessage(connection.getResponseMessage());

    response.setContentStream(inputStream);

    response.setContentLength(connection.getContentLength());
    response.setContentEncoding(connection.getContentEncoding());
    response.setContentType(connection.getContentType());
    //header
    HashMap<String, String> headerMap = new HashMap<>();
    for (Entry<String, List<String>> header : connection.getHeaderFields().entrySet()) {
      if (header.getKey() != null) {
        StringBuilder value = new StringBuilder();
        for (String v : header.getValue()) {
          value.append(v).append(";");
        }
        headerMap.put(header.getKey(), value.toString());
      }
    }
    response.setHeaders(headerMap);
    return response;
  }

  private HttpURLConnection openConnection(URL url, Request<?> request) throws IOException {
    HttpURLConnection connection = (HttpURLConnection) url.openConnection();

    int timeoutMs = request.getTimeoutMs();
    connection.setConnectTimeout(timeoutMs);
    connection.setReadTimeout(timeoutMs);
    connection.setUseCaches(false);
    connection.setDoInput(true);

    // use caller-provided custom SslSocketFactory, if any, for HTTPS
    if ("https".equals(url.getProtocol())) {
      if (mSslSocketFactory != null) {
        ((HttpsURLConnection) connection).setSSLSocketFactory(mSslSocketFactory);
      } else {
        //信任所有证书
        HTTPSTrustManager.allowAllSSL();
      }
    }
    return connection;
  }

  /* package */
  static void setConnectionParametersForRequest(HttpURLConnection connection, Request<?> request)
      throws IOException {
    Method method = request.getMethod();
    if (method == Method.GET) {
      connection.setRequestMethod("GET");
    } else if (method == Method.DELETE) {
      connection.setRequestMethod("DELETE");
    } else if (method == Method.POST) {
      connection.setRequestMethod("POST");
      addBodyIfExists(connection, request);
    } else if (method == Method.PUT) {
      connection.setRequestMethod("PUT");
      addBodyIfExists(connection, request);
    } else if (method == Method.HEAD) {
      connection.setRequestMethod("HEAD");
    } else if (method == Method.OPTIONS) {
      connection.setRequestMethod("OPTIONS");
    } else if (method == Method.TRACE) {
      connection.setRequestMethod("TRACE");
    } else if (method == Method.PATCH) {
      connection.setRequestMethod("PATCH");
      addBodyIfExists(connection, request);
    } else {
      throw new IllegalStateException("Unknown method type.");
    }
  }

  /**
   * 如果有body则添加
   */
  private static void addBodyIfExists(HttpURLConnection connection, Request<?> request)
      throws IOException {
    byte[] body = request.getBody();
    if (body != null) {
      connection.setDoOutput(true);
      connection.addRequestProperty(HEADER_CONTENT_TYPE, request.getBodyContentType());
      DataOutputStream out = new DataOutputStream(connection.getOutputStream());
      out.write(body);
      out.close();
    }
  }
}
