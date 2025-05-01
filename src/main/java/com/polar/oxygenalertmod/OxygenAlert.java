package com.polar.oxygenalertmod;

import net.minecraft.tags.FluidTags;
import net.minecraft.client.Minecraft;
import net.minecraft.client.sounds.SoundManager;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.resources.sounds.SimpleSoundInstance;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

@Mod(ModMain.MODID)
public class OxygenAlert {

    private static final DeferredRegister<SoundEvent> SOUND_EVENTS = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, ModMain.MODID);
    public static final RegistryObject<SoundEvent> LOW_OXYGEN_SOUND = SOUND_EVENTS.register("oxygen_alert_30", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(ModMain.MODID, "oxygen_alert_30")));
    public static final RegistryObject<SoundEvent> VERY_LOW_OXYGEN_SOUND = SOUND_EVENTS.register("oxygen_5", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(ModMain.MODID, "oxygen_5")));

    private boolean played30 = false;
    private boolean played5 = false;

    public OxygenAlert() {
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::onClientSetup);
        SOUND_EVENTS.register(FMLJavaModLoadingContext.get().getModEventBus());
        
    }

    private void onClientSetup(FMLClientSetupEvent event) {
    }

    @OnlyIn(Dist.CLIENT)
    @SubscribeEvent
    public void onClientTick(TickEvent.ClientTickEvent event) {
        if (event.phase != TickEvent.Phase.END) return;

        Minecraft mc = Minecraft.getInstance();
        LocalPlayer player = mc.player;
        SoundManager soundManager = mc.getSoundManager();

        if (player == null) return;
      
        int air = player.getAirSupply();

        if (player.isEyeInFluid(FluidTags.WATER)) {
            if (air < 1200) return;
            
            if (air <= 60 && !played30) { // 30 seconds of oxygen (60 ticks)
                soundManager.play(SimpleSoundInstance.forUI(LOW_OXYGEN_SOUND.get(), 1.0F, 1.0F));
                played30 = true;
            }

            if (air <= 10 && !played5) { // 5 seconds of oxygen (10 ticks)
                soundManager.play(SimpleSoundInstance.forUI(VERY_LOW_OXYGEN_SOUND.get(), 1.0F, 1.0F));
                played5 = true;
            }

            if (air > 60) {
                played30 = false;
            }

            if (air > 10) {
                played5 = false;
            }
        }
    }

}
