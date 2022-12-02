package main.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Business {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private  long id;
    private String title;
    private String fullTextOfBusiness;

    public Business() {
    }

    public Business(String title, String fullTextOfBusiness) {
        this.title = title;
        this.fullTextOfBusiness = fullTextOfBusiness;
    }

    public long getId() {
        return id;
    }

    public synchronized void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public synchronized void setTitle(String title) {
        this.title = title;
    }

    public String getFullTextOfBusiness() {
        return fullTextOfBusiness;
    }

    public synchronized void setFullTextOfBusiness(String fullTextOfBusiness) {
        this.fullTextOfBusiness = fullTextOfBusiness;
    }
}
