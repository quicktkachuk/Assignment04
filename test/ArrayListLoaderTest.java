import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ArrayListLoaderTest {
    private ArrayListLoader arrayListLoader;

    @BeforeEach
    public void setUp()
    {
        this.arrayListLoader = new ArrayListLoader();
    }

    @AfterEach
    public void tearDown()
    {
        this.arrayListLoader = null;
    }

    @Test
    public void getArtists()
    {
        this.arrayListLoader.setFileName("artists.dat");
        this.arrayListLoader.load();

        assertNotEquals(null, this.arrayListLoader.getDataArray());
    }

    @Test
    public void getFriends()
    {
        this.arrayListLoader.setFileName("user_friends.dat");
        this.arrayListLoader.load();

        assertNotEquals(null, this.arrayListLoader.getDataArray());
    }

    @Test
    public void getUserArtistRelation()
    {
        this.arrayListLoader.setFileName("user_artists.dat");
        this.arrayListLoader.load();

        assertNotEquals(null, this.arrayListLoader.getDataArray());
    }
}
