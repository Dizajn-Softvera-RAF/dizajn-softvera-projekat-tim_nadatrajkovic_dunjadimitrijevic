package raf.dsw.classycraft.app.model.log_factory;

import raf.dsw.classycraft.app.Observer.ISubscriber;
import raf.dsw.classycraft.app.model.message.Message;

public interface Logger extends ISubscriber { //kao moze i ovako logger samo da nasledjuje da je subscriber i onda je updatuje isto stagod
    void napraviLogger(Message message);
}
