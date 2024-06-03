package com.mercury.classes.request_handling;

public class Parameter {
    private ParameterType type;
    private String name;
    private String value;
    
    public ParameterType getType() {
        return type;
    }
    
    public void setType(ParameterType type) {
        this.type = type;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

}
