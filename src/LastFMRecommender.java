import java.util.ArrayList;
import java.util.HashMap;
import java.util.PriorityQueue;

public class LastFMRecommender
{
    private HashMap<Integer, PriorityQueue<Integer>> friendMap;
    private HashMap<Integer, PriorityQueue<Artist>> userMap;
    private HashMap<Integer, Artist> artistMap;
    private PriorityQueue<Artist> popularArtistQueue;


    public LastFMRecommender(String friendsRoute, String artistsRoute, String userArtistRelationRoute)
    {
        ArrayListLoader loader = new ArrayListLoader();

        artistMapLoader(getArrayList(artistsRoute,loader));
        friendMapLoader(getArrayList(friendsRoute, loader));
        userMapLoader(getArrayList(userArtistRelationRoute, loader));
    }

    public void listFriends(int user)
    {
        //Prints the list of friends of the given user
    }

    public void commonFriends(int user1, int user2)
    {
        //Prints the user1's friends in common with user2
    }

    public void listArtists(int user1, int user2)
    {
        //prints the list of artists listened by both users
    }

    public void listTop10()
    {
        //prints the list of the top 10 most popular artists listened by all users
    }

    public void recommend10(int user)
    {
        //Recommends 10 most popular artists listened by the given user and their friends
    }

    private ArrayList<String> getArrayList(String route, ArrayListLoader loader)
    {
        loader.clear();
        loader.setFileName(route);
        loader.load();

        return loader.getDataArray();
    }

    private void artistMapLoader(ArrayList<String> artistList)
    {

    }

    private void friendMapLoader(ArrayList<String> friendList)
    {

    }

    private void userMapLoader(ArrayList<String> userList)
    {

    }

}
