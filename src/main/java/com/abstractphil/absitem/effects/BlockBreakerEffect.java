package com.abstractphil.absitem.effects;

import com.redmancometh.reditems.abstraction.BlockBreakEffect;
import com.redmancometh.warcore.util.Pair;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

import java.util.List;
import java.util.function.Function;

public class BlockBreakerEffect extends AbsEffectClass implements BlockBreakEffect {
    @Override
    public void broke(BlockBreakEvent blockBreakEvent, int i) {

    }

    @Override
    public String getKey() {
        return super.getKey();
    }

    @Override
    public List<Pair<String, Function<Integer, String>>> placeholders() {
        return super.placeholders();
    }

    @Override
    public Pair<Boolean, String> hasBuffType(ItemStack item) {
        return super.hasBuffType(item);
    }

    @Override
    public boolean applicableFor(ItemStack item) {
        return super.applicableFor(item);
    }
    // As levels are gained, the block breaker effect becomes more potent.

}
