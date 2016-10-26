package de.teamproject16.pbft.Messages;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import static de.teamproject16.pbft.Messages.Types.PROPOSE;


/**
 * Created by IngridBoldt on 29.09.16.
 */
public class ProposeMessage extends Message {

    Number node;
    Number leader;
    Number proposal;
    List value_store;

    /**
     * Propose message
     * @param sequence_no of tries
     * @param node the id of the sender
     * @param leader
     * @param proposal
     * @param value_store values from all nodes in the network
     */
    public ProposeMessage(Number sequence_no, Number node, Number leader, Number proposal, List value_store) {
        super(PROPOSE, sequence_no);
        this.node = node;
        this.leader = leader;
        this.proposal = proposal;
        this.value_store = value_store;
    }

    /**
     * Create a propose message object from the data out JSONObject.
     * @param data JSONObject
     * @return a new propose message object with the specific data.
     * @throws JSONException
     */
    public static ProposeMessage messageDecipher(JSONObject data) throws JSONException {
        List<Integer> value_store = new Gson().fromJson((String) data.get("value_store"), new TypeToken<List<String>>() {}.getType());
        return new ProposeMessage((Number) data.get("sequence_no"), (Number) data.get("node"),
                (Number) data.get("leader"), (Number) data.get("proposal"), value_store);
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
        data.put("proposal", this.proposal);
        data.put("value_store", this.value_store);
        return data;
    }
}

