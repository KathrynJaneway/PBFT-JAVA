import com.google.gson.Gson;
import de.teamproject16.pbft.CancelableLinkedBlockingQueue;
import de.teamproject16.pbft.Messages.*;
import org.json.JSONObject;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static de.teamproject16.pbft.Messages.Types.*;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by IngridBoldt on 24.10.16.
 */
public class TestMessage {
    @Test
    public void testInitMessageConvert() throws Exception{
        JSONObject testInit = new JSONObject();
        testInit.put("type", INIT);
        testInit.put("sequence_no", 3);
        testInit.put("node", 1);
        testInit.put("value", 5.3);
        Object te = Message.messageConvert(testInit);
        assertThat("InitMessage", te, instanceOf(InitMessage.class));
        System.out.println(te.toString());
    }

    @Test
    public void testProposeMessageConvert() throws Exception{
        JSONObject testPropo = new JSONObject();
        testPropo.put("type", PROPOSE);
        testPropo.put("sequence_no", 3);
        testPropo.put("node", 1);
        testPropo.put("leader", 2);
        testPropo.put("proposal", 3.5);
        List<InitMessage> value_store = new ArrayList<InitMessage>();
        value_store.add(new InitMessage(((int) System.currentTimeMillis()/ 1000), 2, (float) 5.3));
        String gson = new Gson().toJson(value_store);
        testPropo.put("value_store", gson);
        Object te = Message.messageConvert(testPropo);
        assertThat("ProposeMessage", te, instanceOf(ProposeMessage.class));
        System.out.println(te.toString());
    }

    @Test
    public void testPrevoteMessage() throws Exception{
        JSONObject testPrev = new JSONObject();
        testPrev.put("type", PREVOTE);
        testPrev.put("sequence_no", 3);
        testPrev.put("node", 1);
        testPrev.put("leader", 2);
        testPrev.put("value", 5.3);
        Object te = Message.messageConvert(testPrev);
        assertThat("PrevoteMessage", te, instanceOf(PrevoteMessage.class));
        System.out.println(te.toString());
    }

    @Test
    public void testVoteMessage() throws Exception{
        JSONObject testVote = new JSONObject();
        testVote.put("type", VOTE);
        testVote.put("sequence_no", 3);
        testVote.put("node", 1);
        testVote.put("leader", 2);
        testVote.put("value", 5.3);
        Object te = Message.messageConvert(testVote);
        assertThat("VoteMessage", te, instanceOf(VoteMessage.class));
        System.out.println(te.toString());
    }
}
