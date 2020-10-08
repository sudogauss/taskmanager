package sample;

import java.lang.String;
import java.lang.Exception;
import java.lang.invoke.WrongMethodTypeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

class WrongFormatException extends Exception {
    public WrongFormatException() {
        super();
    }
    public WrongFormatException(String s) {
        super(s);
    }
}

public abstract class GlobalTask {
    private int id; // use for database as primary key, possible to get
    private float importance; // the key-concept in task management, can be modified
    private String description;
    private String theme; // description
    private String deadline; // task ends
    private String start; // task begins
    private boolean done; // true if task is done
    private char digits[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};

    protected boolean inDigits(char symbol) {
        for(int i=0; i<10; i++) {
            if(symbol == digits[i]) return true;
        }
        return false;
    }

    protected boolean checkFormat(String date) {
        if(date.length() != 19) {
            return false;
        }
        for(int i=0; i < date.length(); i++) {
            switch (i) {
                case 2:
                case 5:
                    if(date.charAt(i) != '-') return false;
                case 10:
                    if(date.charAt(i) != ' ') return false;
                case 13:
                case 16:
                    if(date.charAt(i) != ':') return false;
                default:
                    if(! inDigits(date.charAt(i)) ) return false;
            }
        }
        return true;
    }

    public GlobalTask(int id, float importance, String theme, String description, String deadline) throws WrongFormatException {
        this.id = id;
        this.importance = importance;
        this.theme = theme;
        this.description = description;
        if(checkFormat(deadline)) this.deadline = deadline;
        else {
            throw new WrongFormatException("You use wrong format");
        }
        LocalDateTime nowInstant = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        this.start = nowInstant.format(formatter);
        this.done = false;

    }

    public boolean isFinished() {
        return done;
    }

    public void finish() {
        this.done  = true;
    }


    public int getId() {
        return this.id;
    }

    public float getImportance() {
        return this.importance;
    }

    public void setImportance(float importanceValue) {
        this.importance = importanceValue;
    }

    public void timeRemaining() {

    }
}
