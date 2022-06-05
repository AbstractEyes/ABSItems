package com.abstractphil.absitem.tools;

import com.google.gson.JsonObject;
import com.google.gson.internal.Primitives;
import com.redmancometh.reditems.RedItems;
import com.redmancometh.reditems.mediator.AttachmentManager;
import com.redmancometh.reditems.mediator.EnchantManager;
import com.redmancometh.reditems.mediator.NBTManager;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nullable;
import javax.lang.model.type.PrimitiveType;
import java.util.ArrayList;

// Helper methods for manipulating the json using the ABS keychain style.
public class JsonUtil {
    public static boolean isRedItem(ItemStack item) {
        return(em().isRedItem(item));
    }

    public static EnchantManager em() {
        return RedItems.getInstance().getEnchantManager();
    }
    public static AttachmentManager am() {
        return RedItems.getInstance().getAttachManager();
    }
    public static NBTManager nbt() {
        return RedItems.getInstance().getNbtManager();
    }

    public static ItemStack prepareJsonContainer(ItemStack item) {
        if(isRedItem(item)) {
            if(!em().getData(item).isPresent()) {
                // This shouldn't happen, but if it does, the console should know.
                System.out.println(item);
                System.out.println("Simple container not present for RedItem.");
            }
        }
        return item;
    }

    private static JsonObject getJsonContainer(ItemStack item) {
        if(isRedItem(item)) {
            if(hasJsonContainer(item))
                return em().getData(item).get().getData();
        }
        return null;
    }
    private static boolean hasJsonContainer(ItemStack item) {
        return em().getData(item).isPresent();
    }

    // Commit unnecessary to be shared when used internally only.
    private static ItemStack commit(ItemStack item, JsonObject container) {
        if(em().getData(item).isPresent()) {
            em().getData(item).get().setData(container);
            em().getData(item).get().commit(item);
        }
        return item;
    }

    @Nullable private static JsonObject safeAddJsonWithKeyChain(JsonObject json, ArrayList<String> keyChain) {
        ArrayList<String> list = keyChain;
        String key = list.remove(0);
        if(list.size() > 0){
            if(!json.has(key)){
                json.add(key, new JsonObject());
                return safeAddJsonWithKeyChain(json, list);
            }
        }
        json.add(key, new JsonObject());
        return json;
    }
    @Nullable private static JsonObject safeGetJsonWithKeyChain(JsonObject json, ArrayList<String> keyChain) {
        ArrayList<String> list = keyChain;
        String key = list.remove(0);
        if(list.size() > 0){
            if(json.has(key)){
                return safeAddJsonWithKeyChain(json, list);
            } else {
                return null;
            }
        }
        return json;
    }

    // Sets basic json object data with key chain.
    public static ItemStack setJsonData(ItemStack item, ArrayList<String> keyChain, JsonObject data){
        JsonObject json = getJsonContainer(item);
        if(json != null) {
            if(keyChain.size() > 0) {
                String finalKey = keyChain.remove(keyChain.size() - 1);
                json = safeAddJsonWithKeyChain(json, keyChain);
                // Todo: test assert
                assert json != null; json.add(finalKey, data);
            }
        }
        return commit(item, json);
    }
    public static ItemStack setJsonData(ItemStack item, ArrayList<String> keyChain, int data){
        JsonObject json = getJsonContainer(item);
        if(json != null) {
            if(keyChain.size() > 0) {
                String finalKey = keyChain.remove(keyChain.size() - 1);
                json = safeAddJsonWithKeyChain(json, keyChain);
                // Todo: test assert
                assert json != null; json.addProperty(finalKey, data);
            }
        }
        return commit(item, json);
    }
    public static ItemStack setJsonData(ItemStack item, ArrayList<String> keyChain, boolean data){
        JsonObject json = getJsonContainer(item);
        if(json != null) {
            if(keyChain.size() > 0) {
                String finalKey = keyChain.remove(keyChain.size() - 1);
                json = safeAddJsonWithKeyChain(json, keyChain);
                // Todo: test assert
                assert json != null; json.addProperty(finalKey, data);
            }
        }
        return commit(item, json);
    }
    public static ItemStack setJsonData(ItemStack item, ArrayList<String> keyChain, String data){
        JsonObject json = getJsonContainer(item);
        if(json != null) {
            if(keyChain.size() > 0) {
                String finalKey = keyChain.remove(keyChain.size() - 1);
                json = safeAddJsonWithKeyChain(json, keyChain);
                // Todo: test assert
                assert json != null; json.addProperty(finalKey, data);
            }
        }
        return commit(item, json);
    }

