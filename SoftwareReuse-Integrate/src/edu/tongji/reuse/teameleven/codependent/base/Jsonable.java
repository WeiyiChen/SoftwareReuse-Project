package edu.tongji.reuse.teameleven.codependent.base;

/**
 * Created by daidongyang on 5/24/16.
 */
public interface Jsonable {
    String toJsonString();
    void setByJsonString(String jsonString);
}
