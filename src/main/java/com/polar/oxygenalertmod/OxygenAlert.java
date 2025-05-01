package com.polar.oxygenalertmod;

import net.minecraft.client.resources.sounds.SimpleSoundInstance;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.FluidTags;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;


@Mod(ModMain.MODID)
public class OxygenAlert {

    private boolean already_played_30 = false;
    private boolean already_played_10 = false;

    

    public OxygenAlert() {       
    }

    private void onClientSetup(FMLClientSetupEvent event) {
    }

    @OnlyIn(Dist.CLIENT)
    @SubscribeEvent
    public void onClientTick(TickEvent.ClientTickEvent event) {
        if (event.phase != TickEvent.Phase.END) return;

        Minecraft mc = Minecraft.getInstance();
        LocalPlayer player = mc.player;
       
        if (player == null) return;
        
        int air = player.getAirSupply();
        ModMain.LOGGER.info("Current air supply: {}", air);

        if (!player.isEyeInFluid(FluidTags.WATER)) {
            already_played_30 = false;
            already_played_10 = false;


            ModMain.LOGGER.info("Not in water, resetting flags");
            return;
        }
        if (air <= 30 && !already_played_30){
            ModMain.LOGGER.info("Playing oxygen_alert_30 sound");
            playAlertSound(ModRegistry.OXYGEN_ALERT_30.get());
            already_played_30=true;
        }
        if (air <= 10 && !already_played_10){
            ModMain.LOGGER.info("Playing oxygen_alert_10 sound");
            playAlertSound(ModRegistry.OXYGEN_ALERT_10.get());
            already_played_10 =true;
        }
    }

    private void playAlertSound(SoundEvent soundEvent) {
        
        Minecraft mc = Minecraft.getInstance();
        LocalPlayer player = mc.player;
        if (player != null && mc.getSoundManager() != null) {
            SimpleSoundInstance instance = new SimpleSoundInstance(soundEvent.getLocation(), SoundSource.AMBIENT, 1.0F, 1.0F, player.getRandom(), player.blockPosition());
            instance.setPitch(1f);
            mc.getSoundManager().play(instance);

        }
    }
}
