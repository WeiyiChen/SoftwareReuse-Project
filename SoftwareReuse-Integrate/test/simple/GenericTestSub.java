package simple;

/**
 * Created by daidongyang on 5/22/16.
 */
public class GenericTestSub<T> extends GenericTest<T, Integer> {
    @Override
    T test(T t){
        System.out.println("sub class");

        return super.test(t);
    }
}
