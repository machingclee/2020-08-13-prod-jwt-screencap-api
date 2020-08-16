package com.screencap.dictionary.daos;

import com.screencap.dictionary.models.entities.Note;
import com.screencap.dictionary.models.entities.Page;
import com.screencap.dictionary.models.entities.Vocab;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DaoConfig {

    @Bean
    public EntityDao<Note> getNoteDao() {
        EntityDao<Note> dao = new EntityDao<Note>();
        return dao;
    }

    @Bean
    public EntityDao<Page> getPageDao() {
        EntityDao<Page> dao = new EntityDao<Page>();
        return dao;
    }

    @Bean
    public EntityDao<Vocab> getVocabDao() {
        EntityDao<Vocab> dao = new EntityDao<Vocab>();
        return dao;
    }
}
