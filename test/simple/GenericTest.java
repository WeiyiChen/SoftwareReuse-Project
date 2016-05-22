package simple;

/**
 * Created by daidongyang on 5/22/16.
 */
public class GenericTest<T,U> {
    T test(T t){
        System.out.println(t.getClass());
        return t;
    }
    U test2(U t){
        System.out.println(t.getClass());
        return t;
    }
}
