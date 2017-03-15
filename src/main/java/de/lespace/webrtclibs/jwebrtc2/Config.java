/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.lespace.webrtclibs.jwebrtc2;

import java.io.FileInputStream;  
import java.io.IOException;  
import java.util.Properties;  

/**
 *
 * @author nico
 */
public class Config {

    //public static String default_KMS_WS_URI = "ws://192.168.0.17:8888/kurento";
    //public static String default_KMS_WS_URI = "ws://192.168.43.251:8888/kurento";
    //public static String serverUrl = "192.168.43.251:8080/jWebrtc"; //without protokoll
    
    public static String DEFAULT_KMS_WS_URI = "ws://104.196.104.219:8888/kurento";
    //public static String DEFAULT_SERVER_URL = "192.168.11.81/jWebrtc"; //without protokoll

    //public static String default_KMS_WS_URI = "ws://192.168.0.16:8888/kurento";
//    public static String default_KMS_WS_URI = "ws://192.168.43.251:8888/kurento";
   // public static String serverUrl = "192.168.0.16:8080/jWebrtc"; //without protokoll

   /* public static String TURN_CONFIG = "{\n" +
                    "	\"username\": \"akashionata\",\n" +
                    "	\"password\": \"silkroad2015\",\n" +
                    "	\"uris\": [\n" +
                    "		\"turn:5.9.154.226:3478?transport=tcp\"\n" +
 //                   "		\"turn:5.9.154.226:3478?transport=udp\",\n" +
 //                   "		\"turn:5.9.154.226:3478?transport=tcp\"\n" +
                    "	]\n" +
                    "}"; */
    
   static{
	 Properties prop = new Properties();  
	try {  
		System.out.println("DEFAULT_KMS_WS_URI");    
		prop.load(Config.class.getResourceAsStream("/config.properties"));  
            DEFAULT_KMS_WS_URI =  prop.getProperty("DEFAULT_KMS_WS_URI");
            System.out.println("DEFAULT_KMS_WS_URI");
        } catch(IOException e) {  
            e.printStackTrace();  
        }  
	}

}
