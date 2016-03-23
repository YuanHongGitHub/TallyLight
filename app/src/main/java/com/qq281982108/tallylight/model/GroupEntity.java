package com.qq281982108.tallylight.model;

import java.util.List;

/**
 * 项目名称：TallyLight
 * 创建人：yh
 * 创建时间：2016-03-23 18:04
 * 类名：GroupEntity
 * 修改备注：
 */
public class GroupEntity {

    private String groupName;
    private List<ChildEntity> childEntities;

    public GroupEntity(String groupName) {
        this.groupName = groupName;
    }

    public List<ChildEntity> getChildEntities() {
        return childEntities;
    }

    public void setChildEntities(List<ChildEntity> childEntities) {
        this.childEntities = childEntities;
    }

    public String getGroupName() {
        return groupName;
    }
}