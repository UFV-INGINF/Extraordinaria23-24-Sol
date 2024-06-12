package com.ufv.dis.api.models;

public class Meta {

    private int totalItems;
    private int itemCount;
    private int itemsPerPage;
    private int totalPages;
    private int currentPage;

    public int getTotalItems() {
        return totalItems;
    }

    public void setTotalItems(int totalItems) {
        this.totalItems = totalItems;
    }

    public int getItemCount() {
        return itemCount;
    }

    public void setItemCount(int itemCount) {
        this.itemCount = itemCount;
    }

    public int getItemsPerPage() {
        return itemsPerPage;
    }

    public void setItemsPerPage(int itemsPerPage) {
        this.itemsPerPage = itemsPerPage;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public Meta(int totalItems, int itemCount, int itemsPerPage, int totalPages, int currentPage) {
        this.totalItems = totalItems;
        this.itemCount = itemCount;
        this.itemsPerPage = itemsPerPage;
        this.totalPages = totalPages;
        this.currentPage = currentPage;
    }

    @Override
    public String toString() {
        return "Meta{" +
                "totalItems=" + totalItems +
                ", itemCount=" + itemCount +
                ", itemsPerPage=" + itemsPerPage +
                ", totalPages=" + totalPages +
                ", currentPage=" + currentPage +
                '}';
    }


}
