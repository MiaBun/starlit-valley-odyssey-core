package com.CuteNekoDragon.Core.common.event;

import com.CuteNekoDragon.Core.SVOCore;
import com.CuteNekoDragon.Core.common.data.SVOTags;
import com.CuteNekoDragon.Core.utils.skills.skill.GlobalSkillData;
import com.CuteNekoDragon.Core.utils.skills.skill.SkillType;
import com.CuteNekoDragon.Core.utils.skills.skill.SkillXPManager;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.Tags;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.player.ItemFishedEvent;
import net.minecraftforge.event.level.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import javax.swing.text.html.parser.Entity;

@Mod.EventBusSubscriber(modid = SVOCore.MOD_ID)
public class XPEventHandler {

    @SubscribeEvent
    public static void onBlockBreak(BlockEvent.BreakEvent event) {
        if (!(event.getPlayer() instanceof ServerPlayer sp)) return;
        BlockState state = event.getState();

        if(state.getBlock() instanceof CropBlock crop && crop.isMaxAge(state)) {
            SkillXPManager.addXP(sp, SkillType.FARMING, GlobalSkillData.XP_FARMING_HARVEST);
            return;
        }

        if (state.is(BlockTags.LOGS)) {
            SkillXPManager.addXP(sp, SkillType.FORAGING, GlobalSkillData.XP_FORAGING_LOG);
            return;
        }

        if (state.is(Tags.Blocks.ORES)) {
            SkillXPManager.addXP(sp, SkillType.MINING, GlobalSkillData.XP_MINING_ORE);
        }
    }

    @SubscribeEvent
    public static void onFish(ItemFishedEvent event) {
        if(event.getEntity() instanceof ServerPlayer sp) {
            SkillXPManager.addXP(sp, SkillType.FISHING, GlobalSkillData.XP_FISHING_CATCH);
        }
    }

    @SubscribeEvent
    public static void onKill(LivingDeathEvent event) {
        if(!(event.getSource().getEntity() instanceof  ServerPlayer sp)) return;
        LivingEntity killed = event.getEntity();

        if(killed.getType().is(SVOTags.Entities.NORDIC)) {
            SkillXPManager.addXP(sp, SkillType.ELDER_EDA, GlobalSkillData.XP_ELDER_EDA_KILL);
        } else {
            SkillXPManager.addXP(sp, SkillType.COMBAT, GlobalSkillData.XP_COMBAT_KILL);
        }
    }
}
