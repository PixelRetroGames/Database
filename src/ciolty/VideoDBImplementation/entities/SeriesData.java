package ciolty.VideoDBImplementation.entities;

import fileio.SerialInputData;
import fileio.ShowInput;

import java.util.ArrayList;

public final class SeriesData extends ShowData {
    private final int numberOfSeasons;
    private final ArrayList<SeasonData> seasons;

    public SeriesData(final SerialInputData inputData) {
        super((ShowInput) inputData);
        this.numberOfSeasons = inputData.getNumberSeason();

        seasons = new ArrayList<SeasonData>();
        for (int i = 0; i < numberOfSeasons; i++) {
            seasons.add(new SeasonData(inputData.getSeasons().get(i)));
        }
    }

    public int getNumberOfSeasons() {
        return numberOfSeasons;
    }

    public ArrayList<SeasonData> getSeasons() {
        return seasons;
    }

    @Override
    public String toString() {
        return "SerialInputData{" + " title= "
                + super.getTitle() + " " + " year= "
                + super.getYear() + " cast {"
                + super.getCast() + " }\n" + " genres {"
                + super.getGenres() + " }\n "
                + " numberSeason= " + numberOfSeasons
                + ", seasons=" + seasons + "\n\n" + '}';
    }
}
