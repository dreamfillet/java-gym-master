package ru.yandex.practicum.gym;

public class CounterOfTrainings implements Comparable<CounterOfTrainings> {
    private Coach coach;
    private long trainingCount;

    public CounterOfTrainings(Coach coach, long trainingCount) {
        this.coach = coach;
        this.trainingCount = trainingCount;
    }

    @Override
    public int compareTo(CounterOfTrainings o) {
       int countCompare =  Long.compare(o.trainingCount, this.trainingCount);
       if (countCompare != 0) {
            return countCompare;
        }
        return this.coach.getSurname().compareTo(o.coach.getSurname());
    }

    public Coach getCoach() {
        return coach;
    }

    public long getTrainingCount() {
        return trainingCount;
    }

    @Override
    public String toString() {
        return "Тренер {" +
                "Фамилия ='" + coach.getSurname() + '\'' +
                ", Имя ='" + coach.getName() + '\'' +
                ", Отчество ='" + coach.getMiddleName() + '\'' +
                " Количество занятий: " + getTrainingCount() +
                '}';
    }
}
