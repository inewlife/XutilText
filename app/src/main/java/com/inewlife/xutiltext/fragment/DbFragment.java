package com.inewlife.xutiltext.fragment;

import android.support.v4.app.Fragment;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.inewlife.xutiltext.Bean.Student;
import com.inewlife.xutiltext.Bean.Teacher;
import com.inewlife.xutiltext.R;
import com.lidroid.xutils.DbUtils;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.db.sqlite.Selector;
import com.lidroid.xutils.db.table.DbModel;
import com.lidroid.xutils.exception.DbException;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by inewlife on 2016/3/2.
 */
public class DbFragment extends Fragment {
    private DbUtils db;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.db_fragment,container,false);
        ViewUtils.inject(this,view);
     return  view;
    }
    @ViewInject(R.id.db_test_btn)
    private  Button stopBtn;
    @ViewInject(R.id.result_txt)
    private TextView resultText;
    @OnClick(R.id.db_test_btn)
    public void testDb(View view){
        String temp = "";
        Teacher teacher = new Teacher();
        teacher.name = "测试"+System.currentTimeMillis();
        teacher.setAdmin(true);
        teacher.setEmail("990719700@qq.com");

        DbUtils db = DbUtils.create(this.getActivity());
        db.configAllowTransaction(true);
        db.configDebug(true);

        Student student = new Student();
        student.name = "学生的名字";
        try {
            Teacher test = db.findFirst(Selector.from(Teacher.class).where("id","int",new int[]{1,3,6}));
            if (test != null){
                student.teacher = test;
                temp += "first teacher:" + test + "\n";
                resultText.setText(temp);
            }else{
                student.teacher = teacher;
            }

            teacher.setTime(new Date());
            teacher.setDate(new java.sql.Date(new Date().getTime()));
            db.saveBindingId(student);

            List<Student> students = db.findAll(Selector.from(Student.class));
            temp += "students size:"+students.size()+"\n";
            resultText.setText(temp);

            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.DATE, -1);
            calendar.add(Calendar.HOUR, 3);

            List<Teacher> list = db.findAll(
                    Selector.from(Teacher.class)
                            .where("id", "<", 54)
                            .and("time", ">", calendar.getTime())
                            .orderBy("id")
                            .limit(10));
            temp += "findTeacherSzie="+list.size()+"\n";
            resultText.setText(temp);
            if (list.size()>0){
                temp += "last teacher:" + list.get(list.size()-1)+"\n";
                resultText.setText(temp);
            }

            Teacher entity = db.findById(Teacher.class, student.teacher.getId());
            temp += "find by id:" + entity.toString() + "\n";
            resultText.setText(temp);

            List<DbModel> dbModels = db.findDbModelAll(Selector.from(Teacher.class)
                    .groupBy("name")
                    .select("name", "count(name) as count"));
            temp += "group by result:" + dbModels.get(0).getDataMap() + "\n";
            resultText.setText(temp);
        } catch (DbException e) {
            temp += "error :" + e.getMessage() + "\n";
            resultText.setText(temp);
        }
    }
}
