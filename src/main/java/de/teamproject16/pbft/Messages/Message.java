package de.teamproject16.pbft.Messages;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by IngridBoldt on 29.09.16.
 */
public class Message {

    public enum messageType {
        INIT, LEADER_CHANGE, PROPOSE, PREVOTE, VOTE
    }

    static Number type;
    Number sequence_no;

    public Message(Number type, Number sequence_no){
        this.type = type;
        this.sequence_no = sequence_no;
    }


    public String toString(){
        try {
            return this.messageEncode().toString();
        } catch (JSONException e) {
            e.printStackTrace();
            return "Error toString/de.teamproject16.pbft.Messages.Message";
        }
    }

    public JSONObject messageEncode () throws JSONException {
        JSONObject data = new JSONObject();
        data.put("type", this.type);
        data.put("sequence_no", this.sequence_no);
        return data;
    }

    public static Message messageConvert(JSONObject data) throws JSONException {
        try {
            type = Integer.parseInt(data.getString("type"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        if ((Number) messageType.valueOf("INIT").ordinal() == type ){
            return InitMessage.messageDecipher(data);
        }
        if ((Number) messageType.valueOf("LEADER_CHANGE").ordinal() == type){
            return LeaderChangeMessage.messageDecipher(data);
        }
        if ((Number) messageType.valueOf("PROPOSE").ordinal() == type){
            return ProposeMessage.messageDecipher(data);
        }
        if ((Number) messageType.valueOf("PREVOTE").ordinal() == type){
            return PrevoteMessage.messageDecipher(data);
        }
        if ((Number) messageType.valueOf("VOTE").ordinal() == type){
            return VoteMessage.messageDecipher(data);
        }
        return new Message((Number) data.get("type"), (Number) data.get("sequence_no"));
    }

}
