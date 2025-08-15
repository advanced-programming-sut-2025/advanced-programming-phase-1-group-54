package io.github.stardewmini.common.model.relationships;

import io.github.stardewmini.common.model.DateTime;
import io.github.stardewmini.common.model.lives.Player;

public record Talk(Player sayer, String message, DateTime timestamp) {
}
