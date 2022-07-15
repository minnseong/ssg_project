package repository;

import db.WiseSayingTable;
import domain.WiseSaying;

import java.util.List;

public class WiseSayingRepository {

    private WiseSayingTable wiseSayingTable;

    public WiseSayingRepository() {
        this.wiseSayingTable = new WiseSayingTable("prod_data");
    }

    public WiseSaying add(String content, String author) {
        return wiseSayingTable.save(content, author);
    }

    public List<WiseSaying> findAll() {
        return wiseSayingTable.findAll();
    }

    public WiseSaying findById(int id) {
        return wiseSayingTable.findById(id);
    }

    public void modify(int id, String content, String author) {
        wiseSayingTable.modify(id, content, author);
    }

    public void remove(int id) {
        wiseSayingTable.removeById(id);
    }

    public WiseSaying write(String content, String author) {
        return wiseSayingTable.save(content, author);
    }
}