package ciolty.VideoDBImplementation.entities;

import fileio.SerialInputData;
import fileio.ShowInput;

import java.util.ArrayList;

public final class SeriesData extends VideoData {
    private final int numberOfSeasons;
    private final ArrayList<SeasonData> seasons;

    public SeriesData(final SerialInputData inputData) {
        super((ShowInput) inputData);
        this.numberOfSeasons = inputData.getNumberSeason();

        seasons = new ArrayList<SeasonData>();
        for (int i = 0; i < numberOfSeasons; i++) {
            seasons.add(new SeasonData(inputData.getSeasons().get(i)));
        }

        this.priority = 2;
    }

    public int getNumberOfSeasons() {
        return numberOfSeasons;
    }

    public ArrayList<SeasonData> getSeasons() {
        return seasons;
    }

    @Override
    public double getRating() {
        double seriesRating = 0.0f;
        for (SeasonData seasonDataIterator : seasons) {
            seriesRating += seasonDataIterator.getRating();
        }
        seriesRating /= numberOfSeasons;
        return seriesRating;
    }

    @Override
    public int getDuration() {
        int duration = 0;
        for (SeasonData seasonDataIterator : seasons) {
            duration += seasonDataIterator.getDuration();
        }
        return duration;
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
