import com.google.gson.Gson;
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
        System.out.println(te.toString());
    }

    @Test
    public void testProposeMessageConvert() throws Exception{
        JSONObject testPropo = new JSONObject();
        testPropo.put("type", 3);
        testPropo.put("sequence_no", 3);
        testPropo.put("node", 1);
        testPropo.put("leader", 2);
        testPropo.put("proposal", 3.5);
        ArrayList value_store = new ArrayList();
        value_store.add(2.4);
        value_store.add(3.6);
        String gson = new Gson().toJson(value_store);
        testPropo.put("value_store", gson);
        Object te = Message.messageConvert(testPropo);
        assertThat("ProposeMessage", te, instanceOf(ProposeMessage.class));
        System.out.println(te.toString());
    }

    @Test
    public void testPrevoteMessage() throws Exception{

    }
}
