package io.github.stardewmini.model.relationships;

import io.github.stardewmini.model.DateTime;
import io.github.stardewmini.model.lives.Player;

public record Talk(Player sayer, String message, DateTime timestamp) {
}
