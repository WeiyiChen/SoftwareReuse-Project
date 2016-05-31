package edu.tongji.reuse.teameleven.processor.ctrl;

import wheellllll.license.License;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by daidongyang on 5/31/16.
 */
public class LicenseCtrl {
    private int maxMessagePerLogin = 100;
    private int maxMessagePerSecond = 5;
    private Map<String, License> userLicenses;
    public LicenseCtrl(){
        userLicenses = new HashMap<>();
    }

    public void setMaxMessagePerLogin(int maxMessagePerLogin) {
        this.maxMessagePerLogin = maxMessagePerLogin;
    }

    public void setMaxMessagePerSecond(int maxMessagePerSecond) {
        this.maxMessagePerSecond = maxMessagePerSecond;
    }

    public void addUser(String user){
        userLicenses.put(user, new License(License.LicenseType.BOTH,
                maxMessagePerLogin, maxMessagePerSecond));
    }

    public boolean use(String user){
        License license = userLicenses.get(user);
        if(license == null){
            userLicenses.put(user, new License(License.LicenseType.BOTH,
                    maxMessagePerLogin, maxMessagePerSecond));
            return false;
        }
        License.Availability availability = license.use();
        if(availability == License.Availability.THROUGHPUTEXCEEDED ||
                availability == License.Availability.CAPACITYEXCEEDED){
            return false;
        }
        return true;
    }

    public void removeUser(String user){
        userLicenses.remove(user);
    }
}
