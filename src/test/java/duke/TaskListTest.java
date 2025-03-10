package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.task.TaskName;

public class TaskListTest {
    @Test
    public void addTaskTest() {
        try {
            TaskList list = new TaskList();
            list.addTask(TaskName.TODO, "Task1");
            list.addTask(TaskName.EVENT, "Task2 /at Tuesday");
            list.addTask(TaskName.DEADLINE, "Task3 /by Next monday");
            assertEquals(list.displayTask(), "\tHere are the tasks in your list:\n"
                    + "\t1.[T][ ] Task1\n"
                    + "\t2.[E][ ] Task2 (at: Tuesday)\n"
                    + "\t3.[D][ ] Task3 (by: Next monday)\n");
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void deleteTaskTest() {
        try {
            TaskList list = new TaskList();
            list.addTask(TaskName.TODO, "Task1");
            list.addTask(TaskName.EVENT, "Task2 /at Tuesday");
            list.addTask(TaskName.DEADLINE, "Task3 /by Next monday");
            list.deleteTask("1");
            list.deleteTask("1");
            list.deleteTask("1");
            assertEquals(list.displayTask(), "\tHere are the tasks in your list:\n");
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void markTaskTest() {
        try {
            TaskList list = new TaskList();
            list.addTask(TaskName.TODO, "Task1");
            list.addTask(TaskName.EVENT, "Task2 /at Tuesday");
            list.addTask(TaskName.DEADLINE, "Task3 /by Next monday");
            list.markTask("1");
            list.markTask("2");
            list.markTask("3");
            assertEquals(list.displayTask(), "\tHere are the tasks in your list:\n"
                    + "\t1.[T][X] Task1\n"
                    + "\t2.[E][X] Task2 (at: Tuesday)\n"
                    + "\t3.[D][X] Task3 (by: Next monday)\n");
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
    }
}
