package com.parksmartbackend.parksmart.model;

import com.parksmartbackend.parksmart.untils.EnumUtil;

public enum ERole {

    ROLE_SYSTEM_ADMIN {

        @Override
        public String toString() {

            return "System Admin";
        }
    },
    ROLE_PARKING_ADMINISTRATOR {

        @Override
        public String toString() {

            return "Parking Administrator";
        }
    };

    public static ERole fromString(String input) {

        return EnumUtil.getEnumFromString(ERole.class, input);
    }

}
