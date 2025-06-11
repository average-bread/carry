package cursedbread.carry;

import com.mojang.nbt.tags.CompoundTag;
import net.minecraft.core.entity.Entity;
import net.minecraft.core.util.helper.Side;
import net.minecraft.core.world.ICarriable;
import net.minecraft.core.world.World;

public class CarryBlock implements ICarriable {
	@Override
	public void heldTick(World world, Entity entity) {

	}

	@Override
	public boolean tryPlace(World world, Entity entity, int i, int j, int k, Side side, double d, double e) {
		return false;
	}

	@Override
	public void drop(World world, Entity entity) {

	}

	@Override
	public boolean canBeCarried(World world, Entity entity) {
		return false;
	}

	@Override
	public ICarriable pickup(World world, Entity entity) {
		return null;
	}

	@Override
	public void writeToNBT(CompoundTag compoundTag) {

	}

	@Override
	public void readFromNBT(CompoundTag compoundTag) {

	}
}
