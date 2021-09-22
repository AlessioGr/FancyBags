package me.chickenstyle.backpack;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import me.chickenstyle.backpack.configs.CustomBackpacks;
import org.bukkit.util.StringUtil;

public class FancyTab implements TabCompleter{
	
	List<String> arguments = new ArrayList<String>();
	
	
	@Override
	public List<String> onTabComplete(CommandSender sender,Command cmd, String label, String[] args) {
		if (arguments.isEmpty()) {
			arguments.add("addbackpack");
			arguments.add("reload");
			arguments.add("give");
			arguments.add("help");
		}
		
		List<String> result = new ArrayList<String>();
		if (args.length == 1) {
			result.addAll(arguments);
		}
		
		if(args.length == 2 && args[0].toLowerCase().equals("give")) {
			for (Player player:Bukkit.getServer().getOnlinePlayers()) {
				result.add(player.getName());
			}
		}
		
		if(args.length == 3 && args[0].toLowerCase().equals("give")) {
			for (Backpack pack:CustomBackpacks.getBackpacks()) {
				result.add(pack.getId() + "");
			}
		}
		arguments.clear();
		StringUtil.copyPartialMatches(args[args.length-1], result, arguments);
		return arguments;
		
		

		
	}

}
