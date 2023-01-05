package com.game.controller;


import com.game.dto.DateSortFilter;
import com.game.dto.PlayerCreateOrUpdateRequest;
import com.game.dto.PlayerFilter;
import com.game.dto.PlayerResponse;
import com.game.service.PlayerService;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rest/players")
public class PlayerController {
    private final PlayerService playerService;

    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @GetMapping("/count")
    public ResponseEntity<Integer> countPlayers (PlayerFilter filter) {
        return ResponseEntity.ok(playerService.countPlayers(filter));
    }

    @GetMapping
    public ResponseEntity<List<PlayerResponse>> getPlayers( PlayerFilter filter,
                                                           DateSortFilter dateSortFilter
                                                              ) {
        return ResponseEntity.ok(playerService.getPlayers(filter, dateSortFilter));
    }

    @DeleteMapping("/{id:.+}")
    public ResponseEntity<?> deletePlayer(@PathVariable Long id) {
        playerService.deletePlayer(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{id:.+}")
    public ResponseEntity<PlayerResponse> updatePlayer(@PathVariable Long id,
                                                       @RequestBody PlayerCreateOrUpdateRequest request) {
        return ResponseEntity.ok(
                playerService.updatePlayer(id, request)
        );
    }

    @PostMapping
    public ResponseEntity<PlayerResponse> createPlayer(@RequestBody PlayerCreateOrUpdateRequest request) {
        return ResponseEntity.ok(
                playerService.createPlayer(request));

    }

    @GetMapping("{id:.+}")
    public ResponseEntity<PlayerResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(
                playerService.getById(id));
    }
}
