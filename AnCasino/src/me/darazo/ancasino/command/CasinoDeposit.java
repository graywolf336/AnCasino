package me.darazo.ancasino.command;

import org.bukkit.entity.Player;

import com.craftyn.casinoslots.AnCasino;

import me.darazo.ancasino.slot.SlotMachine;

public class CasinoDeposit extends AnCommand {
	
	// Command for removing slot machine
	public CasinoDeposit(AnCasino plugin, String[] args, Player player) {
		super(plugin, args, player);
	}
	
	public Boolean process() {
		// Correct command format
		if(args.length == 3) {
			
			// Slot exists
			if(plugin.slotData.isSlot(args[1])) {
				SlotMachine slot = plugin.slotData.getSlot(args[1]);
				
				// Can access slot
				if(isOwner(slot)) {
					String Line3 = args[2];
					double amount;
					try {
						if (Line3.startsWith("-")) {
							sendMessage("Must deposit a postive amount.");
							return true;
						}else {
							amount = Double.parseDouble(args[2]);
						}
					} catch (NumberFormatException e) {
						sendMessage("Third arugment must be a number.");
						return true;
					}
					slot.deposit(amount);
					sendMessage(amount +  " deposited to " + args[1] + ".");
				}
				// No access
				else {
					sendMessage("You do not own this slot machine.");
				}
			}
			
			// Slot does not exist
			else {
				sendMessage("Invalid slot machine.");
			}
		}
		
		// Incorrect command format
		else {
			sendMessage("Usage:");
			sendMessage("/casino deposit <slotname> <amount>");
		}
		return true;
	}

}
