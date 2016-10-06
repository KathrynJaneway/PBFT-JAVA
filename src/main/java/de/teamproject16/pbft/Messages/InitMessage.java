package de.teamproject16.pbft.Messages;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by IngridBoldt on 29.09.16.
 */
public class InitMessage extends Message {
    Number value;
    Number node;

    public InitMessage(Number type, Number sequence_no, Number node, Number value) {
        super(type, sequence_no);
        this.node = node;
        this.value = value;
    }

    public static InitMessage messageDecipher(JSONObject data) throws JSONException {
        return new InitMessage((Number) data.get("type"), (Number) data.get("sequence_no"), (Number) data.get("node"), (Number) data.get("value"));
    }

    public JSONObject messageEncode() throws JSONException {
        JSONObject data = super.messageEncode();
        data.put("node", this.node);
        data.put("value", this.value);
        return data;
    }
}
