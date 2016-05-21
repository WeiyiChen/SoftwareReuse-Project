package base;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by daidongyang on 5/21/16.
 */
public class JsonBuilderBaseTest {

    @Test
    public void getTypeUserContentJson() throws Exception {
        JsonBuilderBase.getTypeContentJson("a","hello");
    }
}