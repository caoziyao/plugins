package com.zel.market.common.enumcom;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public enum SeasonEnum {
    SPRING("春"),

    SUMMER("夏"),

    AUTUMN("秋"),

    WINTER("冬");

    private static final Map<String, SeasonEnum> MAP = new HashMap<>();

    static {

        for (SeasonEnum season : values()) {
            MAP.put(season.name, season);
        }
    }

    private final String name;

    SeasonEnum(String name) {

        this.name = name;
    }

    public static SeasonEnum valueOfSeason(String name) {

        return MAP.get(name);
    }

    public String toLocale() {
        if (Locale.CHINA.equals(Locale.getDefault())) {
            return name;
        }
        return toString();
    }

    public static void main(String[] args) {

        // 如果转换不成功，抛出 java.lang.IllegalArgumentException
        SeasonEnum spring = SeasonEnum.valueOf("SPRING");
        System.out.println(spring);

        // null
        SeasonEnum summer = SeasonEnum.valueOfSeason("夏1");
        System.out.println(summer);

        SeasonEnum summer2 = SeasonEnum.valueOfSeason("夏");
        System.out.println(summer2);

        System.out.println(SeasonEnum.AUTUMN.toLocale());
    }
}
