package com.inewlife.xutiltext.Bean;

import com.lidroid.xutils.db.annotation.Column;
import com.lidroid.xutils.db.annotation.Finder;
import com.lidroid.xutils.db.annotation.Table;
import com.lidroid.xutils.db.sqlite.FinderLazyLoader;

import java.util.Date;

/**
 * Created by inewlife on 2016/3/3.
 */
@Table(name = "teacher", execAfterTableCreated = "CREATE UNIQUE INDEX index_name ON teacher(name,email)")
public class Teacher extends EntityBean{
    @Column(column = "name") // 建议加上注解， 混淆后列名不受影响
    public String name;

    @Column(column = "email")
    private String email;

    @Column(column = "isAdmin")
    private boolean isAdmin;

    @Column(column = "time")
    private Date time;

    @Column(column = "date")
    private java.sql.Date date;

    @Finder(valueColumn = "id",targetColumn = "teacherId")
    public FinderLazyLoader<Student> students;

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public java.sql.Date getDate() {
        return date;
    }

    public void setDate(java.sql.Date date) {
        this.date = date;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "id=" + getId() +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", isAdmin=" + isAdmin +
                ", time=" + time +
                ", date=" + date +
                '}';
    }

}
