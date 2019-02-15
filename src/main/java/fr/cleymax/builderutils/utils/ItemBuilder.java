package fr.cleymax.builderutils.utils;

import net.minecraft.util.com.mojang.authlib.GameProfile;
import net.minecraft.util.com.mojang.authlib.properties.Property;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.UUID;

/**
 * This file is a part of BuilderUtils.
 * <p>
 * Copyright (c) BuilderUtils < ""> - All rights reserved
 *
 * @author Clément (Cleymax) {@literal <cleymaxpro@gmail.com>}
 * Created the 15/02/2019 at 16:38.
 */

public class ItemBuilder
{
	private ItemStack is;
	
	public ItemBuilder(Material material)
	{
		this(material, 1);
	}
	
	public ItemBuilder(Material material, int count)
	{
		this(material, count, (byte) 0);
	}
	
	public ItemBuilder(Material material, int count, byte damage)
	{
		is = new ItemStack(material, count, damage);
	}
	
	public ItemBuilder setName(String name)
	{
		ItemMeta im = is.getItemMeta();
		im.setDisplayName(name);
		is.setItemMeta(im);
		return this;
	}
	
	public static ItemStack createHead(String data, int count, String name, String... lore)
	{
		ItemStack item = new ItemStack(Material.SKULL_ITEM);
		item.setDurability((short) 3);
		item.setAmount(count);
		ItemMeta im = item.getItemMeta();
		im.setDisplayName(name);
		im.setLore(Arrays.asList(lore));
		item.setItemMeta(im);
		
		SkullMeta   sm      = (SkullMeta) item.getItemMeta();
		GameProfile profile = new GameProfile(UUID.randomUUID(), null);
		profile.getProperties().put("textures", new Property("textures", data));
		
		try
		{
			Field profileField = sm.getClass().getDeclaredField("profile");
			profileField.setAccessible(true);
			profileField.set(sm, profile);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		item.setItemMeta(sm);
		return item;
	}
	
	public ItemBuilder setOwner(String name)
	{
		SkullMeta im = (SkullMeta) is.getItemMeta();
		im.setOwner(name);
		is.setItemMeta(im);
		return this;
	}
	
	public ItemBuilder setLore(String... lines)
	{
		ItemMeta im = is.getItemMeta();
		im.setLore(Arrays.asList(lines));
		is.setItemMeta(im);
		return this;
	}
	
	public ItemStack build()
	{
		return is;
	}
}
