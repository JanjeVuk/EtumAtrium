package net.etum.etumatrium.akia.region.builder;

import java.util.ArrayList;
import java.util.List;

public class Region {

    // Primary
    private final int id;
    private final List<Coordinate> coordinates;
    private final String name;

    // Settings
    private final boolean showTitle;

    // External
    private final Member memberManager;
    private final Group groupManager;

    public Region(int id, List<Coordinate> coordinateList, String name, boolean showTitle, Member memberManager, Group groupManager) {
        this.id = id;
        this.coordinates = new ArrayList<>(coordinateList);
        this.name = name;
        this.showTitle = showTitle;
        this.memberManager = memberManager;
        this.groupManager = groupManager;
    }

    public int getId() {
        return id;
    }

    public List<Coordinate> getCoordinates() {
        return new ArrayList<>(coordinates);
    }

    public String getName() {
        return name;
    }

    public boolean isShowTitle() {
        return showTitle;
    }

    public Member getMemberManager() {
        return memberManager;
    }

    public Group getGroupManager() {
        return groupManager;
    }

    @Override
    public String toString() {
        return "Region{" +
                "id=" + id +
                ", coordinates=" + coordinates +
                ", name='" + name + '\'' +
                ", showTitle=" + showTitle +
                ", memberManager=" + memberManager +
                ", groupManager=" + groupManager +
                '}';
    }
}

