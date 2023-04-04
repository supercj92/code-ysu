package com.cfysu.cli;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Author canglong
 * @Date 2022/1/27
 */
@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
        int[][] twoDArrays = new int[2][2];
        twoDArrays[0][0] = 1;
        twoDArrays[0][1] = 2;

        int[][][] threeDArrays = new int[3][3][3];
        threeDArrays[0][0][0] = 1;
        threeDArrays[0][0][1] = 2;

        int[][][] ns = {
            {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
            },
            {
                {10, 11},
                {12, 13}
            },
            {
                {14, 15, 16},
                {17, 18}
            }
        };
    }
}
