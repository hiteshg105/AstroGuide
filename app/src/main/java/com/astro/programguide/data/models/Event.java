package com.astro.programguide.data.models;

/**
 * Created by hitesh on 8/22/17.
 */

public class Event {
    private String           genre;

    private String           channelStbNumber;

    private VernacularData[] vernacularData;

    private String           epgEventImage;

    private String           episodeId;

    private boolean          highlight;

    private int              contentId;

    private String           displayDateTime;

    private String           longSynopsis;

    private String           actors;

    private String           programmeId;

    private String           producers;

    private String           live;

    private String           ottBlackout;

    private String           siTrafficKey;

    private String           channelHD;

    private ContentImage[]   contentImage;

    private String           channelId;

    private String           channelTitle;

    private String           displayDateTimeUtc;

    private String           certification;

    private String           displayDuration;

    private String           eventID;

    private String           shortSynopsis;

    private String           groupKey;

    private String           premier;

    private String           programmeTitle;

    private String           directors;

    private String           subGenre;

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getChannelStbNumber() {
        return channelStbNumber;
    }

    public void setChannelStbNumber(String channelStbNumber) {
        this.channelStbNumber = channelStbNumber;
    }

    public VernacularData[] getVernacularData() {
        return vernacularData;
    }

    public void setVernacularData(VernacularData[] vernacularData) {
        this.vernacularData = vernacularData;
    }

    public String getEpgEventImage() {
        return epgEventImage;
    }

    public void setEpgEventImage(String epgEventImage) {
        this.epgEventImage = epgEventImage;
    }

    public String getEpisodeId() {
        return episodeId;
    }

    public void setEpisodeId(String episodeId) {
        this.episodeId = episodeId;
    }

    public boolean getHighlight() {
        return highlight;
    }

    public void setHighlight(boolean highlight) {
        this.highlight = highlight;
    }

    public int getContentId() {
        return contentId;
    }

    public void setContentId(int contentId) {
        this.contentId = contentId;
    }

    public String getDisplayDateTime() {
        return displayDateTime;
    }

    public void setDisplayDateTime(String displayDateTime) {
        this.displayDateTime = displayDateTime;
    }

    public String getLongSynopsis() {
        return longSynopsis;
    }

    public void setLongSynopsis(String longSynopsis) {
        this.longSynopsis = longSynopsis;
    }

    public String getActors() {
        return actors;
    }

    public void setActors(String actors) {
        this.actors = actors;
    }

    public String getProgrammeId() {
        return programmeId;
    }

    public void setProgrammeId(String programmeId) {
        this.programmeId = programmeId;
    }

    public String getProducers() {
        return producers;
    }

    public void setProducers(String producers) {
        this.producers = producers;
    }

    public String getLive() {
        return live;
    }

    public void setLive(String live) {
        this.live = live;
    }

    public String getOttBlackout() {
        return ottBlackout;
    }

    public void setOttBlackout(String ottBlackout) {
        this.ottBlackout = ottBlackout;
    }

    public String getSiTrafficKey() {
        return siTrafficKey;
    }

    public void setSiTrafficKey(String siTrafficKey) {
        this.siTrafficKey = siTrafficKey;
    }

    public String getChannelHD() {
        return channelHD;
    }

    public void setChannelHD(String channelHD) {
        this.channelHD = channelHD;
    }

    public ContentImage[] getContentImage() {
        return contentImage;
    }

    public void setContentImage(ContentImage[] contentImage) {
        this.contentImage = contentImage;
    }

