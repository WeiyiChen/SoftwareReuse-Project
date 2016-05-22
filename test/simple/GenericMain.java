package simple;

import java.util.Arrays;

/**
 * Created by daidongyang on 5/22/16.
 */
public class GenericMain {
    public static void main(String[] args){
        int[] a = {2, 1, 3, 9};
        System.out.println(Arrays.toString(a));
        Arrays.sort(a);
        System.out.println(Arrays.toString(a));
    }
}
