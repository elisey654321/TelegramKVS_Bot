package bots_utils;

import junit.framework.TestCase;
import org.junit.Test;

public class KVS_Bot_ButtonUtilsTest extends TestCase {

    @Test
    public void testGetLastModifiedInCatalog() {
        System.out.println(KVS_Bot_ButtonUtils.getLastModifiedInCatalog("\\\\TECH-1C-04\\Test"));
    }
}