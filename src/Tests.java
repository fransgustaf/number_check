import static org.junit.Assert.*;
import java.util.List;
import org.junit.Test;
  

public class Tests {  
  
    @Test
    public void verifyPersonNumber() {
        List<String> list = List.of("850717-5019", "201701102384", "141206-2380", "20080903-2386", "7101169295", "198107249289", "19021214-9819", "190910199827", "191006089807", "192109099180", "4607137454", "194510168885", "900118+9811", "189102279800", "189912299816");

        for(var number: list) {
            InputNumber inputNumber = new InputNumber(number);
            inputNumber.setup();
            assertTrue(inputNumber.verifyInput());
        }
    }

    @Test
    public void verifyWrongPersonNumber() {
        List<String> list = List.of("201701272394", "190302299813");

        for(var number: list) {
            InputNumber inputNumber = new InputNumber(number);
            inputNumber.setup();
            assertFalse(inputNumber.verifyInput());
        }
    }

    @Test
    public void verifySamordningNumber() {
        List<String> list = List.of("190910799824");

        for(var number: list) {
            InputNumber inputNumber = new InputNumber(number);
            inputNumber.setup();
            assertTrue(inputNumber.verifyInput());
        }
    }

    @Test
    public void verifyOrganisationNumber() {
        List<String> list = List.of("556614-3185", "16556601-6399", "262000-1111", "857202-7566");

        for(var number: list) {
            InputNumber inputNumber = new InputNumber(number);
            inputNumber.setup();
            assertTrue(inputNumber.verifyInput());
        }
    }
}  