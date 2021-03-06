package com.avans.gui;

import com.avans.gui.actionlisteners.OverlayFiveAL;
import com.avans.NFS;
import com.avans.handlers.program.Movie;
import com.avans.util.DataUtil;

import javax.swing.*;
import java.awt.*;

/**
 * fifth overlay of the application
 */
public class OverlayFive extends JPanel {

    private JComboBox<String> JCFilms;
    private JLabel showViewersCount;
    private JPanel mainPanel; // Specific panel in the Center to separate
    private JPanel westPanel;
    private JPanel eastPanel;

    public OverlayFive() {
        this.setLayout(new BorderLayout());
        this.setBackground(Color.darkGray);
        addComponents();
    }

    private void addComponents() {
        // Title of the overlay
        JLabel title = new JLabel("<html>Overzicht 5: Toon abonnee's die volledige film hebben gezien & " +
                "<br>langste film onder de 16", SwingConstants.CENTER);
        title.setFont(new Font("Helvetiva Neue", Font.PLAIN, 20));
        title.setForeground(Color.white);

        this.add(title, BorderLayout.NORTH);
        setMainPanel();
        this.add(this.mainPanel);
    }

    // Initializes the main panel
    private void setMainPanel() {
        this.mainPanel = new JPanel();
        this.mainPanel.setLayout(new GridLayout(1, 2));
        this.mainPanel.setBackground(Color.darkGray);

        addComponentsToMainPanel();
    }

    private void addComponentsToMainPanel() {
        setWestPanel();
        setEastPanel();
        this.mainPanel.add(this.westPanel);
        this.mainPanel.add(this.eastPanel);
    }

    // Initializes the west panel for the JCFilms
    private void setWestPanel() {
        this.westPanel = new JPanel();
        this.westPanel.setLayout(new GridLayout(6, 1));
        this.westPanel.setBackground(Color.gray);
        addComponentsToWestPanel();
    }

    private void addComponentsToWestPanel() {
        JLabel chooseFilm = new JLabel("Kies een film", SwingConstants.CENTER);
        chooseFilm.setFont(new Font("Helvetica Neue", Font.BOLD, 18));
        chooseFilm.setForeground(Color.lightGray);

        setShowViewersCount();
        setJCFilms();

        this.westPanel.add(new JLabel("")); // Spacer
        this.westPanel.add(new JLabel("")); // Spacer
        this.westPanel.add(chooseFilm);
        this.westPanel.add(this.JCFilms);
        this.westPanel.add(this.showViewersCount);
    }


    private void setShowViewersCount() {
        this.showViewersCount = new JLabel("0", SwingConstants.CENTER);
        this.showViewersCount.setFont(new Font("Helvetica Neue", Font.BOLD, 16));
        this.showViewersCount.setForeground(Color.white);
    }

    // Initializes the JCFilms JComboBox
    private void setJCFilms() {
        this.JCFilms = new JComboBox<>();
        for (Movie m : NFS.getHandler().getPrograms(Movie.class)) {
            this.JCFilms.addItem(m.getTitle());
        }
        this.JCFilms.setPreferredSize(new Dimension(130, 20));
        this.JCFilms.setFont(new Font("Helvetica Neue", Font.PLAIN, 12));

        // Action listener for JCFilms JComboBox
        OverlayFiveAL fal = new OverlayFiveAL(this.JCFilms, this.showViewersCount);
        this.JCFilms.addActionListener(fal);
    }

    // initializes the east panel for the longest movie
    private void setEastPanel() {
        this.eastPanel = new JPanel();
        this.eastPanel.setLayout(new GridLayout(6, 1));
        this.eastPanel.setBackground(Color.gray);
        addComponentsToEastPanel();
    }

    private void addComponentsToEastPanel() {
        // Description for the longest film
        JLabel longestFilmDiscr = new JLabel("Langste film onder 16 jaar:", SwingConstants.CENTER);
        longestFilmDiscr.setForeground(Color.lightGray);
        longestFilmDiscr.setFont(new Font("Helvetiva Neue", Font.BOLD, 18));

        // Shows the longest movie under the age of 16 from the database
        JLabel setLongestFilm = new JLabel("", SwingConstants.CENTER);
        setLongestFilm.setFont(new Font("Helvetica Neue", Font.ITALIC, 18));
        setLongestFilm.setForeground(Color.white);

        if (!(DataUtil.getLongestMovie(16) == null)) {
            setLongestFilm.setText(DataUtil.getLongestMovie(16).getTitle());

        } else {
            setLongestFilm.setText("Geen film onder de 16 is gevonden.");

        }

        this.eastPanel.add(new JLabel(""));
        this.eastPanel.add(new JLabel(""));
        this.eastPanel.add(longestFilmDiscr);
        this.eastPanel.add(setLongestFilm);
    }

}
