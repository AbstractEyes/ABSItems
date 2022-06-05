package com.abstractphil.absitem.enums;

public enum AbsNBTKeys {
    ABS_KEY("abs", 0),
    ITEM_LEVEL("item:level", 0),
    ITEM_EXP("item:exp", 1),
    ITEM_TOTAL_LEVEL("item:totallevel", 2),
    ITEM_EFFICIENCY("item:efficiency", 3),
    EFFECT_LEVEL("%effect-name%:level", 4),
    EFFECT_EXP("%effect-name%:exp", 5),
    EFFECT_EFFICIENCY("%effect-name%:efficiency", 6);

    private final String key;
    private final int value;
    AbsNBTKeys(String keyIn, int valueIn) {
        this.key = keyIn;
        this.value = valueIn;
    }
    public String getKey() {
        return key;
    }
    public int getValue() {
        return value;
    }
}