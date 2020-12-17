import com.edouardcourty.realtimesync.repository.TimeRepository;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TimeRepositoryTest {
    @Test
    void testNoonConversion()
    {
        int hours = 12;
        int minutes = 0;
        int supposedComputedTime = 6000;

        long calculatedTime = TimeRepository.convertTimeToMinecraftTicks(hours, minutes);

        assertEquals(calculatedTime, supposedComputedTime, String.format("Error, computed time is too high: %s. %s expected.", calculatedTime, supposedComputedTime));
    }

    @Test
    void testNoonAndAHalfConversion()
    {
        int hours = 12;
        int minutes = 30;
        int supposedComputedTimeToBeAbove = 6000;
        int supposedComputedTimeToBeUnder = 8000;

        long calculatedTime = TimeRepository.convertTimeToMinecraftTicks(hours, minutes);

        assertTrue(
                calculatedTime > supposedComputedTimeToBeAbove,
                String.format(
                        "Error, computed time is too low: %s. It was supposed to be higher than %s",
                        calculatedTime,
                        supposedComputedTimeToBeAbove
                )
        );
        assertTrue(
                calculatedTime < supposedComputedTimeToBeUnder,
                String.format(
                        "Error, computed time is too high: %s. It was supposed to be lower than %s",
                        calculatedTime,
                        supposedComputedTimeToBeAbove
                )
        );
    }

    @Test
    void testMidnightAndAHalfConversion()
    {
        int hours = 0;
        int minutes = 30;
        int supposedComputedTimeToBeAbove = 18000;
        int supposedComputedTimeToBeUnder = 20000;

        long calculatedTime = TimeRepository.convertTimeToMinecraftTicks(hours, minutes);

        assertTrue(
                calculatedTime > supposedComputedTimeToBeAbove,
                String.format(
                        "Error, computed time is too low: %s. It was supposed to be higher than %s",
                        calculatedTime,
                        supposedComputedTimeToBeAbove
                )
        );
        assertTrue(
                calculatedTime < supposedComputedTimeToBeUnder,
                String.format(
                        "Error, computed time is too high: %s. It was supposed to be lower than %s",
                        calculatedTime,
                        supposedComputedTimeToBeAbove
                )
        );
    }

    @Test
    void testMidnightConversion()
    {
        int hours = 0;
        int minutes = 0;
        int supposedComputedTime = 18000;

        long calculatedTime = TimeRepository.convertTimeToMinecraftTicks(hours, minutes);

        assertEquals(calculatedTime, supposedComputedTime, String.format("Error, computed time is too high: %s. %s expected.", calculatedTime, supposedComputedTime));
    }
}
