package com.game.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.game.entity.Profession;
import com.game.entity.Race;
import org.springframework.lang.NonNull;

import java.util.Objects;


public class PlayerCreateOrUpdateRequest {


    private final String name;

    private final String title;

    private final Race race;

    private final Profession profession;

    private final Long birthday;

    private final Boolean banned;

    private final Integer experience;

@JsonCreator
    public PlayerCreateOrUpdateRequest(@JsonProperty("name") String name,
                                       @JsonProperty("title") String title,
                                       @JsonProperty("race") Race race,
                                       @JsonProperty("profession") Profession profession,
                                       @JsonProperty("birthday") Long birthday,
                                       @JsonProperty("banned") Boolean banned,
                                       @JsonProperty("experience") Integer experience) {
        this.name = name;
        this.title = title;
        this.race = race;
        this.profession = profession;
        this.birthday = birthday;
        this.banned = banned != null && banned;
        this.experience = experience;
    }


    public String getName() {
        return name;
    }

    public String getTitle() {
        return title;
    }

    public Race getRace() {
        return race;
    }

    public Profession getProfession() {
        return profession;
    }

    public Long getBirthday() {
        return birthday;
    }

    public Boolean getBanned() {
        return banned;
    }

    public Integer getExperience() {
        return experience;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PlayerCreateOrUpdateRequest)) return false;
        PlayerCreateOrUpdateRequest that = (PlayerCreateOrUpdateRequest) o;
        return getName().equals(that.getName()) && getTitle().equals(that.getTitle()) && getRace() == that.getRace() && getProfession() == that.getProfession() && getBirthday().equals(that.getBirthday()) && getBanned().equals(that.getBanned()) && getExperience().equals(that.getExperience());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getTitle(), getRace(), getProfession(), getBirthday(), getBanned(), getExperience());
    }

    @Override
    public String toString() {
        return "PlayerCreateOrUpdateRequest{" +
                "name='" + name + '\'' +
                ", title='" + title + '\'' +
                ", race=" + race +
                ", profession=" + profession +
                ", birthday=" + birthday +
                ", banned=" + banned +
                ", experience=" + experience +
                '}';
    }
}
