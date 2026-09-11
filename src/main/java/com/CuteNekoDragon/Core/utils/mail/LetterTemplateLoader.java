package com.CuteNekoDragon.Core.utils.mail;

import com.CuteNekoDragon.Core.SVOCore;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.Resource;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.server.packs.resources.SimplePreparableReloadListener;
import net.minecraft.util.profiling.ProfilerFiller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LetterTemplateLoader extends SimplePreparableReloadListener<Map<ResourceLocation, LetterTemplate>> {

    private static final String FOLDER = "svo_letters";
    private static Map<ResourceLocation, LetterTemplate> TEMPLATES = Map.of();

    public static LetterTemplate getTemplate(ResourceLocation id) {
        return TEMPLATES.get(id);
    }

    public static Map<ResourceLocation, LetterTemplate> getAllTemplates() {
        return TEMPLATES;
    }

    @Override
    protected Map<ResourceLocation, LetterTemplate> prepare(ResourceManager resourceManager, ProfilerFiller profilerFiller) {

        Map<ResourceLocation, LetterTemplate> result = new HashMap<>();
        Map<ResourceLocation, Resource> found = resourceManager.listResources(FOLDER,
                path -> path.getPath().endsWith(".md"));

        for (var entry : found.entrySet()) {
            ResourceLocation path = entry.getKey();
            ResourceLocation templateFileId = toTemplateFileId(path);
            try (var reader = new BufferedReader(
                    new InputStreamReader(entry.getValue().open(), StandardCharsets.UTF_8))) {
                LetterTemplate template = parse(reader, templateFileId);
                if (template != null) {
                    if (result.containsKey(template.getId())) {
                        SVOCore.LOGGER.warn("Duplicate mailbox letter id '{}' (from {}), overwriting",
                                template.getId(), path);
                    }
                    result.put(template.getId(), template);
                }

            } catch (IOException e) {
                SVOCore.LOGGER.error("Failed to read mailbox letter {}", path, e);
            }
        }
        SVOCore.LOGGER.info("Loaded {} mailbox letter template(s)", result.size());
        return result;
    }

    @Override
    protected void apply(Map<ResourceLocation, LetterTemplate> resourceLocationLetterTemplateMap, ResourceManager resourceManager, ProfilerFiller profilerFiller) {
        TEMPLATES = Map.copyOf(resourceLocationLetterTemplateMap);
    }

    @SuppressWarnings("removal")
    private static ResourceLocation toTemplateFileId(ResourceLocation path) {
        String p = path.getPath();
        p = p.substring(FOLDER.length() + 1, p.length() - ".md".length());
        return new ResourceLocation(path.getNamespace(), p);
    }
    @SuppressWarnings("removal")
    private static LetterTemplate parse(BufferedReader reader, ResourceLocation fallbackId) throws IOException {
        List<String> lines = new ArrayList<>();
        String line;
        while ((line = reader.readLine()) != null) {
            lines.add(line);
        }

        Map<String, String> frontmatter = new HashMap<>();
        int bodyStart = 0;

        if (!lines.isEmpty() && lines.get(0).trim().equals("---")) {
            int closing = -1;
            for (int i = 1; i < lines.size(); i++) {
                if (lines.get(i).trim().equals("---")) {
                    closing = i;
                    break;
                }
            }
            if (closing == -1) {
                SVOCore.LOGGER.error("Mailbox letter {} has an opening '---' but no closing '---'", fallbackId);
                return null;
            }
            for (int i = 1; i < closing; i++) {
                String raw = lines.get(i);
                int colon = raw.indexOf(':');
                if (colon == -1) continue;
                String key = raw.substring(0, colon).trim().toLowerCase();
                String value = raw.substring(colon + 1).trim();
                frontmatter.put(key, value);
            }
            bodyStart = closing + 1;
        }

        ResourceLocation id = frontmatter.containsKey("id")
                ? new ResourceLocation(fallbackId.getNamespace(), frontmatter.get("id"))
                : fallbackId;
        ResourceLocation npcId = frontmatter.containsKey("npc")
                ? ResourceLocation.tryParse(frontmatter.get("npc"))
                : new ResourceLocation(fallbackId.getNamespace(), "unknown");
        String title = frontmatter.getOrDefault("title", id.getPath());

        // trim a single leading/trailing blank line left over from the frontmatter block
        List<String> body = new ArrayList<>(lines.subList(bodyStart, lines.size()));
        while (!body.isEmpty() && body.get(0).isBlank()) body.remove(0);
        while (!body.isEmpty() && body.get(body.size() - 1).isBlank()) body.remove(body.size() - 1);

        return new LetterTemplate(id, npcId == null ? new ResourceLocation(fallbackId.getNamespace(), "unknown") : npcId,
                title, body);
    }
}