    public static ItemStack setJsonData(ItemStack item, String key, JsonObject obj) {
        JsonObject json = getJsonContainer(item);
        if(json != null) json.add(key, obj);
        return commit(item, json);
    }
    public static ItemStack setJsonData(ItemStack item, String key, int data) {
        JsonObject json = getJsonContainer(item);
        if(json != null) json.addProperty(key, data);
        return commit(item, json);
    }
    public static ItemStack setJsonData(ItemStack item, String key, String data) {
        JsonObject json = getJsonContainer(item);
        if(json != null) json.addProperty(key, data);
        return commit(item, json);
    }
    public static ItemStack setJsonData(ItemStack item, String key, boolean data) {
        JsonObject json = getJsonContainer(item);
        if(json != null) json.addProperty(key, data);
        return commit(item, json);
    }

    @Nullable public static JsonObject getJsonData(ItemStack item, ArrayList<String> keyChain) {
        JsonObject json = getJsonContainer(item);
        if(json != null) {
            if(keyChain.size() > 0) {
                String finalKey = keyChain.remove(keyChain.size() - 1);
                json = safeGetJsonWithKeyChain(json, keyChain);
                if(json != null && json.has(finalKey)) return json.getAsJsonObject(finalKey);
                return null;
            }
        }
        return null;
    }
    @Nullable public static Integer getJsonData(ItemStack item, ArrayList<String> keyChain, int val) {
        JsonObject json = getJsonContainer(item);
        if(json != null) {
            if(keyChain.size() > 0) {
                String finalKey = keyChain.remove(keyChain.size() - 1);
                json = safeGetJsonWithKeyChain(json, keyChain);
                if(json != null && json.has(finalKey)) return json.get(finalKey).getAsInt();
                return null;
            }
        }
        return null;
    }
    @Nullable public static Boolean getJsonData(ItemStack item, ArrayList<String> keyChain, boolean val) {
        JsonObject json = getJsonContainer(item);
        if(json != null) {
            if(keyChain.size() > 0) {
                String finalKey = keyChain.remove(keyChain.size() - 1);
                json = safeGetJsonWithKeyChain(json, keyChain);
                if(json != null && json.has(finalKey)) return json.get(finalKey).getAsBoolean();
                return null;
            }
        }
        return null;
    }
    @Nullable public static String getJsonData(ItemStack item, ArrayList<String> keyChain, String val) {
        JsonObject json = getJsonContainer(item);
        if(json != null) {
            if(keyChain.size() > 0) {
                String finalKey = keyChain.remove(keyChain.size() - 1);
                json = safeGetJsonWithKeyChain(json, keyChain);
                if(json != null && json.has(finalKey)) return json.get(finalKey).getAsString();
                return null;
            }
        }
        return null;
    }

    @Nullable public static JsonObject getJsonData(ItemStack item, String key, JsonObject val) {
        ArrayList<String> keyChain = new ArrayList<>();
        keyChain.add(key);
        return getJsonData(item, keyChain);
    }
    @Nullable public static Integer getJsonData(ItemStack item, String key, int val) {
        ArrayList<String> keyChain = new ArrayList<>();
        keyChain.add(key);
        return getJsonData(item, keyChain, val);
    }
    @Nullable public static Boolean getJsonData(ItemStack item, String key, boolean val) {
        ArrayList<String> keyChain = new ArrayList<>();
        keyChain.add(key);
        return getJsonData(item, keyChain, val);
    }
    @Nullable public static String getJsonData(ItemStack item, String key,  String val) {
        ArrayList<String> keyChain = new ArrayList<>();
        keyChain.add(key);
        return getJsonData(item, keyChain, val);
    }


}
