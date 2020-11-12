package com.reni.myapplication.server;

public class BaseURL {

    public static String baseUrl = "http://10.117.5.117:5050/";

    public static String login   = baseUrl + "user/login";
    public static String register   = baseUrl + "user/registrasi";

    //motor
    public static String dataMotor   = baseUrl + "Motor/datamotor";

    public static String editDataMotor   = baseUrl + "Motor/ubah/";
    public static String hapusData   = baseUrl + "Motor/hapus/";
    public static String inputMotor   = baseUrl + "Motor/input";

}
