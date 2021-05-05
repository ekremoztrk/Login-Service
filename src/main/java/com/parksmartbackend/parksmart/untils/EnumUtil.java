package com.parksmartbackend.parksmart.untils;

public class EnumUtil {

    /**
     * A common method for all enums since they can't have another base class
     * 
     * @param <T> Enum type
     * @param c enum type. All enums must be all caps.
     * @param string case insensitive
     * @return corresponding enum, or null
     */

    private EnumUtil() {

        throw new IllegalStateException("Utility class");
    }

    public static <T extends Enum<T>> T getEnumFromString(Class<T> c, String string) {

        if (c != null && string != null) {
            try {
                return Enum.valueOf(c, string.trim().toUpperCase());
            } catch (IllegalArgumentException ex) {
                // throw new IllegalArgumentException(ex.getMessage());
            }
        }
        return null;
    }
}
