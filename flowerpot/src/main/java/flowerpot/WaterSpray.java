package flowerpot;

public class WaterSpray {
    private int capacity;
    private int remainingWaterInMl = 0;

    public WaterSpray(int capacity) {
        this.capacity = capacity;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getRemainingWaterInMl() {
        return remainingWaterInMl;
    }

    public void addWater(int amountInMl) {
        this.remainingWaterInMl += amountInMl;
        this.remainingWaterInMl = Math.min(this.remainingWaterInMl, 5);
    }

    // 가득 채워야겠다!
    public void fillUp() {
        this.remainingWaterInMl = capacity;
    }

    public void spray() {
        this.remainingWaterInMl -= Math.min(this.remainingWaterInMl, 5);
    }

}
