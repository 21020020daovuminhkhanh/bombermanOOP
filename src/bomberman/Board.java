package bomberman;

import bomberman.Entity.Entity;

import java.util.ArrayList;
import java.util.List;

public class Board {
    private List<Entity> entities = new ArrayList<>();

    public Board() {

    }

    public void addEntity(Entity entity) {
        entities.add(entity);
    }
}
