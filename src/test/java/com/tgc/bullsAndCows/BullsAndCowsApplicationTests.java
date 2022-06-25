package com.tgc.bullsAndCows;

import com.tgc.bullsAndCows.entity.Game;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

@SpringBootTest
class BullsAndCowsApplicationTests {

	@Test
	void contextLoads() {
		System.out.println(new Game(1000, 0, new Date().getTime()));
	}
}
