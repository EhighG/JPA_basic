package hellojpa;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
//@DiscriminatorValue("A") // 기본값은 엔티티명이나, 필요 시 바꿀 수 있다.
public class Album extends Item {

    private String artist;

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }
}
