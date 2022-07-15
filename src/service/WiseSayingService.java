package service;

import domain.WiseSaying;
import repository.WiseSayingRepository;
import util.Util;

import java.util.List;
import java.util.stream.Collectors;

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

    public void dumpToJson() {

        List<WiseSaying> wiseSayings = wiseSayingRepository.findAll();

        String json = "[" + wiseSayings.stream()
                .map(wiseSaying -> wiseSaying.toJson())
                .collect(Collectors.joining(",")) + "]";

        Util.saveToFile("%s/data.json".formatted("prod_data"), json);
    }
}
