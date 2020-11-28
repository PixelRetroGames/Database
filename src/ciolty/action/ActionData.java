package ciolty.action;

import fileio.ActionInputData;

import java.util.List;

public final class ActionData {
    private final int actionId;
    private final String actionType;
    private final String type;
    private final String username;
    private final String objectType;
    private final String sortType;
    private final String criteria;
    private final String title;
    private final String genre;
    private final int number;
    private final double grade;
    private final int seasonNumber;
    private final List<List<String>> filters;

    public ActionData(final ActionInputData inputData) {
        actionId = inputData.getActionId();
        actionType = inputData.getActionType();
        username = inputData.getUsername();
        objectType = inputData.getObjectType();
        sortType = inputData.getSortType();
        criteria = inputData.getCriteria();
        title = inputData.getTitle();
        genre = inputData.getGenre();
        number = inputData.getNumber();
        grade = inputData.getGrade();
        seasonNumber = inputData.getSeasonNumber();
        filters = inputData.getFilters();

        // Why aren't type and objectType the same?
        if (inputData.getType() == null) {
            type = objectType;
        } else {
            type = inputData.getType();
        }
    }

    public int getActionId() {
        return actionId;
    }

    public String getActionType() {
        return actionType;
    }

    public String getType() {
        return type;
    }

    public String getUsername() {
        return username;
    }

    public String getObjectType() {
        return objectType;
    }

    public String getSortType() {
        return sortType;
    }

    public String getCriteria() {
        return criteria;
    }

    public String getTitle() {
        return title;
    }

    public String getGenre() {
        return genre;
    }

    public int getNumber() {
        return number;
    }

    public double getGrade() {
        return grade;
    }

    public int getSeasonNumber() {
        return seasonNumber;
    }

    public List<List<String>> getFilters() {
        return filters;
    }
}
