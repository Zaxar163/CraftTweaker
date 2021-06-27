/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package minetweaker.mc1710;

import minetweaker.IPlatformFunctions;
import minetweaker.api.chat.IChatMessage;
import minetweaker.api.item.IItemDefinition;

import minetweaker.mc1710.item.MCItemDefinition;
import minetweaker.mc1710.chat.MCChatMessage;
import net.minecraft.item.Item;

/**
 *
 * @author Stan
 */
public class MCPlatformFunctions implements IPlatformFunctions {
	public static final MCPlatformFunctions INSTANCE = new MCPlatformFunctions();

	private MCPlatformFunctions() {
	}

	@Override
	public IChatMessage getMessage(String message) {
		return new MCChatMessage(message);
	}

	@Override
	public void distributeScripts(byte[] data) {
		throw new IllegalArgumentException("Runtime script reloading is not supported!!!");
	}

	@Override
	public IItemDefinition getItemDefinition(int id) {
		Item item = Item.getItemById(id);
		if (item == null)
			return null;
		String sid = Item.itemRegistry.getNameForObject(item);
		return new MCItemDefinition(sid, item);
	}
}
