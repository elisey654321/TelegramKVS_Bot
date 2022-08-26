package data;

import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

import static data.TgBot.getListTgBots;

public class TgBotTest extends TestCase {

    @Test
    public void testGetListTgBots() {
        System.out.println("Start tested: testGetListTgBots");
        List<TgBot> botList = getListTgBots();
        Assert.assertEquals(botList.size(),1);
        System.out.println("Result: botList => " + botList);
        System.out.println("End tested: testGetListTgBots");
    }
}