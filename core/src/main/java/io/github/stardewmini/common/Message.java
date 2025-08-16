package io.github.stardewmini.common;

import java.util.HashMap;

public class Message {
	private Type type;
	private HashMap<String, Object> body;

	/*
	 * Empty constructor needed for JSON Serialization/Deserialization
	 */
	public Message() {}

	public Message(HashMap<String, Object> body, Type type) {
		this.body = body;
		this.type = type;
	}

	public Type getType() {
		return type;
	}

	public <T> T getFromBody(String fieldName) {
		return (T) body.get(fieldName);
	}

	public int getIntFromBody(String fieldName) {
		return (int) ((double) ((Double) body.get(fieldName)));
	}

    public boolean getBooleanFromBody(String fieldName) {
        return (boolean) ((Boolean) body.get(fieldName));
    }

    public float getFloatFromBody(String fieldName) {
        return (float) ((double) ((Double) body.get(fieldName)));
    }

    public long getLongFromBody(String fieldName) {
        return (long) ((double) ((Double) body.get(fieldName)));
    }

    public enum Type {
		command,
		response,
        update
	}
}
