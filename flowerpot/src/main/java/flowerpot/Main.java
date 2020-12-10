package flowerpot;

public class Main {

    public static void main(String[] args) {
        WaterSpray waterSpray = new WaterSpray(100);
        waterSpray.fillUp();

        FlowerPot pot = new FlowerPot(10);

        for (int i = 0; i < 2; ++i) {
//            waterSpray.sprayTo(pot);
            pot.addWater(waterSpray);
        }

        pot.liveAnotherDay();
        System.out.printf("pot alive? %s", pot.isAlive());
    }
}
