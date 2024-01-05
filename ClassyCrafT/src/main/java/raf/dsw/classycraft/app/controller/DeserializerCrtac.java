package raf.dsw.classycraft.app.controller;

import raf.dsw.classycraft.app.model.composite_abstraction.ClassyNode;
import raf.dsw.classycraft.app.model.composite_abstraction.ClassyNodeComposite;

public class DeserializerCrtac {
    public void crtajDecu(ClassyNode parent)
    {
        if(!(parent instanceof ClassyNodeComposite))
        {
            return;
        }

        else{
            for (ClassyNode child: ((ClassyNodeComposite)parent).getChildren())
            {
                child.setParent(parent);

                //observeri, painteri ili view, u drvo


                //view i painter su subscriberi za model (interclass, veze)
                //veze subscriberi interclassova
                /*
                * connection.addSubscriber(dv);
                * interclass.addSubscriber(dv);
                *
                * package.addSubscriber(packageView)
                * */

                //System.out.println(child.toString());
            }
        }

    }
}
