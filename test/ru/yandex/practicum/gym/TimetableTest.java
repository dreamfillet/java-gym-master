package ru.yandex.practicum.gym;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TimetableTest {

    @Test
    void testGetTrainingSessionsForDaySingleSession() {
        Timetable timetable = new Timetable();

        Group group = new Group("Акробатика для детей", Age.CHILD, 60);
        Coach coach = new Coach("Васильев", "Николай", "Сергеевич");
        TrainingSession singleTrainingSession = new TrainingSession(group, coach,
                DayOfWeek.MONDAY, new TimeOfDay(13, 0));

        timetable.addNewTrainingSession(singleTrainingSession);

        List<TrainingSession> mondaySession = timetable.getTrainingSessionsForDay(DayOfWeek.MONDAY);
        assertEquals(1, mondaySession.size(), "В понедельник одно занятие");
        for(TrainingSession session : mondaySession){
            System.out.println("Понедельник: " + session.getTimeOfDay() + "," + session.getGroup());
        }
        List<TrainingSession> tuesdaySession = timetable.getTrainingSessionsForDay(DayOfWeek.TUESDAY);
        assertEquals(0, tuesdaySession.size(), "Во вторник нет занятий");

    }

    @Test
    void testGetTrainingSessionsForDayMultipleSessions() {
        Timetable timetable = new Timetable();

        Coach coach = new Coach("Васильев", "Николай", "Сергеевич");

        Group groupAdult = new Group("Акробатика для взрослых", Age.ADULT, 90);
        TrainingSession thursdayAdultTrainingSession = new TrainingSession(groupAdult, coach,
                DayOfWeek.THURSDAY, new TimeOfDay(20, 0));

        timetable.addNewTrainingSession(thursdayAdultTrainingSession);

        Group groupChild = new Group("Акробатика для детей", Age.CHILD, 60);
        TrainingSession mondayChildTrainingSession = new TrainingSession(groupChild, coach,
                DayOfWeek.MONDAY, new TimeOfDay(13, 0));
        TrainingSession thursdayChildTrainingSession = new TrainingSession(groupChild, coach,
                DayOfWeek.THURSDAY, new TimeOfDay(13, 0));
        TrainingSession saturdayChildTrainingSession = new TrainingSession(groupChild, coach,
                DayOfWeek.SATURDAY, new TimeOfDay(10, 0));

        timetable.addNewTrainingSession(mondayChildTrainingSession);
        timetable.addNewTrainingSession(thursdayChildTrainingSession);
        timetable.addNewTrainingSession(saturdayChildTrainingSession);

        List<TrainingSession> mondaySession = timetable.getTrainingSessionsForDay(DayOfWeek.MONDAY);
        assertEquals(1, mondaySession.size(), "В понедельник одно занятие");
        for(TrainingSession session : mondaySession){
            System.out.println("Понедельник: " + session.getTimeOfDay() + "," + session.getGroup());
        }
        List<TrainingSession> thursdaySession = timetable.getTrainingSessionsForDay(DayOfWeek.THURSDAY);
        assertEquals(2, thursdaySession.size(), "В четверг два занятия");
        for(TrainingSession session : thursdaySession){
            System.out.println("Четверг: " + session.getTimeOfDay() + "," + session.getGroup());
        }
        List<TrainingSession> tuesdaySession = timetable.getTrainingSessionsForDay(DayOfWeek.THURSDAY);
        assertEquals(2, tuesdaySession.size(), "Во вторник нет занятий");
        List<TrainingSession> saturdaySession = timetable.getTrainingSessionsForDay(DayOfWeek.SATURDAY);
        assertEquals(1, saturdaySession.size(), "В субботу одно занятие");
        for(TrainingSession session : saturdaySession){
            System.out.println("Суббота: " + session.getTimeOfDay() + "," + session.getGroup());
        }
    }

    @Test
    void testGetTrainingSessionsForDayAndTime() {
        Timetable timetable = new Timetable();

        Group group = new Group("Акробатика для детей", Age.CHILD, 60);
        Coach coach = new Coach("Васильев", "Николай", "Сергеевич");
        TimeOfDay timeOfDay = new TimeOfDay(13, 0);
        TrainingSession trainingSession = new TrainingSession(group, coach,
                DayOfWeek.MONDAY, timeOfDay);

        timetable.addNewTrainingSession(trainingSession);

        List<TrainingSession> mondaySession = timetable.getTrainingSessionsForDayAndTime(DayOfWeek.MONDAY, timeOfDay);
        assertEquals(1, mondaySession.size(), "В понедельник одно занятие");
        for(TrainingSession session : mondaySession){
            System.out.println("Понедельник: " + session.getTimeOfDay() + "," + session.getGroup());
        }
    }

    @Test
    void testGetTrainingSessionsForDayAndTimeMultipleSessions() {
        Timetable timetable = new Timetable();

        Coach coach = new Coach("Васильев", "Николай", "Сергеевич");
        Group groupAdult = new Group("Акробатика для взрослых", Age.ADULT, 90);
        TimeOfDay timeOfThursday1 = new TimeOfDay(20, 0);
        TrainingSession thursdayAdultTrainingSession = new TrainingSession(groupAdult, coach,
                DayOfWeek.THURSDAY, timeOfThursday1);

        timetable.addNewTrainingSession(thursdayAdultTrainingSession);

        Group groupChild = new Group("Акробатика для детей", Age.CHILD, 60);
        TimeOfDay timeOfMonday = new TimeOfDay(13, 0);
        TrainingSession mondayChildTrainingSession = new TrainingSession(groupChild, coach,
                DayOfWeek.MONDAY, timeOfMonday);
        TrainingSession thursdayChildTrainingSession = new TrainingSession(groupChild, coach,
                DayOfWeek.THURSDAY, timeOfThursday1);
        TimeOfDay timeOfSaturday= new TimeOfDay(10, 0);
        TrainingSession saturdayChildTrainingSession = new TrainingSession(groupChild, coach,
                DayOfWeek.SATURDAY, timeOfSaturday);

        timetable.addNewTrainingSession(mondayChildTrainingSession);
        timetable.addNewTrainingSession(thursdayChildTrainingSession);
        timetable.addNewTrainingSession(saturdayChildTrainingSession);

        List<TrainingSession> mondaySession = timetable.getTrainingSessionsForDayAndTime(DayOfWeek.MONDAY, timeOfMonday);
        assertEquals(1, mondaySession.size(), "В понедельник одно занятие");
        for(TrainingSession session : mondaySession){
            System.out.println("Понедельник: " + session.getTimeOfDay() + "," + session.getGroup());
        }
        List<TrainingSession> thursdaySessions = timetable.getTrainingSessionsForDayAndTime(DayOfWeek.THURSDAY,timeOfThursday1);
        assertEquals(2, thursdaySessions.size(), "В четверг два занятия");
        for(TrainingSession session : thursdaySessions){
            System.out.println("Четверг: " + session.getTimeOfDay() + "," + session.getGroup());
        }
        List<TrainingSession> saturdaySession = timetable.getTrainingSessionsForDay(DayOfWeek.SATURDAY);
        assertEquals(1, saturdaySession.size(), "В субботу одно занятие");
        for(TrainingSession session : saturdaySession){
            System.out.println("Суббота: " + session.getTimeOfDay() + "," + session.getGroup());
        }
    }

    @Test
    void testGetCountByCoaches() {
        Timetable timetable = new Timetable();

        Coach coach1 = new Coach("Васильев", "Николай", "Сергеевич");
        Group groupAdult1 = new Group("Акробатика для взрослых", Age.ADULT, 90);


        Coach coach2 = new Coach("Иванов", "Иван", "Сергеевич");
        Group groupAdult2 = new Group("Акробатика для взрослых", Age.ADULT, 90);
        Group groupChild1 = new Group("Акробатика для детей", Age.CHILD, 30);

        Coach coach3 = new Coach("Петров", "Петр", "Сергеевич");
        Group groupAdult3 = new Group("Акробатика для взрослых", Age.ADULT, 90);
        Group groupChild2 = new Group("Акробатика для детей", Age.CHILD, 30);
        Group groupChild3 = new Group("Бассеин для детей", Age.CHILD, 30);

        TrainingSession TrainingSession1 = new TrainingSession(groupAdult1, coach1,
                DayOfWeek.MONDAY, new TimeOfDay(20, 0));
        TrainingSession TrainingSession2 = new TrainingSession(groupAdult2, coach2,
                DayOfWeek.TUESDAY, new TimeOfDay(10, 0));
        TrainingSession TrainingSession3 = new TrainingSession(groupChild1, coach2,
                DayOfWeek.TUESDAY, new TimeOfDay(11, 0));
        TrainingSession TrainingSession4 = new TrainingSession(groupAdult3, coach3,
                DayOfWeek.THURSDAY, new TimeOfDay(20, 0));
        TrainingSession TrainingSession5 = new TrainingSession(groupChild2, coach3,
                DayOfWeek.THURSDAY, new TimeOfDay(12, 0));
        TrainingSession TrainingSession6 = new TrainingSession(groupChild3, coach3,
                DayOfWeek.THURSDAY, new TimeOfDay(13, 0));

        timetable.addNewTrainingSession(TrainingSession1);
        timetable.addNewTrainingSession(TrainingSession2);
        timetable.addNewTrainingSession(TrainingSession3);
        timetable.addNewTrainingSession(TrainingSession4);
        timetable.addNewTrainingSession(TrainingSession5);
        timetable.addNewTrainingSession(TrainingSession6);

        System.out.println("Список тренеров и их количества занятий:" + timetable.getCountByCoaches());

        Long coach1ListQuantity = timetable.getCountByCoach(coach1);
        assertEquals(1, coach1ListQuantity);
        Long coach2ListQuantity = timetable.getCountByCoach(coach2);
        assertEquals(2, coach2ListQuantity);
        Long coach3ListQuantity = timetable.getCountByCoach(coach3);
        assertEquals(3, coach3ListQuantity);

    }

}
