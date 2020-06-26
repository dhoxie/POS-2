package com.scottsdaleair.utils.config;

public class ConfigStatus {
    private boolean dirty;

    public ConfigStatus() {
        this.dirty = false;
    }

    public ConfigStatus(boolean isDirty) {
        this.dirty = isDirty;
    }

    public boolean isDirty() {
        return this.dirty;
    }

    public void dirtify() {
        this.dirty = true;
    }

    public void clean() {
        this.dirty = false;
    }
}