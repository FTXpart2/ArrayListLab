
public class Song {
    private String name;
    private String artist;
    private String album;
    public Song(String x, String y, String d){
        name = x;
        album = y;
        artist = d;
    }
    public String getName(){
        return name;
    }
    public String getArtist(){
        return artist;
    }
    public String getAlbum(){
        return album;
    }
    public String toString(){
        return name + " by " + artist + " (Album: " + album + ")";
    }


    public boolean equals(Object o) {
        Song obj = (Song) o;
        if (obj.getName().equals(name)) {
          if (obj.getArtist() == artist) {
            if(obj.getAlbum() == album){
                return true;
            }
          }
        }
        return false;
      }
}