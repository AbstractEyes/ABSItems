package com.abstractphil.absitem.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GiveCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (!sender.isOp())
			return false;
		String itemName = args[0];
		String playerName = args[1];
		int quantity = args.length > 2 ? Integer.parseInt(args[2]) : 1;
		Player target = Bukkit.getPlayer(playerName);
		//Todo: do some stuff with the commands.
		//ItemStack buffItem = TemplateMain.getInstance().getTemplateController().getAttachmentItem(itemName);
		//buffItem.setAmount(quantity);
		//buffItem = RedItems.getInstance().getAttachManager().makeAttachment(buffItem);
		//target.getInventory().addItem(buffItem);
		return true;
	}

}
