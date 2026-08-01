package com.CuteNekoDragon.Core.common.datagen.advancements;

import com.CuteNekoDragon.Core.common.datagen.advancements.tabs.Chapter1;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.common.data.ForgeAdvancementProvider;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class SVOAdvancementsProvider extends ForgeAdvancementProvider {
    public SVOAdvancementsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries, ExistingFileHelper existingFileHelper) {
        super(output, registries, existingFileHelper, List.of(new Chapter1()));
    }
}
