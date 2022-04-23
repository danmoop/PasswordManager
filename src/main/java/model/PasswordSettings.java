package model;

public class PasswordSettings {
    private int minLength;
    private int maxLength;
    private int upperCharsNum;
    private boolean useSpecialChars;
    private boolean useUpperChars;

    public PasswordSettings(int minLength, int maxLength, int upperCharsNum, boolean useSpecialChars, boolean useUpperChars) {
        this.minLength = minLength;
        this.maxLength = maxLength;
        this.upperCharsNum = upperCharsNum;
        this.useSpecialChars = useSpecialChars;
        this.useUpperChars = useUpperChars;
    }

    public int getMinLength() {
        return minLength;
    }

    public void setMinLength(int minLength) {
        this.minLength = minLength;
    }

    public int getMaxLength() {
        return maxLength;
    }

    public void setMaxLength(int maxLength) {
        this.maxLength = maxLength;
    }

    public int getUpperCharsNum() {
        return upperCharsNum;
    }

    public void setUpperCharsNum(int upperCharsNum) {
        this.upperCharsNum = upperCharsNum;
    }

    public boolean isUseSpecialChars() {
        return useSpecialChars;
    }

    public void setUseSpecialChars(boolean useSpecialChars) {
        this.useSpecialChars = useSpecialChars;
    }

    public boolean isUseUpperChars() {
        return useUpperChars;
    }

    public void setUseUpperChars(boolean useUpperChars) {
        this.useUpperChars = useUpperChars;
    }
}