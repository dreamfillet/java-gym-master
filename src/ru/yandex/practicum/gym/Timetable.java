package ru.yandex.practicum.gym;

import java.util.*;

public class Timetable {

    private Map<DayOfWeek, TreeMap<TimeOfDay, List<TrainingSession>>> timetable = new HashMap<>(); // ключ день недели, значение название тренировки

    public void addNewTrainingSession(TrainingSession trainingSession) {
        DayOfWeek day = trainingSession.getDayOfWeek();
        TimeOfDay timeOfDay = trainingSession.getTimeOfDay();
        TreeMap<TimeOfDay, List<TrainingSession>> daysTable = timetable.get(day);
        if (daysTable == null) {
            daysTable = new TreeMap<>();
            timetable.put(day, daysTable);
        }

        List<TrainingSession> trainingSessions = daysTable.get(timeOfDay);
        if (trainingSessions == null) {
            trainingSessions = new ArrayList<>();
            daysTable.put(timeOfDay, trainingSessions);
        }

        trainingSessions.add(trainingSession);
    }

    public List<TrainingSession> getTrainingSessionsForDay(DayOfWeek dayOfWeek) {
        TreeMap<TimeOfDay, List<TrainingSession>> sortedTimetable = timetable.get(dayOfWeek);
        if (sortedTimetable == null) {
            return Collections.emptyList();
        } else {
            List<TrainingSession> trainingSessionList = new ArrayList<>();
            for (List<TrainingSession> sessions : sortedTimetable.values()) {
                trainingSessionList.addAll(sessions);
            }
            return trainingSessionList;
        }

    }

    public List<TrainingSession> getTrainingSessionsForDayAndTime(DayOfWeek dayOfWeek, TimeOfDay timeOfDay) {
        TreeMap<TimeOfDay, List<TrainingSession>> sortedTimetable = timetable.get(dayOfWeek);
        if (sortedTimetable == null) {
            return Collections.emptyList();
        } else {
            return sortedTimetable.get(timeOfDay);
        }
    }

    public Long getCountByCoach(Coach coach) {
        long count = 0;

        for (TreeMap<TimeOfDay, List<TrainingSession>> dayTable : timetable.values()) {
            for (List<TrainingSession> trainingSessions : dayTable.values()) {
                for (TrainingSession session : trainingSessions) {
                    if (session.getCoach().equals(coach)) {
                        count++;
                    }
                }
            }
        }
        return count;
    }

    public List<CounterOfTrainings> getCountByCoaches() {
        Map<Coach, Long> coachCountMap = new HashMap<>();

        for (TreeMap<TimeOfDay, List<TrainingSession>> trainingTable : timetable.values()) {
            for (List<TrainingSession> trainingSessions : trainingTable.values()) {
                for (TrainingSession session : trainingSessions) {
                    Coach coach = session.getCoach();
                    coachCountMap.put(coach, coachCountMap.getOrDefault(coach, 0L) + 1);
                }
            }
        }

        List<CounterOfTrainings> result = new ArrayList<>();
        for (Map.Entry<Coach, Long> entry : coachCountMap.entrySet()) {
            result.add(new CounterOfTrainings(entry.getKey(), entry.getValue()));
        }

        Collections.sort(result);
        return result;
    }

}

