package com.abstractphil.absitem.cfg;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class AbsLevelNode {
    private int expToLevel, multiplierGain;
    private Map<String, Integer> unlockedEffects;
}
