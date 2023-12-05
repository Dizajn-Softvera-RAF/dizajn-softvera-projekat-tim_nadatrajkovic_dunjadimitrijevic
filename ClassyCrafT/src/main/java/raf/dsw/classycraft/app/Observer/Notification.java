package raf.dsw.classycraft.app.Observer;

public class Notification {
    private Object objectOfNotification;
    private NotificationType notificationType;

    public Object getObjectOfNotification() {
        return objectOfNotification;
    }

    public void setObjectOfNotification(Object objectOfNotification) {
        this.objectOfNotification = objectOfNotification;
    }

    public NotificationType getNotificationType() {
        return notificationType;
    }

    public void setNotificationType(NotificationType notificationType) {
        this.notificationType = notificationType;
    }

    public Notification(Object objectOfNotification, NotificationType notificationType) {
        this.objectOfNotification = objectOfNotification;
        this.notificationType = notificationType;
    }

    @Override
    public String toString() {
        return "Notification{" +
                "objectOfNotification=" + objectOfNotification +
                ", notificationType=" + notificationType.toString() +
                '}';
    }
}
