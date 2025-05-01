package com.polar.oxygenalertmod;

import net.minecraft.tags.FluidTags;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.IEventBus;
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
        
        if (!player.isEyeInFluid(FluidTags.WATER)) {
            already_played_30 = false;
            already_played_10 = false;
            return;
        }

        

    }


}
