package com.game.dto;

import com.game.entity.Profession;
import com.game.entity.Race;

import java.util.Date;
import java.util.Objects;

public class PlayerResponse {

    private final Long id;

    private final String name;

    private final String title;

    private final Race race;

    private final Profession profession;

    private final Date birthday;

    private final Boolean banned;

    private final Integer experience;

    private final Integer level;

    private final Integer untilNextLevel;


    public PlayerResponse(Long id, String name, String title, Race race, Profession profession, Date birthday, Boolean banned, Integer experience, Integer level, Integer untilNextLevel) {
        this.id = id;
        this.name = name;
        this.title = title;
        this.race = race;
        this.profession = profession;
        this.birthday = birthday;
        this.banned = banned;
        this.experience = experience;
        this.level = level;
        this.untilNextLevel = untilNextLevel;
    }


    public Long getId() {
        return id;
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

    public Date getBirthday() {
        return birthday;
    }

    public Boolean getBanned() {
        return banned;
    }

    public Integer getExperience() {
        return experience;
    }

    public Integer getLevel() {
        return level;
    }

    public Integer getUntilNextLevel() {
        return untilNextLevel;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PlayerResponse)) return false;
        PlayerResponse response = (PlayerResponse) o;
        return getId().equals(response.getId()) && getName().equals(response.getName()) && getTitle().equals(response.getTitle()) && getRace() == response.getRace() && getProfession() == response.getProfession() && getBirthday().equals(response.getBirthday()) && getBanned().equals(response.getBanned()) && getExperience().equals(response.getExperience()) && getLevel().equals(response.getLevel()) && getUntilNextLevel().equals(response.getUntilNextLevel());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getTitle(), getRace(), getProfession(), getBirthday(), getBanned(), getExperience(), getLevel(), getUntilNextLevel());
    }

    @Override
    public String toString() {
        return "PlayerResponse{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", title='" + title + '\'' +
                ", race=" + race +
                ", profession=" + profession +
                ", birthday=" + birthday +
                ", banned=" + banned +
                ", experience=" + experience +
                ", level=" + level +
                ", untilNextLevel=" + untilNextLevel +
                '}';
    }
}
