package com.bik.smalldata.config;

import com.google.gson.Gson;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;

public final class Configuration
{
    private static Config config;

    private Configuration(){}


    private static Config getConfig()
    {
        if (config == null)
        {
            Gson gson = new Gson();
            Reader reader = null;
            try
            {
                reader = Files.newBufferedReader(Paths.get("./config/config.json"));
                config = gson.fromJson(reader, Config.class);
            }
            catch (Exception e)
            {
                System.out.println("Could not read configuration");
                e.printStackTrace();
            }
        }
        return config;
    }


}
