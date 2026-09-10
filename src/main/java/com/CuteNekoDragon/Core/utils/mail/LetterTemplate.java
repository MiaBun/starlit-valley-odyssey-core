package com.CuteNekoDragon.Core.utils.mail;

import lombok.Getter;
import net.minecraft.resources.ResourceLocation;

import java.util.List;

public class LetterTemplate {

    @Getter
    private final ResourceLocation id;
    @Getter
    private final ResourceLocation defaultNpcId;
    @Getter
    private final String title;
    @Getter
    private final List<String> bodyLines;

    public LetterTemplate(ResourceLocation id, ResourceLocation defaultNpcId, String title, List<String> bodyLines) {
        this.id = id;
        this.defaultNpcId = defaultNpcId;
        this.title = title;
        this.bodyLines = bodyLines;
    }

}
