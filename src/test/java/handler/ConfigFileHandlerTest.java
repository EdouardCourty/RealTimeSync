package handler;

import com.edouardcourty.realtimesync.handler.ConfigFileHandler;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ConfigFileHandlerTest {
    @Test
    public void testTimezoneFails()
    {
        String timezone = "Wrong/Timezone";

        try {
            ConfigFileHandler.validateTimezone(timezone);
            fail("Should have thrown.");
        } catch (Exception ignored) {
        }
    }

    @Test
    public void testTimezoneSucceeds()
    {
        String timezone = "Europe/Paris";

        try {
            ConfigFileHandler.validateTimezone(timezone);
        } catch (Exception exception) {
            fail("Should not have thrown.");
        }
    }
}
