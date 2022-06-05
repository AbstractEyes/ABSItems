package com.abstractphil.absitem.tools;

import com.redmancometh.reditems.RedItems;
import com.redmancometh.reditems.mediator.AttachmentManager;
import com.redmancometh.reditems.mediator.EnchantManager;
import com.redmancometh.reditems.mediator.NBTManager;
import net.minecraft.server.v1_8_R3.NBTBase;
import net.minecraft.server.v1_8_R3.NBTTagCompound;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nullable;

public class NBTUtil {

    public static EnchantManager em() {
        return RedItems.getInstance().getEnchantManager();
    }
    public static AttachmentManager am() {
        return RedItems.getInstance().getAttachManager();
    }
    public static NBTManager nbt() {
        return RedItems.getInstance().getNbtManager();
    }

    // Intrinsically a reusable static util for all NBT tag needs.
    @Nullable
    public static NBTTagCompound getNBTTags(ItemStack item) {
        if(nbt().getTag(item).isPresent() ) {
            return nbt().getTag(item).get();
        }
        return null;
    }
    public static ItemStack setNBTDefault(ItemStack item) {
        if(!nbt().getTag(item).isPresent()){
            return nbt().setData(item, new NBTTagCompound());
        }
        return item;
    }
    public static ItemStack setNBTData(ItemStack item, NBTTagCompound comp) {
        if(nbt().getTag(item).isPresent()) {
            return nbt().setData(item, comp);
        }
        return item;
    }

    // Simple nbt interface util.
    public static ItemStack setData(ItemStack item, String key, Object saveData) {
        if(getNBTTags(item) != null){
            NBTTagCompound nbtTags = getNBTTags(item);
            nbtTags = setDataInternal(nbtTags, key, saveData);
            item = nbt().setData(item, nbtTags);
        }
        return item;
    }

    public static ItemStack removeData(ItemStack item, String key) {
        if(getNBTTags(item) != null){
            NBTTagCompound nbtTags = getNBTTags(item);
            nbtTags = removeDataInternal(nbtTags, key);
            item = nbt().setData(item, nbtTags);
        }
        return item;
    }

    private static NBTTagCompound removeDataInternal(NBTTagCompound nbtTags, String key) {
        if(nbtTags.hasKey(key)) {
            nbtTags.remove(key);
        }
        return nbtTags;
    }

    private static NBTTagCompound setDataInternal(NBTTagCompound nbtTags, String key, Object saveData) {
        if(saveData instanceof Integer) nbtTags.setInt(key, (Integer)saveData);
        else if(saveData instanceof String) nbtTags.setString(key, (String)saveData);
        else if(saveData instanceof Short) nbtTags.setShort(key, (Short)saveData);
        else if(saveData instanceof NBTBase) nbtTags.set(key, (NBTBase)saveData);
        else if(saveData instanceof Boolean) nbtTags.setBoolean(key, (Boolean) saveData);
        else if(saveData instanceof Double) nbtTags.setDouble(key, (Double)saveData);
        else if(saveData instanceof int[]) nbtTags.setIntArray(key, (int[])saveData);
        else if(saveData instanceof Float) nbtTags.setFloat(key, (Float)saveData);
        else if(saveData instanceof Byte) nbtTags.setByte(key, (Byte)saveData);
        else if(saveData instanceof byte[]) nbtTags.setByteArray(key, (byte[])saveData);
        return nbtTags;
    }

    @Nullable
    public static Integer getIntData(ItemStack item, String key) {
        if(hasData(item, key)) return nbt().getTag(item).get().getInt(key);
        return null;
    }
    @Nullable
    public static String getStringData(ItemStack item, String key) {
        if(hasData(item, key)) return nbt().getTag(item).get().getString(key);
        return null;
    }
    @Nullable
    public static Boolean getBooleanData(ItemStack item, String key) {
        if(hasData(item, key)) return nbt().getTag(item).get().getBoolean(key);
        return null;
    }
    @Nullable
    public static Short getShortData(ItemStack item, String key) {
        if(hasData(item, key)) return nbt().getTag(item).get().getShort(key);
        return null;
    }
    @Nullable
    public static Double getDoubleData(ItemStack item, String key) {
        if(hasData(item, key)) return nbt().getTag(item).get().getDouble(key);
        return null;
    }
    @Nullable
    public static NBTBase getNBTData(ItemStack item, String key) {
        if(hasData(item, key)) return nbt().getTag(item).get().get(key);
        return null;
    }
    @Nullable
    public static int[] getIntArrayData(ItemStack item, String key) {
        if(hasData(item, key)) return nbt().getTag(item).get().getIntArray(key);
        return null;
    }
    @Nullable
    public static byte[] getByteArrayData(ItemStack item, String key) {
        if(hasData(item, key)) return nbt().getTag(item).get().getByteArray(key);
        return null;
    }
    @Nullable
    public static Byte getByteData(ItemStack item, String key) {
        if(hasData(item, key)) return nbt().getTag(item).get().getByte(key);
        return null;
    }

    public static boolean hasData(ItemStack item, String key) {
        return (getNBTTags(item) != null && nbt().getTag(item).get().hasKey(key));
    }

}
