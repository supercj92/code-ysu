package com.cfysu.cli;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

/**
 * @Author canglong
 * @Date 2022/1/27
 */
@ShellComponent
public class CLIMain {
    @ShellMethod("Add two integers together.")
    public int add(int a, int b) {
        return a + b;
    }
}
