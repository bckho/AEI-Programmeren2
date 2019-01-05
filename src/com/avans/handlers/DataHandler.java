package com.avans.handlers;

import com.avans.database.*;
import com.avans.database.tables.SubscriptionTable;
import com.avans.database.tables.SerieTable;
import com.avans.handlers.program.Movie;
import com.avans.handlers.program.Program;
import com.avans.handlers.program.Serie;
import com.avans.handlers.user.Subscriber;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.avans.database.tables.SubscriptionTable.LAST_NAME;
import static com.avans.database.tables.SubscriptionTable.NAME;
import static com.avans.database.tables.MovieTable.*;
import static com.avans.database.tables.ProgramTable.*;

/*
    Created By Robin Egberts On 12/30/2018
    Copyrighted By OrbitMines ©2018
*/
public class DataHandler {

    private static Join MOVIE_JOIN = new Join(Join.Type.INNER_JOIN, ID, FK_ID);
    private static Join SERIE_JOIN = new Join(Join.Type.INNER_JOIN, ID, SerieTable.ID);

    private List<Subscriber> subscribers;
    private List<Program> programs;

    public DataHandler() {
        this.subscribers = new ArrayList<>();
        this.programs = new ArrayList<>();

        this.init();
    }

    /**
     * ADD METHODS
     */
    public boolean addSubscriber(String name, String lastName) {
        int id = subscribers.size() + 1;

        if (isSubscriber(id))
            return false;

        this.subscribers.add(new Subscriber(id, name, lastName));
        return true;
    }

    public boolean addSerie(String title, String genre) {
        int id = programs.size() + 1;

        if (isProgram(id))
            return false;

        if (isProgram(title))
            return false;

        this.programs.add(new Serie(id, title, genre));

        return true;
    }

    public boolean addEpisode(String title, int episode, int duration, boolean nextEpisode) {
        if (!isProgram(title))
            return false;

        Serie serie = (Serie) getProgram(title);

        if (serie.isEpisode(episode))
            return false;

        serie.addEpisode(episode, duration, nextEpisode);

        return true;
    }

    public boolean addMovie(String title, int duration, String genre, int ageIndication) {
        int id = programs.size() + 1;

        if (isProgram(title) || isProgram(id))
            return false;


        programs.add(new Movie(id, title, duration, ageIndication, genre));

        return true;
    }

    /**
     * GETTERS
     */

    //subscribers
    public List<Subscriber> getSubscribers() {
        return subscribers;
    }

    public Subscriber getSubscriber(int id) {
        for (Subscriber s : subscribers) {
            if (s.getId() == id) {
                return s;
            }
        }
        return null;
    }

    public List<Subscriber> getSubscribers(String name, String lastName) {
        List<Subscriber> subscribers = new ArrayList<>();

        for (Subscriber s : this.subscribers) {
            if (s.getName().equalsIgnoreCase(name) && s.getLastName().equalsIgnoreCase(lastName)) {
                subscribers.add(s);
            }
        }
        return subscribers;
    }

    //program
    public Program getProgram(String name) {
        for (Program p : programs) {
            if (p.getTitle().equalsIgnoreCase(name)) {
                return p;
            }
        }
        return null;
    }

    public Program getProgram(int id) {
        for (Program p : programs) {
            if (p.getId() == id) {
                return p;
            }
        }
        return null;
    }

    public <T extends Program> List<T> getPrograms(Class<T> type) {
        if (type == Program.class)
            return (List<T>) programs;

        List<T> programs = new ArrayList<>();

        for (Program p : this.programs) {
            if (p.getClass() == type) {
                programs.add((T) p);
            }
        }
        return programs;
    }

    /**
     * BOOLEANS
     */
    public boolean isProgram(String name) {
        return getProgram(name) != null;
    }

    public boolean isProgram(int id) {
        return getProgram(id) != null;
    }

    public boolean isSubscriber(int id) {
        return getSubscriber(id) != null;
    }

    /**
     * delete() method
     */
    public boolean delete(Removable removable) {
        boolean deleted = removable.delete();

        if (deleted) {
            if (removable instanceof Program)
                this.programs.remove(removable);

            if (removable instanceof Subscriber)
                this.subscribers.remove(removable);
        }
        return deleted;
    }

    /**
     * init() method
     */
    private void init() {
        for (Map<Column, Object> values : getEntry(MOVIE_JOIN, ID, TITLE, DURATION))
            programs.add(new Movie((int) values.get(ID)));

        for (Map<Column, Object> values : getEntry(SERIE_JOIN, ID, TITLE))
            programs.add(new Serie((int) values.get(ID)));

        for (Map<Column, Object> values : getEntry(SUBSCRIPTION_TABLE, SubscriptionTable.ID, SubscriptionTable.NAME, SubscriptionTable.LAST_NAME))
            subscribers.add(new Subscriber((int) values.get(SubscriptionTable.ID), (String) values.get(NAME), (String) values.get(LAST_NAME)));
    }

    private List<Map<Column, Object>> getEntry(From from, Column... columns) {
        return Database.get().getEntry(from, columns);
    }

    /**
     * serialize() method
     */
    public void serialize() {
        for (Subscriber s : subscribers)
            s.serialize();

        for (Program p : programs)
            p.serialize();
    }
}
