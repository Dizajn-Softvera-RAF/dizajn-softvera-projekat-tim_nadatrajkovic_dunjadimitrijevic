package raf.dsw.classycraft.app.core;

import raf.dsw.classycraft.app.view.MainFrame;

public class ApplicationFramework {

    private static ApplicationFramework instance;

    //buduca polja za model celog projekta
    private ClassyRepositoryImplementation classyRepositoryImplementation;

    private ApplicationFramework(){

    }

    public void initialize(){

        MainFrame.getInstance().setVisible(true);
        classyRepositoryImplementation = new ClassyRepositoryImplementation();
    }

    public ClassyRepositoryImplementation getClassyRepositoryImplementation() {
        return classyRepositoryImplementation;
    }

    public static ApplicationFramework getInstance(){
        if(instance==null){
            instance = new ApplicationFramework();
        }
        return instance;
    }
}
