package de.teamproject16.pbft.Messages;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by IngridBoldt on 29.09.16.
 */
public class InitMessage extends Message {
    Number value;
    Number node;

    /**
     *InitMessage
     * @param type messagetype
     * @param sequence_no of tries
     * @param node the id of the sender
     * @param value the value of the sensor from the node
     */
    public InitMessage(int type, Number sequence_no, Number node, Number value) {
        super(type, sequence_no);
        this.node = node;
        this.value = value;
    }

    /**
     * Create a initmessage object from the data out JSONObject.
     * @param data JSONObject
     * @return a new InitMessage object with the specific data.
     * @throws JSONException
     */
    public static InitMessage messageDecipher(JSONObject data) throws JSONException {
        return new InitMessage(data.getInt("type"), (Number) data.get("sequence_no"), (Number) data.get("node"),
                (Number) data.get("value"));
    }

    /**
     * Create JSONObject for the network.
     * @return data JSONObject
     * @throws JSONException
     */
    public JSONObject messageEncode() throws JSONException {
        JSONObject data = super.messageEncode();
        data.put("node", this.node);
        data.put("value", this.value);
        return data;
    }
}
