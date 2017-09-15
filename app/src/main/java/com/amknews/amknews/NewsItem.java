package com.amknews.amknews;

/**
 * Created by alex on 9/15/17.
 */

public class NewsItem {
    String id;
    String title;
    int newspic;

    public NewsItem(String id, String title, int newspic) {
        this.id = id;
        this.title = title;
        this.newspic = newspic;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getNewspic() {
        return newspic;
    }

    public void setNewspic(int newspic) {
        this.newspic = newspic;
    }
}
