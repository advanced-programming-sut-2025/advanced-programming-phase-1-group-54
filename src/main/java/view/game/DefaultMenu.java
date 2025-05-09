package view.game;

import controller.GameController;
import model.enums.commands.GameCommand;

import java.util.Scanner;

public class DefaultMenu implements GameSubMenu {
    @Override
    public void run(Scanner scanner) {
        String input = scanner.nextLine();

        if (GameCommand.EXIT_GAME.matches(input))
            handleExitGame();
        else if (GameCommand.NEXT_TURN.matches(input))
            handleNextTurn();
        else if (GameCommand.TIME.matches(input))
            handleShowTime();
        else if (GameCommand.DATE.matches(input))
            handleShowDate();
        else if (GameCommand.DATETIME.matches(input))
            handleShowDateTime();
        else if (GameCommand.DAY_OF_THE_WEEK.matches(input))
            handleShowDayOfWeek();
        else if (GameCommand.SEASON.matches(input))
            handleShowSeason();
        else if (GameCommand.WEATHER.matches(input))
            handleShowWeather();
        else if (GameCommand.WEATHER_FORECAST.matches(input))
            handleShowWeatherForecast();

        else if (GameCommand.GREENHOUSE_BUILD.matches(input))
            handleGreenHouseBuild();

//      todo  else if (GameComman
    }

    private void handleExitGame() {
        showResult(GameController.exitGame());
    }

    private void handleNextTurn() {
        showResult(GameController.nextTurn());
    }

    private void handleShowDate() {
        showResult(GameController.showDate());
    }

    private void handleShowDateTime() {
        showResult(GameController.showTime());
    }

    private void handleShowTime() {
        showResult(GameController.showDateTime());
    }

    private void handleShowDayOfWeek() {
        showResult(GameController.showDayOfWeek());
    }

    private void handleShowSeason() {
        showResult(GameController.showSeason());
    }

    private void handleShowWeather() {
        showResult(GameController.showWeather());
    }

    private void handleShowWeatherForecast() {
        showResult(GameController.showWeatherForecast());
    }

    private void handleGreenHouseBuild() {

    }
}
