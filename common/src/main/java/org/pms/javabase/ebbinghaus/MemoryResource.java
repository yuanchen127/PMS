package org.pms.javabase.ebbinghaus;

import java.util.Date;

public class MemoryResource implements Comparable<MemoryResource>{
    private String content;
    private Date createTime;//创建时间
    private Date firstTime;//第一次记忆正确的时间
    private Date lastTime; //上次记忆正确的时间
    private boolean memory;

    public MemoryResource(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getFirstTime() {
        return firstTime;
    }

    public void setFirstTime(Date firstTime) {
        this.firstTime = firstTime;
    }

    public Date getLastTime() {
        return lastTime;
    }

    public void setLastTime(Date lastTime) {
        this.lastTime = lastTime;
    }

    public boolean isMemory() {
        return memory;
    }

    public void setMemory(boolean memory) {
        this.memory = memory;
    }

    @Override
    public int compareTo(MemoryResource o) {
        return Integer.parseInt(String.valueOf(this.lastTime.after(o.getLastTime())));
    }
}
