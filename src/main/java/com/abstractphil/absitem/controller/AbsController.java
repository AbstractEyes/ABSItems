package com.abstractphil.absitem.controller;

import com.abstractphil.absitem.cfg.AbsEffectData;
import com.abstractphil.absitem.cfg.AbsItemData;
import com.abstractphil.absitem.effects.AbsEffectClass;
import com.abstractphil.absitem.effects.AbsItem;
import com.abstractphil.absitem.mediators.AbsItemManager;
import com.abstractphil.absitem.mediators.AbsPlaceholderManager;
import com.redmancometh.configcore.config.ConfigManager;
import com.abstractphil.absitem.cfg.AbsConfig;

import com.redmancometh.reditems.RedItems;
import lombok.Getter;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Getter
public class AbsController {
	private final ConfigManager<AbsConfig> cfg =
			new ConfigManager<>("mainProjectConfig.json", AbsConfig.class);
	private final Map<String, AbsEffectClass> effectMap = new ConcurrentHashMap<>();
	private final Map<String, AbsItem> itemMap = new ConcurrentHashMap<>();
	private AbsItemManager manager;
	private AbsPlaceholderManager placeholders;

	public void init() {
		cfg.init();
		prepareEffectData();
		prepareItemData();
		manager = new AbsItemManager(cfg.getConfig(), effectMap, itemMap);
		placeholders = new AbsPlaceholderManager(manager);
	}

	public AbsItemManager itemManager() {
		return manager;
	}
	public AbsPlaceholderManager placeholderManager() {
		return placeholders;
	}

	private void prepareEffectData() {
		Map<String, AbsEffectData> effects = cfg.getConfig().getEffects();
		effects.forEach((name, effectData) -> {
			try {
				System.out.println("ABS-EFFECT: " + effectData);
				System.out.println("NEW INSTANCE OF " + effectData.getClazz());
				AbsEffectClass effectInstance = effectData.getClazz().newInstance();
				effectInstance.setData(effectData);
				System.out.println("THE INSTANCE: " + effectInstance);
				System.out.println("NAME: " + effectInstance.getName());
				effectMap.put(name, effectInstance);
				RedItems.getInstance().getEnchantManager().registerEffect(effectInstance);
			} catch (InstantiationException | IllegalAccessException e) {
				e.printStackTrace();
			}
		});
	}

	private void prepareItemData() {
		Map<String, AbsItemData> effects = cfg.getConfig().getItems();
		effects.forEach((name, itemData) -> {
			try {
				System.out.println("ABS-ITEM: " + itemData);
				System.out.println("NEW INSTANCE OF " + itemData.getClazz());
				AbsItem absItemInstance = itemData.getClazz().newInstance();
				absItemInstance.setData(itemData);
				System.out.println("THE INSTANCE: " + absItemInstance);
				System.out.println("NAME: " + absItemInstance.getName());
				itemMap.put(name, absItemInstance);
				RedItems.getInstance().getEnchantManager().registerEffect(absItemInstance);
			} catch (InstantiationException | IllegalAccessException e) {
				e.printStackTrace();
			}
		});
	}

	public AbsEffectData getEffectData(String attachmentName) {
		return cfg.getConfig().getEffects().get(attachmentName);
	}

	public AbsEffectClass getEffectObject(String attachmentName) {
		return effectMap.get(attachmentName);
	}

	public AbsConfig cfg() {
		return cfg.getConfig();
	}

	public void terminate() {
		effectMap.values()
				.forEach((effect) -> RedItems.getInstance().getEnchantManager().deregisterEffect(effect));
	}
}
