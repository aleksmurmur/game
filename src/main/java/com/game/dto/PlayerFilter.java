package com.game.dto;

import com.game.entity.Profession;
import com.game.entity.Race;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Objects;


public class PlayerFilter {


    private final String name;

    private final String title;

    private final Race race;

    private final Profession profession;

    private final Long after;

    private final Long before;

    private final Boolean banned;

    private final Integer minExperience;

    private final Integer maxExperience;

    private final Integer minLevel;

    private final Integer maxLevel;


    public PlayerFilter(String name,
                        String title,
                        Race race,
                        Profession profession,
                        Long after,
                        Long before,
                        Boolean banned,
                        Integer minExperience,
                        Integer maxExperience,
                        Integer minLevel,
                        Integer maxLevel
                        ) {
        this.name = name;
        this.title = title;
        this.race = race;
        this.profession = profession;
        this.after = after;
        this.before = before;
        this.banned = banned;
        this.minExperience = minExperience;
        this.maxExperience = maxExperience;
        this.minLevel = minLevel;
        this.maxLevel = maxLevel;
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

    public Long getAfter() {
        return after;
    }

    public Long getBefore() {
        return before;
    }

    public Boolean getBanned() {
        return banned;
    }

    public Integer getMinExperience() {
        return minExperience;
    }

    public Integer getMaxExperience() {
        return maxExperience;
    }

    public Integer getMinLevel() {
        return minLevel;
    }

    public Integer getMaxLevel() {
        return maxLevel;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PlayerFilter)) return false;
        PlayerFilter that = (PlayerFilter) o;
        return Objects.equals(getName(), that.getName()) && Objects.equals(getTitle(), that.getTitle()) && getRace() == that.getRace() && getProfession() == that.getProfession() && Objects.equals(getAfter(), that.getAfter()) && Objects.equals(getBefore(), that.getBefore()) && Objects.equals(getBanned(), that.getBanned()) && Objects.equals(getMinExperience(), that.getMinExperience()) && Objects.equals(getMaxExperience(), that.getMaxExperience()) && Objects.equals(getMinLevel(), that.getMinLevel()) && Objects.equals(getMaxLevel(), that.getMaxLevel());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getTitle(), getRace(), getProfession(), getAfter(), getBefore(), getBanned(), getMinExperience(), getMaxExperience(), getMinLevel(), getMaxLevel());
    }

    @Override
    public String toString() {
        return "PlayerFilter{" +
                "name='" + name + '\'' +
                ", title='" + title + '\'' +
                ", race=" + race +
                ", profession=" + profession +
                ", after=" + after +
                ", before=" + before +
                ", banned=" + banned +
                ", minExperience=" + minExperience +
                ", maxExperience=" + maxExperience +
                ", minLevel=" + minLevel +
                ", maxLevel=" + maxLevel +
                '}';
    }
}
