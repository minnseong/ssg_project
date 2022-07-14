package service;

import domain.WiseSaying;
import repository.WiseSayingRepository;
import util.Rq;

import java.util.List;
import java.util.Scanner;

public class WiseSayingService {
    private WiseSayingRepository wiseSayingRepository;

    public WiseSayingService() {
        wiseSayingRepository = new WiseSayingRepository();
    }

    public WiseSaying write(String content, String author) {
        return wiseSayingRepository.write(content, author);
    }

    public List<WiseSaying> findAll() {
        return wiseSayingRepository.findAll();
    }

    public WiseSaying findById(int id) {
        return wiseSayingRepository.findById(id);
    }

    public void modify(int id, String content, String author) {
        wiseSayingRepository.modify(id, content, author);
    }

    public void remove(int id) {
       wiseSayingRepository.remove(id);
    }
}