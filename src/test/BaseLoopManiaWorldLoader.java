package test;

import unsw.loopmania.Character;
import unsw.loopmania.LoopManiaWorldLoader;
import unsw.loopmania.PathTile;

import java.io.FileNotFoundException;

public class BaseLoopManiaWorldLoader extends LoopManiaWorldLoader {
    public BaseLoopManiaWorldLoader(String filename) throws FileNotFoundException {
        super(filename);
    }

    @Override
    public void onLoad(Character character) {

    }

    @Override
    public void onLoad(PathTile pathTile, PathTile.Direction into, PathTile.Direction out) {

    }
}
