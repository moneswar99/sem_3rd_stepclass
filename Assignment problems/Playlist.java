import java.util.Arrays;

public class Playlist {
    private final String[] songs;
    private int songCount;

    public Playlist(int maximumSongs) {
        if (maximumSongs < 0) {
            throw new IllegalArgumentException("Maximum song count cannot be negative.");
        }
        songs = new String[maximumSongs];
    }

    public boolean addSong(String title) {
        if (title == null || title.trim().isEmpty()) {
            throw new IllegalArgumentException("Song title cannot be blank.");
        }
        if (songCount == songs.length) {
            return false;
        }
        songs[songCount++] = title;
        return true;
    }

    public String[] getSongs() {
        return Arrays.copyOf(songs, songCount);
    }

    public int getSongCount() {
        return songCount;
    }

    public static void main(String[] args) {
        Playlist playlist = new Playlist(10);
        playlist.addSong("Song A");
        playlist.addSong("Song B");
        String[] copy = playlist.getSongs();
        copy[0] = "Hacked";
        System.out.println("Playlist songs: " + Arrays.toString(playlist.getSongs()));
        System.out.println("Song count: " + playlist.getSongCount());
    }
}
