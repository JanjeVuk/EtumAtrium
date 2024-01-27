package net.etum.etumatrium.akia.region.builder;

import java.util.ArrayList;
import java.util.List;

public class Member {
    private final List<String> memberList;

    public Member() {
        this.memberList = new ArrayList<>();
    }

    public void add(String member) {
        memberList.add(member);
    }

    public void remove(String member) {
        memberList.remove(member);
    }

    public List<String> getList() {
        return new ArrayList<>(memberList);
    }

    @Override
    public String toString() {
        return "Member{" +
                "memberList=" + memberList +
                '}';
    }
}
