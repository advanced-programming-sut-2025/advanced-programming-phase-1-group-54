package io.github.stardewmini.client.Renderers.Plants;

import com.badlogic.gdx.graphics.g2d.Sprite;
import io.github.stardewmini.common.model.items.plants.Tree;

public class TreeRenderer {
    private final Tree tree;
    private Sprite sprite;

    public TreeRenderer(Tree tree) {
        this.tree = tree;
        sprite = new Sprite();
    }

    public Tree getTree() {
        return tree;
    }

    public Sprite getSprite() {
        return sprite;
    }

    public void setSprite(Sprite sprite) {
        this.sprite = sprite;
    }
}