    public String getChannelId() {
        return channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

    public String getChannelTitle() {
        return channelTitle;
    }

    public void setChannelTitle(String channelTitle) {
        this.channelTitle = channelTitle;
    }

    public String getDisplayDateTimeUtc() {
        return displayDateTimeUtc;
    }

    public void setDisplayDateTimeUtc(String displayDateTimeUtc) {
        this.displayDateTimeUtc = displayDateTimeUtc;
    }

    public String getCertification() {
        return certification;
    }

    public void setCertification(String certification) {
        this.certification = certification;
    }

    public String getDisplayDuration() {
        return displayDuration;
    }

    public void setDisplayDuration(String displayDuration) {
        this.displayDuration = displayDuration;
    }

    public String getEventID() {
        return eventID;
    }

    public void setEventID(String eventID) {
        this.eventID = eventID;
    }

    public String getShortSynopsis() {
        return shortSynopsis;
    }

    public void setShortSynopsis(String shortSynopsis) {
        this.shortSynopsis = shortSynopsis;
    }

    public String getGroupKey() {
        return groupKey;
    }

    public void setGroupKey(String groupKey) {
        this.groupKey = groupKey;
    }

    public String getPremier() {
        return premier;
    }

    public void setPremier(String premier) {
        this.premier = premier;
    }

    public String getProgrammeTitle() {
        return programmeTitle;
    }

    public void setProgrammeTitle(String programmeTitle) {
        this.programmeTitle = programmeTitle;
    }

    public String getDirectors() {
        return directors;
    }

    public void setDirectors(String directors) {
        this.directors = directors;
    }

    public String getSubGenre() {
        return subGenre;
    }

    public void setSubGenre(String subGenre) {
        this.subGenre = subGenre;
    }

    @Override
    public String toString() {
        return "ClassPojo [genre = " + genre + ", channelStbNumber = " + channelStbNumber + ", vernacularData = " + vernacularData + ", epgEventImage = " + epgEventImage
                + ", episodeId = " + episodeId + ", highlight = " + highlight + ", contentId = " + contentId + ", displayDateTime = " + displayDateTime + ", longSynopsis = "
                + longSynopsis + ", actors = " + actors + ", programmeId = " + programmeId + ", producers = " + producers + ", live = " + live + ", ottBlackout = " + ottBlackout
                + ", siTrafficKey = " + siTrafficKey + ", channelHD = " + channelHD + ", contentImage = " + contentImage + ", channelId = " + channelId + ", channelTitle = "
                + channelTitle + ", displayDateTimeUtc = " + displayDateTimeUtc + ", certification = " + certification + ", displayDuration = " + displayDuration + ", eventID = "
                + eventID + ", shortSynopsis = " + shortSynopsis + ", groupKey = " + groupKey + ", premier = " + premier + ", programmeTitle = " + programmeTitle + ", directors = "
                + directors + ", subGenre = " + subGenre + "]";
    }

    public class VernacularData {
        private String vernacularProgrammeTitle;

        private String vernacularLongSynopsis;

        private String vernacularLanguage;

        private String actors;

        private String vernacularShortSynopsis;

        private String directors;

        private String producers;

        public String getVernacularProgrammeTitle() {
            return vernacularProgrammeTitle;
        }

        public void setVernacularProgrammeTitle(String vernacularProgrammeTitle) {
            this.vernacularProgrammeTitle = vernacularProgrammeTitle;
        }

        public String getVernacularLongSynopsis() {
            return vernacularLongSynopsis;
        }

        public void setVernacularLongSynopsis(String vernacularLongSynopsis) {
            this.vernacularLongSynopsis = vernacularLongSynopsis;
        }

        public String getVernacularLanguage() {
            return vernacularLanguage;
        }

        public void setVernacularLanguage(String vernacularLanguage) {
            this.vernacularLanguage = vernacularLanguage;
        }

        public String getActors() {
            return actors;
        }

        public void setActors(String actors) {
            this.actors = actors;
        }

        public String getVernacularShortSynopsis() {
            return vernacularShortSynopsis;
        }

        public void setVernacularShortSynopsis(String vernacularShortSynopsis) {
            this.vernacularShortSynopsis = vernacularShortSynopsis;
        }

        public String getDirectors() {
            return directors;
        }

        public void setDirectors(String directors) {
            this.directors = directors;
        }

        public String getProducers() {
            return producers;
        }

        public void setProducers(String producers) {
            this.producers = producers;
        }

        @Override
        public String toString() {
            return "ClassPojo [vernacularProgrammeTitle = " + vernacularProgrammeTitle + ", vernacularLongSynopsis = " + vernacularLongSynopsis + ", vernacularLanguage = "
                    + vernacularLanguage + ", actors = " + actors + ", vernacularShortSynopsis = " + vernacularShortSynopsis + ", directors = " + directors + ", producers = "
                    + producers + "]";
        }
    }

    public class ContentImage {
        String imageURL;
        String imageRole;
        String imageCaption;

        String imageCaptionLanguage;

        public String getImageURL() {
            return imageURL;
        }

        public void setImageURL(String imageURL) {
            this.imageURL = imageURL;
        }

        public String getImageRole() {
            return imageRole;
        }

        public void setImageRole(String imageRole) {
            this.imageRole = imageRole;
        }

        public String getImageCaption() {
            return imageCaption;
        }

        public void setImageCaption(String imageCaption) {
            this.imageCaption = imageCaption;
        }

        public String getImageCaptionLanguage() {
            return imageCaptionLanguage;
        }

        public void setImageCaptionLanguage(String imageCaptionLanguage) {
            this.imageCaptionLanguage = imageCaptionLanguage;
        }

        @Override
        public String toString() {
            return "ContentImage{" + "imageURL='" + imageURL + '\'' + ", imageRole='" + imageRole + '\'' + ", imageCaption='" + imageCaption + '\'' + ", imageCaptionLanguage='"
                    + imageCaptionLanguage + '\'' + '}';
        }

    }
}
