package edu.tongji.reuse.teameleven.coserver.ctrl;

import edu.tongji.reuse.teameleven.coserver.model.ServerConfigBean;
import octoteam.tahiti.config.ConfigManager;
import octoteam.tahiti.config.loader.JsonAdapter;

import java.io.IOException;

/**
 * Created by daidongyang on 5/31/16.
 */
public class ConfigCtrl {
    private static ServerConfigBean serverConfigBean;
    private synchronized static void loadServerConfigBean(){
        if(null == serverConfigBean){
            ConfigManager configManager = new ConfigManager(new JsonAdapter(), "data/config.json");
            try {
                serverConfigBean = configManager.loadToBean(ServerConfigBean.class);
            } catch (IOException e) {
                serverConfigBean = null;
                e.printStackTrace();
            }
        }
    }
    public static ServerConfigBean getConfig(){
        if(null == serverConfigBean){
            loadServerConfigBean();
        }
        return serverConfigBean;
    }


}
