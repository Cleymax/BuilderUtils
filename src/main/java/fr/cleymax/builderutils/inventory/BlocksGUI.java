package fr.cleymax.builderutils.inventory;

import fr.cleymax.builderutils.utils.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

/**
 * This file is a part of BuilderUtils.
 * <p>
 * Copyright (c) BuilderUtils < ""> - All rights reserved
 *
 * @author Clément (Cleymax) {@literal <cleymaxpro@gmail.com>}
 * Created the 15/02/2019 at 16:36.
 */

public class BlocksGUI
{
	private static String endGatewayB64   = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZGU0MDNmNDc1YThhNGU1M2ZhMmYxN2Q4MmZkOTU3Y2NkZDFmOWY2NzViYTRhYzQzOWJjN2VmNGJjNjE2ZmIifX19";
	private static String mushroomB64     = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvM2ZhMzljY2Y0Nzg4ZDkxNzlhODc5NWU2YjcyMzgyZDQ5Mjk3YjM5MjE3MTQ2ZWRhNjhhZTc4Mzg0MzU1YjEzIn19fQ==";
	private static String stemB64         = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvODRkNTQxMjc1YzdmOTI0YmNiOWViMmRiYmY0Yjg2NmI3NjQ5YzMzMGE2YTAxM2I1M2Q1ODRmZDRkZGYxODZjYSJ9fX0=";
	private static String fullStemB64     = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZjU1ZmE2NDJkNWViY2JhMmM1MjQ2ZmU2NDk5YjFjNGY2ODAzYzEwZjE0ZjUyOTljOGU1OTgxOWQ1ZGMifX19";
	private static String doubleSlabB64   = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOWVjZTM2OWE0YTE0YzdiOGU3ZWNhYmJjOWQ4MzIzOGI1ZjU1YzQ3Mzg0NjNmNjY4NzhjOTZiNGM3MjE1M2UifX19";
	private static String stoneB64        = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOGRkMGNkMTU4YzJiYjY2MTg2NTBlMzk1NGIyZDI5MjM3ZjViNGMwZGRjN2QyNThlMTczODBhYjY5NzlmMDcxIn19fQ==";
	private static String sandstoneB64    = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZDM2ZTk4NjI4MzJhNmZjYzQ4NTVmYTc5YzFhYWU1ZTczYjkxOTdlYzZiYmQzOGY2MzEyZWZhYzY4MGM2MzI4NSJ9fX0=";
	private static String redSandstoneB64 = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYTJkYTdhYTFhZTZjYzlkNmMzNmMxOGE0NjBkMjM5ODE2MmVkYzIyMDdmZGZjOWUyOGE3YmY4NGQ3NDQxYjhhMiJ9fX0=";
	private static String oakB64          = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZTA1NWFjNzE1YmVkMjdiYTMyODkwNzFmN2QxYjFjNWFiOTRkMTJjNDA5ZjkwNDc1MGMxM2NkM2QwYWI0YTQifX19";
	private static String spruceB64       = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZmZmODk2MWRhODQ3ZTM3YmJkMTE0ZWUxZjc5ODg4MmE5ZWY1NTFjNjkyZjE2NWNhYWFlNGNmNWJmZWVmIn19fQ==";
	private static String birchB64        = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOGIxMjM5NzMxZTM5ZjMxYTFhZDFkYmNkMmI3ODI4YzU5ZmVjY2RlNzhhNzhhNzJmMjVkZjNjMWUyMjY3MzRlYyJ9fX0=";
	private static String jungleB64       = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYTkyYmE5MzYxYmRkODVlMjI2OWU3ZjBiZTEzMWNjMzI1NDFkMWUzOGM2YmExMzdlOGQyMWM2MmY2MjZhYWIxIn19fQ==";
	private static String acaciaB64       = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZTM5ZmE2ZjFhOTY0NTExMWIyNzk2M2E3NmI4YzY2MTE2YTA3MGJjMzkzMDdjNWI5ODU2NzVjODE0ZTI2NSJ9fX0=";
	private static String darkOakB64      = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYWU1YjQ4YTU5MmUxZWI4NGRkMjMwMWU2OWUyYWFhNTFlN2I3YWZjODJmMzFmZjRkMzYzMzFmZTczNzVjNzIifX19";
	
	/**
	 * Open inventory to selected player
	 *
	 * @param player
	 */
	public void open(Player player)
	{
		Inventory inv = Bukkit.createInventory(null, 27, ChatColor.AQUA + "Blocks");
		inv.setItem(0, new ItemBuilder(Material.HUGE_MUSHROOM_1, 1, (byte) 0).setName("§3Brown Mushroom Block")
		                                                                     .build());
		inv.setItem(1, new ItemBuilder(Material.HUGE_MUSHROOM_2, 1, (byte) 0).setName("§3Red Mushroom Block").build());
		inv.setItem(2, ItemBuilder.createHead(mushroomB64, 1, "§3Mushroom Block", "§7§lID§7 99:0"));
		inv.setItem(3, ItemBuilder.createHead(stemB64, 1, "§3Mushroom Block", "§3Mushroom Stem", "§7§lID§7 99:10"));
		inv.setItem(4, ItemBuilder.createHead(fullStemB64, 1, "§3Mushroom Full Stem", "§7§lID§7 99:15"));
		inv.setItem(5, ItemBuilder.createHead(doubleSlabB64, 1, "§3Double Stone Slab", "§7§lID§7 43:0"));
		inv.setItem(6, ItemBuilder.createHead(stoneB64, 1, "§3Smooth Stone", "§7§lID§7 43:8"));
		inv.setItem(7, ItemBuilder.createHead(sandstoneB64, 1, "§3Smooth Sandstone", "§7§lID§7 43:9"));
		inv.setItem(8, ItemBuilder.createHead(redSandstoneB64, 1, "§3Smooth Red Sandstone", "§7§lID§7 181:8"));
		inv.setItem(9, ItemBuilder.createHead(oakB64, 1, "§3Full Bark Oak", "§7§lID§7 17:12"));
		inv.setItem(10, ItemBuilder.createHead(spruceB64, 1, "§3Full Bark Spruce", "§7§lID§7 17:13"));
		inv.setItem(11, ItemBuilder.createHead(birchB64, 1, "§3Full Bark Birch", "§7§lID§7 17:14"));
		inv.setItem(12, ItemBuilder.createHead(jungleB64, 1, "§3Full Bark Jungle", "§7§lID§7 17:15"));
		inv.setItem(13, ItemBuilder.createHead(acaciaB64, 1, "§3Full Bark Acacia", "§7§lID§7 162:12"));
		inv.setItem(14, ItemBuilder.createHead(darkOakB64, 1, "§3Full Bark Dark Oak", "§7§lID§7 162:13"));
		inv.setItem(18, new ItemBuilder(Material.MOB_SPAWNER, 1).setName("§3Spawner Cage").build());
		inv.setItem(20, new ItemBuilder(Material.DRAGON_EGG, 1).setName("§3Dragon Egg").build());
		player.openInventory(inv);
	}
}
