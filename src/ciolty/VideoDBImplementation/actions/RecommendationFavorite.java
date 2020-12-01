package ciolty.VideoDBImplementation.actions;

import ciolty.VideoDBImplementation.entities.UserData;
import ciolty.VideoDBImplementation.entities.VideoData;

import java.util.Map;

public final class RecommendationFavorite extends RecommendationPremium {
    public RecommendationFavorite() {
        super();
        setFailMessage("FavoriteRecommendation cannot be applied!");
    }

    @Override
    public String execute() {
        String favorite = getFavoriteVideo(userData);
        if (favorite == null) {
            return failMessage;
        }
        return "FavoriteRecommendation result: " + favorite;
    }

    private String getFavoriteVideo(final UserData userData) {
        Map<String, Integer> favoriteVideos = getUnitOfWork().getUserRepository().getAllFavorites();
        String favoriteVideo = null;
        int maxOccurences = 0;
        boolean repeat = false;
        for (Map.Entry<String, Integer> entry : favoriteVideos.entrySet()) {
            if (!userData.getHistory().containsKey(entry.getKey())) {
                if (entry.getValue() == maxOccurences) {
                    repeat = true;
                    VideoData o1 = getUnitOfWork().getMovieRepository().get(favoriteVideo);
                    if (o1 == null) {
                        favoriteVideo = entry.getKey();
                    } else {
                        VideoData o2 = getUnitOfWork().getMovieRepository().get(entry.getKey());
                        if (o2 != null) {
                            if (o1.getTitle().compareTo(o2.getTitle()) > 0) {
                                favoriteVideo = entry.getKey();
                            }
                        }
                    }
                }
                if (entry.getValue() > maxOccurences) {
                    repeat = false;
                    maxOccurences = entry.getValue();
                    favoriteVideo = entry.getKey();
                }
            }
        }
        if (repeat) {
            System.err.println("Repeat");
            for (Map.Entry<String, Integer> entry : favoriteVideos.entrySet()) {
                if (!userData.getHistory().containsKey(entry.getKey())) {
                    if (entry.getValue() == maxOccurences) {
                        System.err.println(entry.getKey());
                    }
                }
            }
        }
        return favoriteVideo;
    }
}
