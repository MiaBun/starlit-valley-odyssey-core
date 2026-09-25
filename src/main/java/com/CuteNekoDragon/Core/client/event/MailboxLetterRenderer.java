package com.CuteNekoDragon.Core.client.event;

import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RenderLevelStageEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import com.CuteNekoDragon.Core.SVOCore;
import com.CuteNekoDragon.Core.client.util.ClientMailCache;
import com.CuteNekoDragon.Core.client.util.ClientMailboxTracker;
import com.CuteNekoDragon.Core.common.data.items.SVOItems;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;

@Mod.EventBusSubscriber(modid = SVOCore.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class MailboxLetterRenderer {

    private static final double OFFSET_X = 0.0;
    private static final double OFFSET_Y = 1.17;
    private static final double OFFSET_Z = -0.36;

    private static final float YAW = 0f;

    private static final float PITCH = -30f;
    private static final float SCALE = 0.35f;
    private static final double MAX_RENDER_DIST_SQR = 48 * 48;

    private static final int MAX_LETTERS = 12;

    private static final double LETTER_SPACING = 0.07;

    private static ItemStack letterStack;

    @SubscribeEvent
    public static void onRenderLevel(RenderLevelStageEvent event) {
        if (event.getStage() != RenderLevelStageEvent.Stage.AFTER_BLOCK_ENTITIES) return;

        Minecraft mc = Minecraft.getInstance();
        ClientLevel level = mc.level;
        if (level == null || mc.player == null) return;

        int unread = (int) ClientMailCache.getLetters().stream().filter(i -> !i.isRead()).count();
        if (unread <= 0) return;
        int lettersToDraw = Math.min(unread, MAX_LETTERS);

        if (letterStack == null) letterStack = SVOItems.LETTER.asStack();

        PoseStack pose = event.getPoseStack();
        Vec3 cam = event.getCamera().getPosition();
        ItemRenderer itemRenderer = mc.getItemRenderer();
        MultiBufferSource.BufferSource buffers = mc.renderBuffers().bufferSource();

        boolean drewAnything = false;
        for (BlockPos pos : ClientMailboxTracker.getPositions()) {
            if (pos.distToCenterSqr(cam) > MAX_RENDER_DIST_SQR) continue;;
            if (!level.isLoaded(pos)) continue;
            if (!event.getFrustum().isVisible(new AABB(pos).expandTowards(0, 1, 0))) continue;

            BlockState state = level.getBlockState(pos);
            if (state.isAir()) continue;
            Direction facing = getFacing(state);

            pose.pushPose();;

            pose.translate(pos.getX() + 0.5 - cam.x, pos.getY() - cam.y, pos.getZ() + 0.5 - cam.z);

            pose.mulPose(Axis.YP.rotationDegrees(-facing.toYRot()));

            for (int i = 0; i < lettersToDraw; i++) {
                pose.pushPose();
                pose.translate(OFFSET_X, OFFSET_Y, OFFSET_Z + i * LETTER_SPACING);
                pose.mulPose(Axis.YP.rotationDegrees(YAW));
                pose.mulPose(Axis.XP.rotationDegrees(PITCH));
                pose.scale(SCALE, SCALE, SCALE);

                itemRenderer.renderStatic(letterStack, ItemDisplayContext.FIXED, LightTexture.FULL_BRIGHT,
                        OverlayTexture.NO_OVERLAY, pose, buffers, level, 0);
                pose.popPose();
            }
            pose.popPose();
            drewAnything = true;
        }

        if (drewAnything) buffers.endBatch();
    }

    private static Direction getFacing(BlockState state) {
        for (Property<?> prop : state.getProperties()) {
            if (prop instanceof DirectionProperty dirProp && prop.getName().equals("facing")) {
                Direction d = state.getValue(dirProp);
                if (d.getAxis().isHorizontal()) return d;
            }
        }
        return Direction.NORTH;
    }
}
