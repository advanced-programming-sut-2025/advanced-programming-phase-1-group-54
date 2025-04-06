package model.enums;

public enum Season {
    SPRING(
            new Weather[]{
                    Weather.SUNNY, Weather.RAIN, Weather.STORM
            }
    ),
    SUMMER(
            new Weather[]{
                    Weather.SUNNY, Weather.RAIN, Weather.STORM
            }
    ),
    AUTUMN(
            new Weather[]{
                    Weather.SUNNY, Weather.RAIN, Weather.STORM
            }
    ),
    WINTER(
            new Weather[]{
                    Weather.SUNNY, Weather.SNOW
            }
    );

    private Weather[] weathers;

    Season(Weather[] weathers) {
        this.weathers = weathers;
    }
}
