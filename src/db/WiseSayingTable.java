package db;

import domain.WiseSaying;
import util.Util;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class WiseSayingTable {
    private String baseDir;

    public WiseSayingTable(String baseDir) {
        this.baseDir = baseDir;
    }

    public void saveFile(WiseSaying wiseSaying) {
        Util.mkdir("%s/wise_saying".formatted(baseDir));
        String body = wiseSaying.toJson();
        Util.saveToFile("%s/wise_saying/%d.json".formatted(baseDir, wiseSaying.getId()), body);
    }

    public WiseSaying save(String content, String author) {
        int id = getLastId() + 1;

        WiseSaying wiseSaying = new WiseSaying(id, content, author);
        saveFile(wiseSaying);

        saveLastId(id);

        return wiseSaying;
    }

    private void saveLastId(int id) {
        Util.saveToFile("%s/wise_saying/last_id.txt".formatted(baseDir), id + "");
    }

    private int getLastId() {
        String lastId = Util.readFromFile("%s/wise_saying/last_id.txt".formatted(baseDir));

        if (lastId.isEmpty()) {
            return 0;
        }

        return Integer.parseInt(lastId);
    }

    public List<WiseSaying> findAll() {
        List<Integer> fileIds = getFileIds();

        return fileIds
                .stream()
                .map(id -> findById(id))
                .collect(Collectors.toList());
    }

    private List<Integer> getFileIds() {
        String path = "%s/wise_saying".formatted(baseDir);
        List<String> fileNames = Util.getFileNamesFromDir(path);

        return fileNames
                .stream()
                .filter(fileName -> !fileName.equals("last_id.txt"))
                .map(fileName -> fileName.replace(".json", ""))
                .mapToInt(Integer::parseInt)
                .boxed()
                .collect(Collectors.toList());
    }

    public WiseSaying findById(int id) {
        String path = "%s/wise_saying/%d.json".formatted(baseDir, id);

        if (!new File(path).exists()) {
            return null;
        }

        Map<String, Object> map = Util.jsonToMapFromFile(path);

        if (map == null) {
            return null;
        }

        return new WiseSaying((int) map.get("id"), (String) map.get("content"), (String) map.get("author"));
    }

    public void removeById(int id) {
        String path = "%s/wise_saying/%d.json".formatted(baseDir, id);

        new File(path).delete();
    }

    public void modify(int id, String content, String author) {
        WiseSaying wiseSaying = new WiseSaying(id, content, author);
        saveFile(wiseSaying);
    }
}
