package com.ufv.dis.api.models;


import java.util.Arrays;

public class APIResponse {

    private Meta meta;
    private Character[] items;

    public APIResponse(Meta meta, Character[] items) {
        this.meta = meta;
        this.items = items;
    }

    public Meta getMeta() {
        return meta;
    }

    public void setMeta(Meta meta) {
        this.meta = meta;
    }

    public Character[] getItems() {
        return items;
    }

    public void setitems(Character[] items) {
        this.items = items;
    }
}
