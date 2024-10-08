package com.sendgrid;

import java.util.Map;
import java.util.HashMap;

/**
  * Class Response provides a standard interface to an API's HTTP request.
  */
public class Request {
  private Method method;
  private String baseUri;
  private String endpoint;
  private String body;
  private final Map<String, String> headers;
  private final Map<String, String> queryParams;

  public Request() {
    this.headers = new HashMap<String, String>();
    this.queryParams = new HashMap<String, String>();
    this.reset();
  }

  /**
  * Place the object into an empty state.
  */
  public void reset() {
    this.clearMethod();
    this.clearBaseUri();
    this.clearEndpoint();
    this.clearBody();
    this.clearHeaders();
    this.clearQueryParams();
  }

  public void addQueryParam(String key, String value) {
    this.queryParams.put(key, value);
  }

  public void addHeader(String key, String value) {
    this.headers.put(key, value);
  }

  public String removeQueryParam(String key) {
    return this.queryParams.remove(key);
  }

  public String removeHeader(String key) {
    return this.headers.remove(key);
  }

  public void setMethod(Method method) {
    this.method = method;
  }

  public void setBaseUri(String baseUri) {
    this.baseUri = baseUri;
  }

  public void setEndpoint(String endpoint) {
    this.endpoint = endpoint;
  }

  public void setBody(String body) {
    this.body = body;
  }

  public Map<String, String> getHeaders() {
    return this.headers;
  }

  public Map<String, String> getQueryParams() {
    return this.queryParams;
  }

  public Method getMethod() {
    return this.method;
  }

  public String getBaseUri() {
    return this.baseUri;
  }

  public String getEndpoint() {
    return this.endpoint;
  }

  public String getBody() {
    return this.body;
  }

  public void clearMethod() {
    this.method = null;
  }

  public void clearBaseUri() {
    this.baseUri = "";
  }

  public void clearEndpoint() {
    this.endpoint = "";
  }

  public void clearBody() {
    this.body = "";
  }

  public void clearQueryParams() {
    this.queryParams.clear();
  }

  public void clearHeaders() {
    this.headers.clear();
  }
}