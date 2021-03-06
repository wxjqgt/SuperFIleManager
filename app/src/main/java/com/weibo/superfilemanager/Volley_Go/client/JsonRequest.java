/*
 * Copyright (c) 2014, 张涛.
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

package com.weibo.superfilemanager.Volley_Go.client;

import com.weibo.superfilemanager.Volley_Go.constant.Method;
import com.weibo.superfilemanager.Volley_Go.http.HttpHeaderParser;
import com.weibo.superfilemanager.Volley_Go.http.NetworkResponse;
import com.weibo.superfilemanager.Volley_Go.http.Request;
import com.weibo.superfilemanager.Volley_Go.http.Response;
import com.weibo.superfilemanager.Volley_Go.toolbox.HttpParamsEntry;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Map;

/**
 * 用来发起application/json格式的请求的，我们平时所使用的是form表单提交的参数，
 * 而使用JsonRequest提交的是json参数。
 */
public class JsonRequest extends Request<byte[]> {

  private String mRequestBody;
  private HttpParams mParams;

  public JsonRequest(RequestConfig config, HttpParams params, HttpCallback callback) {
    super(config, callback);
    if (params == null){
      mParams = new HttpParams();
    }else {
      mParams = params;
    }
    mParams = params;
    mRequestBody = mParams.getJsonParams();
  }

  @Override public ArrayList<HttpParamsEntry> getHeaders() {
    return mParams.getHeaders();
  }

  @Override protected void deliverResponse(Map<String, String> headers, byte[] response) {
    if (mCallback != null) {
      mCallback.onSuccess(headers, response);
    }
  }

  @Override public Response<byte[]> parseNetworkResponse(NetworkResponse response) {
    return Response.success(response.data, response.headers,
        HttpHeaderParser.parseCacheHeaders(getUseServerControl(), getCacheTime(), response));
  }

  @Override public String getBodyContentType() {
    return String.format("application/json; charset=%s", getConfig().mEncoding);
  }

  @Override public String getCacheKey() {
    if (getMethod() == Method.POST) {
      return getUrl() + mParams.getJsonParams();
    } else {
      return getUrl();
    }
  }

  @Override public byte[] getBody() {
    try {
      return mRequestBody == null ? null : mRequestBody.getBytes(getConfig().mEncoding);
    } catch (UnsupportedEncodingException uee) {
      return null;
    }
  }

  @Override public Priority getPriority() {
    return Priority.IMMEDIATE;
  }
}
