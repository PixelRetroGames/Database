package ciolty.VideoDBImplementation.actions;

import ciolty.VideoDBImplementation.entities.UserData;
import ciolty.VideoDBImplementation.entities.VideoData;

import java.util.Comparator;
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
        for (Map.Entry<String, Integer> entry : favoriteVideos.entrySet()) {
            if (!userData.getHistory().containsKey(entry.getKey())) {
                if (entry.getValue() == maxOccurences) {
                    VideoData favorite = getUnitOfWork().getMovieRepository().get(favoriteVideo);
                    VideoData key = getUnitOfWork().getMovieRepository().get(entry.getKey());
                    if ((new VideoComparator().compare(favorite, key)) < 0) {
                        favoriteVideo = key.getTitle();
                    }
                }
                if (entry.getValue() > maxOccurences) {
                    maxOccurences = entry.getValue();
                    favoriteVideo = entry.getKey();
                }
            }
        }
        return favoriteVideo;
    }

    private static final class VideoComparator implements Comparator<VideoData> {
        @Override
        public int compare(final VideoData o1, final VideoData o2) {
            if (o1 == null && o2 == null) {
                return 0;
            }
            if (o1 == null) {
                return -1;
            }
            if (o2 == null) {
                return 1;
            }
            return -o1.getTitle().compareTo(o2.getTitle());
        }
    }
}
