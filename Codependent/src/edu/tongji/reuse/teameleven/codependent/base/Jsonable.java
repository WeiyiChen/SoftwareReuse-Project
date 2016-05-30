package edu.tongji.reuse.teameleven.codependent.base;

/**
 * Create jsonString according an instance of a class, and set attribute of the class
 * accordintg to the json string
 * Created by daidongyang on 5/24/16.
 */
public interface Jsonable {
    String toJsonString();
    void setByJsonString(String jsonString);
}
