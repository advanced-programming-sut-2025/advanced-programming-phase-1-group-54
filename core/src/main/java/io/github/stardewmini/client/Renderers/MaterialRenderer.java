package io.github.stardewmini.client.Renderers;

import io.github.stardewmini.common.model.items.Material;

public class MaterialRenderer {
    private final Material material;
    private String string;

    public MaterialRenderer(Material material) {
        this.material = material;
        this.string = material.toString();
    }

    public Material getMaterial() {
        return material;
    }

    public String getString() {
        return string;
    }

    public void setString(String string) {
        this.string = string;
    }
}
