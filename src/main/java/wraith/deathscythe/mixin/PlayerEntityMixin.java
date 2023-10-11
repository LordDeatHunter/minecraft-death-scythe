package wraith.deathscythe.mixin;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import wraith.deathscythe.ItemRegistry;

@Mixin(PlayerEntity.class)
public abstract class PlayerEntityMixin extends Entity {

    public PlayerEntityMixin(EntityType<?> type, World world) {
        super(type, world);
    }

    @Inject(method = "attack", at = @At("HEAD"), cancellable = true)
    public void tryAttack(Entity target, CallbackInfo ci) {
        if (this.getWorld().isClient) return;
        PlayerEntity _this = (PlayerEntity) (Object) this;
        DamageSource source = getDamageSources().playerAttack(_this);

        if (!(target instanceof LivingEntity)) {
            if (!target.damage(source, Float.MAX_VALUE)) {
                target.kill();
            }
            return;
        }

        LivingEntityAccessor livingEntityAccessor = (LivingEntityAccessor) target;
        if (_this.getMainHandStack().getItem() != ItemRegistry.get("reapers_scythe")) return;
        if (!target.damage(source, Float.MAX_VALUE)) {
            livingEntityAccessor.invokeApplyDamage(source, Float.MAX_VALUE);
            ((LivingEntity) target).setAttacker(_this);
            livingEntityAccessor.setLastDamageTaken(Float.MAX_VALUE);
            ((LivingEntity) target).onDeath(source);
        }

        ci.cancel();
    }
}
