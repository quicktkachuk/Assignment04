import edu.princeton.cs.algs4.In;

import java.util.*;

public class LastFMRecommender
{

    private HashMap<Integer, User> userMap;
    private HashMap<Integer, Artist> artistMap;
    private PriorityQueue<Artist> popularArtistQueue;


    public LastFMRecommender(String friendsRoute, String artistsRoute, String userArtistRelationRoute)
    {
        this.popularArtistQueue = new PriorityQueue<>(10);
        this.userMap = new HashMap<>();
        ArrayListLoader loader = new ArrayListLoader();

        artistMapLoader(getArrayList(artistsRoute,loader));
        friendLoader(getArrayList(friendsRoute, loader));
        userMapLoader(getArrayList(userArtistRelationRoute, loader));
    }

    public int getArtistMapSize()
    {
        return this.artistMap.size();
    }

    public int getUserMapSize()
    {
        return this.userMap.size();
    }

    public void listFriends(int user)
    {
        ArrayList<Integer> friendList;

        if(!this.userMap.containsKey(user))
            throw new IndexOutOfBoundsException();
        else
        {
            System.out.println("The userID " + user + " has the following friends: ");
            friendList = this.userMap.get(user).getFriends();
            for (int id:friendList)
            {
                System.out.print(id + ", ");
            }
            System.out.print("End\n");
        }
    }

