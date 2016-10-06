package de.teamproject16.pbft.Messages;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by IngridBoldt on 06.10.16.
 */
public class VoteMessage extends Message {

    Number node;
    Number leader;
    Number value;

    public VoteMessage(Number type, Number sequence_no, Number node, Number leader, Number value) {
        super(type, sequence_no);
        this.node = node;
        this.leader = leader;
        this.value = value;
    }

    public static VoteMessage messageDecipher(JSONObject data) throws JSONException {
        return new VoteMessage((Number) data.get("type"), (Number) data.get("sequence_no"), (Number) data.get("node"),
                (Number) data.get("leader"), (Number) data.get("value"));
    }

    public JSONObject messageEncode() throws JSONException {
        JSONObject data = super.messageEncode();
        data.put("node", this.node);
        data.put("leader", this.leader);
        data.put("value", this.value);
        return data;
    }
}
