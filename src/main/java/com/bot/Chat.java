package com.bot;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "chat")
public class Chat {
    private String chat_id;
    private Integer seq;

    public void setId(String chat_id) {
        this.chat_id = chat_id;
    }

    @Id
    public String getId() {
        return chat_id;
    }

    public Integer getSeq() {
        return seq;
    }

    public void setSeq(Integer seq) {
        this.seq = seq;
    }
}
