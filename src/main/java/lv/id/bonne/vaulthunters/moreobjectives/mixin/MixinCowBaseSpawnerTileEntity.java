//
// Created by BONNe
// Copyright - 2023
//


package lv.id.bonne.vaulthunters.moreobjectives.mixin;


import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import iskallia.vault.block.entity.BaseSpawnerTileEntity;
import lv.id.bonne.vaulthunters.moreobjectives.events.ExtraCommonEvents;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;


/**
 * This mixin replaces spawned entity with cow if vault is cow vault.
 */
@Mixin(value = BaseSpawnerTileEntity.class, remap = false)
public abstract class MixinCowBaseSpawnerTileEntity
{
    /**
     * This method redirects entity spawn to cow if vault is cow vault.
     * @param instance Entity type instance.
     * @param serverLevel Server level.
     * @param itemStack Item stack.
     * @param player Player.
     * @param blockPos Block position.
     * @param spawnType Spawn type.
     * @param p_20598_ boolean flag.
     * @param p_20599_ boolean flag.
     * @return The cow entity or original entity instance.
     */
    @Redirect(method = "spawnEntity",
        at = @At(value = "INVOKE",
            target = "Lnet/minecraft/world/entity/EntityType;spawn(Lnet/minecraft/server/level/ServerLevel;Lnet/minecraft/world/item/ItemStack;Lnet/minecraft/world/entity/player/Player;Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/entity/MobSpawnType;ZZ)Lnet/minecraft/world/entity/Entity;"),
        remap = true)
    private static Entity changeEntity(EntityType instance,
        ServerLevel serverLevel,
        ItemStack itemStack,
        Player player,
        BlockPos blockPos,
        MobSpawnType spawnType,
        boolean p_20598_,
        boolean p_20599_)
    {
        return ExtraCommonEvents.SPAWNER_ENTITY_CREATE.
           invoke(instance, serverLevel).
           getEntityType().
           spawn(serverLevel, itemStack, player, blockPos, spawnType, p_20598_, p_20599_);
    }
}
