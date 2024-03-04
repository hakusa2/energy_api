package com.energy.welfare.dto;

public class Banner {

    private Long id;
    private String title;
    private String description;
    private String imageFile;
    private String imageUrl;
    private String imageFileDetail;
    private String imageUrlDetail;
    private String linkUrl;
    private String target;
    private String useYn;
    private String createdAt;
    private String updatedAt;

    public Banner() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getImageFile() {
        return imageFile;
    }

    public void setImageFile(String imageFile) {
        this.imageFile = imageFile;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getImageFileDetail() {
        return imageFileDetail;
    }

    public void setImageFileDetail(String imageFileDetail) {
        this.imageFileDetail = imageFileDetail;
    }

    public String getImageUrlDetail() {
        return imageUrlDetail;
    }

    public void setImageUrlDetail(String imageUrlDetail) {
        this.imageUrlDetail = imageUrlDetail;
    }

    public String getLinkUrl() {
        return linkUrl;
    }

    public void setLinkUrl(String linkUrl) {
        this.linkUrl = linkUrl;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

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
