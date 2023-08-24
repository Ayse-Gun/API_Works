package pojos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)  // bunlarin disinda data geldiginde gormezden gel demek oluyor , id gibi
public class JsonPlaceHolderPojo {

    // 1. Private variable'lar olustur

    private Integer userId;
    private String title;
    private Boolean completed;


    // 2. Parametreli ve Parametresiz counstructorlar olustur

    public JsonPlaceHolderPojo(Integer userId, String title, Boolean completed) {
        this.userId = userId;
        this.title = title;
        this.completed = completed;
    }

    public JsonPlaceHolderPojo() {

    }

    // 3. public getter ve setter methodlarini olustur

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Boolean getCompleted() {
        return completed;
    }

    public void setCompleted(Boolean completed) {
        this.completed = completed;
    }


    // 4. toString() methodunu olustur

    @Override
    public String toString() {
        return "JsonPlaceHolderPojo{" +
                "userId=" + userId +
                ", title='" + title + '\'' +
                ", completed=" + completed +
                '}';
    }
}

/*
         {
             "userId": 55,
             "title": "Tidy your room",
             "completed": false
           }
 */
// Notes : //getter'lar parametresiz olur, setter'Lar parametreli
//setter'lar void olması lazım.
//Parametrelerin null gelme ihtimaline karsi if li ifadeleri olusturduk. Patch islemi icin
