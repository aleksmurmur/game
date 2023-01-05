package com.game.service;

import com.game.dto.DateSortFilter;
import com.game.dto.PlayerCreateOrUpdateRequest;
import com.game.dto.PlayerFilter;
import com.game.dto.PlayerResponse;
import com.game.entity.Player;
import com.game.entity.Profession;
import com.game.exceptions.FieldValidationException;
import com.game.exceptions.IdValidationException;
import com.game.exceptions.PlayerNotFoundException;
import com.game.repository.PlayerRepository;


import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.function.LongPredicate;
import java.util.stream.Collectors;

@Service
public class PlayerService {


    private PlayerRepository playerRepository;


    public PlayerService(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;

    }

    public Integer countPlayers(PlayerFilter filter) {
        return playerRepository.findAll(playersWithFilter(filter)).size();
    }

    public List<PlayerResponse> getPlayers(PlayerFilter filter, DateSortFilter dateSortFilter) {

         return playerRepository.findAll(
                 playersWithFilter(filter),
                 PageRequest.of(dateSortFilter.getPageNumber(),
                                 dateSortFilter.getPageSize(),
                                 Sort.by(dateSortFilter.getOrder().getFieldName())))
                 .stream()
                 .map(this::convertToDto)
                 .collect(Collectors.toList());
    }

    public void deletePlayer(Long id) {
        validateId(id);
        playerRepository.delete(getPlayer(id));
    }

    public PlayerResponse updatePlayer(Long id, PlayerCreateOrUpdateRequest request) {
        validateId(id);
        Player player = getPlayer(id);
        validateRequest(request);
        if (request.getName() != null) player.setName(request.getName());
        if (request.getTitle() != null) player.setTitle(request.getTitle());
        if (request.getRace() != null) player.setRace(request.getRace());
        if (request.getProfession() != null) player.setProfession(request.getProfession());
        if (request.getBirthday() != null) player.setBirthday(new Date(request.getBirthday()));
        if (request.getBanned() != null) player.setBanned(request.getBanned());
        if (request.getExperience() != null) {
            player.setExperience(request.getExperience());
            player.setLevel(countLevel(request.getExperience()));
            player.setUntilNextLevel(countExpToNextLevel(request.getExperience()));
        }
        return convertToDto(playerRepository.save(player));


    }

    public PlayerResponse createPlayer(PlayerCreateOrUpdateRequest request) {
        nullCheck(request);
        validateRequest(request);
        Player player = convertToEntity(request);
        return convertToDto(playerRepository.save(player));
    }

    public PlayerResponse getById(Long id) {
        validateId(id);
        Player player = getPlayer(id);
        return convertToDto(player);
    }

    private void nullCheck(PlayerCreateOrUpdateRequest request) {

        if (request.getName() == null || request.getTitle() == null
                || request.getProfession() == null || request.getRace() == null
                || request.getBirthday() == null || request.getExperience() == null) throw new FieldValidationException(
                "Fields name, title, race, profession, birthday and experience should not be null");
    }

    private void validateId(Long id) {
        if (id <= 0 ) throw new IdValidationException("Id must be positive");

    }

    private void validateRequest(PlayerCreateOrUpdateRequest request) {

        if (request.getName() != null && request.getName().length() > 12 ) {
            throw new FieldValidationException("Name should not be longer 12 symbols");
        }
        if (request.getTitle() != null && request.getTitle().length() > 30)  throw new FieldValidationException("Title should not be longer 30 symbols");
        if (request.getName() != null && request.getName().isEmpty()) throw new FieldValidationException("Name should not be empty");
        if (request.getExperience() != null && (request.getExperience() < 0 || request.getExperience() > 10000000))
            throw new FieldValidationException("Experience should be in 0...10,000,000");
        if (request. getBirthday() != null) {
            if (request.getBirthday() < 0) throw new FieldValidationException("Birthday millis should not be negative)");
            DateFormat obj = new SimpleDateFormat("yyyy");
            int birthYear = Integer.parseInt(obj.format(new Date(request.getBirthday())));
            if (birthYear < 2000 || birthYear > 3000)
                throw new FieldValidationException("Birthday date should be in 2000...3000 years");
        }
    }

    private Player convertToEntity(PlayerCreateOrUpdateRequest request) {
        return new Player(
                request.getName(),
                request.getTitle(),
                request.getRace(),
                request.getProfession(),
                new Date(request.getBirthday()),
                request.getBanned(),
                request.getExperience(),
                countLevel(request.getExperience()),
                countExpToNextLevel(request.getExperience())
        );
    }

    private Integer countLevel(Integer exp) {
        Double level = ((Math.sqrt(2500 + 200 * exp) - 50) / 100);
        return level.intValue();
    }

    private Integer countExpToNextLevel(Integer exp) {
        return (50 * (countLevel(exp) + 1) * (countLevel(exp) + 2) - exp);
    }


    private PlayerResponse convertToDto(Player player) {
        return new PlayerResponse(
                player.getId(),
                player.getName(),
                player.getTitle(),
                player.getRace(),
                player.getProfession(),
                player.getBirthday(),
                player.getBanned(),
                player.getExperience(),
                player.getLevel(),
                player.getUntilNextLevel()
        );
    }

    private Player getPlayer(Long id) {
        return playerRepository.findById(id)
                .orElseThrow(() ->
                        new PlayerNotFoundException(String.format("Player with %s not found", id)));
    }

    private Specification<Player> playersWithFilter(PlayerFilter filter) {

        return (root, cq, cb) -> {
                if (Objects.isNull(filter)) return cb.conjunction();
                List<Predicate> predicates = new ArrayList<>();
                if (filter.getName() != null) predicates.add(cb.like(root.get("name"), "%" + filter.getName() + "%"));
                if (filter.getTitle() != null) predicates.add(cb.like(root.get("title"), "%" + filter.getTitle() + "%"));
                if (filter.getRace() != null) predicates.add(cb.equal(root.get("race"), filter.getRace()));
                if (filter.getProfession() != null) predicates.add(cb.equal(root.get("profession"), filter.getProfession()));
                if (filter.getAfter() != null) predicates.add(cb.greaterThanOrEqualTo(root.get("birthday"), new Date(filter.getAfter())));
                if (filter.getBefore() != null) predicates.add(cb.lessThanOrEqualTo(root.get("birthday"), new Date(filter.getBefore())));
                if (filter.getBanned() != null) predicates.add(cb.equal(root.get("banned"), filter.getBanned()));
                if (filter.getMinExperience() != null) predicates.add(cb.greaterThanOrEqualTo(root.get("experience"), filter.getMinExperience()));
                if (filter.getMaxExperience() != null) predicates.add(cb.lessThanOrEqualTo(root.get("experience"), filter.getMaxExperience()));
                if (filter.getMinLevel() != null) predicates.add(cb.greaterThanOrEqualTo(root.get("level"), filter.getMinLevel()));
                if (filter.getMaxLevel() != null) predicates.add(cb.lessThanOrEqualTo(root.get("level"), filter.getMaxLevel()));

                return cb.and(predicates.toArray(new Predicate[0]));



        };
    }
}
