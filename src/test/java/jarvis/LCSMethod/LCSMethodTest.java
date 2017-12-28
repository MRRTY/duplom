package jarvis.LCSMethod;

import org.junit.Test;

public class LCSMethodTest {
    @Test
    public void isEquals() throws Exception {
        LCSMethod method = new  LCSMethod();
        System.out.println(method.getResult("здорове ", "здоровий "));
    }

}