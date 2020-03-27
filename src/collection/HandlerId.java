package collection;

import java.util.HashSet;

/**
 * Класс, который производит работу с id
 */
public class HandlerId {
    private HashSet<Integer> usedId;
    private HashSet<Integer> unusedId;

    public HandlerId() {
        usedId = new HashSet<Integer>();
        unusedId = new HashSet<Integer>();
    }

    /**
     * Метод, который предоставляет свободный id
     */
    public Integer provideId() {
        Integer id;
        if(unusedId.isEmpty()) {
            id = usedId.size();
            usedId.add(usedId.size());
        }
        else {
            id = unusedId.iterator().next();
            unusedId.remove(id);
            usedId.add(id);
        }
        return id;
    }

    /**
     * Метод, который проверяет наличие id в HashSet
     */
    public boolean contains(Integer id) {
        return usedId.contains(id);
    }

    /**
     * Метод, который удаляет ненужный id
     */
    public void removeId(Integer id) {
        unusedId.add(id);
        usedId.remove(id);
    }

    /**
     * Метод, который очищает обработчик id
     */
    public void clear() {
        usedId.clear();
        unusedId.clear();
    }
}
