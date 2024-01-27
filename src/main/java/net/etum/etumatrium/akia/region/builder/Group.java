package net.etum.etumatrium.akia.region.builder;

import java.util.ArrayList;
import java.util.List;

public class Group {
    private final List<String> groupList;

    public Group() {
        this.groupList = new ArrayList<>();
    }

    public void add(String group) {
        groupList.add(group);
    }

    public void remove(String group) {
        groupList.remove(group);
    }

    public List<String> getList() {
        return new ArrayList<>(groupList);
    }

    @Override
    public String toString() {
        return "Group{" +
                "groupList=" + groupList +
                '}';
    }
}
