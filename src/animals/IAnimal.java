package animals;

import graphics.IMoveable;

public interface IAnimal extends IMoveable {
    public boolean eat(int energy);
}
