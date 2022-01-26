package projekt.dto;

import java.io.Serializable;

public class TestDto implements Serializable {

    private String test;
    private String test2;

    public TestDto(String test, String test2) {
        this.test = test;
        this.test2 = test2;
    }

    public String getTest() {
        return test;
    }

    public void setTest(String test) {
        this.test = test;
    }

    public String getTest2() {
        return test2;
    }

    public void setTest2(String test2) {
        this.test2 = test2;
    }
}
