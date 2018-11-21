package algorithm.dynamic.packing.bags;

public class Item {
    private int weight;
    private int cost;

    public Item(int weight, int cost) {
        this.weight = weight;
        this.cost = cost;
    }

    public int getWeight() {
        return weight;
    }

    public int getCost() {
        return cost;
    }

    @Override
    public String toString() {
        return "Item{" +
                "weight=" + weight +
                ", cost=" + cost +
                '}';
    }
}
