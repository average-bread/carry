package cursedbread.carry.mixin;

import net.minecraft.core.block.entity.TileEntity;
import net.minecraft.core.block.motion.CarriedBlock;
import net.minecraft.core.entity.player.Player;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.util.helper.Side;
import net.minecraft.core.world.World;
import net.minecraft.server.world.ServerPlayerController;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = ServerPlayerController.class, remap = false)
public class ServerPlayerControllerMixin {

	@Inject(method = "useOrPlaceItemStackOnTile(Lnet/minecraft/core/entity/player/Player;Lnet/minecraft/core/world/World;Lnet/minecraft/core/item/ItemStack;IIILnet/minecraft/core/util/helper/Side;DD)Z", at = @At("HEAD"), cancellable = true)
	public void pickupAnything(Player player, World world, ItemStack itemstack, int blockX, int blockY, int blockZ, Side side, double xPlaced, double yPlaced, CallbackInfoReturnable<Boolean> cir) {
		if (player.canInteract()) {
			int blockId = world.getBlockId(blockX, blockY, blockZ);
			if (player.getHeldObject() == null && blockId != 0) {
				if (player.isSneaking() && itemstack == null) {
					TileEntity tileEntity = world.getTileEntity(blockX, blockY, blockZ);
					if (tileEntity == null) {
						player.setHeldObject(new CarriedBlock(player, blockId, world.getBlockMetadata(blockX, blockY, blockZ), null));
						world.setBlockWithNotify(blockX, blockY, blockZ, 0);
						cir.setReturnValue(true);
					}
				}
			}
		}
	}
}
