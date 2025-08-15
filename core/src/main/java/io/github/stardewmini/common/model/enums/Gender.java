package io.github.stardewmini.common.model.enums;

public enum Gender {
    MALE, FEMALE;

    @Override
    public String toString() {
        String out = super.toString();
        return out.charAt(0) + out.substring(1).toLowerCase();
    }
}
