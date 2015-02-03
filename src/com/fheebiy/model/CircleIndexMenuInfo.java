package com.fheebiy.model;


import java.io.Serializable;

/**
 * Created by bob zhou
 * on 14-8-1
 * Description:兴趣圈首页菜单model
 */
public class CircleIndexMenuInfo implements Serializable {
    private boolean isAdmin;
    private long memberCount;
    private long atCount;
    private String interestLogo;
    private String interestName;
    private String interestDesc;
    private int interestType;
    private boolean gotoHtml;
    private long interestId;
    private String srpId;
    private String keyword;
    private int followMyCount;//新增回复我的

    public int getFollowMyCount() {
        return followMyCount;
    }

    public void setFollowMyCount(int followMyCount) {
        this.followMyCount = followMyCount;
    }

    public String getSrpId() {
        return srpId;
    }

    public void setSrpId(String srpId) {
        this.srpId = srpId;
    }

    public boolean isGotoHtml() {
        return gotoHtml;
    }

    public void setGotoHtml(boolean gotoHtml) {
        this.gotoHtml = gotoHtml;
    }

    public long getInterestId() {
        return interestId;
    }

    public void setInterestId(long interestId) {
        this.interestId = interestId;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

    public long getMemberCount() {
        return memberCount;
    }

    public void setMemberCount(long memberCount) {
        this.memberCount = memberCount;
    }

    public long getAtCount() {
        return atCount;
    }

    public void setAtCount(long atCount) {
        this.atCount = atCount;
    }

    public String getInterestLogo() {
        return interestLogo;
    }

    public void setInterestLogo(String interestLogo) {
        this.interestLogo = interestLogo;
    }

    public String getInterestName() {
        return interestName;
    }

    public void setInterestName(String interestName) {
        this.interestName = interestName;
    }

    public String getInterestDesc() {
        return interestDesc;
    }

    public void setInterestDesc(String interestDesc) {
        this.interestDesc = interestDesc;
    }

    public int getInterestType() {
        return interestType;
    }

    public void setInterestType(int interestType) {
        this.interestType = interestType;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }
}
