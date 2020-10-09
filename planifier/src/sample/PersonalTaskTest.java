package sample;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PersonalTaskTest  {

    @Test
    public void TimeRemainingTest() {
        PersonalTask testTask = null;
        try {
            testTask = new PersonalTask(0,0, "testTask", "This task is for start moment testing", "09-09-2021 15:20:45");
        } catch (WrongFormatException e) {
            e.printStackTrace();
        }
        System.out.println(testTask.timeRemaining()[0]);
    }
}