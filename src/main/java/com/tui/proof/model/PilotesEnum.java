package com.tui.proof.model;


public enum PilotesEnum {
    LOW_COUNT(5),
    MEDIUM_COUNT(10),
    HIGH_COUNT(15);

    public Integer value;

    PilotesEnum(Integer value) {
        this.value = value;
    }
}
