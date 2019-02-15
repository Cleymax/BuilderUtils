package fr.cleymax.builderutils.commands;

import fr.cleymax.builderutils.BuilderUtils;
import fr.cleymax.builderutils.inventory.BlocksGUI;
import fr.cleymax.builderutils.inventory.MainGUI;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * This file is a part of BuilderUtils.
 * <p>
 * Copyright (c) BuilderUtils < ""> - All rights reserved
 *
 * @author Clément (Cleymax) {@literal <cleymaxpro@gmail.com>}
 * Created the 15/02/2019 at 16:29.
 */

public class CommandBuilderUtils implements CommandExecutor
{
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
	{
		if (!(sender instanceof Player))
		{
			sender.sendMessage(ChatColor.RED + "You must be a player to perform this command !");
			return true;
		}
		Player p = (Player) sender;
		if (p.hasPermission(BuilderUtils.getInstance().getDescription().getName() + "." + command.getName()))
		{
			MainGUI gui = new MainGUI();
			gui.open(p);
		}
		p.sendMessage(ChatColor.RED + "You don't have permission to perform this command !");
		return true;
	}
}
