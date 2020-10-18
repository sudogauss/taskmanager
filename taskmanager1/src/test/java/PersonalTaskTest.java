import org.example.GlobalTask;
import org.example.PersonalTask;
import org.junit.jupiter.api.Test;

public class PersonalTaskTest {
    @Test // shows remaining time and test deadline < now case and exception test
    public void testTimeRemaining() {
        PersonalTask testTask1 = null;
        PersonalTask testTask2 = null;
        PersonalTask testTask3 = null;
        PersonalTask testTask4 = null;
        try {
             testTask1 = new PersonalTask(0,0,"test","testTask", "09-09-2021 15:35:41", "tester");
             testTask2 = new PersonalTask(0,0,"test","testTask", "09-09-3890 09:01:40", "tester");
             testTask3 = new PersonalTask(0,0,"test","testTask", "09-09-2000 15:38:41", "tester");
        } catch (GlobalTask.WrongFormatException e) {
            e.printStackTrace();
        }
        long[] date1 = testTask1.timeRemaining();
        System.out.println(date1[0] + " " + date1[1] + " " + date1[2] + " " + date1[3]);
        long[] date2 = testTask2.timeRemaining();
        System.out.println(date2[0] + " " + date2[1] + " " + date2[2] + " " + date2[3]);
        long[] date3 = testTask3.timeRemaining();
        System.out.println(date3[0] + " " + date3[1] + " " + date3[2] + " " + date3[3]);
        try {
            testTask4 = new PersonalTask(0,0,"test","testTask", "09-09:2000 15:38:41", "tester");
        } catch (GlobalTask.WrongFormatException e) {
            e.printStackTrace();
        }
    }

    @Test // shows
    public void testStart() {
        PersonalTask testStartTask = null;
        try {
             testStartTask = new PersonalTask(0,0,"test","testTask","01-01-2000 15:35:35", "tester");
        } catch (GlobalTask.WrongFormatException e) {
            e.printStackTrace();
        }
        System.out.println(testStartTask.getStart());
    }
}
