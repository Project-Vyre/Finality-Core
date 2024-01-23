package com.oekt.finality.mixin;

import net.minecraft.client.Options;
import net.minecraft.nbt.CompoundTag;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(Options.class)
public class OptionsMixin {
    
    @Unique private static final String ESSENTIAL_ZOOM_KEY = "key_keybind.name.ZOOM";
    @Unique private static final String ZUME_ZOOM_KEY = "key_key.zume.zoom";
    @Unique private static final String UNBOUND_VALUE = "key.keyboard.unknown";
    
    @Redirect(method = "load(Z)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/Options;dataFix(Lnet/minecraft/nbt/CompoundTag;)Lnet/minecraft/nbt/CompoundTag;"))
    public CompoundTag load$dataFix(Options instance, CompoundTag compoundTag) {
        if (compoundTag.contains(ESSENTIAL_ZOOM_KEY) && !compoundTag.contains(ZUME_ZOOM_KEY)) {
            compoundTag.putString(ZUME_ZOOM_KEY, compoundTag.getString(ESSENTIAL_ZOOM_KEY));
            compoundTag.putString(ESSENTIAL_ZOOM_KEY, UNBOUND_VALUE);
        }
        
        return compoundTag;
    }
    
}
