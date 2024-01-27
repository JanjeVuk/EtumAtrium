package net.etum.etumatrium.akia.region.gui.builder;

import java.util.ArrayList;
import java.util.List;

public class Region {

    private final int id;
    private final List<Coordinate> coordinateList;
    private final String name;
    private final List<String> memberList;
    private final List<String> groupList;

    public Region(int id, List<Coordinate> coordinateList, String name) {
        this.id = id;
        this.coordinateList = coordinateList;
        this.name = name;
        this.memberList = new ArrayList<>();
        this.groupList = new ArrayList<>();
    }

    // Méthodes pour gérer les membres
    public void addMember(String member) {
        memberList.add(member);
    }

    public void removeMember(String member) {
        memberList.remove(member);
    }

    public List<String> getMembers() {
        return memberList;
    }

    // Méthodes pour gérer les groupes
    public void addGroup(String group) {
        groupList.add(group);
    }

    public void removeGroup(String group) {
        groupList.remove(group);
    }

    public List<String> getGroups() {
        return groupList;
    }


    @Override
    public String toString() {
        return "RegionBuilder{" +
                "id=" + id +
                ", coordinateList=" + coordinateList +
                ", name='" + name + '\'' +
                ", memberList=" + memberList +
                ", groupList=" + groupList +
                '}';
    }
}
