package com.CuteNekoDragon.Core.common.svogt.machine.singleblock.artisan;

import com.google.common.collect.Tables;
import com.gregtechceu.gtceu.api.GTValues;
import com.gregtechceu.gtceu.api.capability.recipe.IO;
import com.gregtechceu.gtceu.api.capability.recipe.IRecipeHandler;
import com.gregtechceu.gtceu.api.capability.recipe.ItemRecipeCapability;
import com.gregtechceu.gtceu.api.capability.recipe.RecipeCapability;
import com.gregtechceu.gtceu.api.gui.GuiTextures;
import com.gregtechceu.gtceu.api.gui.UITemplate;
import com.gregtechceu.gtceu.api.machine.IMachineBlockEntity;
import com.gregtechceu.gtceu.api.machine.MetaMachine;
import com.gregtechceu.gtceu.api.machine.feature.ICleanroomProvider;
import com.gregtechceu.gtceu.api.machine.feature.IRecipeLogicMachine;
import com.gregtechceu.gtceu.api.machine.feature.IUIMachine;
import com.gregtechceu.gtceu.api.machine.trait.*;
import com.gregtechceu.gtceu.api.recipe.GTRecipeType;
import com.lowdragmc.lowdraglib.gui.modular.ModularUI;
import com.lowdragmc.lowdraglib.syncdata.ISubscription;
import com.lowdragmc.lowdraglib.syncdata.annotation.DescSynced;
import com.lowdragmc.lowdraglib.syncdata.annotation.Persisted;
import com.lowdragmc.lowdraglib.syncdata.field.ManagedFieldHolder;
import com.lowdragmc.lowdraglib.utils.Position;
import lombok.Getter;
import lombok.Setter;

import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.Nullable;

import java.util.*;


public class CharkoalKiln extends MetaMachine implements IRecipeLogicMachine, IUIMachine {

    protected static final ManagedFieldHolder MANAGED_FIELD_HOLDER =
            new ManagedFieldHolder(CharkoalKiln.class, MetaMachine.MANAGED_FIELD_HOLDER);

    @Getter
    @Persisted
    @DescSynced
    public final RecipeLogic recipeLogic;

    @Getter
    public final GTRecipeType[] recipeTypes;

    @Getter
    @Setter
    @Persisted
    public int activeRecipeType = 0;

    @Nullable
    @Getter
    @Setter
    private ICleanroomProvider cleanroom;

    @Persisted
    public final NotifiableItemStackHandler importItems, exportItems;

    @Getter
    protected final Map<IO, List<RecipeHandlerList>> capabilitiesProxy = new EnumMap<>(IO.class);
    @Getter
    protected final Map<IO, Map<RecipeCapability<?>, List<IRecipeHandler<?>>>> capabilitiesFlat = new EnumMap<>(IO.class);

    private final List<ISubscription> traitSubscriptions = new ArrayList<>();

    public CharkoalKiln(IMachineBlockEntity holder) {
        super(holder);
        this.recipeTypes = getDefinition().getRecipeTypes();
        this.recipeLogic = new RecipeLogic(this);
        this.importItems = new NotifiableItemStackHandler(this, 1, IO.IN);
        this.exportItems = new NotifiableItemStackHandler(this, 1, IO.OUT);
    }

    @Override
    public ManagedFieldHolder getFieldHolder() {
        return MANAGED_FIELD_HOLDER;
    }

    @Override
    public void onLoad() {
        super.onLoad();
        Map<IO, List<IRecipeHandler<?>>> ioTraits = new EnumMap<>(IO.class);
        for (MachineTrait trait : getTraits()) {
            if (trait instanceof IRecipeHandlerTrait<?> handlerTrait) {
                ioTraits.computeIfAbsent(handlerTrait.getHandlerIO(), i -> new ArrayList<>()).add(handlerTrait);
            }
        }
        for (var entry : ioTraits.entrySet()) {
            var handlerList = RecipeHandlerList.of(entry.getKey(), entry.getValue());
            this.addHandlerList(handlerList);
            traitSubscriptions.add(handlerList.subscribe(recipeLogic::updateTickSubscription));
        }
    }

    @Override
    public void onUnload() {
        super.onUnload();
        traitSubscriptions.forEach(ISubscription::unsubscribe);
        traitSubscriptions.clear();
        capabilitiesProxy.clear();
        capabilitiesFlat.clear();
        recipeLogic.inValid();
    }

    @Override
    public boolean keepSubscribing() {
        return false;
    }

    @Override
    public GTRecipeType getRecipeType() {
        return recipeTypes[activeRecipeType];
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void clientTick() {
        super.clientTick();
        if(getLevel() == null || !getLevel().isClientSide || !isActive()) return;

        if (GTValues.RNG.nextFloat() < 0.2F) {
            var pos = getPos();
            double dx = 0.3 - 0.5;
            double dz = 0.3 - 0.5;

            double forwardAmt = -dz;
            double rightAmt = dx;

            Direction facing = getBlockState().getValue(BlockStateProperties.HORIZONTAL_FACING);
            Direction right = facing.getClockWise();

            double x = pos.getX() + 0.5 + facing.getStepX() * forwardAmt + right.getStepX() * rightAmt;
            double y = pos.getY() + 1.3;
            double z = pos.getZ() + 0.5 + facing.getStepZ() * forwardAmt + right.getStepZ() * rightAmt;

            double vx = (GTValues.RNG.nextFloat() - 0.5) * 0.02;
            double vy = 0.05 + GTValues.RNG.nextFloat() * 0.03;
            double vz = (GTValues.RNG.nextFloat() - 0.5) * 0.02;

            getLevel().addParticle(ParticleTypes.SMOKE, x, y, z, vx, vy, vz);
        }
    }

    @Override
    public ModularUI createUI(Player entityPlayer) {
        var storages = Tables.newCustomTable(new EnumMap<>(IO.class), LinkedHashMap<RecipeCapability<?>, Object>::new);
        storages.put(IO.IN, ItemRecipeCapability.CAP, importItems.storage);
        storages.put(IO.OUT, ItemRecipeCapability.CAP, exportItems.storage);

        var group = getRecipeType().getRecipeUI().createUITemplate(recipeLogic::getProgressPercent, storages, new CompoundTag(), Collections.emptyList());

        Position pos = new Position((Math.max(group.getSize().width + 4 + 8, 176) - 4 - group.getSize().width) / 2 + 4, 32);
        group.setSelfPosition(pos);

        return new ModularUI(176, 166, this, entityPlayer)
                .background(GuiTextures.BACKGROUND)
                .widget(group)
                .widget(UITemplate.bindPlayerInventory(entityPlayer.getInventory(), GuiTextures.SLOT, 7, 84, true));
    }

}
