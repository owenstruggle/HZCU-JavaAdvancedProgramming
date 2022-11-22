package com.owem.login.control;

import com.owem.login.beans.UserBean;
import com.owem.login.dao.BaseDAO;
import com.owem.login.dao.UserDAO;
import com.owem.login.utils.JDBCUtils;

/**
 * @author Owem
 * @date 2022/9/27 16:29
 * @description TODO
 **/
public class UserManager extends BaseDAO<UserBean> implements UserDAO {

    @Override
    public Boolean loginIn(int id, String password) {
        boolean isLogin = false;

        try {
            String sql = "select count(1) " +
                    "from UserInfo " +
                    "where id = ? and password = ?;";
            Long count = getValue(JDBCUtils.getConnection(), sql, id, password);
            if (count == 1L) {
                isLogin = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return isLogin;
    }
}
