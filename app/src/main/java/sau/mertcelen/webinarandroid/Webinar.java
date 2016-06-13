package sau.mertcelen.webinarandroid;

/**
 * Created by mertcelen on 13/06/16.
 */
public class Webinar {
    private String title,description,presenterName,guestName,guestMail,startDate,startMessage;
    private int id,duration,maxUser;
    private boolean online,completed,started,isPublic,enApplause,enMessage,enLobby;

    @Override
    public String toString() {
        return "Webinar{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", presenterName='" + presenterName + '\'' +
                ", guestName='" + guestName + '\'' +
                ", guestMail='" + guestMail + '\'' +
                ", startDate='" + startDate + '\'' +
                ", startMessage='" + startMessage + '\'' +
                ", id=" + id +
                ", duration=" + duration +
                ", maxuser=" + maxUser +
                ", online=" + online +
                ", completed=" + completed +
                ", started=" + started +
                ", isPublic=" + isPublic +
                ", enApplause=" + enApplause +
                ", enMessage=" + enMessage +
                ", enLobby=" + enLobby +
                '}';
    }

    public Webinar(String title, String description, String presenterName, String guestName, String guestMail, String startDate, String startMessage, int id, int duration, int maxUser, boolean online, boolean completed, boolean started, boolean isPublic, boolean enApplause, boolean enMessage, boolean enLobby) {
        this.title = title;
        this.description = description;
        this.presenterName = presenterName;
        this.guestName = guestName;
        this.guestMail = guestMail;
        this.startDate = startDate;
        this.startMessage = startMessage;
        this.id = id;
        this.duration = duration;
        this.maxUser = maxUser;
        this.online = online;
        this.completed = completed;
        this.started = started;
        this.isPublic = isPublic;
        this.enApplause = enApplause;
        this.enMessage = enMessage;
        this.enLobby = enLobby;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getPresenterName() {
        return presenterName;
    }

    public String getGuestName() {
        return guestName;
    }

    public String getGuestMail() {
        return guestMail;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getStartMessage() {
        return startMessage;
    }

    public int getId() {
        return id;
    }

    public int getDuration() {
        return duration;
    }

    public int getMaxuser() {
        return maxUser;
    }

    public boolean isOnline() {
        return online;
    }

    public boolean isCompleted() {
        return completed;
    }

    public boolean isStarted() {
        return started;
    }

    public boolean isPublic() {
        return isPublic;
    }

    public boolean isEnApplause() {
        return enApplause;
    }

    public boolean isEnMessage() {
        return enMessage;
    }

    public boolean isEnLobby() {
        return enLobby;
    }
}
