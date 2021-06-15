public class Artist
{
    private int id;
    private int weightTotal;
    private String name;

    public Artist()
    {
        this.id = -1;
        this.weightTotal = -1;
        this.name = "";
    }

    public Artist(int id, String name) {
        this.id = id;
        this.name = name;
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
}
