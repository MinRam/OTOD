package com.otod.server.otod.pojos;

public class UpdateOption {
    private boolean recommened;

    public UpdateOption() {
    }

    public UpdateOption(boolean recommened){
        this.recommened = recommened;
    }

    public boolean isRecommened() {
        return recommened;
    }

    public void setRecommened(boolean recommened) {
        this.recommened = recommened;
    }
}
