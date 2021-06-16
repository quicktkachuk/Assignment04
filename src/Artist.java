public class Artist implements Comparable
{
    private int id;
    private int weightTotal;
    private String name;

    public Artist()
    {
        this.id = -1;
        this.weightTotal = 0;
        this.name = "";
    }

    public Artist(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Artist(Artist artist)
    {
        this.id = artist.getId();
        this.name = artist.getName();
        this.weightTotal = artist.getWeightTotal();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getWeightTotal() {
        return weightTotal;
    }

    public void setWeightTotal(int weightTotal) {
        this.weightTotal = weightTotal;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int compareTo(Object o) {
        if(!(o instanceof Artist))
            throw new IllegalArgumentException();
        else if(this.weightTotal < ((Artist) o).getWeightTotal())
            return -1;
        else if(this.weightTotal > ((Artist) o).getWeightTotal())
            return 1;
        else
            return 0;
    }
}
