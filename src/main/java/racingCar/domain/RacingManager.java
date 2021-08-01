package racingCar.domain;

import java.util.ArrayList;
import java.util.List;
import racingCar.ui.PrintService;

public class RacingManager {

    private final List<RacingCar> cars = new ArrayList<>();
    private final NumberGenerator numberGenerator;
    private final WinnerPolicy winnerPolicy;

    private int numberOfCar;
    private int tryCount;

    public RacingManager(String[] names, int tryCount) {
        this.numberOfCar = names.length;
        this.tryCount = tryCount;
        numberGenerator = new RandomNumberGenerator();
        winnerPolicy = new WinnerPolicyImpl();
        generateCars(names);
    }

    public int getNumberOfCar() {
        return numberOfCar;
    }

    public int getCarsArraySize() {
        return cars.size();
    }

    private void generateCars() {
        for (int i = 0; i < numberOfCar; i++) {
            this.cars.add(new RacingCar());
        }
    }

    private void generateCars(String[] names) {
        for (int i = 0; i < numberOfCar; i++) {
            this.cars.add(new RacingCar(names[i]));
        }
    }

    private void doOneLap() {
        for (RacingCar car : cars) {
            car.goForward(numberGenerator.getNumber());
        }
    }

    private void showCurrentDrivenDistanceWithName() {
        for (RacingCar car : cars) {
            PrintService.printDistanceWithName(car.showDrivenDistance(), car.getCarName());
        }
    }

    public void doFullRace() {
        PrintService.printResultMessage();
        for (int i = 0; i < tryCount; i++) {
            doOneLap();
            PrintService.printEmptyLine();
            showCurrentDrivenDistanceWithName();
        }
        PrintService.printEmptyLine();
        PrintService.printWinners(winnerPolicy.winnerDecision(cars));
    }

}
