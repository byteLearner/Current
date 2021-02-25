package com.current.model;

/**
 * @description: CountDownLatch使用model
 * @author: liuqiang
 * @time: 2021/2/25 9:53
 */
public enum  CountryEnum {
    ONE(1, "齐"), TWO(2, "楚"), THREE(3, "燕"), FOUR(4, "赵"), FIVE(5, "魏"), SIX(6, "韩");

    private Integer index;
    private String country;

    CountryEnum(Integer index, String country) {
        this.index = index;
        this.country = country;
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
    public static String getEnumCountry(Integer i) {
        CountryEnum[] countryEnums = CountryEnum.values();
        for (CountryEnum countryEnum:countryEnums) {
            if (countryEnum.getIndex() == i) {
                return countryEnum.getCountry();
            }
        }
        return null;
    }
}
