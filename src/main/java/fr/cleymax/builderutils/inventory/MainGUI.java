package fr.cleymax.builderutils.inventory;

import fr.cleymax.builderutils.utils.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

/**
 * This file is a part of BuilderUtils.
 * <p>
 * Copyright (c) BuilderUtils < ""> - All rights reserved
 *
 * @author Clément (Cleymax) {@literal <cleymaxpro@gmail.com>}
 * Created the 15/02/2019 at 17:20.
 */

public class MainGUI
{
	public void open(Player player)
	{
		Inventory inv = Bukkit.createInventory(null, 27, "§3BuilderUtils");
		for (int i = 0; i < 27; i++)
			inv.setItem(i, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, (byte) 8).setName("§e").build());
		inv.setItem(13, new ItemBuilder(Material.GRASS, 1).setName("§3Blocks").build());
		player.openInventory(inv);
	}
}
