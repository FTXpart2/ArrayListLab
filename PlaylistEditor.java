import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.Collections;
import java.util.Comparator;
import java.util.*;

public class PlaylistEditor extends JFrame {
    private MyArrayList<Song> playlist;
    private JPanel panel;
    private JTextField t1,t2,t3;
    private JComboBox<String> optionsBox;

    public PlaylistEditor() {
        playlist = new MyArrayList<>();
        // Adding songs of my choice
        playlist.add(new Song("Greatest Love Of All", "Whitney Houston", "Whitney Houston"));
        playlist.add(new Song("Where Do Broken Hearts Go", "Whitney", "Whitney Houston"));
        playlist.add(new Song("I Have Nothing", "The Bodyguard Soundtrack", "Whitney Houston"));
        playlist.add(new Song("Hotel California", "Hotel California", "The Eagles"));
        playlist.add(new Song("I Will Always Love You", "The Bodyguard Soundtrack", "Whitney Houston"));
        playlist.add(new Song("Go Your Own Way", "Rumours", "Fleetwood Mac"));
        playlist.add(new Song("How Will I Know", "Whitney Houston", "Whitney Houston"));


        setTitle("Playlist Editor");
        setSize(900, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        // sets panel for selection box
        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        // create a panel for the control buttons
        //Control Panel to seperate from the list panel, easier interface.
        JPanel controlPanel = new JPanel();
        JButton addSong = new JButton("Add Song");
        JButton shuffle = new JButton("Shuffle");

        t1 = new JTextField(10);
        t2 = new JTextField(10);
        t3 = new JTextField(10);

        // add a dropdown for sorting criteria
        optionsBox = new JComboBox<>(new String[]{"Sort by Name", "Sort by Album", "Sort by Artist"});
        JButton sort = new JButton("Sort");

        controlPanel.add(new JLabel("Name:"));
        controlPanel.add(t1);
        controlPanel.add(new JLabel("Album:"));
        controlPanel.add(t2);
        controlPanel.add(new JLabel("Artist:"));
        controlPanel.add(t3);
        controlPanel.add(addSong);
        controlPanel.add(optionsBox);
        controlPanel.add(sort);
        controlPanel.add(shuffle);

        add(controlPanel, BorderLayout.NORTH);
        // makes it scrollable if playlist extends past.
        add(new JScrollPane(panel), BorderLayout.CENTER);

        // update the panel with initial songs
        updatePanel();

        // add buttons and their actions
        addSong.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = t1.getText().trim();
                String album = t2.getText().trim();
                String artist = t3.getText().trim();
                if (!name.isEmpty() && !album.isEmpty() && !artist.isEmpty()) {
                    playlist.add(new Song(name, album, artist));
                    t1.setText("");
                    t2.setText("");
                    t3.setText("");
                    updatePanel();
                }
            }
        });
        //sorts
        sort.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedCriteria = (String) optionsBox.getSelectedItem();
                //sorts based on option from the dropdown box added. 
                switch (selectedCriteria) {
                    case "Sort by Name":
                        playlist.sort(Comparator.comparing(Song::getName));
                        break;
                    case "Sort by Album":
                        playlist.sort(Comparator.comparing(Song::getAlbum));
                        break;
                    case "Sort by Artist":
                        playlist.sort(Comparator.comparing(Song::getArtist));
                        break;
                }
                updatePanel();
            }
        });
        //shuffle
        shuffle.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                playlist.shuffle();
                updatePanel();
            }
        });
    }





    //This method is used to draw the delete buttons next to each song (Found off stack overflow)
    private void updatePanel() {
        panel.removeAll();  // Remove all existing components

        for (int i = 0; i < playlist.size(); i++) {
            Song song = playlist.get(i);
            JPanel itemPanel = new JPanel();
            itemPanel.setLayout(new FlowLayout(FlowLayout.LEFT));

            // Display song position and details
            JLabel label = new JLabel((i + 1) + ". " + song.toString());
            JButton deleteButton = new JButton("Delete");

            final int index = i; // Use a final variable for action listener

            deleteButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    playlist.remove(index);
                    updatePanel(); // Refresh the panel
                }
            });

            itemPanel.add(label);
            itemPanel.add(deleteButton);
            panel.add(itemPanel);
        }

        panel.revalidate(); // Refresh the layout
        panel.repaint();    // Redraw the panel
    }



}
