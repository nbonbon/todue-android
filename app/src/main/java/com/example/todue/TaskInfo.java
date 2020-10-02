package com.example.todue;

public class TaskInfo {
    private String title;
    private String description;
    private boolean isComplete;

    public TaskInfo()
    {
        this("", "", false);
    }

    public TaskInfo(String title, String description)
    {
        this(title, description, false);
    }

    public TaskInfo(String title, String description, boolean isComplete)
    {
        this.title = title;
        this.description = description;
        this.isComplete = isComplete;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isComplete() {
        return isComplete;
    }

    public void setComplete(boolean complete) {
        isComplete = complete;
    }

    private String getCompareKey() {
        return title + "|" + description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TaskInfo that = (TaskInfo) o;

        return getCompareKey().equals(that.getCompareKey());
    }

    @Override
    public int hashCode() {
        return getCompareKey().hashCode();
    }

    @Override
    public String toString() {
        return getCompareKey();
    }
}
