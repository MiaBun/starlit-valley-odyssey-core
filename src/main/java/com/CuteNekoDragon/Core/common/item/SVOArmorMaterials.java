package com.CuteNekoDragon.Core.common.item;

import com.CuteNekoDragon.Core.common.data.items.SVOItems;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;

import com.CuteNekoDragon.Core.SVOCore;

import java.util.function.Supplier;

public enum SVOArmorMaterials implements ArmorMaterial {

    COPPER("copper", 15,
            new int[] { 2, 5, 6, 2 },   // boots, leggings, chestplate, helmet
            9,                        // enchantability
            SoundEvents.ARMOR_EQUIP_IRON,
            1.0f,                     // toughness
            0.0f,                     // knockback resistance
            () -> Ingredient.of(Items.COPPER_INGOT)
            ),
    IRIDIUM("iridium", 37,
            new int[] { 3, 6, 8, 3 },   // boots, leggings, chestplate, helmet
            15,                        // enchantability
            SoundEvents.ARMOR_EQUIP_NETHERITE,
            0.0f,                     // toughness
            0.0f,                     // knockback resistance
            () -> Ingredient.of(SVOItems.IRIDIUM_INGOT)
            );

    private static final int[] HEALTH_PER_SLOT = { 13, 15, 16, 11 };
    private final String name;
    private final int durabilityMultiplier;
    private final int[] slotProtections;
    private final int enchantmentValue;
    private final SoundEvent sound;
    private final float toughness;
    private final float knockbackResistance;
    private final Supplier<Ingredient> repairIngredient;

    SVOArmorMaterials(String name, int durabilityMultiplier, int[] slotProtections, int enchantmentValue,
                      SoundEvent sound, float toughness, float knockbackResistance,
                      Supplier<Ingredient> repairIngredient) {
        this.name = name;
        this.durabilityMultiplier = durabilityMultiplier;
        this.slotProtections = slotProtections;
        this.enchantmentValue = enchantmentValue;
        this.sound = sound;
        this.toughness = toughness;
        this.knockbackResistance = knockbackResistance;
        this.repairIngredient = repairIngredient;
    }

    @Override
    public int getDurabilityForType(ArmorItem.Type type) {
        return HEALTH_PER_SLOT[type.ordinal()] * durabilityMultiplier;
    }

    @Override
    public int getDefenseForType(ArmorItem.Type type) {
        return slotProtections[type.ordinal()];
    }

    @Override
    public int getEnchantmentValue() {
        return enchantmentValue;
    }

    @Override
    public SoundEvent getEquipSound() {
        return sound;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return repairIngredient.get();
    }

    @Override
    public String getName() {
        return SVOCore.MOD_ID + ":" + name;
    }

    @Override
    public float getToughness() {
        return toughness;
    }

    @Override
    public float getKnockbackResistance() {
        return knockbackResistance;
    }
}
