package edu.tongji.reuse.teameleven.processor.ctrl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private Logger logger;
    public LicenseCtrl(){
        userLicenses = new HashMap<>();
        logger = LoggerFactory.getLogger(this.getClass());
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
            logger.warn("license == null");
            return false;
        }
        License.Availability availability = license.use();
        if(availability == License.Availability.THROUGHPUTEXCEEDED ||
                availability == License.Availability.CAPACITYEXCEEDED){
            logger.info("availability is false");
            return false;
        }
        logger.info("availability is true");
        return true;
    }

    public void removeUser(String user){
        userLicenses.remove(user);
    }
}
