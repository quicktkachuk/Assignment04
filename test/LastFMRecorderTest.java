import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class LastFMRecorderTest
{
    private LastFMRecommender recommender;

    @BeforeEach
    public void setUp()
    {
        this.recommender = new LastFMRecommender("user_friends.dat","artists.dat","user_artists.dat");
    }

    @AfterEach
    public void tearDown()
    {
        this.recommender = null;
    }

    @Test
    public void testAristMap()
    {
        assertEquals(17632,  this.recommender.getArtistMapSize());
    }

    @Test
    public void testUserMap()
    {
        assertEquals(1892,  this.recommender.getUserMapSize());
    }


    @Test
    public void testListFriends()
    {

    }
}
