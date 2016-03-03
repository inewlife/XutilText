package com.inewlife.xutiltext.Bean;

import com.lidroid.xutils.db.annotation.Column;
import com.lidroid.xutils.db.annotation.Foreign;
import com.lidroid.xutils.db.annotation.Table;
import com.lidroid.xutils.db.annotation.Transient;

/**
 * Created by inewlife on 2016/3/3.
 */
@Table(name = "student")
public class Student extends EntityBean {
    @Column(column = "name")
    public String name;
    @Column(column = "email")
    public String email;
    @Foreign(column = "teacherId",foreign = "id")
    public Teacher teacher;
    // Transient使这个列被忽略，不存入数据库
    @Transient
    public String willIgnore;

    public static String staticFieldWillIgnore; // 静态字段也不会存入数据库
    @Column(column = "text")
    private String text;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getEmail() {

        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + getId() +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", teacher=" + teacher +
                ", willIgnore='" + willIgnore + '\'' +
                ", text='" + text + '\'' +
                '}';
    }
}
