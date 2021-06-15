public class User
{
    private int[] friends;
    private int[] artists;

    public User()
    {

    }

    public User(int friendSize, int artistSize)
    {
        this.friends = new int[friendSize];
        this.artists = new int[artistSize];
    }

    public int[] getFriends() {
        return friends;
    }

    public void setFriends(int[] friends) {
        this.friends = friends;
    }

    public int[] getArtists() {
        return artists;
    }

    public void setArtists(int[] artists) {
        this.artists = artists;
    }
}
