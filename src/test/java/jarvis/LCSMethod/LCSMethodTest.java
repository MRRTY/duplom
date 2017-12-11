package jarvis.LCSMethod;

import org.junit.Test;

import static org.junit.Assert.*;

public class LCSMethodTest {
    @Test
    public void isEquals() throws Exception {
        LCSMethod method = new  LCSMethod();
        System.out.println(method.isEquals("здорове ", "здоровий ", 50));
    }

}