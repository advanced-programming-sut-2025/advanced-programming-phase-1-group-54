package model.relationships;

import model.DateTime;
import model.alive.Player;

public record Talk(Player sayer, String message, DateTime timestamp) {
}
