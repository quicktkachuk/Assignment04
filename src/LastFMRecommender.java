import javax.management.openmbean.InvalidKeyException;
import java.util.*;

public class LastFMRecommender
{
//    private HashMap<Integer, PriorityQueue<Integer>> friendMap;
//    private HashMap<Integer, LinkedList<Artist>> userMap;
    private HashMap<Integer, User> userMap;
    private HashMap<Integer, Artist> artistMap;
    private PriorityQueue<Artist> popularArtistQueue;


    public LastFMRecommender(String friendsRoute, String artistsRoute, String userArtistRelationRoute)
    {
        this.popularArtistQueue = new PriorityQueue<>(Collections.reverseOrder());
        ArrayListLoader loader = new ArrayListLoader();

        artistMapLoader(getArrayList(artistsRoute,loader));
        friendMapLoader(getArrayList(friendsRoute, loader));
        userMapLoader(getArrayList(userArtistRelationRoute, loader));
        popularArtistBuilder();
    }

    public int getArtistMapSize()
    {
        return this.artistMap.size();
    }

    public int getUserMapSize()
    {
        return this.userMap.size();
    }

    public int getFriendsMapSize()
    {
        return this.friendMap.size();
    }

    public void listFriends(int user)
    {
        PriorityQueue<Integer> friendsQueue;

        if(!this.friendMap.containsKey(user))
            throw new IndexOutOfBoundsException();
        else
        {
            System.out.println("The userID " + user + " has the following friends: ");
            friendsQueue = this.friendMap.get(user);
            for (int id:friendsQueue)
            {
                System.out.print(id + ", ");
            }
            System.out.print("End\n");
        }
    }

    public void commonFriends(int user1, int user2)
    {
        PriorityQueue<Integer> friend1 = new PriorityQueue<>();
        PriorityQueue<Integer> friend2 = new PriorityQueue<>();

        if(!(this.friendMap.containsKey(user1)) || !(this.friendMap.containsKey(user2)))
            throw new IndexOutOfBoundsException();
        else
        {
            System.out.println("The user " + user1 + " and user " + user2 + " have the following friends in common: ");

            friend1.addAll(this.friendMap.get(user1));
            friend2.addAll(this.friendMap.get(user2));

            friend1.retainAll(friend2);

            for (int userID:friend1)
            {
                System.out.print(userID + ", ");
            }
            System.out.print("End\n");
        }
    }

    public void listArtists(int user1, int user2)
    {
        //prints the list of artists listened by both users
        LinkedList<Artist> userList1 = new LinkedList<>();
        LinkedList<Artist> userList2 = new LinkedList<>();

        if(!(this.userMap.containsKey(user1)) || !(this.userMap.containsKey(user2)))
            throw new IndexOutOfBoundsException();
        else
        {
            System.out.println("The user " + user1 + " and user " + user2 + " have the following artists in common: ");

            userList1.addAll(this.userMap.get(user1));
            userList2.addAll(this.userMap.get(user2));

            userList1.retainAll(userList2);

            for (Artist artist:userList1)
            {
                System.out.print(artist.getName() + ", ");
            }
            System.out.print("End\n");
        }
    }

    public void listTop10()
    {
        System.out.println("The top 10 artists are: ");
        for(int i = 0; i < 10; i++)
        {
            System.out.println("#" + (i+1) + ": " + this.popularArtistQueue.poll().getName());
        }
    }

    //NEED TO FINISH THIS
    public void recommend10(int user)
    {
        //Recommends 10 most popular artists listened by the given user and their friends
        if(!(this.friendMap.containsKey(user)))
            throw new IndexOutOfBoundsException();
        else {


            for (int userID : this.friendMap.get(user)) {

            }
        }
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
        this.artistMap = new HashMap<>();

        //Remove first line which just has column info
        artistList.remove(0);

        for (String line:artistList)
        {
            String[] brokenLine = line.split("\t");
            Artist newArtist = new Artist();
            newArtist.setId(Integer.parseInt(brokenLine[0]));
            newArtist.setName(brokenLine[1]);

            this.artistMap.put(newArtist.getId(), newArtist);
        }

    }

    private void friendMapLoader(ArrayList<String> friendList)
    {
        this.friendMap = new HashMap<>();

        //Removing first row, only contains column info
        friendList.remove(0);
        for (String line:friendList)
        {
            String[] brokenLine = line.split("\t");

            int userID = Integer.parseInt(brokenLine[0]);
            int friendID = Integer.parseInt(brokenLine[1]);

            //Check to see if userID is already present in HashMap
            if(this.friendMap.containsKey(userID))
            {
                PriorityQueue<Integer> existingQueue = this.friendMap.get(userID);
            }
            else
            {
                PriorityQueue<Integer> newQueue = new PriorityQueue<>();
                newQueue.add(friendID);
                this.friendMap.put(userID,newQueue);
            }
        }
    }

    private void userMapLoader(ArrayList<String> userList)
    {
        this.userMap = new HashMap<>();

        //Removing first row, it only contains column information
        userList.remove(0);

        for (String line: userList)
        {
            String[] brokenLine = line.split("\t");

            int userID = Integer.parseInt(brokenLine[0]);
            int artistID = Integer.parseInt(brokenLine[1]);
            int weight = Integer.parseInt(brokenLine[2]);

            //Check to see if userID exists
            if(this.userMap.containsKey(userID))
            {
                LinkedList<Artist> existingQueue = this.userMap.get(userID);
                existingQueue.add(this.artistMap.get(artistID));
            }
            else
            {
                LinkedList<Artist> newQueue = new LinkedList<>();
                newQueue.add(this.artistMap.get(artistID));
                this.userMap.put(userID,newQueue);
            }

            //Update weight for current artist
            Artist currentArtist = this.artistMap.get(artistID);
            currentArtist.setWeightTotal(weight + currentArtist.getWeightTotal());
        }
    }

    private void popularArtistBuilder()
    {
        Set<Integer> artistIDs = this.artistMap.keySet();

        for (int artistID : artistIDs)
        {
            Artist currentArtist = this.artistMap.get(artistID);
            this.popularArtistQueue.add(currentArtist);
        }
    }

    public static void main(String[] args)
    {
        LastFMRecommender recommender = new LastFMRecommender("user_friends.dat","artists.dat","user_artists.dat");
        recommender.listFriends(2);
        recommender.commonFriends(2,275);
        recommender.listArtists(2,275);
        recommender.listTop10();
        System.out.println("Done!");
    }

}
