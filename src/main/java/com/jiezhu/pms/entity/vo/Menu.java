package com.jiezhu.pms.entity.vo;

import java.util.ArrayList;
import java.util.List;

public class Menu {
    private Integer id;
    private String name;
    private List<Menu> children = new ArrayList<Menu>();
    private boolean flag;

    public void addChild(Menu menu) {
        children.add(menu);
    }

    public List<Menu> getChildren() {
        return children;
    }

    public Integer getId() {
        return id;
    }

    public List<Menu> getLeaf() {
        List<Menu> menuList = new ArrayList();
        if (children.size() == 0)
            return children;
        for (Menu menu : children) {
            if (menu.getChildren() == null) {
                menuList.add(menu);
            } else {
                Menu m = new Menu();
                m.setId(menu.id);
                m.setName(menu.name);
                m.setFlag(menu.flag);
                menuList.add(m);
                menuList.addAll(menu.getChildren());
            }
        }
        return menuList;
    }

    public String getName() {
        return name;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setChildren(List<Menu> children) {
        this.children = children;
    }

    public void setFlag() {
        this.flag = true;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

}
