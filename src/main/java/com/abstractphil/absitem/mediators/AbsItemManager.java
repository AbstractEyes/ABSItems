package com.abstractphil.absitem.mediators;

import com.abstractphil.absitem.cfg.AbsEffectData;
import com.abstractphil.absitem.cfg.AbsConfig;
import com.abstractphil.absitem.effects.AbsEffectClass;
import com.abstractphil.absitem.effects.AbsItem;
import com.abstractphil.absitem.enums.AbsNBTKeys;
import com.abstractphil.absitem.tools.AbsItemUtil;
import com.abstractphil.absitem.tools.AbsLevelUtil;
import com.abstractphil.absitem.tools.NBTUtil;
import com.google.gson.JsonObject;
import com.redmancometh.reditems.RedItems;
import com.redmancometh.reditems.abstraction.Effect;
import com.redmancometh.reditems.mediator.AttachmentManager;
import com.redmancometh.reditems.mediator.EnchantManager;
import com.redmancometh.reditems.storage.EnchantData;
import com.redmancometh.reditems.storage.SimpleContainer;
import com.redmancometh.warcore.util.ItemUtil;
import net.minecraft.server.v1_8_R3.NBTTagCompound;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nullable;
import java.util.*;

/*
import com.abstractphil.pumpkin.PumpkinMain;
import com.abstractphil.pumpkin.cfg.PumpkinConfig;
import com.abstractphil.pumpkin.cfg.PumpkinEffectData;
import com.abstractphil.pumpkin.effects.AbstractAxeEffect;
*/
public class AbsItemManager {
    // Manages all of the registered abs items effects.
    AbsConfig config;
    Map<String, AbsEffectClass> effectData;
    Map<String, AbsItem> itemData;

    public AbsItemManager(AbsConfig cfgIn,
                          Map<String, AbsEffectClass> effectsDataIn,
                          Map<String, AbsItem> itemDataIn) {
        config = cfgIn;
        effectData = effectsDataIn;
        itemData = itemDataIn;
    }


    public boolean canLevelEffect(ItemStack item, String effectName, int amount) {
        return (getEffectLevel(item, effectName) + amount) <= getMaxEffectLevel(effectName);
    }

    public ItemStack levelEffect(ItemStack item, String effectName) {
        return levelEffect(item, effectName, 1);
    }

    public ItemStack levelEffect(ItemStack item, String effectName, int levelAmount) {
        return AbsLevelUtil.addItemLevel(item, effectData.get(effectName), levelAmount);
    }

    private int getMaxEffectLevel(String effectName) {
        return effectData.get(effectName).getMaxNaturalLevel();
    }

    public int getEffectLevel(ItemStack item, String effectName) {
        return AbsLevelUtil.getEffectLevel(item, effectData.get(effectName));
    }

    public boolean hasEffect(ItemStack item, String effectName) {
        for (EnchantData effect : eManager().getEffects(item)) {
            if (effect.getEffect().getName().equals(effectName)) return true;
        }
        return false;
    }

    public static boolean hasActiveEffect(Player player, String effectName) {
        for (EnchantData effect : RedItems.getInstance().getEnchantManager().getActiveEffects(player)) {
            if(effectName.equals(effect.getEffect().getName())) return true;
        }
        return false;
    }

    @Nullable
    public static Effect getEffect(ItemStack item, String effectName) {
        for (EnchantData data : AbsItemUtil.em().getEffects(item)) {
            if (effectName.equals(data.getEffect().getName())) {
                return data.getEffect();
            }
        }
        return null;
    }

    public EnchantManager eManager() {
        return RedItems.getInstance().getEnchantManager();
    }
    public AttachmentManager aManager() {
        return RedItems.getInstance().getAttachManager();
    }

    public ItemStack makeAbsItemContainer(String absItemEffectName) {
        return makeSimpleRedItem(absItemEffectName, 0);
    }

