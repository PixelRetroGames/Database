package ciolty.VideoDBImplementation.entities;

import fileio.ActorInputData;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

import static utils.Utils.awardsToString;

public final class ActorData {
    private final String name;
    private final String careerDescription;
    private final ArrayList<String> filmography;
    private final Map<String, Integer> awards;
    private final int numberOfAwards;

    public ActorData(final ActorInputData inputData) {
        this.name = inputData.getName();
        this.careerDescription = inputData.getCareerDescription();
        this.filmography = inputData.getFilmography();
        this.awards = new LinkedHashMap<>();
        inputData.getAwards().forEach((award, nr) -> awards.put(awardsToString(award), nr));
        int awardsCount = 0;
        for (Map.Entry<String, Integer> entry : awards.entrySet()) {
            awardsCount += entry.getValue();
        }
        this.numberOfAwards = awardsCount;
    }

    public String getName() {
        return name;
    }

    public ArrayList<String> getFilmography() {
        return filmography;
    }

    public Map<String, Integer> getAwards() {
        return awards;
    }

    public String getCareerDescription() {
        return careerDescription;
    }

    @Override
    public String toString() {
        return "ActorInputData{"
                + "name='" + name + '\''
                + ", careerDescription='"
                + careerDescription + '\''
                + ", filmography=" + filmography + '}';
    }

    public int getNumberOfAwards() {
        return numberOfAwards;
    }
}
