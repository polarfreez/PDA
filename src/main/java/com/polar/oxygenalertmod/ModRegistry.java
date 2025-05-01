package com.polar.oxygenalertmod;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;

//@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModRegistry {

    public static final String MODID = "oxygenalertmod";
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MODID);
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, MODID);
    public static final DeferredRegister<BlockEntityType<?>> TILE_ENTITIES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, MODID);
    public static final DeferredRegister<SoundEvent> SOUND_EVENTS = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, MODID);
    public static final RegistryObject<SoundEvent> LOW_OXYGEN_SOUND = SOUND_EVENTS.register("oxygen_alert_10", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(MODID, "oxygen_alert_10")));
    public static final RegistryObject<SoundEvent> VERY_LOW_OXYGEN_SOUND = SOUND_EVENTS.register("oxygen_alert_30", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(MODID, "oxygen_alert_30")));
    public static void init(IEventBus modEventBus) {
        SOUND_EVENTS.register(FMLJavaModLoadingContext.get().getModEventBus());
        MinecraftForge.EVENT_BUS.register(new OxygenAlert());
    }
//  public static final RegistryObject<Item> OVERWORLD_KEY = ITEMS.register("whatever", () -> new Item(new Item.Properties().group(ItemGroup.MISC)));
//  public static final RegistryObject<Block> STUFF_BLOCK = BLOCKS.register("stuff", () -> new Block(Block.Properties.of(Material.STONE)));
//  public static final RegistryObject<Item> STUFF_ITEM = ITEMS.register("stuff", () -> new BlockItem(STUFF_BLOCK.get(), new Item.Properties()));
//  @SubscribeEvent
//  public static void onContainerRegistry(final RegistryEvent.Register<ContainerType<?>> event) {
//    // IForgeRegistry<ContainerType<?>> r = event.getRegistry();
//  }
}
