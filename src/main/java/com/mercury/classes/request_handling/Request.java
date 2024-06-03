package com.mercury.classes.request_handling;

import java.util.ArrayList;

import com.mercury.classes.serializing.JsonSerializer;
import com.mercury.interfaces.IJsonSerializable;

public class Request implements IJsonSerializable<Request> {
    private String uri;
    private ArrayList<Parameter> queryParameters;
    private ArrayList<Parameter> pathParameters;
    private String body;

    public Request() {
        queryParameters = new ArrayList<>();
        pathParameters = new ArrayList<>();
    }

    public String serialize() {
        JsonSerializer<Request> jsonSerializer = new JsonSerializer<>(Request.class);
        return jsonSerializer.serialize(this);
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public void addToQuery() {
        queryParameters.add(new Parameter());
    }

    public void addToPath() {
        pathParameters.add(new Parameter());
    }

    public void removeFromQuery(Parameter queryParameter) {
        queryParameters.remove(queryParameter);
    }

    public void removeFromPath(Parameter pathParameter) throws Exception {
        if (!uri.contains(pathParameter.getName())) {
            pathParameters.remove(pathParameter);
        }

        throw new Exception("Path parameter cannot be removed; still present in uri.");
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
