package com.energy.welfare.dto;

public class Faq {

    private Long id;
    private String qTitle;
    private String aTitle;
    private String description;
    private String[] descriptionList;
    private String useYn;
    private String createdAt;
    private String updatedAt;

    public Faq() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getQTitle() { return qTitle; }

    public void setQTitle(String qTitle) { this.qTitle = qTitle; }

    public String getATitle() { return aTitle; }

    public void setATitle(String aTitle) { this.aTitle = aTitle; }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String[] getDescriptionList() { return descriptionList; }

    public void setDescriptionList(String[] descriptionList) { this.descriptionList = descriptionList; }

    public String getUseYn() {
        return useYn;
    }

    public void setUseYn(String useYn) {
        this.useYn = useYn;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }
}
