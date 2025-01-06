CREATE TABLE game
(
    id           BIGINT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    right_answer INT,
    is_guessed   BOOLEAN,
    limitation   varchar,
    start_time   TIMESTAMP,
    player_id    BIGINT,
    CONSTRAINT fk_game_on_player FOREIGN KEY (player_id) REFERENCES player (id)
);