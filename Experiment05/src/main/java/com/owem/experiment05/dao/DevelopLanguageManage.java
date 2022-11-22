package com.owem.experiment05.dao;

import com.owem.experiment05.JDBCUtils;
import com.owem.experiment05.beans.DevelopLanguageBean;

import java.util.List;

/**
 * @author Owem
 * @date 2022/10/18 22:11
 * @description TODO
 **/
public class DevelopLanguageManage extends BaseDAO<DevelopLanguageBean> {
    public List<DevelopLanguageBean> getDevelopLanguages() {
        List<DevelopLanguageBean> list = null;
        try {
            String sql = "select language_name " +
                    "from develop_languages;";
            list = getForList(JDBCUtils.getConnection(), sql);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
