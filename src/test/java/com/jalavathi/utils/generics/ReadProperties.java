package com.jalavathi.utils.generics;

import com.jalavathi.utils.GlobalConstants;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

public class ReadProperties {
    public HashMap<String,String> getProperties() throws IOException {
        Properties properties = new Properties();
        properties.load(new FileReader(GlobalConstants.CONFIG_PROPERTY_FILE_PATH));
        HashMap<String,String> map = new HashMap<String,String>((Map)properties);
       return map;
    }
}
