package rahandaz.exilios.com.rahandaz;

/**
 * Created by NaviD on 9/14/2017.
 */

public class ProjectModel {

    String title, category, description, imageUrl, userImageUri, remainingTime, creator;
    int goalAmount, currentAmount, projectId;

    public ProjectModel(String title, String category, String description, String imageUrl, String userImageUri, String remainingTime, String creator, int goalAmount, int currentAmount, int projectId) {
        this.title = title;
        this.category = category;
        this.description = description;
        this.imageUrl = imageUrl;
        this.userImageUri = userImageUri;
        this.remainingTime = remainingTime;
        this.creator = creator;
        this.goalAmount = goalAmount;
        this.currentAmount = currentAmount;
        this.projectId = projectId;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getUserImageUri() {
        return userImageUri;
    }

    public void setUserImageUri(String userImageUri) {
        this.userImageUri = userImageUri;
    }

    public String getRemainingTime() {
        return remainingTime;
    }

    public void setRemainingTime(String remainingTime) {
        this.remainingTime = remainingTime;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public int getGoalAmount() {
        return goalAmount;
    }

    public void setGoalAmount(int goalAmount) {
        this.goalAmount = goalAmount;
    }

    public int getCurrentAmount() {
        return currentAmount;
    }

    public void setCurrentAmount(int currentAmount) {
        this.currentAmount = currentAmount;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }
}
