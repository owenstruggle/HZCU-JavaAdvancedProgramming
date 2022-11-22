package com.owem.experiment05.dao;

import com.owem.experiment05.JDBCUtils;
import com.owem.experiment05.beans.StudentBean;

import java.util.List;

/**
 * @author Owem
 * @date 2022/10/18 22:10
 * @description TODO
 **/
public class StudentManage extends BaseDAO<StudentBean> {
    public boolean isExist(int id) {
        List<StudentBean> list;
        try {
            String sql = "select * " +
                    "from students " +
                    "where id = ?;";
            list = getForList(JDBCUtils.getConnection(), sql, id);
            return list != null && list.size() != 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public void deleteStudent(int id) {
        try {
            String sql = "delete from students " +
                    "where id = ?;";
            update(JDBCUtils.getConnection(), sql, id);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateStudent(StudentBean newStudent) {
        try {
            String sql = "update students set full_name = ?, gender = ?, phone_number = ?, address = ?, hobby = ?, area = ?, develop_language = ? " +
                    "where id = ?;";
            update(JDBCUtils.getConnection(), sql, newStudent.getFull_name(), newStudent.isGender(), newStudent.getPhone_number(), newStudent.getAddress(), newStudent.getHobby(), newStudent.getArea(), newStudent.getDevelop_language(), newStudent.getId());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void insertStudent(StudentBean newStudent) {
        try {
            String sql = "insert into students(id, full_name, gender, phone_number, address, hobby, area, develop_language) " +
                    "values (?, ?, ?, ?, ?, ?, ?, ?);";
            update(JDBCUtils.getConnection(), sql, newStudent.getId(), newStudent.getFull_name(), newStudent.isGender(), newStudent.getPhone_number(), newStudent.getAddress(), newStudent.getHobby(), newStudent.getArea(), newStudent.getDevelop_language());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public StudentBean getStudent(int id) {
        List<StudentBean> newStudentList = null;
        try {
            String sql = "select * " +
                    "from students " +
                    "where id = ?;";
            newStudentList = getForList(JDBCUtils.getConnection(), sql, id);
        } catch (Exception e) {
            e.printStackTrace();
        }

        assert newStudentList != null;
        return newStudentList.get(0);
    }
}
