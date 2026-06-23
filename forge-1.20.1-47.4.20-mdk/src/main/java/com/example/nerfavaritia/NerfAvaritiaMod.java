package com.example.nerfavaritia;

import com.mojang.logging.LogUtils;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

// O valor aqui DEVE ser exatamente o mesmo modId que está no seu mods.toml e build.gradle
@Mod(NerfAvaritiaMod.MODID)
public class NerfAvaritiaMod
{
    public static final String MODID = "nerf_avaritia";
    private static final Logger LOGGER = LogUtils.getLogger();

    public NerfAvaritiaMod(FMLJavaModLoadingContext context)
    {
        IEventBus modEventBus = context.getModEventBus();

        // Registra o método de inicialização comum
        modEventBus.addListener(this::commonSetup);

        // Registra o mod no barramento de eventos principal do Forge
        MinecraftForge.EVENT_BUS.register(this);
    }

    private void commonSetup(final FMLCommonSetupEvent event)
    {
        LOGGER.info("Nerf Avaritia inicializado com sucesso!");
    }
}