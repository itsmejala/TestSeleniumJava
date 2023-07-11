package com.jalavathi.utils.logs;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Logs {
   private static final Logger Log = LogManager.getLogger(Logs.class);

   //information messages
   public static void info(String message){
      Log.info(message);
   }

   //warning messages
   public static void warn(String message){
      Log.warn(message);
   }


   //error messagse
   public static void error(String message) {
      Log.error(message);
   }

   //Fatal Level Logs
   public static void fatal(String message) {
      Log.fatal(message);
   }

   //Debug messages
   public static void debug(String message) {
      Log.debug(message);
   }
}
