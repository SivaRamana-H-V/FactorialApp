package com.example.factorialapp;


import android.content.Context;

public class OffloadCode {
    Context context;
    String code;
    String parameters;
    public String Codeformat(String code,String parameters){
        String format="import java.util.*;\n" +
                "import java.math.*;" +
                "class Main{"+
                "final static int N ="+Integer.parseInt(parameters)+";"+
                    code+
                    "public static void main(String[] args) {"+
                        "fact(N);\n" +
                    "}"+
                "}";
        return format;
    }
    OffloadCode(String code, String parameters, Context context){
        this.code=Codeformat(code,parameters);
        this.parameters = parameters;
        this.context=context;
    }
    public String getConnection(){
        OffloadingConnection conn=new OffloadingConnection(context);
        return conn.makeJsonObjectRequest(code,parameters);

    }

}
