package pers.yurwisher.clockwerk.structural.filter;

/**
 * @author yq
 * @date 2019/09/20 10:48
 * @description 人
 * @since V1.0.0
 */
public class Person {

    /**
     * 名称
     */
    private String name;
    /**
     * 性别
     */
    private String gender;

    /**
     * 已婚/未婚
     */
    private Boolean married;

    public Person(String name, String gender, Boolean married) {
        this.name = name;
        this.gender = gender;
        this.married = married;
    }

    public String getName() {
        return name;
    }

    public String getGender() {
        return gender;
    }

    public Boolean getMarried() {
        return married;
    }
}
