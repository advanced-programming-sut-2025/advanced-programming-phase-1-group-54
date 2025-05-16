package model.relationships;

import model.DateTime;
import model.lives.Player;

public record Talk(Player sayer, String message, DateTime timestamp) {
}
