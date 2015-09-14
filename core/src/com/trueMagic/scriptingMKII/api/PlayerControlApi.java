package com.trueMagic.scriptingMKII.api;

import com.trueMagic.object.creature.Player;
import com.trueMagic.scripting.tasks.Task;
import com.trueMagic.scripting.tasks.Tasks;
import com.trueMagic.system.PlayerControlSystem;
import org.mozilla.javascript.ScriptableObject;

/**
 * Created by Declan Easton on 2015-08-15.
 */
public class PlayerControlApi extends ScriptableObject {

    PlayerControlSystem playerController;

    public PlayerControlApi(PlayerControlSystem playerController) {
        this.playerController = playerController;
    }

    private MoveLeft moveLeft = new MoveLeft();

    public String getClassName() {
        return "PlayerAPI";
    }

    public void jsFunction_MoveLeft() {
        Tasks.addTask(moveLeft);
    }

    public class MoveLeft extends Task {

        @Override
        public boolean gameTick(float deltaTime) {
            playerController.moveLeft();
            return true;
        }
    }

    public void jsFunction_MoveRight() {
        playerController.moveRight();
    }

    public void jsFunction_StopMove() {
        playerController.stopMove();
    }

    public  void jsFunction_Jump() {
        playerController.jump();
    }
}
