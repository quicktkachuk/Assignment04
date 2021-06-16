import java.util.ArrayList;

public class User
{
//    private int userID;
    private ArrayList<Integer> friends;
    private ArrayList<Artist> artists;

    public User()
    {
        this.friends = new ArrayList<>();
        this.artists = new ArrayList<>();
    }

    public void addFriend(int friend)
    {
        this.friends.add(friend);
    }

    public void addArtist(Artist artist)
    {
        this.artists.add(artist);
    }
//
//    public boolean contains(int artistID)
//    {
//        boolean found = false;
//        int count = 0;
//        while(!found && count < this.artists.size())
//        {
//            int currentID = this.artists.get(count).getId();
//            if(currentID == artistID) {
//                found = true;
//                return found;
//            }
//            count++;
//        }
//        return false;
//    }
//
//    public int getArtistPosition(int artistID)
//    {
//        boolean found = false;
//        int count = 0;
//        while(!found || count < this.artists.size())
//        {
//            if(this.artists.get(count).getId() == artistID) {
//                found = true;
//                return count;
//            }
//            count++;
//        }
//        return -1;
//    }

    public ArrayList<Integer> getFriends() {
        return friends;
    }

    public void setFriends(ArrayList<Integer> friends) {
        this.friends = friends;
    }

    public ArrayList<Artist> getArtists() {
        return artists;
    }

    public void setArtists(ArrayList<Artist> artists) {
        this.artists = artists;
    }
}
