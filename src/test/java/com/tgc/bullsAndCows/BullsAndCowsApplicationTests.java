package com.tgc.bullsAndCows;

import com.tgc.bullsAndCows.model.Game;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@SpringBootTest
class BullsAndCowsApplicationTests {

	@Test
	void contextLoads() {
		System.out.println(new Game(1000, 0, new Date().getTime()));
	}
}
