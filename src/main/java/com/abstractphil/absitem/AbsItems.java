package com.abstractphil.absitem;

import com.abstractphil.absitem.commands.AbsLvlCommand;
import com.abstractphil.absitem.commands.AbsLvlLegacyCommand;
import com.abstractphil.absitem.commands.AbsMakeCommand;
import com.abstractphil.absitem.controller.AbsController;
import org.bukkit.plugin.java.JavaPlugin;

import com.abstractphil.absitem.commands.GiveCommand;

import lombok.Getter;

@Getter
public class AbsItems extends JavaPlugin {
	private AbsController mainController;

	@Override
	public void onEnable() {
		super.onEnable();
		this.mainController = new AbsController();
		this.mainController.init();
		System.out.println("Initialized template");
		getCommand("givetest").setExecutor(new GiveCommand());
		getCommand("absmake").setExecutor(new AbsMakeCommand());
		getCommand("abslvl").setExecutor(new AbsLvlCommand());
		getCommand("abslvla").setExecutor(new AbsLvlLegacyCommand());
	}

	public AbsController controller() {
		return mainController;
	}

	@Override
	public void onDisable() {
		mainController.terminate();
		super.onDisable();
	}

	public static AbsItems getInstance() {
		return JavaPlugin.getPlugin(AbsItems.class);
	}
}
