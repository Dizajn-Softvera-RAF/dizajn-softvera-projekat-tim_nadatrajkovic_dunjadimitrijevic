package raf.dsw.classycraft.app.commandPattern;

import raf.dsw.classycraft.app.core.ApplicationFramework;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class CommandManager {
    private List<AbstractCommand> commands = new ArrayList<AbstractCommand>();
    private int currentCommand = 0;


    public void dalMozeUndo()
    {
        if(currentCommand >0)
            ApplicationFramework.getInstance().enableUndoAction();
        else
            ApplicationFramework.getInstance().disableUndoAction();
    }

    public void dalMozeRedo()
    {
        if(currentCommand<commands.size())
            ApplicationFramework.getInstance().enableRedoAction();
        else
            ApplicationFramework.getInstance().disableRedoAction();
    }

    public void addCommand(AbstractCommand command){
        while(currentCommand < commands.size())
            commands.remove(currentCommand);
        commands.add(command);
        doCommand();
    }

    /*
     * Metoda koja poziva izvršavanje konkretne komande
     */
    public void doCommand(){
        if(currentCommand < commands.size()){
            commands.get(currentCommand++).doCommand();
            //SwingUtilities.updateComponentTreeUI(MainFrame.getInstance().getMapTree().getTreeView());
            ApplicationFramework.getInstance().enableUndoAction();
        }
        if(currentCommand==commands.size()){
            ApplicationFramework.getInstance().disableRedoAction();
        }
    }

    /*
     * Metoda koja poziva redo konkretne komande
     */
    public void undoCommand(){
        if(currentCommand > 0){
            ApplicationFramework.getInstance().enableRedoAction();
            commands.get(--currentCommand).undoCommand();
            //SwingUtilities.updateComponentTreeUI(MainFrame.getInstance().getMapTree().getTreeView());
        }
        if(currentCommand==0){
            ApplicationFramework.getInstance().disableUndoAction();
        }
    }

}
