package duke;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * A class that encapsulates the saving/reading data for Duke.
 */
public class Storage {
    /**
     * Saves the data of the task in the list into a text file formatted.
     */
    public void saveData(TaskList list) {
        try {
            String path = new File("").getAbsoluteFile() + "/data";
            File file = new File(path);

            // create directory if not yet created
            if (!file.isDirectory()) {
                file.mkdirs();
            }

            FileWriter fw = new FileWriter(file + "/duke.txt");

            list.iterateList(x -> {
                try {
                    fw.write(x.toData() + System.lineSeparator());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return null;
            }); ;

            fw.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Reads the data from the saved file if it exists to update Duke on running.
     */
    public TaskList readData(String filepath) {
        try {
            // set-up to check if file exists
            TaskList list = new TaskList();
            String path = new File("").getAbsoluteFile() + filepath;
            File file = new File(path);

            if (!file.isFile()) {
                return list;
            }

            // read and update TaskList
            Scanner s = new Scanner(file);
            while (s.hasNext()) {
                String[] inputArray = s.nextLine().split(" \\| ");
                Task.TaskName type = Task.TaskName.getTaskType(inputArray[0]);

                list.addTask(type, inputArray[2] + type.getSplit()
                                + (type != Task.TaskName.TODO ? inputArray[3] : "")
                        , inputArray[1].equals("1"));
            }
            return list;
        } catch (IOException | DukeException e) {
            System.out.println(e.getMessage());
            return new TaskList();
        }
    }
}
