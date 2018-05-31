package vip.wefuwu.echo;

import org.msgpack.annotation.Message;

// 对Bean编码时，需要给Bean增加@Message注解
@Message
public class UserInfo {
    private int age;
    private String name;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
