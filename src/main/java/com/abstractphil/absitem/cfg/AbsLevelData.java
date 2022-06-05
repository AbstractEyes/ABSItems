package com.abstractphil.absitem.cfg;

import lombok.Data;

import java.util.List;

@Data
public class AbsLevelData {
    private int maxLevel;
    private int startingLevel;
    private int baseExpToLevel;
    private double expPctIncPerLvl;
    private List<AbsLevelNode> levelMap;
}
