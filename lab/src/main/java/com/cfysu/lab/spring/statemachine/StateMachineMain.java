package com.cfysu.lab.spring.statemachine;

import java.util.EnumSet;

import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.action.Action;
import org.springframework.statemachine.config.StateMachineBuilder;
import org.springframework.statemachine.config.StateMachineBuilder.Builder;

/**
 * @Author canglong
 * @Date 2021/5/21
 */
public class StateMachineMain {
    public static void main(String[] args) throws Exception {

        StateMachineMain stateMachineMain = new StateMachineMain();

        StateMachine<States, Events> stateMachine = stateMachineMain.buildMachineJunction();
        stateMachine.start();
        stateMachine.sendEvent(Events.EVENT1);
        System.out.println(stateMachine.getState().getId());

        //stateMachine.sendEvent(Events.EVENT2);
        //
        //Message<Events> message = MessageBuilder.withPayload(Events.EVENT1).setHeader("key", "val").build();
        //boolean b = stateMachine.sendEvent(message);
        //States id = stateMachine.getState().getId();
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
            .event(Events.EVENT1).action(new Event1Action())
            .and()
            .withExternal()
            .source(States.STATE2).target(States.STATE1)
            .event(Events.EVENT2);

        return builder.build();
    }

    public StateMachine<States, Events> buildMachineJunction() throws Exception {

        Builder<States, Events> builder = StateMachineBuilder.builder();

        builder.configureStates()
            .withStates()
            .initial(States.STATE1)
            .states(EnumSet.allOf(States.class))
            .junction(States.STATE2);

        builder.configureTransitions()
            .withExternal().event(Events.EVENT1).action(new Event1Action()).source(States.STATE1).target(States.STATE2)
            .and()
            .withJunction().source(States.STATE2).first(States.STATE3, ctx -> true).then(States.STATE4, ctx -> false)
            .last(States.STATE2);

        return builder.build();
    }

    static enum States {
        STATE1,
        STATE2,
        STATE3,
        STATE4
    }

    static enum Events {
        EVENT1,
        EVENT2
    }

    public class Event1Action implements Action<States, Events> {

        @Override
        public void execute(StateContext<States, Events> context) {
            Message<Events> message = context.getMessage();
            System.out.println(message.getHeaders().get("key"));
        }
    }
}
