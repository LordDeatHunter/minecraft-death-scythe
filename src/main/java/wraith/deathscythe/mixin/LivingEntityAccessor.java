package wraith.deathscythe.mixin;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(LivingEntity.class)
public interface LivingEntityAccessor {

    @Accessor("lastDamageTaken")
    void setLastDamageTaken(float amount);

    @Invoker
    void invokeApplyDamage(DamageSource source, float amount);

    @Invoker("setAttacker")
    void invokeSetAttacker(@Nullable LivingEntity attacker);

}
