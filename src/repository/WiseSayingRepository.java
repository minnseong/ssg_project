package repository;

import domain.WiseSaying;

import java.util.ArrayList;
import java.util.List;

public class WiseSayingRepository {
    private List<WiseSaying> wiseSayings;
    private int wiseSayingLastId;

    public WiseSayingRepository() {
        wiseSayings = new ArrayList<>();
        wiseSayingLastId = 0;
    }

    public WiseSaying findById(int paramId) {
        for (WiseSaying wiseSaying : wiseSayings) {
            if (wiseSaying.getId() == paramId) {
                return wiseSaying;
            }
        }

        return null;
    }

    public List<WiseSaying> findAll() {
        return wiseSayings;
    }

    public WiseSaying write(String content, String author) {
        int id = ++wiseSayingLastId;
        WiseSaying wiseSaying = new WiseSaying(id, content, author);
        wiseSayings.add(wiseSaying);

        return wiseSaying;
    }
    public void modify(int paramId, String content, String author) {
        WiseSaying foundWiseSaying = findById(paramId);
        foundWiseSaying.setContent(content);
        foundWiseSaying.setAuthor(author);
    }

    public void remove(int paramId) {
        WiseSaying foundWiseSaying = findById(paramId);
        wiseSayings.remove(foundWiseSaying);

    }
}