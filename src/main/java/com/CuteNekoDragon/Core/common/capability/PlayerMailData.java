package com.CuteNekoDragon.Core.common.capability;

import com.CuteNekoDragon.Core.utils.mail.Letter;
import lombok.Getter;
import lombok.Setter;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class PlayerMailData {

    @Getter
    private final List<Letter> letters = new ArrayList<>();
    @Getter
    @Setter
    private boolean toastEnabled = true;

    public void addLetter(Letter letter) {
        letters.add(letter);
    }

    public Optional<Letter> findLetter(UUID letterId) {
        return letters.stream().filter(l -> l.getLetterId().equals(letterId)).findFirst();
    }

    public void copyFrom(PlayerMailData other) {
        this.letters.clear();
        this.letters.addAll(other.letters);
        this.toastEnabled = other.toastEnabled;
    }

    public CompoundTag serializeNBT() {
        CompoundTag tag = new CompoundTag();
        tag.putBoolean("ToastEnabled", toastEnabled);

        ListTag letterList = new ListTag();
        for (Letter letter : letters) {
            letterList.add(letter.serializeNBT());
        }
        tag.put("Letters", letterList);
        return tag;
    }
    public void deserializeNBT(CompoundTag tag) {
        letters.clear();
        toastEnabled = !tag.contains("ToastEnabled") || tag.getBoolean("ToastEnabled");

        ListTag letterList = tag.getList("Letters", Tag.TAG_COMPOUND);
        for (int i = 0; i < letterList.size(); i++) {
            letters.add(Letter.deserializeNBT(letterList.getCompound(i)));
        }
    }

}
