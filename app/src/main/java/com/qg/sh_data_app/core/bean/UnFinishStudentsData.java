package com.qg.sh_data_app.core.bean;

import com.chad.library.adapter.base.entity.node.BaseExpandNode;
import com.chad.library.adapter.base.entity.node.BaseNode;

import org.jetbrains.annotations.Nullable;

import java.util.List;

/**
 * @description:
 * @author: ODM
 * @date: 2020/4/9
 */
public class UnFinishStudentsData extends BaseExpandNode {


    /**
     * time : 2020-04-07
     * students : [{"id":"123","name":"我是天天都不打卡的那个"},{"id":"12345678905","name":"树干"}]
     */

    private String time;
    private List<UnFinishStudent> students;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public List<UnFinishStudent>  getStudents() {
        return students;
    }

    public void setStudents(List<UnFinishStudent>   students) {
        this.students = students;
    }

    @Nullable
    @Override
    public List<BaseNode> getChildNode() {
        return (List<BaseNode>) (Object)students;
    }
}
