import de.teamproject16.pbft.Messages.InitMessage;
import de.teamproject16.pbft.Messages.Message;
import de.teamproject16.pbft.Messages.ProposeMessage;
import org.json.JSONObject;
import org.junit.Test;

import java.util.ArrayList;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by IngridBoldt on 24.10.16.
 */
public class TestMessage {
    @Test
    public void testInitMessageConvert() throws Exception{
        JSONObject testInit = new JSONObject();
        testInit.put("type", 1);
        testInit.put("sequence_no", 3);
        testInit.put("node", 1);
        testInit.put("value", 5.0);
        Object te = Message.messageConvert(testInit);
        assertThat("InitMessage", te, instanceOf(InitMessage.class));
    }

    @Test
    public void testProposeMessageConvert() throws Exception{
        JSONObject testInit = new JSONObject();
        testInit.put("type", 3);
        testInit.put("sequence_no", 3);
        testInit.put("node", 1);
        testInit.put("leader", 2);
        testInit.put("proposal", 3.5);
        ArrayList value_store = new ArrayList();
        value_store.add(2.4);
        value_store.add(3.6);
        testInit.put("value_store", value_store);
        Object te = Message.messageConvert(testInit);
        assertThat("ProposeMessage", te, instanceOf(ProposeMessage.class));
    }
}
