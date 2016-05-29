package edu.tongji.reuse.teameleven.codependent.model;

import edu.tongji.reuse.teameleven.codependent.base.Jsonable;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by daidongyang on 5/29/16.
 */
public class NetInfo implements Jsonable {
    private String ip;
    private int port;

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    @Override
    public String toString() {
        return "NetInfo{" +
                "ip='" + ip + '\'' +
                ", port=" + port +
                '}';
    }

    @Override
    public String toJsonString() {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("type","netinfo");
            jsonObject.put("ip",ip);
            jsonObject.put("port",port);
            return jsonObject.toString();
        } catch (JSONException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public void setByJsonString(String jsonString) {
        try {
            JSONObject jsonObject = new JSONObject(jsonString);
            this.ip = jsonObject.getString("ip");
            this.port = jsonObject.getInt("port");
        } catch (JSONException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
