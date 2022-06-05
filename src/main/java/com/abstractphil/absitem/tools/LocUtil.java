package com.abstractphil.absitem.tools;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;

/**
 * This exists in wands too. This should be moved to a common lib.
 *
 * @author Redmancometh
 *
 */
public class LocUtil {
	public static List<LivingEntity> checkBlockFor(Location loc, List<UUID> exclusions) {
		List<LivingEntity> entities = new ArrayList();
		for (Entity ent : loc.getChunk().getEntities()) {
			if (!(ent instanceof LivingEntity) || exclusions.contains(ent.getUniqueId()))
				continue;
			Location eye = ((LivingEntity) ent).getEyeLocation();
			Location feet = ent.getLocation();
			if (eye.getBlock().getLocation().equals(loc)) {
				entities.add((LivingEntity) ent);
				continue;
			} else if (feet.getBlock().getLocation().equals(loc))
				entities.add((LivingEntity) ent);
		}
		return entities;
	}

	public static Location getSpawnLoc(Location loc) {
		if (loc.getBlock().getType() == Material.AIR)
			return loc;
		for (int x = 0; x < 10; x++) {
			Location newLoc = loc.add(0, x, 0);
			if (newLoc.getBlock().getType() == Material.AIR)
				return newLoc;
		}
		return null;
	}
}
