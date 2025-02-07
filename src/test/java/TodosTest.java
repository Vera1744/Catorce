import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.naming.directory.SearchResult;
import javax.swing.plaf.basic.BasicInternalFrameTitlePane;
import java.util.Arrays;

public class TodosTest {
    @Test
    public void shouldAddThreeTasksOfDifferentType() {
        SimpleTask simpleTask = new SimpleTask(5, "Позвонить родителям");

        String[] subtasks = {"Молоко", "Яйца", "Хлеб"};
        Epic epic = new Epic(55, subtasks);

        Meeting meeting = new Meeting(
                555,
                "Выкатка 3й версии приложения",
                "Приложение НетоБанка",
                "Во вторник после обеда"
        );

        Todos todos = new Todos();

        todos.add(simpleTask);
        todos.add(epic);
        todos.add(meeting);

        Task[] expected = {simpleTask, epic, meeting};
        Task[] actual = todos.findAll();
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void TestSearchSimpleTask() {
        SimpleTask simpleTask = new SimpleTask(5, "Позвонить родителям");
        boolean expected = true;
        boolean actual = simpleTask.matches("Позвонить");
        Assertions.assertEquals(expected, actual);

    }

    @Test
    public void TestSearchEpic() {
        String[] subtasks = {"Молоко", "Яйца", "Хлеб"};
        Epic epic = new Epic(55, subtasks);
        boolean expected = true;
        boolean actual = epic.matches("Яйца");
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void TestSearchMeeting() {
        Meeting meet = new Meeting(
                555,
                "Выкатка 3й версии приложения",
                "Приложение НетоБанка",
                "Во вторник после обеда");
        boolean expected = true;
        boolean actual = meet.matches("НетоБанка");
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void TestSearchShouldFindOneTask() {
        SimpleTask simpleTask = new SimpleTask(22, "Позвонить другу");
        String[] subtasks = {"Творог", "Бананы", "Сыр"};
        Epic epic = new Epic(55, subtasks);

        Todos todos = new Todos();

        todos.add(simpleTask);
        todos.add(epic);

        Task[] expected = {epic};
        Task[] actual = todos.search("Бананы");
        Assertions.assertArrayEquals(expected, actual);

    }
    @Test
    public void TestSearchNotShouldFindOneTask() {
        SimpleTask simpleTask = new SimpleTask(22, "Позвонить другу");
        String[] subtasks = {"Творог", "Бананы", "Сыр"};
        Epic epic = new Epic(55, subtasks);

        Todos todos = new Todos();

        todos.add(simpleTask);
        todos.add(epic);

        Task[] expected = {};
        Task[] actual = todos.search("Хлеб");
        Assertions.assertArrayEquals(expected, actual);

    }

}

