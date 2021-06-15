import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class LastFMRecorderTest
{
    private LastFMRecommender lastFMRecommender;

    @BeforeEach
    public void setUp()
    {
        this.lastFMRecommender = new LastFMRecommender("user_friends.dat","artists.dat","user_artists.dat");
    }

    @AfterEach
    public void tearDown()
    {
        this.lastFMRecommender = null;
    }

    @Test
    public void testListFriends()
    {

    }
}
