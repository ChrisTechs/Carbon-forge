package me.jellysquid.mods.sodium.mixin.core;

import net.minecraft.util.ChatComponentStyle;
import net.minecraft.util.ChatComponentTranslation;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(ChatComponentTranslation.class)
public abstract class MixinChatComponentTranslation extends ChatComponentStyle {


}
