package cn.liuyiyou.mq.entity;

/**
 * @author: liuyiyou.cn
 * @date: 2020/11/23
 * @version: V1.0
 */
public class Student {

    private Long id;
    private String name;
    private String rollNumber;

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getRollNumber() {
        return rollNumber;
    }

    public void setRollNumber(final String rollNumber) {
        this.rollNumber = rollNumber;
    }
}
