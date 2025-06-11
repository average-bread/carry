package cursedbread.carry.mixin;

import net.minecraft.core.block.BlockLogic;
import net.minecraft.core.block.entity.TileEntity;
import net.minecraft.core.block.motion.CarriedBlock;
import net.minecraft.core.entity.player.Player;
import net.minecraft.core.util.helper.Side;
import net.minecraft.core.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import static org.apache.log4j.builders.appender.SocketAppenderBuilder.LOGGER;

@Mixin(value = BlockLogic.class, remap = false)

public class CarryMixin {
	@Inject(method = "onBlockRightClicked(Lnet/minecraft/core/world/World;IIILnet/minecraft/core/entity/player/Player;Lnet/minecraft/core/util/helper/Side;DD)Z", at = @At("HEAD"), cancellable = true)
	public void onBlockRightClicked(World world, int x, int y, int z, Player player, Side side, double xHit, double yHit, CallbackInfoReturnable<Boolean> cir) {
		if (player.getHeldItem() == null && player.isSneaking()){
			player.setHeldObject(new CarriedBlock(player, world.getBlockId(x, y, z), world.getBlockMetadata(z, y, z), null));
			world.setBlockWithNotify(x, y, z, 0);
		}
	}
}
