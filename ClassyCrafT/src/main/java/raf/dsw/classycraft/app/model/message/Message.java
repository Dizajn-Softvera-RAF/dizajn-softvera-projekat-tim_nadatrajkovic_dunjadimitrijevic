package raf.dsw.classycraft.app.model.message;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

public class Message {
    //sadrzaj, tip, timestamp
    private String sadrzaj;
    private MessageType tip;
    private LocalDateTime datum;

    public Message(String sadrzaj, MessageType tip) {
        this.sadrzaj = sadrzaj;
        this.tip = tip;
        this.datum = LocalDateTime.now();
    }

    @Override
    public String toString() {
        return "["+tip+"] "+"["+ datum.toString()+"] "+sadrzaj;
    }

    public String getSadrzaj() {
        return sadrzaj;
    }

    public void setSadrzaj(String sadrzaj) {
        this.sadrzaj = sadrzaj;
    }

    public MessageType getTip() {
        return tip;
    }

    public void setTip(MessageType tip) {
        this.tip = tip;
    }

    public LocalDateTime getDatum() {
        return datum;
    }

    public void setDatum(LocalDateTime datum) {
        this.datum = datum;
    }
}
