package com.screencap.dictionary.controllers;

import java.util.List;
import javax.transaction.Transactional;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.screencap.dictionary.models.ErrorMessage;
import com.screencap.dictionary.models.UploadVocabsRequestBody;
import com.screencap.dictionary.models.dtos.NoteDto;
import com.screencap.dictionary.models.dtos.PageDto;
import com.screencap.dictionary.models.dtos.VocabDto;
import com.screencap.dictionary.models.entities.Vocab;
import com.screencap.dictionary.services.ScreencapsServices.ScreencapsServices;
import com.screencap.dictionary.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/screencaps")
@Transactional
public class ScreencapsController {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private ScreencapsServices screencapsService;



    @PostMapping
    public ResponseEntity<?> uploadVocabs(@RequestHeader("authorization") String authorizationHeader, @RequestBody String requestBody)
        throws Exception {
        String token = authorizationHeader.replace("Bearer ", "");
        String username = jwtUtil.extractUsername(token);

        UploadVocabsRequestBody deserializedBody = null;

        try {

            deserializedBody = objectMapper.readValue(
                requestBody,
                UploadVocabsRequestBody.class
            );

            screencapsService.deleteAll(username);
            screencapsService.saveVocabs(username, deserializedBody);

            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return new ResponseEntity<ErrorMessage>(new ErrorMessage(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping("/notes")
    @GetMapping
    public List<NoteDto> getNotes(@RequestHeader("authorization") String authorizationHeader) {
        String token = authorizationHeader.replace("Bearer ", "");
        String username = jwtUtil.extractUsername(token);

        List<NoteDto> notes = screencapsService.noteServices.getNotesByUsername(username);
        return notes;
    }

    @RequestMapping("/notes/{noteId}")
    @DeleteMapping
    public ResponseEntity<?> deleteNoteById(
        @RequestHeader("authorization") String authorizationHeader,
        @PathVariable("noteId") Integer noteId
    ) {
        String token = authorizationHeader.replace("Bearer ", "");
        String username = jwtUtil.extractUsername(token);

        try {
            screencapsService.noteServices.deleteNoteByUsernameAndNoteId(username, noteId);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }



    @RequestMapping("/pages")
    @GetMapping
    public ResponseEntity<?> getPages(@RequestHeader("authorization") String authorizationHeader) {
        String token = authorizationHeader.replace("Bearer ", "");
        String username = jwtUtil.extractUsername(token);
        try {
            List<PageDto> pages = screencapsService.pageServices.getPagesByUsername(username);
            return ResponseEntity.ok(pages);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping("/pages/byNoteId/{noteId}")
    @GetMapping
    public ResponseEntity<?> gesByNoteId(
        @RequestHeader("authorization") String authorizationHeader,
        @PathVariable("noteId") Integer noteId
    ) {

        String token = authorizationHeader.replace("Bearer ", "");
        String username = jwtUtil.extractUsername(token);

        try {

            List<PageDto> pages = screencapsService.pageServices.getPagesByUsernameAndNoteId(username, noteId);
            return ResponseEntity.ok(pages);

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping("/pages/{pageId}")
    @DeleteMapping
    public ResponseEntity<?> deletePageById(
        @RequestHeader("authorization") String authorizationHeader,
        @PathVariable("pageId") Integer pageId
    ) {
        String token = authorizationHeader.replace("Bearer ", "");
        String username = jwtUtil.extractUsername(token);

        try {
            screencapsService.pageServices.deletePageByUsernameAndNoteId(username, pageId);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            System.out.println(e);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }


    @RequestMapping("/vocabs")
    @GetMapping
    public ResponseEntity<?> getVocabs(@RequestHeader("authorization") String authorizationHeader) {
        String token = authorizationHeader.replace("Bearer ", "");
        String username = jwtUtil.extractUsername(token);
        try {
            List<VocabDto> pages = screencapsService.vocabService.getVocabsByUsername(username);
            return ResponseEntity.ok(pages);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @RequestMapping("/vocabs/byPageId/{pageId}")
    @GetMapping
    public ResponseEntity<?> getVocabsByUsernameAndPageId(
        @RequestHeader("authorization") String authorizationHeader,
        @PathVariable("pageId") Integer pageId
    ) {

        String token = authorizationHeader.replace("Bearer ", "");
        String username = jwtUtil.extractUsername(token);

        try {

            List<VocabDto> pages = screencapsService.vocabService.getVocabsByUsernameAndPageId(username, pageId);
            return ResponseEntity.ok(pages);

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }


    @RequestMapping("/thinkTwice/deleteAll")
    @GetMapping
    public ResponseEntity<?> deleteAll(@RequestHeader("authorization") String authorizationHeader) {
        String token = authorizationHeader.replace("Bearer ", "");
        String username = jwtUtil.extractUsername(token);

        screencapsService.deleteAll(username);

        return ResponseEntity.ok().build();
    }



}