    // Creates a simple red item.
    public ItemStack makeSimpleRedItem(String effectName, int level) {
        return makeSimpleRedItem(effectData.get(effectName), level);
    }
    private static ItemStack makeSimpleRedItem(AbsEffectClass effectIn, int level){
        ItemStack item;
        AbsEffectData dataIn = effectIn.getData();
        // Building with Abs and WarCore.
        if(dataIn.getMaterial().equals(Material.SKULL_ITEM)) {
            item = AbsItemUtil.buildSkullItem(dataIn.getMaterial(), dataIn.getDisplayName(), dataIn.getSkullHost());
        } else {
            item = ItemUtil.buildItem(dataIn.getMaterial(), dataIn.getDisplayName(), dataIn.getDisplayLore());
        }
        item = RedItems.getInstance().getEnchantManager().attachEffect(item, effectIn, level);
        item = NBTUtil.setData(item,
                effectIn.getName() + ":" + AbsNBTKeys.EFFECT_LEVEL.name(), level);
        if(AbsItemUtil.em().getData(item).isPresent()) {
            AbsItemUtil.em().getData(item).get().setData(new JsonObject());
        }
        return item;
    }

    private AbsEffectClass getEffect(String effectName) {
        return effectData.get(effectName);
    }

    private static ArrayList<EnchantData> removeEffect(ArrayList<EnchantData> data, Effect effectToRemove) {
        if(data.size() == 0) return data;
        int oi = -1;
        for (int i = 0, dataSize = data.size(); i < dataSize; i++) {
            EnchantData ench = data.get(i);
            if (ench.getEffect().getName().equals(effectToRemove.getName())) {
                oi = i;
                break;
            }
        }
        if(oi >= 0) data.remove(oi);
        return data;
    }

    public static boolean isRedItem(ItemStack item) {
        return RedItems.getInstance().getEnchantManager().isRedItem(item);
    }

    /*
    private void setInternalEffectLevel(ItemStack item, String effectName, int level) {
        System.out.println("Getting internal effect level: " + item + " " + effectName + " " + level);
        Class<? extends Effect> classType = getEffectClass(effectName);
        Object effect = getEffect(getEffectClass(effectName), item, effectName);
        System.out.println("Getting effect list: " + eManager().getEffects(item));
        if(effect != null) {
            System.out.println("Setting internal effect" + effect);
            if(effect.getData() != null) {
                System.out.println("Setting level via json interface");
                AbsPhilJsonUtil.setEffectLevel(getJsonStatistics(item), effectName, level);
            }
        }
    }
    */
    /*
    // Creates a simple red item attachment.
    private ItemStack makeSimpleRedItemAttachment(String effectName, int level){
        return makeSimpleRedItemAttachment(effectData.get(effectName).getData(), level);
    }
    private ItemStack makeSimpleRedItemAttachment(PumpkinEffectData dataIn, int level){
        ItemStack item = makeSimpleRedItem(dataIn, level);
        aManager().makeAttachment(item);
        return item;
    }*/
    /*
    public int pumpkinsBroken(ItemStack item) {
        if(getJsonStatistics(item) != null && isPumpkinAxe(item) && getJsonStatistics(item) != null)
            return AbsPhilJsonUtil.getPumpkinsBroken(getJsonStatistics(item));
        return 0;
    }
    */
    //public int getEffectLevel(ItemStack item, String effectName) {
    //  if(eManager().isRedItem(item) && eManager().getEffectLevel(item, ))
    //}
    /*
    private ItemStack levelEffectSafeWithLores(Player player, ItemStack item, String effectName, int levelAmount) {
        try {
            if(!canLevelEffect(player, item, effectName, levelAmount)){
                //player.sendMessage("Blessing level " + passiveName + " is too high to level up.");
                return item;
            }
            //System.out.println("Passive name: " + passiveName);
            int level = getEffectLevel(player, item, effectName);
            //System.out.println("Level: " + level);
            if(level < 0) level = 0;
            System.out.println("Raw Effect: " + getEffect(effectName));
            Effect effect = getEffect(effectName);
            int finalLevel = Math.max(1, level + levelAmount);
            if(finalLevel <= effect.getMaxNaturalLevel()) {
                System.out.println("Level before: " + getEffectLevel(player, item, effectName));
                // Get simple container.
                Optional<SimpleContainer> data = RedItems.getInstance().getEnchantManager().getData(item);
                // Get enchantment list.
                System.out.println("Level Data: " + data);
                @Nullable ArrayList<EnchantData> eData;
                if(data.isPresent()) {
                    eData = (ArrayList<EnchantData>)data.get().getEnchants();
                    if(eData != null){
                        //System.out.println("eData " + eData);
                        ArrayList<EnchantData> newData = removeEffect(eData, effectData.get(effectName));
                        //System.out.println("newData " + newData);
                        data.get().setEnchants(newData);
                        //System.out.println("newData " + newData);
                    }
                    // Todo: set level via nbt tag
                    //setEffectLevel(data.get().getData(), effectName, finalLevel);
                    eManager().attachEffect(item, effectData.get(effectName), finalLevel);

                    /*
                    String vanillaEnchant = getEffect(effectName).getData().getVanillaEnchant();
                    if(vanillaEnchant != null && !vanillaEnchant.equals("")) {
                        if(item.containsEnchantment(Enchantment.getByName(vanillaEnchant))){
                            item.removeEnchantment(Enchantment.getByName(vanillaEnchant));
                        }
                        item.addEnchantment(Enchantment.getByName(vanillaEnchant), finalLevel);
                    }
                    System.out.println("readyitem " + item);
                    item = bakePumpkinAxeLores(player, item);
                    data.get().commit(item);
                    System.out.println("Final item: " + item);
                } else {
                    System.out.println("You've attempted to level an item that cannot level");

                }
                return item;
            } else {
                return item;
            }
        } catch (Exception ex) {
            System.out.println("failed to add a level");
            ex.printStackTrace();
        }
        return item;
    }
    */

