package com.abstractphil.absitem.mediators;

import com.abstractphil.absitem.tools.AbsItemUtil;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AbsPlaceholderManager {

    private final AbsItemManager effectManager;
    // Placeholder register
    //  Placeholder key
    //  Placeholder replace text

    public AbsPlaceholderManager(AbsItemManager effectManagerIn) {
        effectManager = effectManagerIn;
    }
    private HashMap<String, List<String>> getPlaceholders() {
        return effectManager.getPlaceholders();
    }

    // Todo: prepare placeholder replacement system.
    public String replacePlaceholder(String rawCommand) {
        return "";
    }


    public static String prepare(String unchanged, List<Object> placeholders) {

    }


    public static String colorize(String stringIn) {
        return ChatColor.translateAlternateColorCodes('&', stringIn);
    }

    public static List<String> colorize(List<String> listIn) {
        List<String> list = new ArrayList<>();
        for (String str : listIn) {
            list.add(colorize(str));
        }
        return list;
    }

    public String PreparePlaceholders(Player playerIn, ItemStack itemIn, String rawCommand) {
        if(AbsItemUtil.em().isRedItem(itemIn)) {
            /*
            String out = rawCommand;
            out = out.replace("%player%", playerIn.getName());
            out = out.replace("player", playerIn.getName());
            out = out.replace("{}", playerIn.getName());
            */
        }
        return rawCommand;
    }

}
