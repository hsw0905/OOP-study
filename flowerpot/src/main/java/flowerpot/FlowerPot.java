package flowerpot;

public class FlowerPot {
    private boolean alive = true;
    private final int minDailyWaterInMl;
    private int dailyWaterReceived = 0;

    public FlowerPot(int minDailyWaterInMl) {
        this.minDailyWaterInMl = minDailyWaterInMl;
    }

    public boolean isAlive() {
        return alive;
    }

    public int getMinDailyWaterInMl() {
        return minDailyWaterInMl;
    }

    public void addWater(int amountInMl) {
        dailyWaterReceived += amountInMl;
    }

    public void liveAnotherDay() {
        if (dailyWaterReceived < minDailyWaterInMl) {
            alive = false;
        }
        dailyWaterReceived = 0;
    }
}