    // Pumpkin Axe Specific Functions ----------------------------------------------
    // Pumpkin Axe Specific Functions ----------------------------------------------
    // Pumpkin Axe Specific Functions ----------------------------------------------
    /*
    public boolean isPumpkinAxe(ItemStack item) {
        if(isRedItem(item)){
            for (EnchantData effect : eManager().getEffects(item)) {
                if (effect.getEffect().getName().equals("paxe")) return true;
            }
        }
        return false;
    }
    public boolean isPumpkinMask(ItemStack item) {
        if(isRedItem(item)){
            for (EnchantData effect : eManager().getEffects(item)) {
                if (effect.getEffect().getName().equals("pmask")) return true;
            }
        }
        return false;
    }
    public ItemStack createPumpkinAxe(Player player) {
        ItemStack item = makeSimpleRedItem(effectData.get("paxe"), 1);
        AbsPhilJsonUtil.setEffectLevel(getJsonStatistics(item), "paxe", 1);
        item = bakePumpkinAxeLores(player, item);
        item = getSimpleRedItemData(item).get().commit(item);
        return item;
    }
     */

    /*
    private ItemStack bakePumpkinAxeLores(Player player, ItemStack item) {
        if(isPumpkinAxe(item)) {
            ArrayList<String> lores = new ArrayList<>();
            //lores.add(colorize("**&6Chopped: &c" + pumpkinsBroken(item)) );
            for (EnchantData enchantData : eManager().getEffects(item)) {
                if(enchantData.getEffect().getName().equals("paxe")) continue; // exclude paxe until the end.
                AbstractAxeEffect effect = (AbstractAxeEffect) enchantData.getEffect();
                if(effect.getData().getVanillaEnchant() != null && !effect.getData().getVanillaEnchant().equals("")) continue;
                ArrayList<String> cLores = effect.getBakeLore(player);
                lores.addAll(colorize(cLores));
                lores = replaceAmounts(item, lores);
            }
            // Prepare paxe specific lores last.
            EnchantData enchantData = eManager().getEffects(item).get(0);
            AbstractAxeEffect effect = (AbstractAxeEffect) enchantData.getEffect();
            ArrayList<String> cLores = effect.getBakeLore(player);
            lores.addAll(colorize(cLores));
            lores = replaceAmounts(item, lores);
            // ---------------------------------
            item = ItemUtil.setLore(item, lores);
            return item;
        }
        return item;
    }
    */


    // Pumpkin Axe Specific Functions ----------------------------------------------
    // Pumpkin Axe Specific Functions ----------------------------------------------
    // Pumpkin Axe Specific Functions ----------------------------------------------


    /*
    public ArrayList<String> replaceAmounts(ItemStack item, List<String> listIn) {
        ArrayList<String> list = new ArrayList<>();
        for (String str : listIn) {
            String preppedString = str;
            for (Map.Entry<String, AbstractAxeEffect> entry : effectData.entrySet()) {
                String key = entry.getKey();
                AbstractAxeEffect eff = entry.getValue();
                int internalAmount = getSafeInternalJsonAmountInt(item, eff.getData().getEffectName());
                if (str.contains("%" + eff.getName()))
                    preppedString = str.replace("%" + eff.getName(), String.valueOf(internalAmount));
            }
            list.add(preppedString);
        }
        return list;
    }
    */
    public void terminate() {
    }

}
