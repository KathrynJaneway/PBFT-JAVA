package de.teamproject16.pbft.Messages;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by IngridBoldt on 29.09.16.
 */
public class PrevoteMessage extends Message {

    Number node;
    Number leader;
    Number value;

    /**
     * Prevote message
     * @param type messagetype
     * @param sequence_no of tries
     * @param node the id of the sender
     * @param leader
     * @param value from the node
     */
    public PrevoteMessage(int type, Number sequence_no, Number node, Number leader, Number value) {
        super(type, sequence_no);
        this.node = node;
        this.leader = leader;
        this.value = value;
    }

    /**
     * Create a prevote message object from the data out JSONObject.
     * @param data JSONObject
     * @return a new prevote message object with the specific data.
     * @throws JSONException
     */
    public static PrevoteMessage messageDecipher(JSONObject data) throws JSONException {
        return new PrevoteMessage(data.getInt("type"), (Number) data.get("sequence_no"), (Number) data.get("node"),
                (Number) data.get("leader"), (Number) data.get("value"));
    }

    /**
     * Create JSONObject for the network.
     * @return data JSONObject
     * @throws JSONException
     */
    public JSONObject messageEncode() throws JSONException {
        JSONObject data = super.messageEncode();
        data.put("node", this.node);
        data.put("leader", this.leader);
        data.put("value", this.value);
        return data;
    }
}
