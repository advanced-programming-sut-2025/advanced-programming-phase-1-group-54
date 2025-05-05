package model.enums;

public enum Gender {
    MALE, FEMALE;

    public static Gender getGender(String gender) {
        switch (gender) {
            case "male", "M", "m":
                return Gender.MALE;
            case "female", "F", "f":
                return Gender.FEMALE;
        }
        return null;
    }
}
