package com.canoestudio.canoemixins.mixin.forestry;

import com.canoestudio.canoemixins.CanoeMixins;
import com.canoestudio.canoemixins.config.CanoeModConfig;
import forestry.api.apiculture.IBee;
import forestry.api.apiculture.IBeeHousing;
import forestry.apiculture.BeekeepingLogic;
import forestry.apiculture.ModuleApiculture;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = BeekeepingLogic.class,remap = false)
public abstract class MixinBeeKeepingLogic {
    @Shadow private ItemStack queenStack;

    @Shadow @Final private IBeeHousing housing;

    @Inject(method = "queenWorkTick(Lforestry/api/apiculture/IBee;Lnet/minecraft/item/ItemStack;)V",at= @At(value = "HEAD"),cancellable = true)
    public void checkFaultyQueen(IBee queen, ItemStack queenStack, CallbackInfo ci){
        if(!CanoeModConfig.forestry.faultyQueenFix)
            return;
        if(queen.getMate()==null){
            NBTTagCompound tagCompound = this.queenStack.getTagCompound();
            this.queenStack=new ItemStack(ModuleApiculture.getItems().beePrincessGE);
            this.queenStack.setTagCompound(tagCompound);
            this.housing.getBeeInventory().setQueen(this.queenStack);
            CanoeMixins.getLogger().warn("Replaced a faulty Bee Queen at {}. This would cause a crash normally. {} {}", this.housing.getCoordinates(),queenStack,tagCompound);
            ci.cancel();
        }
    }
}
