package me.chickenstyle.backpack.prompts;

import org.bukkit.conversations.ConversationContext;
import org.bukkit.conversations.NumericPrompt;
import org.bukkit.conversations.Prompt;
import org.bukkit.entity.Player;

import me.chickenstyle.backpack.Backpack;
import me.chickenstyle.backpack.FancyBags;
import me.chickenstyle.backpack.Utils;

public class NumberSlotsPrompt extends NumericPrompt{

	@Override
	public String getPromptText(ConversationContext context) {
		return "<gray>Enter amount of slots between 0-54";
	}

	@Override
	protected Prompt acceptValidatedInput(ConversationContext context, Number number) {
		int slotsAmount = Integer.valueOf(number.toString());
		if (slotsAmount <= 54 && slotsAmount >= 0) {
			int slots = Integer.valueOf(number.toString());
			Player player = (Player) context.getForWhom();
			player.sendMessage(
					FancyBags.getInstance().parse(
							"<green>" + slots
					)
			);
			Backpack pack = FancyBags.creatingBackpack.get(player.getUniqueId());
			pack.setSlotsAmount(slots);
			FancyBags.creatingBackpack.put(player.getUniqueId(), pack);
			return new TexturePrompt();
		} else {
			context.getForWhom().sendRawMessage("<red>This is invalid number for backpack size!");
			return this;
		}
	}
	
	@Override
	protected String getFailedValidationText(ConversationContext context,Number input) {
		return "<red> " + input + " is invalid slots number, please use a valid number!";
	}
}
