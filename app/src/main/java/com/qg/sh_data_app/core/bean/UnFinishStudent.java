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
public class UnFinishStudent  extends BaseExpandNode {
    /**
     * id : 123
     * name : 我是天天都不打卡的那个
     */

    private String id;
    private String name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Nullable
    @Override
    public List<BaseNode> getChildNode() {
        return null;
    }

}
