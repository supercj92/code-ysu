package com.cfysu.statemachine;

import java.util.EnumSet;

import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.config.StateMachineBuilder;
import org.springframework.statemachine.config.StateMachineBuilder.Builder;

/**
 * @Author canglong
 * @Date 2021/5/21
 */
public class StateMachineMain {
    public static void main(String[] args) throws Exception {

        StateMachineMain stateMachineMain = new StateMachineMain();

        StateMachine<States, Events> stateMachine = stateMachineMain.buildMachine();
        stateMachine.start();
        stateMachine.sendEvent(Events.EVENT1);
        stateMachine.sendEvent(Events.EVENT2);
    }

    public StateMachine<States, Events> buildMachine() throws Exception {
        Builder<States, Events> builder = StateMachineBuilder.builder();

        builder.configureStates()
            .withStates()
            .initial(States.STATE1)
            .states(EnumSet.allOf(States.class));

        builder.configureTransitions()
            .withExternal()
            .source(States.STATE1).target(States.STATE2)
            .event(Events.EVENT1)
            .and()
            .withExternal()
            .source(States.STATE2).target(States.STATE1)
            .event(Events.EVENT2);

        return builder.build();
    }

    static enum States {
        STATE1, STATE2
    }

    static enum Events {
        EVENT1, EVENT2
    }
}
