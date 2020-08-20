package ru.graduation.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.LocalTime;

@Component
public class DeadLineTime {

    /**
     * User can change his made vote before deadLine
     */
    public static LocalTime deadLine;

    @Value("#{ T(java.time.LocalTime).parse('${deadLine}', T(java.time.format.DateTimeFormatter).ofPattern('HH:mm'))}")
    public void setDeadLine(LocalTime deadLine) {
        this.deadLine = deadLine;
    }
}
