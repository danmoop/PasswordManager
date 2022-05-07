package application.tools;

import application.model.PasswordSettings;

public class PasswordGenerationManager {

    public static String generatePass(PasswordSettings passwordSettings) {
        int minLength = passwordSettings.getMinLength();
        int maxLength = passwordSettings.getMaxLength();
        int upperCharsNum = passwordSettings.getUpperCharsNum();
        int specialCharsNum = passwordSettings.getSpecialCharsNum();
        boolean useUpperChars = passwordSettings.isUseUpperChars();
        boolean useSpecialChars = passwordSettings.isUseSpecialChars();

        StringBuilder sb = new StringBuilder();
        int length = (int) (Math.round(Math.random() * (maxLength - minLength)) + minLength);
        final String chars = "qwertyuiopasdfghjklzxcvbnm";
        final String specChars = "!'#$%&'()*+,-./:;<=>?@[\\]^_`{|}~";

        for (int i = 0; i < length; i++) {
            sb.append(chars.charAt((int) Math.floor(Math.random() * chars.length())));
        }

        if (useSpecialChars) {
            while (specialCharsNum > 0) {
                int pos = (int) Math.floor(Math.random() * length);
                if (sb.charAt(pos) < 'a' && sb.charAt(pos) > 'z') continue;

                char randomSpecialChar = specChars.charAt((int) Math.floor(Math.random() * specChars.length()));
                sb.setCharAt(pos, randomSpecialChar);
                specialCharsNum--;
            }
        }

        if (useUpperChars) {
            while (upperCharsNum > 0) {
                int pos = (int) Math.floor(Math.random() * length);
                char ch = sb.charAt(pos);

                if (Character.isUpperCase(ch) || (ch < 'a' || ch > 'z')) continue;

                sb.setCharAt(pos, Character.toUpperCase(ch));
                upperCharsNum--;
            }
        }

        return sb.toString();
    }
}