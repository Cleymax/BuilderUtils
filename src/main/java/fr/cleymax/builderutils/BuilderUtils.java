package fr.cleymax.builderutils;

import fr.cleymax.builderutils.commands.CommandBlocks;
import fr.cleymax.builderutils.commands.CommandBuilderUtils;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class BuilderUtils extends JavaPlugin implements Listener
{
	private static BuilderUtils instance;
	
	@Override
	public void onEnable()
	{
		instance = this;

//		Register Events
		PluginManager pm = getServer().getPluginManager();
		pm.registerEvents(this, this);
//		Register Commands
		getCommand("bu").setExecutor(new CommandBuilderUtils());
		getCommand("blocks").setExecutor(new CommandBlocks());
		
	}
	
	@Override
	public void onDisable()
	{
		instance = null;
	}
	
	public static BuilderUtils getInstance()
	{
		return instance;
	}
	
	@EventHandler
	public void onInventoryClick(InventoryClickEvent e)
	{
		if (e.getClickedInventory() == null || e.getCurrentItem() == null) return;
		if (e.getCurrentItem().getType() == Material.AIR) return;
		if (e.getClickedInventory().getName().contains("Blocks"))
		{
			e.setCancelled(true);
			e.getWhoClicked().getInventory().addItem(e.getCurrentItem());
		}
	}
	
	@EventHandler(priority = EventPriority.MONITOR)
	public void onPlayerPlaceBlock(BlockPlaceEvent e)
	{
		if (!e.isCancelled())
		{
			if (e.getBlock().getType() == null) return;
			if (e.getBlock().getType() == Material.SKULL && e.getItemInHand().hasItemMeta() &&
			    e.getItemInHand().getItemMeta().hasLore() &&
			    e.getItemInHand().getItemMeta().getLore().get(0).startsWith("§7§lID§7"))
			{
				String[] part = e.getItemInHand().getItemMeta().getLore().get(0).replace("§7§lID§7 ", "").split(":");
				int      id   = Integer.parseInt(part[0]);
				int      data = Integer.parseInt(part[1]);
				
				Bukkit.getScheduler().runTaskLater(BuilderUtils.getInstance(), () -> {
					Material mat = Material.getMaterial(id);
					Block    b   = e.getBlockPlaced();
					b.setType(mat);
					b.setData((byte) data, true);
				}, 0L);
				e.setCancelled(true);
			}
		}
	}
	
	@EventHandler(priority = EventPriority.MONITOR)
	public void placeOfBlockInteract(PlayerInteractEvent e)
	{
		if (!e.isCancelled())
		{
			if (e.getAction().equals(Action.RIGHT_CLICK_BLOCK))
			{
				if (e.getPlayer().getItemInHand().getType().equals(Material.SKULL_ITEM) &&
				    e.getPlayer().getItemInHand().hasItemMeta() &&
				    e.getPlayer().getItemInHand().getItemMeta().hasLore() &&
				    e.getPlayer().getItemInHand().getItemMeta().getLore().get(0).startsWith("§7§lID§7 "))
				{
					String[] id = e.getPlayer()
					               .getItemInHand()
					               .getItemMeta()
					               .getLore()
					               .get(0)
					               .replace("§7§lID§7 ", "")
					               .split(":");
					int _id   = Integer.parseInt(id[0]);
					int _data = Integer.parseInt(id[1]);
					Bukkit.getScheduler().runTaskLater(BuilderUtils.getInstance(), new RenderHead(e, _id, _data), 0L);
					e.setCancelled(true);
				}
			}
		}
	}
	
	private class RenderHead implements Runnable
	{
		private PlayerInteractEvent e;
		private int                 id, data;
		
		public RenderHead(PlayerInteractEvent e, int id, int data)
		{
			this.e = e;
			this.id = id;
			this.data = data;
		}
		
		@Override
		public void run()
		{
			Block b = null;
			if (this.e.getBlockFace().equals(BlockFace.DOWN))
			{
				b = this.e.getClickedBlock().getLocation().clone().add(0.0D, -1.0D, 0.0D).getBlock();
			}
			else if (this.e.getBlockFace().equals(BlockFace.UP))
			{
				b = this.e.getClickedBlock().getLocation().clone().add(0.0D, 1.0D, 0.0D).getBlock();
			}
			else if (this.e.getBlockFace().equals(BlockFace.NORTH))
			{
				b = this.e.getClickedBlock().getLocation().clone().add(0.0D, 0.0D, -1.0D).getBlock();
			}
			else if (this.e.getBlockFace().equals(BlockFace.EAST))
			{
				b = this.e.getClickedBlock().getLocation().clone().add(1.0D, 0.0D, 0.0D).getBlock();
			}
			else if (this.e.getBlockFace().equals(BlockFace.SOUTH))
			{
				b = this.e.getClickedBlock().getLocation().clone().add(0.0D, 0.0D, 1.0D).getBlock();
			}
			else if (this.e.getBlockFace().equals(BlockFace.WEST))
			{
				b = this.e.getClickedBlock().getLocation().clone().add(-1.0D, 0.0D, 0.0D).getBlock();
			}
			b.setTypeId(id);
			b.setData((byte) data, true);
		}
	}
}
