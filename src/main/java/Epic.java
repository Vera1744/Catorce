public class Epic extends Task {
    protected String[] subtasks = new String[0];

    public String[] getSubtasks() {
        return subtasks;
    }

    public Epic(int id, String[] subtasks) {
        super(id);
        this.subtasks = subtasks;
    }

    public void addSubtask(String subtask) {
        String[] tmp = new String[subtasks.length + 1];
        for (int i = 0; i < subtasks.length; i++) {
            tmp[i] = subtasks[i];
        }
        tmp[tmp.length - 1] = subtask;
        subtasks = tmp;
    }

    @Override
    public boolean matches(String query) {
        for (int i = 0; i < subtasks.length; i++) {
            if (subtasks[i].contains(query)) {
                return true;
            }
        } return false;
    }
}






