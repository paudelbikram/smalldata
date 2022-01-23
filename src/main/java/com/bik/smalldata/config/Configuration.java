package com.bik.smalldata.config;

import com.google.gson.Gson;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class Configuration
{
    private static Config config;
    private Configuration(){}


    public static Connection getDbConnection() {
        Config config = getConfig();
        try {
            //DriverManager can also take properly formatted url like this "jdbc:<dbtype>:[location]/<dbname>?<parameters>"
            return DriverManager.getConnection(config.getDburl(), config.getDbusername(), config.getDbpassword());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


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