    public void commonFriends(int user1, int user2)
    {

        if(!(this.userMap.containsKey(user1)) || !(this.userMap.containsKey(user2)))
            throw new IndexOutOfBoundsException();
        else
        {
            System.out.println("The user " + user1 + " and user " + user2 + " have the following friends in common: ");

            ArrayList<Integer> friend1 = new ArrayList<>(this.userMap.get(user1).getFriends());
            ArrayList<Integer> friend2 = new ArrayList<>(this.userMap.get(user2).getFriends());

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

        if(!(this.userMap.containsKey(user1)) || !(this.userMap.containsKey(user2)))
            throw new IndexOutOfBoundsException();
        else
        {
            System.out.println("The user " + user1 + " and user " + user2 + " have the following artists in common: ");

            ArrayList<Integer> userList1 = getArtistIDList(this.userMap.get(user1).getArtists());
            ArrayList<Integer> userList2 = getArtistIDList(this.userMap.get(user2).getArtists());

            userList1.retainAll(userList2);

            for (int artistID:userList1)
            {
                System.out.print(this.artistMap.get(artistID).getName() + ", ");
            }
            System.out.print("End\n");
        }
    }

    public void listTop10()
    {
        popularArtistBuilder();
        System.out.println("The top 10 artists are: ");
        for(int i = 10; i > 0; i--)
        {
            System.out.println("#" + (i) + ": " + this.popularArtistQueue.poll().getName());
        }
    }

    //NEED TO FINISH THIS
    public void recommend10(int user)
    {
        //Recommends 10 most popular artists listened by the given user and their friends
        if(!(this.userMap.containsKey(user)))
            throw new IndexOutOfBoundsException();
        else
        {
            //Find artistID in common

            //List of friends
            ArrayList<Integer> friendList = this.userMap.get(user).getFriends();

            //List of artist IDs user listens to (deep copy)
            ArrayList<Integer> finalIDList = new ArrayList<>(getArtistIDList(this.userMap.get(user).getArtists()));

            //Start final list, deep copy of user's artists
            ArrayList<Artist> finalArtistList = new ArrayList<>(this.userMap.get(user).getArtists());

            for (int friend:friendList)
            {
                //Get user profile of friend
                User userFriend = this.userMap.get(friend);

                //Get list of artistIDs associated w/current user
                ArrayList<Integer> friendsArtistIDs = getArtistIDList(this.userMap.get(friend).getArtists());

                //Get list of artists associated w/current user
                ArrayList<Artist> friendsArtists = this.userMap.get(friend).getArtists();

                for (int artistID:friendsArtistIDs)
                {
                    int friendIndex = getIndexOfArtist(artistID, friendsArtists);

                    //Check if artistID is shared between the two lists
                    if(finalIDList.contains(artistID))
                    {
                        //If in list, increase weight appropriately
                        //Get weight for each user, update
                        int finalIndex = getIndexOfArtist(artistID, finalArtistList);
                        finalArtistList.get(finalIndex).setWeightTotal(userFriend.getArtists().get(friendIndex).getWeightTotal() + finalArtistList.get(finalIndex).getWeightTotal());
                    }
                    else
                    {
                        //If artistID is not on list, add the corresponding artist
                        finalArtistList.add(userFriend.getArtists().get(friendIndex));
                    }
                    finalIDList.get(artistID);
                }
            }

            Collections.sort(finalArtistList);

            //Print out list of top 10
            System.out.println("");
            for(int i = finalArtistList.size() - 1; i > finalArtistList.size() - 10; i--)
            {
                System.out.println(finalArtistList.get(i).getName());
            }
        }
    }

    private int getIndexOfArtist(int artistID, ArrayList<Artist> artists)
    {
        boolean found = false;
        int count = 0;
        while(!found || count < artists.size())
        {
            int currentID = artists.get(count).getId();
            if(currentID == artistID) {
                found = true;
                return count;
            }
            count++;
        }
        return -1;
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

    private void friendLoader(ArrayList<String> friendList)
    {

        //Removing first row, only contains column info
        friendList.remove(0);
        for (String line:friendList)
        {
            String[] brokenLine = line.split("\t");

            int userID = Integer.parseInt(brokenLine[0]);
            int friendID = Integer.parseInt(brokenLine[1]);

            //Check to see if userID is already present in HashMap
            if(this.userMap.containsKey(userID))
            {
                ArrayList<Integer> existingList = this.userMap.get(userID).getFriends();
                existingList.add(friendID);
            }
            else
            {
                ArrayList<Integer> newList = new ArrayList<>();
                newList.add(friendID);
                User newUser = new User();
                newUser.setFriends(newList);
//                newUser.setUserID(userID);
                this.userMap.put(userID, newUser);
            }
        }
    }

    private void userMapLoader(ArrayList<String> userList)
    {
//        this.userMap = new HashMap<>();

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
                ArrayList<Artist> currentArtists = this.userMap.get(userID).getArtists();
                Artist existingArtist = this.artistMap.get(artistID);
                Artist newArtist = new Artist();

                newArtist.setName(existingArtist.getName());
                newArtist.setId(existingArtist.getId());
                newArtist.setWeightTotal(weight);

                currentArtists.add(newArtist);
//                LinkedList<Artist> existingQueue = this.userMap.get(userID);
//                existingQueue.add(this.artistMap.get(artistID));
            }
            else
            {
                System.out.println("Why'd you go to Ravenholm?");
//                LinkedList<Artist> newQueue = new LinkedList<>();
//                newQueue.add(this.artistMap.get(artistID));
//                this.userMap.put(userID,newQueue);
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
            if(this.popularArtistQueue.size() > 10)
            {
                this.popularArtistQueue.poll();
            }
        }
    }

    //Turns list of artists into list of artistIDs, which can then be
    private ArrayList<Integer> getArtistIDList(ArrayList<Artist> artists)
    {
        ArrayList<Integer> artistIDList = new ArrayList<>();
        for (Artist artist:artists)
        {
            artistIDList.add(artist.getId());
        }

        return artistIDList;
    }

    public static void main(String[] args)
    {
        LastFMRecommender recommender = new LastFMRecommender("user_friends.dat","artists.dat","user_artists.dat");
        recommender.listFriends(2);
        recommender.commonFriends(2,275);
        recommender.listArtists(2,275);
        recommender.listTop10();
        recommender.recommend10(2);
        System.out.println("Done!");
    }

}
