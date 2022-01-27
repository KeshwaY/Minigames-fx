package projekt.server.dto;

import projekt.server.ActionType;

public class ActionDto extends AbstractDto {
    private ActionType actionType;
    private String input;

    public ActionType getActionType() {
        return actionType;
    }

    public void setActionType(ActionType actionType) {
        this.actionType = actionType;
    }

    public String getInput() {
        return input;
    }

    public void setInput(String input) {
        this.input = input;
    }
}
