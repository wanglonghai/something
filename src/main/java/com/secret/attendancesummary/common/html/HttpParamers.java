package com.secret.attendancesummary.common.html;


import com.alibaba.fastjson.JSON;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * 请求参数
 */
public class HttpParamers {
    private Map<String, Object> params = new HashMap();
    private HttpMethod httpMethod;
    private String jsonParamer = "";
    private HttpHeader httpHeader=new HttpHeader();

    public HttpHeader getHttpHeader(){
        return httpHeader;
    }
    public void addHeader(String k,String v){
        httpHeader.addParam(k,v);
    }
    public HttpParamers(HttpMethod httpMethod) {
        this.httpMethod = httpMethod;
    }

    public static HttpParamers httpPostParams() {
        return new HttpParamers(HttpMethod.POST);
    }

    public static HttpParamers httpGetParams() {
        return new HttpParamers(HttpMethod.GET);
    }

    public HttpParamers addParam(String name, Object value) {
        this.params.put(name, value);
        return this;
    }
    public HttpParamers addParam(Map<String,Object> params) {
        for (String k:params.keySet()) {
            if(params.get(k)!=null){
                this.params.put(k,params.get(k).toString());
            }
        }
        return this;
    }
    public HttpMethod getHttpMethod() {
        return this.httpMethod;
    }

    public String getQueryString(String charset) throws IOException {
        if ((this.params == null) || (this.params.isEmpty())) {
            return null;
        }
        StringBuilder query = new StringBuilder();
        Set<Map.Entry<String, Object>> entries = this.params.entrySet();

        for (Map.Entry<String, Object> entry : entries) {
            String name = entry.getKey();
            Object value = entry.getValue();
            if(StringUtils.isNotEmpty(name) && value!=null ){
                query.append("&").append(name).append("=").append(URLEncoder.encode(value.toString(), charset));
            }
        }
        return query.substring(1);
    }

    public boolean isJson() {
        return !isEmpty(this.jsonParamer);
    }

    public Map<String, Object> getParams() {
        return this.params;
    }

    public String toString() {
        return "HttpParamers " + JSON.toJSONString(this);
    }

    public String getJsonParamer() {
        return this.jsonParamer;
    }

    public void setJsonParamer() {
        this.jsonParamer = JSON.toJSONString(this.params);
    }

    private static boolean isEmpty(CharSequence cs) {
        return (cs == null) || (cs.length() == 0);
    }
}
