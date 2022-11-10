/*
 * This file is part of ComputerCraft - http://www.computercraft.info
 * Copyright Daniel Ratcliffe, 2011-2022. Do not distribute without permission.
 * Send enquiries to dratcliffe@gmail.com
 */
package dan200.computercraft.gametest.core;

import dan200.computercraft.api.ComputerCraftAPI;
import dan200.computercraft.export.Exporter;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.minecraft.resources.ResourceLocation;

public class TestMod implements ModInitializer, ClientModInitializer {
    @Override
    public void onInitialize() {
        TestHooks.init();

        var phase = new ResourceLocation(ComputerCraftAPI.MOD_ID, "test_mod");
        ServerLifecycleEvents.SERVER_STARTED.addPhaseOrdering(Event.DEFAULT_PHASE, phase);
        ServerLifecycleEvents.SERVER_STARTED.register(phase, TestHooks::onServerStarted);

        CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> CCTestCommand.register(dispatcher));
    }

    @Override
    public void onInitializeClient() {
        ClientCommandRegistrationCallback.EVENT.register((dispatcher, registryAccess) -> Exporter.register(dispatcher));
    }
}
