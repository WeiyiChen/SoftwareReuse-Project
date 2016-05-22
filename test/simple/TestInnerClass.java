package simple;

/**
 * Created by daidongyang on 5/22/16.
 */
public class TestInnerClass {
    public class InnerClass{
        public int i = 5;
    }
    private InnerClass inner;

    public TestInnerClass(){
        inner = new InnerClass();
    }

    public static void main(String[] args){
        new TestInnerClass();
    }
}
