package minetweaker.mc1710;

import minetweaker.MineTweakerImplementationAPI;
import minetweaker.api.event.PlayerCraftedEvent;
import minetweaker.api.event.PlayerLoggedInEvent;
import minetweaker.api.event.PlayerLoggedOutEvent;
import minetweaker.api.event.PlayerSmeltedEvent;
import minetweaker.api.minecraft.MineTweakerMC;
import minetweaker.api.player.IPlayer;
import minetweaker.mc1710.recipes.MCCraftingInventory;
import minetweaker.mc1710.recipes.MCRecipeManager;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent;

/**
 *
 * @author Stan
 */
public class FMLEventHandler {
	@SubscribeEvent
	public void onPlayerLoggedIn(PlayerEvent.PlayerLoggedInEvent ev) {
		//if (ev.player instanceof EntityPlayerMP) {
		//	MineTweakerMod.NETWORK_HANDLER.playerLogin((EntityPlayerMP) ev.player);
		//}
		MineTweakerImplementationAPI.events.publishPlayerLoggedIn(new PlayerLoggedInEvent(MineTweakerMC.getIPlayer(ev.player)));
	}

	@SubscribeEvent
	public void onPlayerItemCrafted(PlayerEvent.ItemCraftedEvent ev) {
		IPlayer iPlayer = MineTweakerMC.getIPlayer(ev.player);
		if (MCRecipeManager.hasTransformerRecipes()) {
			MCRecipeManager.applyTransformations(MCCraftingInventory.get(ev.craftMatrix, ev.player), iPlayer);
		}

		if (MineTweakerImplementationAPI.events.hasPlayerCrafted()) {
			MineTweakerImplementationAPI.events.publishPlayerCrafted(new PlayerCraftedEvent(iPlayer, MineTweakerMC.getIItemStack(ev.crafting), MCCraftingInventory.get(ev.craftMatrix, ev.player)));
		}
	}

	@SubscribeEvent
	public void onPlayerItemSmelted(PlayerEvent.ItemSmeltedEvent ev) {
		if (MineTweakerImplementationAPI.events.hasPlayerSmelted()) {
			MineTweakerImplementationAPI.events.publishPlayerSmelted(new PlayerSmeltedEvent(MineTweakerMC.getIPlayer(ev.player), MineTweakerMC.getIItemStack(ev.smelting)));
		}
	}

	@SubscribeEvent
	public void onPlayerLoggedOut(PlayerEvent.PlayerLoggedOutEvent ev) {
		MineTweakerImplementationAPI.events.publishPlayerLoggedOut(new PlayerLoggedOutEvent(MineTweakerMC.getIPlayer(ev.player)));
	}

}
